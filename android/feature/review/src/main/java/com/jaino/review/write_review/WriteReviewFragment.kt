package com.jaino.review.write_review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jaino.common.extensions.showToast
import com.jaino.common.navigation.AppNavigator
import com.jaino.review.R
import com.jaino.review.databinding.FragmentWriteReviewBinding
import dagger.hilt.android.AndroidEntryPoint
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
            val direction = WriteReviewFragmentDirections.actionWriteReviewFragmentToReviewListFragment(
                args.drinkId
            )
            findNavController().navigate(direction)
        }

        binding.goToHomeButton.setOnClickListener {
            findNavController().navigate("BeJuRyu://feature/dictionary".toUri())
        }

        binding.reviewPostButton.setOnClickListener {
            viewModel.postReview(args.drinkId)
        }
    }

    private fun observeData(){
        viewModel.uiEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is WriteReviewViewModel.UiEvent.Failure -> {
                        if(it.message != null) {
                            requireContext().showToast(it.message)
                        }
                    }

                    is WriteReviewViewModel.UiEvent.Success -> {
                        showDialog()
                    }
                }
            }
    }

    private fun showDialog(){

    }

    private fun navigateDictionary(){

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}