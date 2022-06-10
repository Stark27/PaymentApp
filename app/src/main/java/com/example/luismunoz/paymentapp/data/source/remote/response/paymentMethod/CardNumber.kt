package com.example.luismunoz.paymentapp.data.source.remote.response.paymentMethod

import com.google.gson.annotations.SerializedName

data class CardNumber(

	@SerializedName("length")
	val length: Int,

	@SerializedName("validation")
	val validation: String
)