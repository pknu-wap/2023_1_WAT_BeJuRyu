package com.jaino.analyze.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jaino.analyze.R
import com.jaino.analyze.databinding.FragmentAnalyzeHomeBinding
import com.jaino.common.navigation.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AnalyzeHomeFragment : Fragment() {

    private var _binding: FragmentAnalyzeHomeBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_analyze_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
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
        startActivity(appNavigator.navigateToSetting())
    }

    private fun navigateToSearch(){
        findNavController().navigate("BeJuRyu://feature/dictionary".toUri())
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}