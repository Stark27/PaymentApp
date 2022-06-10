package com.example.luismunoz.paymentapp.domain.model.paymentMethod

import com.example.luismunoz.paymentapp.data.source.remote.response.paymentMethod.PaymentMethodResponse

fun PaymentMethodResponse.fromDomainToUi() = DataPaymentMethod(
    paymentMethodId = id,
    paymentMethodName = name,
    paymentMethodPath = secureThumbnail
)
