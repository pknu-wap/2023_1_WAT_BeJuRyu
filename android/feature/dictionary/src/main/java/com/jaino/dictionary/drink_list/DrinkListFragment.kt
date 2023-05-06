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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.jaino.common.extensions.showToast
import com.jaino.dictionary.R
import com.jaino.dictionary.databinding.FragmentDrinkListBinding
import com.jaino.dictionary.drink_list.adapter.DrinkDataAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DrinkListFragment : Fragment(){

    private var _binding : FragmentDrinkListBinding?= null
    private val binding get() = requireNotNull(_binding) { "binding object is not initialized"}

    private lateinit var adapter : DrinkDataAdapter

    private val viewModel : DrinkListViewModel by viewModels()
    private val args : DrinkListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drink_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeData()
        initView()
    }

    private fun initAdapter(){
        adapter = DrinkDataAdapter(
            itemClick = { id ->
                navigateToInfo(id)
            }
        )
        binding.drinkDataRecyclerView.adapter = adapter
        binding.drinkDataRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
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

    private fun initView(){
        if(args.type.isNotBlank()){
            viewModel.getDrinkListByType(args.type)
        }
        else if(args.word.isNotBlank()){
            viewModel.getDrinkListByWord(args.word)
        }

        binding.backToListButton.setOnClickListener{
            val direction = DrinkListFragmentDirections
                .actionDrinkListFragmentToDrinkSearchFragment()
            findNavController().navigate(direction)
        }
    }

    private fun navigateToInfo(id : Long){
        val direction = DrinkListFragmentDirections.actionDrinkListFragmentToDrinkInfoFragment(id)
        findNavController().navigate(direction)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}