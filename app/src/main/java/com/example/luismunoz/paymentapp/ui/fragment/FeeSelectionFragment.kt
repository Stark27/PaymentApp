package com.example.luismunoz.paymentapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.luismunoz.paymentapp.R
import com.example.luismunoz.paymentapp.databinding.FragmentFeeSelectionBinding
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.model.DataFee
import com.example.luismunoz.paymentapp.domain.model.DataSummary
import com.example.luismunoz.paymentapp.util.ITEM_SELECTED_KEY
import com.example.luismunoz.paymentapp.viewmodel.FeeSelectionViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Fragment that manage and show design to fee selection
 */
@AndroidEntryPoint
class FeeSelectionFragment : Fragment() {

    private var _binding: FragmentFeeSelectionBinding? = null
    private val binding get() = _binding!!
    private val args: FeeSelectionFragmentArgs by navArgs()
    private val viewModel: FeeSelectionViewModel by viewModels()
    private lateinit var adapter: ArrayAdapter<DataFee>
    private lateinit var spinner: Spinner

    private lateinit var amountValue: String
    private lateinit var paymentMethodName: String
    private lateinit var bankName:  String
    private var itemSelected: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFeeSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        amountValue = args.amount
        paymentMethodName = args.paymentMethodName
        bankName = args.issuerName

        initAdapter(view)
        initListeners()
        onFragmentReady()
    }

    private fun initListeners() {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                itemSelected = position
                val data: DataFee = spinner.selectedItem as DataFee
                binding.tvFeeSelectionFragmentRecommendedMessageTest.text = data.message
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // No implemented method
            }
        }

        binding.btnFeeSelectionFragmentFinishPay.setOnClickListener {
            val data: DataFee = spinner.selectedItem as DataFee

            val dataSummary = DataSummary(
                amountValue = amountValue,
                paymentMethodName = paymentMethodName,
                bankName = bankName,
                feeNumber = data.numberFee,
                feeAmount = data.feeAmount,
                totalAmount = data.totalAmount,
                transactionId = data.paymentMethodOptionId
            )

            val action = FeeSelectionFragmentDirections.actionFeeSelectionFragmentToSuccessDialog(dataSummary)
            findNavController().navigate(action)
        }
    }

    private fun initAdapter(view: View) {
        spinner = view.findViewById(R.id.spnFeeSelectionFragmentList)
        adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, listOf<DataFee>())
        spinner.adapter = adapter
    }

    private fun onFragmentReady() {
        val amountValue = args.amount
        val paymentMethodId = args.paymentMethodId
        val issuerId = args.issuerId

        viewModel.getAllFeeObserver(amountValue, paymentMethodId, issuerId).observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loading -> {
                    binding.sflFeeSelectionFragmentContentContainer.visibility = View.VISIBLE
                    binding.llFeeSelectionFragmentContentContainer.visibility =  View.GONE
                    binding.errorContainer.clGenericErrorLayoutContainer.visibility = View.GONE
                    binding.btnFeeSelectionFragmentFinishPay.isEnabled = false
                }
                is Resource.Success -> {
                    binding.sflFeeSelectionFragmentContentContainer.visibility = View.GONE
                    binding.llFeeSelectionFragmentContentContainer.visibility =  View.VISIBLE
                    binding.errorContainer.clGenericErrorLayoutContainer.visibility = View.GONE
                    binding.btnFeeSelectionFragmentFinishPay.isEnabled = true

                    spinner = binding.spnFeeSelectionFragmentList
                    adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, result.data)
                    spinner.adapter = adapter
                    spinner.setSelection(itemSelected)
                }
                is Resource.Error -> {
                    binding.sflFeeSelectionFragmentContentContainer.visibility = View.GONE
                    binding.llFeeSelectionFragmentContentContainer.visibility =  View.GONE
                    binding.btnFeeSelectionFragmentFinishPay.visibility = View.GONE
                    binding.errorContainer.clGenericErrorLayoutContainer.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ITEM_SELECTED_KEY, itemSelected)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if (savedInstanceState != null) {
            itemSelected = savedInstanceState.getInt(ITEM_SELECTED_KEY)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}