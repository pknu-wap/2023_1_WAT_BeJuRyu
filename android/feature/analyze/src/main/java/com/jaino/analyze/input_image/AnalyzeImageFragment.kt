package com.jaino.analyze.input_image

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.launch
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jaino.analyze.R
import com.jaino.analyze.databinding.FragmentAnalyzeImageBinding
import com.jaino.common.utils.PickPhotoContract
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalyzeImageFragment : Fragment() {

    private lateinit var singlePhotoPickerLauncher : ActivityResultLauncher<Void?>

    private var _binding: FragmentAnalyzeImageBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private val viewModel : AnalyzeImageViewModel by viewModels()
    private val args : AnalyzeImageFragmentArgs by navArgs()

    //TODO savedStateHandle 관리
    override fun onAttach(context: Context) {
        super.onAttach(context)
        singlePhotoPickerLauncher =  registerForActivityResult(PickPhotoContract())
        { imageUri: Uri? ->
            if (imageUri != null) {
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
            inflater, R.layout.fragment_analyze_image, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        if(args.imageUri.isNotEmpty()){
            loadImage(args.imageUri)
        }
        binding.analyzeInputImage.setOnClickListener {
            showDialog()
        }
        binding.analyzeImageDoneButton.setOnClickListener {
            viewModel.postAnalyzeInput(args.analyzeText)
        }
        binding.analyzeBackButton.setOnClickListener {
            findNavController().popBackStack(R.id.analyzeTextFragment, false)
        }
    }

    private fun showDialog(){
        ChooseToolsDialog(
            requireContext(),
            onCameraClick = { navigateToPermission() },
            onAlbumClick = {
                singlePhotoPickerLauncher.launch()
            }
        ).show()
    }

    private fun loadImage(uri: String){
        viewModel.setImageUri(uri)
    }

    private fun navigateToPermission(){
        findNavController().navigate(
            AnalyzeImageFragmentDirections.actionAnalyzeImageFragmentToPermissionsFragment()
        )
    }

    private fun navigateToResult(){
        findNavController().navigate(
            AnalyzeImageFragmentDirections.actionAnalyzeImageFragmentToAnalyzeResultFragment()
        )
    }
}