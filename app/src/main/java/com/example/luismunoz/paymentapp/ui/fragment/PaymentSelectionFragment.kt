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
import com.example.luismunoz.paymentapp.databinding.FragmentPaymentSelectionBinding
import com.example.luismunoz.paymentapp.domain.ItemOnClickListener
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.ui.adapter.PaymentMethodAdapter
import com.example.luismunoz.paymentapp.viewmodel.PaymentSelectionViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Fragment that manage and show design to select a payment method
 */
@AndroidEntryPoint
class PaymentSelectionFragment : Fragment(), ItemOnClickListener {

    private var _binding: FragmentPaymentSelectionBinding? = null
    private val binding get() = _binding!!
    private val args: PaymentSelectionFragmentArgs by navArgs()
    private val viewModel: PaymentSelectionViewModel by viewModels()
    private lateinit var adapter: PaymentMethodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPaymentSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initRecycler()
    }

    private fun initRecycler() {
        binding.rvPaymentSelectionContainer.layoutManager = LinearLayoutManager(requireContext())
        adapter = PaymentMethodAdapter(arrayListOf(), requireContext(), this)
        binding.rvPaymentSelectionContainer.adapter = adapter
    }

    private fun initObservers() {
        viewModel.paymentMethodObserver().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loading -> {
                    binding.rvPaymentSelectionContainer.visibility = View.GONE
                    binding.genericErrorContainer.clGenericErrorLayoutContainer.visibility = View.GONE
                    binding.sflPaymentSelectionFragmentContainer.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.rvPaymentSelectionContainer.visibility = View.VISIBLE
                    binding.sflPaymentSelectionFragmentContainer.visibility = View.GONE
                    binding.genericErrorContainer.clGenericErrorLayoutContainer.visibility = View.GONE
                    adapter.addPaymentMethods(result.data)
                }
                is Resource.Error -> {
                    binding.rvPaymentSelectionContainer.visibility = View.GONE
                    binding.sflPaymentSelectionFragmentContainer.visibility = View.GONE
                    binding.genericErrorContainer.clGenericErrorLayoutContainer.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onClickItem(position: Int, itemId: String) {
        val amount = args.amount
        val action = PaymentSelectionFragmentDirections
            .actionPaymentSelectionFragmentToBankSelectionFragment(
                amount = amount,
                paymentMethodId = itemId
            )

        findNavController().navigate(action)
    }

}