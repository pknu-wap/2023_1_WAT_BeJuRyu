package com.jaino.dictionary.drink_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.jaino.common.navigation.AppNavigator
import com.jaino.dictionary.R
import com.jaino.dictionary.databinding.FragmentDrinkSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DrinkSearchFragment : Fragment() {

    private var _binding : FragmentDrinkSearchBinding ?= null
    private val binding get() =  requireNotNull(_binding) { "binding object is not initialized" }

    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drink_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        binding.backToAnalyzeButton.setOnClickListener{
            startActivity(navigator.navigateToAnalyze())
        }

        binding.searchEditTextView.setOnEditorActionListener { textView, actionId, keyEvent ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH && textView.text.isNotBlank()){
                navigateToDrinkList(
                    word = textView.text.toString(),
                    type = ""
                )
                true
            }
            else false
        }

        binding.drinkChipGroup.setOnCheckedStateChangeListener{ group, checkedIds ->
            // single selection
            val drinkCategory = group.findViewById<Chip>(checkedIds.first()).text.toString()
            navigateToDrinkList(
                word = "",
                type = drinkCategory
            )
        }
    }

    private fun navigateToDrinkList(word: String, type: String){
        val direction = DrinkSearchFragmentDirections
            .actionDrinkSearchFragmentToDrinkListFragment(word, type)
        findNavController().navigate(direction)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
