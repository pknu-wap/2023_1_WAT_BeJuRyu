package com.jaino.review.review_list

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaino.common.extensions.showToast
import com.jaino.review.R
import com.jaino.review.databinding.FragmentReviewListBinding
import com.jaino.review.review_list.adapter.ReviewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ReviewListFragment : Fragment() {

    private var _binding : FragmentReviewListBinding? = null
    private val binding get() = requireNotNull( _binding){ "binding object is not initialized" }

    private val viewModel : ReviewListViewModel by viewModels()

    private val args : ReviewListFragmentArgs by navArgs()

    private lateinit var adapter : ReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initAdapter()
        observeData()
    }

    private fun initViews(){
        viewModel.getReviewList(args.drinkId)
        binding.writeReviewButton.setOnClickListener {
            val direction = ReviewListFragmentDirections.actionReviewListFragmentToWriteReviewFragment(
                args.drinkId
            )
            findNavController().navigate(direction)
        }
    }

    private fun initAdapter(){
        adapter = ReviewAdapter()
        binding.drinkReviewList.adapter = adapter
        binding.drinkReviewList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeData(){
        viewModel.uiEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is ReviewListViewModel.UiEvent.Success -> {
                        adapter.submitList(it.data)
                    }

                    is ReviewListViewModel.UiEvent.Failure -> {
                        if(it.message != null){
                            requireContext().showToast(it.message)
                        }
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}