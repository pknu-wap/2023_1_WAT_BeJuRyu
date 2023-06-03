package com.jaino.analyze.home

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
import com.jaino.analyze.R
import com.jaino.analyze.databinding.FragmentAnalyzeHomeBinding
import com.jaino.analyze.home.adapter.HomeRankAdapter
import com.jaino.common.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AnalyzeHomeFragment : Fragment() {

    private var _binding: FragmentAnalyzeHomeBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private val viewModel : HomeViewModel by viewModels()

    private lateinit var adapter : HomeRankAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_analyze_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeData()
        initViews()
        initViewModelStates()
    }

    private fun initViewModelStates(){
        viewModel.getRankingList()
    }

    private fun initAdapter(){
        adapter = HomeRankAdapter(
            itemClick = {
                navigateToDrinkInfo(it)
            }
        )
        binding.rankRecyclerView.adapter = adapter
        binding.rankRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun observeData(){
        viewModel.homeUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                if(it.isNotEmpty()){
                    adapter.submitList(it)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.homeUiEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is HomeViewModel.UiEvent.Failure -> {
                        if(it.message != null) {
                            requireContext().showToast(it.message)
                        }
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.rankingTag.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    requireContext().getString(com.jaino.designsystem.R.string.most_reviewed) -> {
                        binding.mostReviewedChip.isChecked = true
                    }

                    requireContext().getString(com.jaino.designsystem.R.string.most_reviewed) -> {
                        binding.highestRatedChip.isChecked = true
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initViews(){
        binding.goToAnalyzeButton.setOnClickListener{
            navigateToAnalyzeText()
        }

        binding.goToSearchButton.setOnClickListener{
            navigateToSearch()
        }

        binding.goToSettingButton.setOnClickListener{
            navigateToSetting()
        }
    }

    private fun navigateToAnalyzeText(){
        findNavController().navigate(
            AnalyzeHomeFragmentDirections.actionAnalyzeHomeFragmentToAnalyzeTextFragment()
        )
    }

    private fun navigateToSetting(){
        findNavController().navigate("BeJuRyu://feature/setting".toUri())
    }

    private fun navigateToSearch(){
        findNavController().navigate("BeJuRyu://feature/dictionary".toUri())
    }

    private fun navigateToDrinkInfo(id: Long){
        findNavController().navigate(
            "BeJuRyu://feature/dictionary/info?drinkId=$id".toUri()
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}