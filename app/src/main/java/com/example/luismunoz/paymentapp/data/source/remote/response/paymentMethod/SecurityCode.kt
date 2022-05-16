package com.example.luismunoz.paymentapp.data.source.remote.response.paymentMethod

import com.google.gson.annotations.SerializedName

data class SecurityCode(

	@SerializedName("mode")
	val mode: String? = null,

	@SerializedName("card_location")
	val cardLocation: String? = null,

	@SerializedName("length")
	val length: Int? = null
)