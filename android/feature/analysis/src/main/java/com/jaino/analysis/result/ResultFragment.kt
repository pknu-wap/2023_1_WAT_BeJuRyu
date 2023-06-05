package com.jaino.analysis.result

import android.content.ActivityNotFoundException
import android.content.Context
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
import com.jaino.analysis.R
import com.jaino.analysis.databinding.FragmentResultBinding
import com.jaino.analysis.result.ui.FeedMessage
import com.jaino.common.extensions.showToast
import com.jaino.common.model.UiEvent
import com.jaino.common.widget.ErrorDialog
import com.jaino.model.analysis.AnalysisResult
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.kakao.sdk.template.model.*
import timber.log.Timber

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    private val viewModel: ResultViewModel by viewModels()
    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModelStates()
        observeData()
    }

    private fun initViews() {
        binding.resultHomeButton.setOnClickListener {
            val direction = ResultFragmentDirections
                .actionResultFragmentToHomeFragment()
            findNavController().navigate(direction)
        }

        binding.resultShareButton.setOnClickListener {
            shareKakaoLink(requireContext(), FeedMessage.createTemplate())
        }
    }

    private fun initViewModelStates() {
        viewModel.getAnalysisResult(args.analysisId)
        viewModel.getNickname()
    }

    private fun observeData() {
        viewModel.analysisResultUiEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when (it) {
                    is UiEvent.Failure -> {
                        showErrorDialog(it.error)
                    }
                    is UiEvent.Success -> {}
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.analysisResultUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                if (it.result.drink.name.isNotEmpty()) {
                    setProgress(it.result)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setProgress(result: AnalysisResult) {
        binding.resultProgress.progress = result.level.toFloat()
        with(binding.resultProgress) {
            this.progress = result.level.toFloat()
            this.labelText = "${result.sentiment} ${result.level}단계"
        }
    }

    private fun shareKakaoLink(context : Context, feedMessage: FeedTemplate) {
        if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
            // 카카오톡으로 카카오톡 공유 가능
            ShareClient.instance.shareDefault(context, feedMessage) { sharingResult, error ->
                if (error != null) {
                    context.showToast("카카오톡 공유에 실패하였습니다.")
                    Timber.e(error)
                } else if (sharingResult != null) {
                    startActivity(sharingResult.intent)
                }
            }
        }
        else {
            val sharerUrl = WebSharerClient.instance.makeDefaultUrl(feedMessage)
            try {
                KakaoCustomTabsClient.openWithDefault(context, sharerUrl)
            } catch (e: UnsupportedOperationException) {
                context.showToast("연결 가능한 브라우저가 없습니다.")
            }
            try {
                KakaoCustomTabsClient.open(context, sharerUrl)
            } catch (e: ActivityNotFoundException) {
                context.showToast("연결 가능한 브라우저가 없습니다.")
            }
        }
    }

    private fun showErrorDialog(error: Throwable){
        ErrorDialog(
            requireContext(),
            error = error,
            onRetryButtonClick = {
                viewModel.getAnalysisResult(args.analysisId)
            }
        ).show()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}