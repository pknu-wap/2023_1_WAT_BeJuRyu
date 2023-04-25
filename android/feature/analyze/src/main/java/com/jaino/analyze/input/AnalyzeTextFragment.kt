package com.jaino.analyze.input

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.launch
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.jaino.analyze.AnalyzeViewModel
import com.jaino.analyze.R
import com.jaino.analyze.databinding.FragmentAnalyzeTextBinding
import com.jaino.common.utils.PickPhotoContract
import usecase.validate.ValidateTextExpression

class AnalyzeTextFragment : Fragment() {

    private val viewModel : AnalyzeViewModel by activityViewModels()
    private val expressionUseCase by lazy{ ValidateTextExpression() }

    private var _binding: FragmentAnalyzeTextBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private lateinit var singlePhotoPickerLauncher : ActivityResultLauncher<Void?>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        singlePhotoPickerLauncher =  registerForActivityResult(PickPhotoContract())
        { imageUri: Uri? ->
            if (imageUri != null) {
                // navigateToResult(imageUri.toString())
                navigateToResult()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_analyze_text, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons(){
        binding.analyzeBackButton.setOnClickListener {
            navigateToHome()
        }

        binding.analyzeTextDoneButton.setOnClickListener{
            val text = binding.analyzeTextExpression.text.toString()
            val result = expressionUseCase(text)
            if(!result.successful){
                Toast.makeText(requireContext(), result.errorMessage, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            showDialog()
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

    private fun navigateToHome(){
        findNavController().navigate(
            AnalyzeTextFragmentDirections.actionAnalyzeTextFragmentToAnalyzeHomeFragment()
        )
    }

    private fun navigateToPermission(){
        findNavController().navigate(
            AnalyzeTextFragmentDirections.actionAnalyzeTextFragmentToPermissionsFragment()
        )
    }

    private fun navigateToResult(){
        findNavController().navigate(
            AnalyzeTextFragmentDirections.actionAnalyzeTextFragmentToAnalyzeResultFragment()
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}