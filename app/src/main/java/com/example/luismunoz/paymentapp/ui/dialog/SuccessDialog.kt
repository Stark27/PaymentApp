package com.example.luismunoz.paymentapp.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.luismunoz.paymentapp.R
import com.example.luismunoz.paymentapp.databinding.FragmentSuccessDialogBinding
import com.example.luismunoz.paymentapp.util.Util

class SuccessDialog : DialogFragment() {

    private var _binding: FragmentSuccessDialogBinding? = null
    private val binding get() = _binding!!
    private val args: SuccessDialogArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSuccessDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initViews()
    }

    private fun initViews() {
        val args = args.summary

        binding.tvSuccessDialogFragmentAmountEnteredValue.text = Util.currencyFormat(Util.cleanAmount(args.amountValue).toInt())
        binding.tvSuccessDialogFragmentPaymentMethodValue.text = args.paymentMethodName
        binding.tvSuccessDialogFragmentBankValue.text = args.bankName
        binding.tvSuccessDialogFragmentNumberFeeValue.text = args.feeNumber.toString()
        binding.tvSuccessDialogFragmentFeeAmountValue.text = Util.currencyDecimalFormat(args.feeAmount)
        binding.tvSuccessDialogFragmentTotalAmountValue.text = Util.currencyDecimalFormat(args.totalAmount)

        val transactionIdText = getString(R.string.textPaymentTransactionId, args.transactionId)
        binding.tvSuccessDialogFragmentTransactionId.text = transactionIdText
    }

    private fun initListeners() {
        binding.btnSuccessDialogFragmentAccept.setOnClickListener {
            findNavController().popBackStack(R.id.amountEnterFragment, false)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}