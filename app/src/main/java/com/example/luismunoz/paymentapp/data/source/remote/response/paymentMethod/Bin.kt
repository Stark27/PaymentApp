package com.example.luismunoz.paymentapp.data.source.remote.response.paymentMethod

import com.google.gson.annotations.SerializedName

data class Bin(

	@SerializedName("pattern")
	val pattern: String,

	@SerializedName("installments_pattern")
	val installmentsPattern: String,

	@SerializedName("exclusion_pattern")
	val exclusionPattern: Any
)