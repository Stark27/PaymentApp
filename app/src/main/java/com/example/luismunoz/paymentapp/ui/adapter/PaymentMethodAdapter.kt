package com.example.luismunoz.paymentapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.luismunoz.paymentapp.R
import com.example.luismunoz.paymentapp.databinding.ItemPaymentMethodListBinding
import com.example.luismunoz.paymentapp.domain.ItemOnClickListener
import com.example.luismunoz.paymentapp.domain.model.DataPaymentMethod

/**
 *  Adapter that handle a payment methods list
 */
class PaymentMethodAdapter(
    private var paymentMethods: MutableList<DataPaymentMethod>,
    private var context: Context,
    private var itemOnClick: ItemOnClickListener,
    ): RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_payment_method_list, parent, false)
        return PaymentMethodViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        val paymentMethod = paymentMethods[position]
        holder.bind(paymentMethod, position)
    }

    override fun getItemCount(): Int {
        return paymentMethods.size
    }

    fun addPaymentMethods(list: MutableList<DataPaymentMethod>) {
        paymentMethods.addAll(list)
        notifyDataSetChanged()
    }

     inner class PaymentMethodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = ItemPaymentMethodListBinding.bind(itemView)

        fun bind(paymentMethod: DataPaymentMethod, position: Int) {
            Glide.with(context).load(paymentMethod.paymentMethodPath).into(binding.imgItemPaymentMethodIcon)
            binding.tvItemPaymentMethodName.text = paymentMethod.paymentMethodName
            binding.cvItemPaymentMethodContainer.setOnClickListener { itemOnClick.onClickItem(position, paymentMethod.paymentMethodId, paymentMethod.paymentMethodName) }
        }
    }

}