package com.example.luismunoz.paymentapp.domain.usecase.mock

import com.example.luismunoz.paymentapp.data.source.remote.response.paymentMethod.PaymentMethodResponse
import com.example.luismunoz.paymentapp.domain.model.DataPaymentMethod

class GetPaymentMethodsUseCaseMock {

    fun getSuccessFullExpectedResponse() = arrayListOf(
        DataPaymentMethod(
            paymentMethodId = "master",
            paymentMethodName = "Mastercard",
            paymentMethodPath = "https://http2.mlstatic.com/storage/logos-api-admin/0daa1670-5c81-11ec-ae75-df2bef173be2-xl@2x.png"
        ),
        DataPaymentMethod(
            paymentMethodId = "visa",
            paymentMethodName = "Visa",
            paymentMethodPath = "https://http2.mlstatic.com/storage/logos-api-admin/d589be70-eb86-11e9-b9a8-097ac027487d-xl@2x.png"
        )
    )

    fun getSuccessFilteredExpectedResponse() = arrayListOf(
        DataPaymentMethod(
            paymentMethodId = "visa",
            paymentMethodName = "Visa",
            paymentMethodPath = "https://http2.mlstatic.com/storage/logos-api-admin/d589be70-eb86-11e9-b9a8-097ac027487d-xl@2x.png"
        ),
    )

    fun getEmptyValuesRemotePaymentMethodResponse() = arrayListOf(
        PaymentMethodResponse(
            id = "",
            name = "Mastercard",
            paymentTypeId = "credit_card",
            thumbnail = "https://http2.mlstatic.com/storage/logos-api-admin/0daa1670-5c81-11ec-ae75-df2bef173be2-xl@2x.png",
            status = "active"
        ),
        PaymentMethodResponse(
            id = "visa",
            name = "Visa",
            paymentTypeId = "credit_card",
            thumbnail = "https://http2.mlstatic.com/storage/logos-api-admin/d589be70-eb86-11e9-b9a8-097ac027487d-xl@2x.png",
            status = "active"
        )
    )

    fun getInActiveRemotePaymentMethodResponse() = arrayListOf(
        PaymentMethodResponse(
            id = "master",
            name = "Mastercard",
            paymentTypeId = "credit_card",
            thumbnail = "https://http2.mlstatic.com/storage/logos-api-admin/0daa1670-5c81-11ec-ae75-df2bef173be2-xl@2x.png",
            status = "inactive"
        ),
        PaymentMethodResponse(
            id = "visa",
            name = "Visa",
            paymentTypeId = "credit_card",
            thumbnail = "https://http2.mlstatic.com/storage/logos-api-admin/d589be70-eb86-11e9-b9a8-097ac027487d-xl@2x.png",
            status = "active"
        )
    )

    fun getFullRemotePaymentMethodResponse() = arrayListOf(
        PaymentMethodResponse(
            id = "master",
            name = "Mastercard",
            paymentTypeId = "credit_card",
            thumbnail = "https://http2.mlstatic.com/storage/logos-api-admin/0daa1670-5c81-11ec-ae75-df2bef173be2-xl@2x.png",
            status = "active"
        ),
        PaymentMethodResponse(
            id = "visa",
            name = "Visa",
            paymentTypeId = "credit_card",
            thumbnail = "https://http2.mlstatic.com/storage/logos-api-admin/d589be70-eb86-11e9-b9a8-097ac027487d-xl@2x.png",
            status = "active"
        )
    )

    fun getNullRemotePaymentMethodResponse():List<PaymentMethodResponse>? = null

}
