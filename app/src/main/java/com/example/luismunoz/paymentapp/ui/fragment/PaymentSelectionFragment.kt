package com.example.luismunoz.paymentapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.luismunoz.paymentapp.R
import com.example.luismunoz.paymentapp.databinding.FragmentPaymentSelectionBinding

class PaymentSelectionFragment : Fragment() {

    private var _binding: FragmentPaymentSelectionBinding? = null
    private val binding get() = _binding!!
    private val args: PaymentSelectionFragmentArgs by navArgs()

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

        val amount = args.amount

        binding.btn2.setOnClickListener {
            findNavController().navigate(R.id.action_paymentSelectionFragment_to_bankSelectionFragment)
        }
    }

}