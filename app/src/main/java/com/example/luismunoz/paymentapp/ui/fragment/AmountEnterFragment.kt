package com.example.luismunoz.paymentapp.ui.fragment

import android.os.Bundle
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
import com.example.luismunoz.paymentapp.util.ValidateAmountStatus
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

        initObservers()
        initListeners()
    }

    /**
     *  Init the observer that show an error or trigger a navigation
     */
    private fun initObservers() {
        viewModel.amountValidate.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result ->
                when(result) {
                    ValidateAmountStatus.EMPTY_AMOUNT -> {
                        binding.tilAmountEnterFragmentAmountContainer.error = getString(R.string.text_add_amount_error)
                    }
                    ValidateAmountStatus.ZERO_AMOUNT -> {
                        binding.tilAmountEnterFragmentAmountContainer.error = getString(R.string.text_add_amount_different_to_zero)
                    }
                    else -> {
                        binding.tilAmountEnterFragmentAmountContainer.error = null

                        val amountValue = binding.edAmountEnterFragmentValue.text.toString()
                        val action = AmountEnterFragmentDirections.actionAmountEnterFragmentToPaymentSelectionFragment(amount = amountValue)
                        findNavController().navigate(action)
                    }
                }
            }
        })
    }

    /**
     *  Init listener for keyboard or button
     */
    private fun initListeners() {
        binding.edAmountEnterFragmentValue.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val amountValue = binding.edAmountEnterFragmentValue.text.toString()
                viewModel.validateAmountInput(amountValue)
            }
            false
        }

        binding.btnAmountEnterFragmentGoToPaymentMethod.setOnClickListener {
            val amountValue = binding.edAmountEnterFragmentValue.text.toString()
            viewModel.validateAmountInput(amountValue)
        }
    }

}