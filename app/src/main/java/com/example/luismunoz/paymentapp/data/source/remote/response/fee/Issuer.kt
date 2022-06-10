package com.example.luismunoz.paymentapp.data.source.remote.response.fee

import com.google.gson.annotations.SerializedName

data class Issuer(

	@SerializedName("thumbnail")
	val thumbnail: String,

	@SerializedName("secure_thumbnail")
	val secureThumbnail: String,

	@SerializedName("name")
	val name: String,

	@SerializedName("id")
	val id: String
)