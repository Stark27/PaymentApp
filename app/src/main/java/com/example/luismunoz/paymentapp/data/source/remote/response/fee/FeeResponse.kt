package com.example.luismunoz.paymentapp.data.source.remote.response.fee

import com.google.gson.annotations.SerializedName

data class FeeResponse(

	@SerializedName("payment_method_id")
	val paymentMethodId: String? = null,

	@SerializedName("payer_costs")
	val payerCosts: List<PayerCostsItem?>? = null,

	@SerializedName("merchant_account_id")
	val merchantAccountId: Any? = null,

	@SerializedName("agreements")
	val agreements: Any? = null,

	@SerializedName("processing_mode")
	val processingMode: String? = null,

	@SerializedName("payment_type_id")
	val paymentTypeId: String? = null,

	@SerializedName("issuer")
	val issuer: Issuer? = null
)