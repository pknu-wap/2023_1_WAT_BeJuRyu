package com.jaino.analysis.image_input

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.launch
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jaino.analysis.R
import com.jaino.analysis.databinding.FragmentImageInputBinding
import com.jaino.common.extensions.showToast
import com.jaino.common.extensions.toDateTime
import com.jaino.common.model.UiEvent
import com.jaino.common.utils.PickPhotoContract
import com.jaino.common.widget.ConfirmDialog
import com.jaino.common.widget.ErrorDialog
import com.jaino.common.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ImageInputFragment : Fragment() {

    private lateinit var singlePhotoPickerLauncher : ActivityResultLauncher<Void?>

    private var _binding: FragmentImageInputBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private val viewModel : ImageInputViewModel by viewModels()
    private val args : ImageInputFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        singlePhotoPickerLauncher =  registerForActivityResult(PickPhotoContract())
        { imageUri: Uri? ->
            if (imageUri != null) {
                viewModel.setImageUri(imageUri.toString())
                loadImage(imageUri.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_image_input, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModelStates()
        observeData()
    }

    private fun initViews(){
        binding.analyzeInputImage.setOnClickListener {
            showChooseToolsDialog()
        }
        // 완료 버튼 클릭
        binding.analyzeImageDoneButton.setOnClickListener {
            showConfirmDialog()
        }
        // 뒤로가기 버튼 클릭
        binding.analyzeBackButton.setOnClickListener {
            navigateToText()
        }
    }

    private fun initViewModelStates(){
        // imageUri 저장
        val imageUri = args.imageUri
        if(imageUri.isNotEmpty()){
            viewModel.setImageUri(imageUri)
            loadImage(imageUri)
        }
    }

    private fun loadImage(uri: String){
        val inputStream = requireActivity().contentResolver.openInputStream(uri.toUri())
        if(inputStream != null) {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes)
            val encodedImage: String = Base64.encodeToString(bytes, Base64.DEFAULT)
            viewModel.setImageSource(encodedImage)
        }else{
            Toast.makeText(requireContext(), "사진을 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
            navigateToText()
        }
        inputStream?.close()
    }

    private fun observeData(){
        viewModel.analysisId.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { id ->
                if (id != -1L) {
                    navigateToResult(id)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.analysisUiEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is UiEvent.Failure -> {
                        showErrorDialog(it.error)
                    }
                    is UiEvent.Success -> { }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showChooseToolsDialog(){
        ChooseToolsDialog(
            requireContext(),
            onCameraClick = { navigateToPermission() },
            onAlbumClick = {
                singlePhotoPickerLauncher.launch()
            }
        ).show()
    }

    private fun showConfirmDialog(){
        ConfirmDialog(
            requireContext(),
            "제출 하시겠습니까?",
            onDoneButtonClick = {
                submitSource()
            }
        ).show()
    }

    private fun showLoadingDialog(){
        val dialog = LoadingDialog(requireContext())
        dialog.show()
    }

    private fun showErrorDialog(error: Throwable){
        ErrorDialog(
            requireContext(),
            error = error,
            onRetryButtonClick = {
                submitSource()
            }
        )
    }

    private fun submitSource(){
        viewModel.postAnalysisSource(System.currentTimeMillis().toDateTime(), args.analyzeText)
        showLoadingDialog()
    }

    private fun navigateToPermission(){
        findNavController().navigate(
            ImageInputFragmentDirections.actionImageInputFragmentToPermissionsFragment()
        )
    }

    private fun navigateToResult(analysisId: Long){
        findNavController().navigate(
            ImageInputFragmentDirections.actionImageInputFragmentToResultFragment(
                analysisId
            )
        )
    }

    private fun navigateToText(){
        findNavController().popBackStack(R.id.textInputFragment, false)
    }
}