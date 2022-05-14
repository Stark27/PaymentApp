package com.example.luismunoz.paymentapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import com.example.luismunoz.paymentapp.R
import com.example.luismunoz.paymentapp.databinding.FragmentAmountEnterBinding

class AmountEnterFragment : Fragment() {

    private var _binding: FragmentAmountEnterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAmountEnterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.edAmountEnterFragmentValue.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateAmountInput()
            }
            false
        }

        binding.btnAmountEnterFragmentGoToPaymentMethod.setOnClickListener {
            validateAmountInput()
        }
    }

    private fun validateAmountInput() {
        val amountValue = binding.edAmountEnterFragmentValue.text.toString()

        if (amountValue.isEmpty()) {
            binding.tilAmountEnterFragmentAmountContainer.error = null
            binding.tilAmountEnterFragmentAmountContainer.error = getString(R.string.text_add_amount_error)
        } else if (amountValue.toInt() == 0) {
            binding.tilAmountEnterFragmentAmountContainer.error = null
            binding.tilAmountEnterFragmentAmountContainer.error = getString(R.string.text_add_amount_different_to_zero)
        } else {
            binding.tilAmountEnterFragmentAmountContainer.error = null
            val action = AmountEnterFragmentDirections.actionAmountEnterFragmentToPaymentSelectionFragment(amount = amountValue.toInt())
            findNavController().navigate(action)
        }
    }

}