package com.example.luismunoz.paymentapp.data.source.remote.response.fee

import com.google.gson.annotations.SerializedName

data class Issuer(

	@SerializedName("thumbnail")
	val thumbnail: String? = null,

	@SerializedName("secure_thumbnail")
	val secureThumbnail: String? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("id")
	val id: String? = null
)