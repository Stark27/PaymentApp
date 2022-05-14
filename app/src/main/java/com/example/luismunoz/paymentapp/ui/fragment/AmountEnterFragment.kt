package com.example.luismunoz.paymentapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.btn1.setOnClickListener {
            findNavController().navigate(R.id.action_amountEnterFragment_to_paymentSelectionFragment)
        }
    }

}