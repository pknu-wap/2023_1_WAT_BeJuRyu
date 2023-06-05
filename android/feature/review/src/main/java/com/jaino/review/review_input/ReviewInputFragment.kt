package com.jaino.review.review_input

import android.annotation.SuppressLint
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
import com.jaino.common.navigation.AppNavigator
import com.jaino.common.widget.ConfirmDialog
import com.jaino.common.widget.ErrorDialog
import com.jaino.review.R
import com.jaino.review.databinding.FragmentReviewInputBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ReviewInputFragment : Fragment(){

    private var _binding : FragmentReviewInputBinding? = null
    private val binding get() = requireNotNull(_binding){ "binding object is not initialized" }

    @Inject
    lateinit var appNavigator: AppNavigator

    private val viewModel : ReviewInputViewModel by viewModels()
    private val args : ReviewInputFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_input, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
    }

    private fun initViews(){
        binding.backButton.setOnClickListener {
            navigateToList()
        }

        binding.goToHomeButton.setOnClickListener {
            navigateToDictionary()
        }

        binding.reviewPostButton.setOnClickListener {
            showConfirmDialog()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeData(){
        viewModel.reviewInputEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is UiEvent.Success -> {
                        navigateToList()
                    }

                    is UiEvent.Failure -> {
                        showErrorDialog(it.error)
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.reviewContent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                binding.reviewTextCounter.text = "${it.length}/10자 이상"
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showConfirmDialog(){
        ConfirmDialog(
            requireContext(),
            "작성한 후기를 등록할까요?",
            onDoneButtonClick = {
                viewModel.postReview(args.drinkId)
            }
        ).show()
    }

    private fun showErrorDialog(error: Throwable){
        ErrorDialog(
            requireContext(),
            error = error,
            onRetryButtonClick = {
                viewModel.postReview(args.drinkId)
            }
        ).show()
    }

    private fun navigateToDictionary(){
        findNavController().navigate("BeJuRyu://feature/dictionary".toUri())
    }

    private fun navigateToList(){
        val direction = ReviewInputFragmentDirections
            .actionReviewInputFragmentToReviewListFragment(args.drinkId)
        findNavController().navigate(direction)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}