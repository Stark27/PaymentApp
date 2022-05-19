package com.example.luismunoz.paymentapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.luismunoz.paymentapp.databinding.FragmentBankSelectionBinding
import com.example.luismunoz.paymentapp.domain.ItemOnClickListener
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.ui.adapter.BankAdapter
import com.example.luismunoz.paymentapp.viewmodel.BankSelectionViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Fragment that manage and show design to select a bank
 */
@AndroidEntryPoint
class BankSelectionFragment : Fragment(), ItemOnClickListener {

    private var _binding: FragmentBankSelectionBinding? = null
    private val binding get() = _binding!!
    private val args: BankSelectionFragmentArgs by navArgs()
    private val viewModel: BankSelectionViewModel by viewModels()
    private lateinit var adapter: BankAdapter

    private lateinit var amountValue: String
    private lateinit var methodPaymentId: String
    private lateinit var methodPaymentName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBankSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        amountValue = args.amount
        methodPaymentId = args.paymentMethodId
        methodPaymentName = args.paymentMethodName

        initObservers()
        initRecyclerView()
        onFragmentReady()
    }

    private fun onFragmentReady() {
        viewModel.getAllBanks(methodPaymentId)
    }

    private fun initRecyclerView() {
        binding.rvBankSelectionContainer.layoutManager = LinearLayoutManager(requireContext())
        adapter = BankAdapter(arrayListOf(), requireContext(), this)
        binding.rvBankSelectionContainer.adapter = adapter
    }

    private fun initObservers() {
        viewModel.bankObserver.observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loading -> {
                    binding.rvBankSelectionContainer.visibility = View.GONE
                    binding.genericErrorContainer.clGenericErrorLayoutContainer.visibility = View.GONE
                    binding.sflBankSelectionFragmentContainer.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.rvBankSelectionContainer.visibility = View.VISIBLE
                    binding.sflBankSelectionFragmentContainer.visibility = View.GONE
                    binding.genericErrorContainer.clGenericErrorLayoutContainer.visibility = View.GONE
                    adapter.addBanks(result.data)
                }
                is Resource.Error -> {
                    binding.rvBankSelectionContainer.visibility = View.GONE
                    binding.sflBankSelectionFragmentContainer.visibility = View.GONE
                    binding.genericErrorContainer.clGenericErrorLayoutContainer.visibility = View.VISIBLE
                }
            }
        })

    }

    override fun onClickItem(position: Int, itemId: String, itemName: String) {
        val action = BankSelectionFragmentDirections
            .actionBankSelectionFragmentToFeeSelectionFragment(
                amount = amountValue,
                paymentMethodId = methodPaymentId,
                issuerId = itemId,
                paymentMethodName = methodPaymentName,
                issuerName = itemName
            )

        findNavController().navigate(action)
    }

}