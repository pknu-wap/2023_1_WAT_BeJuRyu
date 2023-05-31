package com.jaino.analyze.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jaino.analyze.R
import com.jaino.analyze.databinding.FragmentAnalyzeResultBinding
import com.jaino.common.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AnalyzeResultFragment : Fragment(){

    private var _binding: FragmentAnalyzeResultBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private val viewModel : AnalysisResultViewModel by viewModels()
    private val args: AnalyzeResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_analyze_result, container, false)
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
        binding.resultHomeButton.setOnClickListener {
             val direction = AnalyzeResultFragmentDirections
                 .actionAnalyzeResultFragmentToAnalyzeHomeFragment()
            findNavController().navigate(direction)
        }
    }

    private fun initViewModelStates(){
        viewModel.getAnalysisResult(args.analysisId)
        viewModel.getNickname()
    }

    private fun observeData(){
        viewModel.analysisResultUiEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is AnalysisResultViewModel.UiEvent.Failure -> {
                        if(it.message != null){
                            requireContext().showToast(it.message)
                        }
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}