package com.jaino.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jaino.navigation.AppNavigator
import com.jaino.setting.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment() {

    @Inject
    lateinit var appNavigator: AppNavigator

    private var _binding: FragmentSettingBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons(){
        binding.settingBackButton.setOnClickListener {
            navigateToHome()
        }

        binding.settingAccountCardView.setOnClickListener {
            navigateToAccount()
        }

        binding.settingProfileCardView.setOnClickListener {
            navigateToProfile()
        }
    }

    private fun navigateToHome(){
        startActivity(appNavigator.navigateToAuth())
    }

    private fun navigateToAccount(){
        findNavController().navigate(
            R.id.action_settingFragment_to_accountFragment
        )
    }

    private fun navigateToProfile(){
        findNavController().navigate(
            R.id.action_settingFragment_to_profileFragment
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}