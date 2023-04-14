package com.jaino.setting.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jaino.setting.R
import com.jaino.setting.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

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