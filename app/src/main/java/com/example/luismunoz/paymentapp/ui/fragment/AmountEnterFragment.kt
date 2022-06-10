package com.example.luismunoz.paymentapp.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.luismunoz.paymentapp.R
import com.example.luismunoz.paymentapp.databinding.FragmentAmountEnterBinding
import com.example.luismunoz.paymentapp.util.*
import com.example.luismunoz.paymentapp.viewmodel.AmountEnterViewModel

/**
 *  Fragment that manage and show design to enter an amount to pay
 */
class AmountEnterFragment : Fragment() {

    private var _binding: FragmentAmountEnterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AmountEnterViewModel by viewModels()

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

        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {
        val amountValue = viewModel.amountValidateValue
        binding.edAmountEnterFragmentValue.setText(amountValue)
    }

    /**
     *  Init the observer that show an error or trigger a navigation
     */
    private fun initObservers() {
        viewModel.validateAmountStatus.observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                ValidateAmountStatus.MIN_AMOUNT -> {
                    binding.edAmountEnterFragmentValue.setText(viewModel.amountValidateValue)
                    binding.tilAmountEnterFragmentAmountContainer.error = getString(R.string.text_add_amount_different_to_zero)
                    binding.btnAmountEnterFragmentGoToPaymentMethod.isEnabled = false
                }
                ValidateAmountStatus.MAX_AMOUNT -> {
                    binding.edAmountEnterFragmentValue.setText(viewModel.amountValidateValue)
                    binding.tilAmountEnterFragmentAmountContainer.error = getString(R.string.text_add_amount_max_allow)
                    binding.btnAmountEnterFragmentGoToPaymentMethod.isEnabled = false
                }
                ValidateAmountStatus.VALID_AMOUNT -> {
                    binding.edAmountEnterFragmentValue.setText(viewModel.amountValidateValue)
                    binding.tilAmountEnterFragmentAmountContainer.error = null
                    binding.btnAmountEnterFragmentGoToPaymentMethod.isEnabled = true
                }
                else -> {
                    binding.edAmountEnterFragmentValue.setText(viewModel.amountValidateValue)
                    binding.btnAmountEnterFragmentGoToPaymentMethod.isEnabled = false
                }
            }

            binding.edAmountEnterFragmentValue.addTextChangedListener(validateTextAmount)
            binding.edAmountEnterFragmentValue.setSelection(binding.edAmountEnterFragmentValue.length())
        })
    }

    /**
     *  Init listener for keyboard or button
     */
    private fun initListeners() {
        binding.edAmountEnterFragmentValue.addTextChangedListener(validateTextAmount)

        binding.edAmountEnterFragmentValue.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val amountValue = binding.edAmountEnterFragmentValue.text.toString()
                viewModel.validateAmountObserver(amountString = amountValue)
            }
            false
        }

        binding.btnAmountEnterFragmentGoToPaymentMethod.setOnClickListener {
            val amountValue = binding.edAmountEnterFragmentValue.text.toString()
            val cleanedAmount = Util.cleanAmount(amountValue)
            val action = AmountEnterFragmentDirections.actionAmountEnterFragmentToPaymentSelectionFragment(amount = cleanedAmount)
            findNavController().navigate(action)
        }
    }

    /**
     *  Watcher that validate amount that user is typing
     */
    private val validateTextAmount = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // No implemented method
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // No implemented method
        }

        override fun afterTextChanged(s: Editable?) {
            val amountString = s.toString()
            binding.edAmountEnterFragmentValue.removeTextChangedListener(this)
            viewModel.validateAmountObserver(amountString)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}