package com.example.luismunoz.paymentapp.data.source.remote.response.bank

import com.google.gson.annotations.SerializedName

data class BankResponse(

	@SerializedName("thumbnail")
	val thumbnail: String? = null,

	@SerializedName("secure_thumbnail")
	val secureThumbnail: String,

	@SerializedName("name")
	val name: String,

	@SerializedName("merchant_account_id")
	val merchantAccountId: Any? = null,

	@SerializedName("processing_mode")
	val processingMode: String? = null,

	@SerializedName("id")
	val id: String,

	@SerializedName("status")
	val status: String
)