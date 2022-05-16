package com.example.luismunoz.paymentapp.domain.usecase

import com.example.luismunoz.paymentapp.data.exception.ServiceException
import com.example.luismunoz.paymentapp.data.source.remote.PaymentSelectionRepositoryImpl
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.usecase.mock.GetPaymentMethodsUseCaseMock
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPaymentMethodsUseCaseTest {

    @MockK
    private lateinit var paymentSelectionRepositoryImpl: PaymentSelectionRepositoryImpl

    private lateinit var getPaymentMethodsUseCase: GetPaymentMethodsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPaymentMethodsUseCase = GetPaymentMethodsUseCase(paymentSelectionRepositoryImpl)
    }

    @Test
    fun `GIVEN an action WHEN GetPaymentMethodsUseCase is called THEN returns an Error value if get an Error response from repository`() = runBlocking {

        //Given
        val exception = Exception()
        coEvery { paymentSelectionRepositoryImpl.getPaymentMethodsFromRemote() } returns Resource.Error(exception)

        // When
        val useCaseResponse = getPaymentMethodsUseCase.getAllRemotePaymentMethod()

        // Then
        assertThat(useCaseResponse).isEqualTo(Resource.Error(exception))
    }

    @Test
    fun `GIVEN an action WHEN GetPaymentMethodsUseCase is called THEN returns a Error value if get null data from repository`() = runBlocking {

        //Given
        val methodPaymentResponse = GetPaymentMethodsUseCaseMock().getNullRemotePaymentMethodResponse()
        val expectedResponse = ServiceException()

        coEvery { paymentSelectionRepositoryImpl.getPaymentMethodsFromRemote() } returns Resource.Success(methodPaymentResponse)

        // When
        val useCaseResponse = getPaymentMethodsUseCase.getAllRemotePaymentMethod()

        // Then
        coVerify(exactly = 1) { paymentSelectionRepositoryImpl.getPaymentMethodsFromRemote() }
        assertThat(useCaseResponse).isInstanceOf(Resource.Error(expectedResponse).javaClass)
    }

    @Test
    fun `GIVEN an action WHEN GetPaymentMethodsUseCase is called THEN returns a Success value if get a success response with some empty fields from repository`() = runBlocking {

        //Given
        val methodPaymentResponse = GetPaymentMethodsUseCaseMock().getEmptyValuesRemotePaymentMethodResponse()
        val expectedResponse = GetPaymentMethodsUseCaseMock().getSuccessFilteredExpectedResponse()

        coEvery { paymentSelectionRepositoryImpl.getPaymentMethodsFromRemote() } returns Resource.Success(methodPaymentResponse)

        // When
        val useCaseResponse = getPaymentMethodsUseCase.getAllRemotePaymentMethod()

        // Then
        coVerify(exactly = 1) { paymentSelectionRepositoryImpl.getPaymentMethodsFromRemote() }
        assertThat(useCaseResponse).isEqualTo(Resource.Success(expectedResponse))
    }

    @Test
    fun `GIVEN an action WHEN GetPaymentMethodsUseCase is called THEN returns a Success value if get a success with some inactive payment methods response from repository`() = runBlocking {

        //Given
        val methodPaymentResponse = GetPaymentMethodsUseCaseMock().getInActiveRemotePaymentMethodResponse()
        val expectedResponse = GetPaymentMethodsUseCaseMock().getSuccessFilteredExpectedResponse()

        coEvery { paymentSelectionRepositoryImpl.getPaymentMethodsFromRemote() } returns Resource.Success(methodPaymentResponse)

        // When
        val useCaseResponse = getPaymentMethodsUseCase.getAllRemotePaymentMethod()

        // Then
        coVerify(exactly = 1) { paymentSelectionRepositoryImpl.getPaymentMethodsFromRemote() }
        assertThat(useCaseResponse).isEqualTo(Resource.Success(expectedResponse))
    }

    @Test
    fun `GIVEN an action WHEN GetPaymentMethodsUseCase is called THEN returns a Success value if get a success response from repository`() = runBlocking {

        //Given
        val methodPaymentResponse = GetPaymentMethodsUseCaseMock().getFullRemotePaymentMethodResponse()
        val expectedResponse = GetPaymentMethodsUseCaseMock().getSuccessFullExpectedResponse()

        coEvery { paymentSelectionRepositoryImpl.getPaymentMethodsFromRemote() } returns Resource.Success(methodPaymentResponse)

        // When
        val useCaseResponse = getPaymentMethodsUseCase.getAllRemotePaymentMethod()

        // Then
        coVerify(exactly = 1) { paymentSelectionRepositoryImpl.getPaymentMethodsFromRemote() }
        assertThat(useCaseResponse).isEqualTo(Resource.Success(expectedResponse))
    }

}