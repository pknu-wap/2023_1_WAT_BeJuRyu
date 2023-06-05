package com.jaino.setting.profile

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
import com.jaino.common.model.UiEvent
import com.jaino.common.widget.ErrorDialog
import com.jaino.setting.R
import com.jaino.setting.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProfileFragment  : Fragment(){

    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private val viewModel : ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initViewModelState()
        observeData()
    }

    private fun initButtons() {
        binding.profileBackButton.setOnClickListener {
            navigateToSetting()
        }
        binding.profileNickNameCardView.setOnClickListener {
            EditNicknameDialog(
                requireContext(),
                onDoneButtonClick = {
                    viewModel.updateNickname(it)
                }
            ).show()
        }
    }

    private fun initViewModelState(){
        viewModel.getNickName()
    }

    private fun observeData(){
        viewModel.profileUiEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is UiEvent.Failure -> {
                        showErrorDialog(it.error)
                    }

                    is UiEvent.Success -> {

                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showErrorDialog(error: Throwable){
        ErrorDialog(
            requireContext(),
            error = error,
            onRetryButtonClick = {
                viewModel.getNickName()
            }
        ).show()
    }

    private fun navigateToSetting(){
        findNavController().navigate(
            R.id.action_profileFragment_to_settingFragment
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}