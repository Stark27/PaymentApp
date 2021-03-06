package com.example.luismunoz.paymentapp.data.source.remote.response.fee

import com.google.gson.annotations.SerializedName

data class PayerCostsItem(

	@SerializedName("installments")
	val installments: Int,

	@SerializedName("installment_amount")
	val installmentAmount: Double,

	@SerializedName("total_amount")
	val totalAmount: Double,

	@SerializedName("payment_method_option_id")
	val paymentMethodOptionId: String,

	@SerializedName("recommended_message")
	val recommendedMessage: String,

	@SerializedName("reimbursement_rate")
	val reimbursementRate: Any? = null,

	@SerializedName("installment_rate")
	val installmentRate: Double? = null,

	@SerializedName("installment_rate_collector")
	val installmentRateCollector: List<String?>? = null,

	@SerializedName("discount_rate")
	val discountRate: Int? = null,

	@SerializedName("min_allowed_amount")
	val minAllowedAmount: Int? = null,

	@SerializedName("labels")
	val labels: List<String?>? = null,

	@SerializedName("max_allowed_amount")
	val maxAllowedAmount: Int? = null
)