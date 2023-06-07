package com.jaino.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaino.home.adapter.HomeRankAdapter
import com.jaino.common.model.UiEvent
import com.jaino.common.widget.ErrorDialog
import com.jaino.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import land.sungbin.systemuicontroller.setStatusBarColor

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private val viewModel : HomeViewModel by viewModels()

    private lateinit var adapter : HomeRankAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
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
                    is UiEvent.Failure -> {
                        showErrorDialog(it.error)
                    }
                    is UiEvent.Success -> {

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
            navigateToTextInput()
        }

        binding.goToSearchButton.setOnClickListener{
            navigateToSearch()
        }

        binding.goToSettingButton.setOnClickListener{
            navigateToSetting()
        }
        setStatusBarColor(getColor(requireContext(), com.jaino.designsystem.R.color.purple))
    }

    private fun showErrorDialog(error: Throwable){
        ErrorDialog(
            requireContext(),
            error = error,
            onRetryButtonClick = {
                viewModel.getRankingList()
            }
        ).show()
    }


    private fun navigateToTextInput(){
        findNavController().navigate("BeJuRyu://feature/analysis/text".toUri())
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