package com.example.luismunoz.paymentapp.data.source.remote.response.paymentMethod

import com.google.gson.annotations.SerializedName

data class SettingsItem(

	@SerializedName("security_code")
	val securityCode: SecurityCode,

	@SerializedName("card_number")
	val cardNumber: CardNumber,

	@SerializedName("bin")
	val bin: Bin
)