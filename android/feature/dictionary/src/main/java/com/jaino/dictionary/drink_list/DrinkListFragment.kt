package com.jaino.dictionary.drink_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaino.common.extensions.showToast
import com.jaino.common.navigation.AppNavigator
import com.jaino.dictionary.R
import com.jaino.dictionary.databinding.FragmentDrinkListBinding
import com.jaino.dictionary.drink_list.adapter.DrinkDataAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class DrinkListFragment : Fragment() {

    private var _binding : FragmentDrinkListBinding ?= null
    private val binding get() =  requireNotNull(_binding) { "binding object is not initialized" }

    private val viewModel : DrinkListViewModel by viewModels()
    private lateinit var adapter : DrinkDataAdapter

    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drink_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initView()
        observeData()
    }

    private fun initView(){
        binding.backToAnalyzeButton.setOnClickListener{
            startActivity(navigator.navigateToAnalyze())
        }
    }

    private fun initAdapter(){
        adapter = DrinkDataAdapter()
        binding.drinkListRecyclerView.adapter = adapter
        binding.drinkListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeData(){
        viewModel.dictUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is DrinkListViewModel.UiState.Init -> { }

                    is DrinkListViewModel.UiState.Success -> {
                        adapter.submitList(it.list)
                    }

                    is DrinkListViewModel.UiState.Failure -> {
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
