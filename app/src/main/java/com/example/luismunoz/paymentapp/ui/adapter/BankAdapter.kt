package com.example.luismunoz.paymentapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.luismunoz.paymentapp.R
import com.example.luismunoz.paymentapp.databinding.ItemBankListBinding
import com.example.luismunoz.paymentapp.domain.ItemOnClickListener
import com.example.luismunoz.paymentapp.domain.model.DataBank

/**
 *  Adapter that handle a bank list
 */
class BankAdapter(
    private var banks: MutableList<DataBank>,
    private var context: Context,
    private var itemOnClick: ItemOnClickListener,
): RecyclerView.Adapter<BankAdapter.BankViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bank_list, parent, false)
        return BankViewHolder(view)
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        val bank = banks[position]
        holder.bind(bank, position)
    }

    override fun getItemCount(): Int {
        return banks.size
    }

    fun addBanks(list: MutableList<DataBank>) {
        banks.addAll(list)
        notifyDataSetChanged()
    }

    inner class BankViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = ItemBankListBinding.bind(itemView)

        fun bind(dataBank: DataBank, position: Int) {
            Glide.with(context).load(dataBank.bankPath).into(binding.imgItemBankIcon)
            binding.tvItemBankName.text = dataBank.bankName
            binding.cvItemBankContainer.setOnClickListener { itemOnClick.onClickItem(position, dataBank.bankId, dataBank.bankName) }
        }
    }

}