package com.jaino.analyze.input_text

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jaino.analyze.R
import com.jaino.analyze.databinding.FragmentAnalyzeTextBinding
import usecase.validate.ValidateTextExpression

class AnalyzeTextFragment : Fragment() {

    private val expressionUseCase by lazy{ ValidateTextExpression() }

    private var _binding: FragmentAnalyzeTextBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_analyze_text, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initViews()
    }

    private fun initButtons(){
        binding.analyzeBackButton.setOnClickListener {
            navigateToHome()
        }

        binding.analyzeTextDoneButton.setOnClickListener{
            val text = binding.analyzeTextExpression.text.toString()
            val result = expressionUseCase(text)
            if(!result.successful){
                Toast.makeText(requireContext(), result.errorMessage, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            navigateToImage(text)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(){

        binding.analyzeTextExpression.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length = binding.analyzeTextExpression.text.toString().length
                binding.analyzeTextCounter.text = "$length/10자 이상"
            }
        })
    }

    private fun navigateToHome(){
        findNavController().navigate(
            AnalyzeTextFragmentDirections.actionAnalyzeTextFragmentToAnalyzeHomeFragment()
        )
    }

    private fun navigateToImage(analyzeText: String){
        findNavController().navigate(
            AnalyzeTextFragmentDirections.actionAnalyzeTextFragmentToAnalyzeImageFragment(
                "", analyzeText
            )
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}