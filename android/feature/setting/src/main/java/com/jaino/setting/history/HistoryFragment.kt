package com.jaino.setting.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaino.common.model.UiEvent
import com.jaino.common.model.UiState
import com.jaino.common.widget.ErrorDialog
import com.jaino.setting.R
import com.jaino.setting.databinding.FragmentHistoryBinding
import com.jaino.setting.history.adapter.AnalysisHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HistoryFragment: Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private lateinit var analysisHistoryAdapter : AnalysisHistoryAdapter
    private val viewModel : HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initAdapter()
        initViewModelStates()
        observeData()
    }

    private fun initAdapter(){
        analysisHistoryAdapter = AnalysisHistoryAdapter(
            onItemClick = {
                navigateAnalysisResult(it)
            }
        )
        binding.profileUserAnalyzeList.layoutManager = LinearLayoutManager(requireContext())
        binding.profileUserAnalyzeList.adapter = analysisHistoryAdapter
    }

    private fun initViews(){
        binding.profileBackButton.setOnClickListener {
            navigateToSetting()
        }
    }

    private fun initViewModelStates(){
        viewModel.getAnalyzeList()
    }

    private fun observeData(){
        viewModel.historyUiEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is UiEvent.Failure -> {
                        showErrorDialog(it.error)
                    }
                    is UiEvent.Success -> {}
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.historyListState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is UiState.Init -> {}

                    is UiState.Success -> {
                        if(it.data.isNotEmpty()){
                            analysisHistoryAdapter.submitList(it.data)
                        }
                    }

                    is UiState.Failure -> {}
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showErrorDialog(error: Throwable){
        ErrorDialog(
            requireContext(),
            error = error,
            onRetryButtonClick = {
                viewModel.getAnalyzeList()
            }
        ).show()
    }

    private fun navigateToSetting(){
        val direction = HistoryFragmentDirections.actionHistoryFragmentToSettingFragment()
        findNavController().navigate(direction)
    }

    private fun navigateAnalysisResult(analysisId: Long){
        findNavController().navigate(
            "BeJuRyu://feature/analyze/result?analysisId=$analysisId".toUri()
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}