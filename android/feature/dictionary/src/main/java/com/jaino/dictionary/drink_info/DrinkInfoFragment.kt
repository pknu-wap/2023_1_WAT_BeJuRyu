package com.jaino.dictionary.drink_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jaino.common.extensions.showToast
import com.jaino.common.navigation.AppNavigator
import com.jaino.dictionary.R
import com.jaino.dictionary.databinding.FragmentDrinkInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class DrinkInfoFragment : Fragment() {

    private var _binding : FragmentDrinkInfoBinding?= null
    private val binding get() = requireNotNull(_binding){ "binding object is not initialized" }

    private val viewModel : DrinkInfoViewModel by viewModels()
    private val args : DrinkInfoFragmentArgs by navArgs()

    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drink_info, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDrinkData(args.id)
        initViews()
        observeData()
    }

    private fun initViews(){
        binding.backButton.setOnClickListener {

        }

        binding.goToHomeButton.setOnClickListener {
            startActivity(navigator.navigateToAnalyze())
        }

        binding.goToSearchButton.setOnClickListener {
            val direction = DrinkInfoFragmentDirections
                .actionDrinkInfoFragmentToDrinkSearchFragment()
            findNavController().navigate(direction)
        }
    }

    private fun observeData(){
        viewModel.drinkInfoEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is DrinkInfoViewModel.UiEvent.Init -> {}

                    is DrinkInfoViewModel.UiEvent.Failure -> {
                        if(it.message != null){
                            requireContext().showToast(it.message)
                        }
                    }
                }
            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}