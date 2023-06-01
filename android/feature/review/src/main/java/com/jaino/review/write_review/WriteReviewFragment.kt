package com.jaino.review.write_review

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
import com.jaino.common.extensions.showToast
import com.jaino.common.navigation.AppNavigator
import com.jaino.review.R
import com.jaino.review.databinding.FragmentWriteReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class WriteReviewFragment : Fragment(){

    private var _binding : FragmentWriteReviewBinding? = null
    private val binding get() = requireNotNull(_binding){ "binding object is not initialized" }

    @Inject
    lateinit var appNavigator: AppNavigator

    private val viewModel : WriteReviewViewModel by viewModels()
    private val args : WriteReviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_write_review, container, false)
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
            viewModel.postReview(args.drinkId)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeData(){
        viewModel.uiEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is WriteReviewViewModel.UiEvent.Success -> {
                        navigateToList()
                    }

                    is WriteReviewViewModel.UiEvent.Failure -> {
                        if(it.message != null) {
                            requireContext().showToast(it.message)
                        }
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.reviewContent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                binding.reviewTextCounter.text = "${it.length}/10자 이상"
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showDialog(){

    }

    private fun navigateToDictionary(){
        findNavController().navigate("BeJuRyu://feature/dictionary".toUri())
    }

    private fun navigateToList(){
        val direction = WriteReviewFragmentDirections
            .actionWriteReviewFragmentToReviewListFragment(args.drinkId)
        findNavController().navigate(direction)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}