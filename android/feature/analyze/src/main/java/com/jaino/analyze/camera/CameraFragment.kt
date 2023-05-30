package com.jaino.analyze.camera

import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaActionSound
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.common.util.concurrent.ListenableFuture
import com.jaino.analyze.R
import com.jaino.analyze.databinding.FragmentCameraBinding
import com.jaino.common.extensions.getCurrentFileName
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK // 후면 비율
    private var screenAspectRatio = AspectRatio.RATIO_4_3 // 3 : 4비율

    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>
    private lateinit var imageCapture : ImageCapture
    private lateinit var imagePreview : Preview
    private lateinit var imageAnalyzer : ImageAnalysis

    private lateinit var cameraExecutor: ExecutorService
    private lateinit var cameraSound: MediaActionSound

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_camera, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCamera()
        setUpCamera()
        initButton()
    }

    private fun initCamera(){
        cameraExecutor = Executors.newSingleThreadExecutor() // init executors
        cameraSound = MediaActionSound() // init cameraSound
    }

    private fun setUpCamera(){
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireActivity())
        cameraProviderFuture.addListener(
            {
                bindCameraUseCases()
            },
            ContextCompat.getMainExecutor(requireContext())
        )
    }

    private fun initButton(){
        binding.SwitchCameraButton.setOnClickListener{
            lensFacing = if (CameraSelector.LENS_FACING_FRONT == lensFacing) {
                CameraSelector.LENS_FACING_BACK
            } else {
                CameraSelector.LENS_FACING_FRONT
            }
            // 카메라 렌즈 바인딩 변경으로, UseCases 재설정
            bindCameraUseCases()
        }
        binding.CaptureImageButton.setOnClickListener{
            captureImage()
        }
    }


    private fun bindCameraUseCases(){
        val cameraProvider = cameraProviderFuture.get()
        val cameraSelector : CameraSelector = CameraSelector.Builder() // 카메라 옵션
            .requireLensFacing(lensFacing) // 후면 카메라
            .build()

        imagePreview = Preview.Builder() // preview 객체 생성
            .setTargetAspectRatio(screenAspectRatio) // 비율 설정
            .build()

        imageAnalyzer = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()

        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY) // 효율 최대치
            .setTargetAspectRatio(screenAspectRatio)
            .build()

        cameraProvider.unbindAll() // for rebinding

        imagePreview.setSurfaceProvider(binding.CameraPreview.surfaceProvider) // view 와 객체 결합
        binding.CameraPreview.scaleType = PreviewView.ScaleType.FIT_CENTER

        cameraProvider.bindToLifecycle(this, cameraSelector, imagePreview,
            imageAnalyzer, imageCapture) // 라이프 사이클 바인딩
    }

    private fun captureImage(){
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(
            requireContext().contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues().getCurrentFileName()
        ).build()

        imageCapture.takePicture(outputFileOptions, cameraExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    navigateToImage(outputFileResults.savedUri.toString())
                }

                override fun onError(exception: ImageCaptureException) {
                    Timber.e(exception)
                }
            }
        )

        // Capture effect
        startCameraSound()
        startCameraScreenAnimation()
    }

    private fun startCameraSound(){
        cameraSound.play(MediaActionSound.SHUTTER_CLICK) // camera sound
    }

    private fun startCameraScreenAnimation(){
        // Display flash animation to indicate that photo was captured
        binding.root.postDelayed({
            binding.root.foreground = ColorDrawable(Color.WHITE)
            binding.root.postDelayed(
                { binding.root.foreground = null }, ANIMATION_FAST_MILLIS
            )
        }, ANIMATION_SLOW_MILLIS)
    }

    private fun navigateToImage(uri : String){
        lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                findNavController().navigate(
                    CameraFragmentDirections.actionCameraFragmentToAnalyzeImageFragment(
                        uri, ""
                    )
                )
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
        cameraExecutor.shutdown()
        cameraSound.release()
    }

    companion object {
        const val ANIMATION_FAST_MILLIS = 100L
        const val ANIMATION_SLOW_MILLIS = 200L
    }
}