package com.jaino.setting.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaino.setting.R
import com.jaino.setting.databinding.FragmentHistoryBinding
import com.jaino.setting.history.adapter.UserAnalyzeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HistoryFragment: Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private lateinit var userAnalyzeAdapter : UserAnalyzeAdapter
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
        observeData()
    }

    private fun initAdapter(){
        userAnalyzeAdapter = UserAnalyzeAdapter(
            onItemClick = {
                navigateToHistoryInfo(it)
            }
        )
        binding.profileUserAnalyzeList.layoutManager = LinearLayoutManager(requireContext())
        binding.profileUserAnalyzeList.adapter = userAnalyzeAdapter
    }

    private fun initViews(){
        binding.profileBackButton.setOnClickListener {
            navigateToSetting()
        }
    }

    private fun observeData(){
        viewModel.historyUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is HistoryViewModel.UiEvent.Init -> {}
                    is HistoryViewModel.UiEvent.Success -> {
                        userAnalyzeAdapter.submitList(it.data)
                    }
                    is HistoryViewModel.UiEvent.Failure -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun navigateToSetting(){
        val direction = HistoryFragmentDirections.actionHistoryFragmentToSettingFragment()
        findNavController().navigate(direction)
    }

    private fun navigateToHistoryInfo(id: Long){
        val direction = HistoryFragmentDirections
            .actionHistoryFragmentToHistoryInfoFragment(id)
        findNavController().navigate(direction)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}