package com.example.luismunoz.paymentapp.data.source.remote.response.paymentMethod

import com.google.gson.annotations.SerializedName

data class PaymentMethodResponse(

	@SerializedName("financial_institutions")
	val financialInstitutions: List<Any?>? = null,

	@SerializedName("settings")
	val settings: List<SettingsItem?>? = null,

	@SerializedName("thumbnail")
	val thumbnail: String? = null,

	@SerializedName("deferred_capture")
	val deferredCapture: String? = null,

	@SerializedName("secure_thumbnail")
	val secureThumbnail: String,

	@SerializedName("min_allowed_amount")
	val minAllowedAmount: Int? = null,

	@SerializedName("processing_modes")
	val processingModes: List<String?>? = null,

	@SerializedName("name")
	val name: String,

	@SerializedName("id")
	val id: String,

	@SerializedName("additional_info_needed")
	val additionalInfoNeeded: List<String?>? = null,

	@SerializedName("payment_type_id")
	val paymentTypeId: String,

	@SerializedName("status")
	val status: String,

	@SerializedName("max_allowed_amount")
	val maxAllowedAmount: Int? = null,

	@SerializedName("accreditation_time")
	val accreditationTime: Int? = null
)