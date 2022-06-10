package com.example.luismunoz.paymentapp.domain.model.fee

import com.example.luismunoz.paymentapp.data.source.remote.response.fee.PayerCostsItem

fun PayerCostsItem.fromDomainToUi() = DataFee(
    numberFee = installments,
    message = recommendedMessage,
    paymentMethodOptionId = paymentMethodOptionId,
    totalAmount = totalAmount,
    feeAmount = installmentAmount
)