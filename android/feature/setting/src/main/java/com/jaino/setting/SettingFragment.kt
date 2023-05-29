package com.jaino.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jaino.common.navigation.AppNavigator
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
            navigateToAnalyze()
        }

        binding.settingAccountCardView.setOnClickListener {
            navigateToAccount()
        }

        binding.settingProfileCardView.setOnClickListener {
            navigateToProfile()
        }

        binding.historyCardView.setOnClickListener {
            navigateToHistory()
        }
    }

    private fun navigateToAnalyze(){
        findNavController().navigate("BeJuRyu://feature/analyze".toUri())
    }

    private fun navigateToAccount(){
        startActivity(appNavigator.navigateToAccount())
    }

    private fun navigateToProfile(){
        findNavController().navigate(
            R.id.action_settingFragment_to_profileFragment
        )
    }

    private fun navigateToHistory(){
        findNavController().navigate(
            R.id.action_settingFragment_to_historyFragment
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}