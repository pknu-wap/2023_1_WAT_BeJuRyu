package com.jaino.setting

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
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
import com.jaino.common.model.UiEvent
import com.jaino.common.navigation.AppNavigator
import com.jaino.common.widget.ErrorDialog
import com.jaino.setting.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment() {

    @Inject
    lateinit var appNavigator: AppNavigator

    private var _binding: FragmentSettingBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private val viewModel : SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelStates()
        initButtons()
        observeData()
    }

    private fun initViewModelStates(){
        viewModel.getNickname()
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

        binding.historyCardView.setOnClickListener {
            navigateToHistory()
        }
    }

    private fun initNicknameColor(){
        val text = binding.settingNicknameTitle.text
        val spannable = SpannableStringBuilder(text)
        spannable.setSpan(
            ForegroundColorSpan(Color.BLACK),
            text.length - 15, text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.settingNicknameTitle.text = spannable
    }

    private fun observeData(){
        viewModel.settingUiEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is UiEvent.Failure ->{
                        showErrorDialog(it.error)
                    }
                    is UiEvent.Success -> { }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.nicknameState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                if(it.isNotEmpty()){
                    initNicknameColor()
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showErrorDialog(error: Throwable){
        ErrorDialog(
            requireContext(),
            error = error,
            onRetryButtonClick = {
                viewModel.getNickname()
            }
        ).show()
    }

    private fun navigateToHome(){
        findNavController().navigate("BeJuRyu://feature/home".toUri())
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