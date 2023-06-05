package com.jaino.dictionary.drink_info

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
import androidx.navigation.fragment.navArgs
import com.jaino.common.model.UiEvent
import com.jaino.common.widget.ErrorDialog
import com.jaino.dictionary.R
import com.jaino.dictionary.databinding.FragmentDrinkInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DrinkInfoFragment : Fragment() {

    private var _binding : FragmentDrinkInfoBinding?= null
    private val binding get() = requireNotNull(_binding){ "binding object is not initialized" }

    private val viewModel : DrinkInfoViewModel by viewModels()
    private val args : DrinkInfoFragmentArgs by navArgs()

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
        viewModel.getDrinkData(args.drinkId)
        initViews()
        observeData()
    }

    private fun initViews(){
        binding.backButton.setOnClickListener {
            findNavController().popBackStack(R.id.drinkListFragment, false)
        }

        binding.goToHomeButton.setOnClickListener {
            findNavController().navigate("BeJuRyu://feature/analyze".toUri())
        }

        binding.goToSearchButton.setOnClickListener {
            val direction = DrinkInfoFragmentDirections
                .actionDrinkInfoFragmentToDrinkSearchFragment()
            findNavController().navigate(direction)
        }

        binding.goToReviewButton.setOnClickListener{
            findNavController()
                .navigate("BeJuRyu://feature/review/list?drinkId=${args.drinkId}".toUri())
        }
    }

    private fun observeData(){
        viewModel.drinkInfoEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when (it) {
                    is UiEvent.Failure -> {
                        showErrorDialog(it.error)
                    }

                    is UiEvent.Success -> {}
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showErrorDialog(error: Throwable){
        ErrorDialog(
            requireContext(),
            error = error,
            onRetryButtonClick = {
                viewModel.getDrinkData(args.drinkId)
            }
        ).show()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}