package com.example.luismunoz.paymentapp.domain.usecase

import com.example.luismunoz.paymentapp.data.exception.ServiceException
import com.example.luismunoz.paymentapp.data.source.remote.BankSelectionRepositoryImpl
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.usecase.mock.GetAllBanksByPaymentMethodIdUseCaseMock
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllBanksByPaymentMethodIdUseCaseTest {

    @MockK
    private lateinit var bankSelectionRepositoryImpl: BankSelectionRepositoryImpl

    private lateinit var getAllBanksByPaymentMethodIdUseCase: GetAllBanksByPaymentMethodIdUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAllBanksByPaymentMethodIdUseCase = GetAllBanksByPaymentMethodIdUseCase(bankSelectionRepositoryImpl)
    }

    @Test
    fun `GIVEN a paymentMethodId value WHEN GetAllBanksByPaymentMethodIdUseCase is called THEN returns an Error value if get an error response from remote repository`() = runBlocking {

        // Given
        val exception = Exception()
        coEvery { bankSelectionRepositoryImpl.getBanksFromRemote(any()) } returns Resource.Error(exception)

        // When
        val useCaseResponse = getAllBanksByPaymentMethodIdUseCase.getAllBanksByPaymentMethodId("master")

        // Then
        coVerify(exactly = 1) { bankSelectionRepositoryImpl.getBanksFromRemote(any()) }
        assertThat(useCaseResponse).isEqualTo(Resource.Error(exception))
    }

    @Test
    fun `GIVEN a paymentMethodId value WHEN GetAllBanksByPaymentMethodIdUseCase is called THEN returns an Error value if get null data from remote repository`() = runBlocking {

        // Given
        val remoteServiceResponse = GetAllBanksByPaymentMethodIdUseCaseMock().getNullRemoteBankResponse()
        val expectedResponse = ServiceException()

        coEvery { bankSelectionRepositoryImpl.getBanksFromRemote(any()) } returns Resource.Success(remoteServiceResponse)

        // When
        val useCaseResponse = getAllBanksByPaymentMethodIdUseCase.getAllBanksByPaymentMethodId("master")

        // Then
        coVerify(exactly = 1) { bankSelectionRepositoryImpl.getBanksFromRemote(any()) }
        assertThat(useCaseResponse).isInstanceOf(Resource.Error(expectedResponse)::class.java)
    }

    @Test
    fun `GIVEN a paymentMethodId value WHEN GetAllBanksByPaymentMethodIdUseCase is called THEN returns a Success value if get some inactive values from remote repository`() = runBlocking {

        // Given
        val remoteServiceResponse = GetAllBanksByPaymentMethodIdUseCaseMock().getSuccessRemoteBankResponseWithInactiveValue()
        val expectedResponse = GetAllBanksByPaymentMethodIdUseCaseMock().getSuccessDataBankResponseWithInactiveValues()

        coEvery { bankSelectionRepositoryImpl.getBanksFromRemote(any()) } returns Resource.Success(remoteServiceResponse)

        // When
        val useCaseResponse = getAllBanksByPaymentMethodIdUseCase.getAllBanksByPaymentMethodId("master")

        // Then
        coVerify(exactly = 1) { bankSelectionRepositoryImpl.getBanksFromRemote(any()) }
        assertThat(useCaseResponse).isEqualTo(Resource.Success(expectedResponse))
    }

    @Test
    fun `GIVEN a paymentMethodId value WHEN GetAllBanksByPaymentMethodIdUseCase is called THEN returns a Success value if get some empty values from remote repository`() = runBlocking {

        // Given
        val remoteServiceResponse = GetAllBanksByPaymentMethodIdUseCaseMock().getSuccessRemoteBankResponseWithEmptyValues()
        val expectedResponse = GetAllBanksByPaymentMethodIdUseCaseMock().getSuccessDataBankResponseWithEmptyValues()

        coEvery { bankSelectionRepositoryImpl.getBanksFromRemote(any()) } returns Resource.Success(remoteServiceResponse)

        // When
        val useCaseResponse = getAllBanksByPaymentMethodIdUseCase.getAllBanksByPaymentMethodId("master")

        // Then
        coVerify(exactly = 1) { bankSelectionRepositoryImpl.getBanksFromRemote(any()) }
        assertThat(useCaseResponse).isEqualTo(Resource.Success(expectedResponse))
    }

    @Test
    fun `GIVEN a paymentMethodId value WHEN GetAllBanksByPaymentMethodIdUseCase is called THEN returns a Success value if get valid data from remote repository`() = runBlocking {

        // Given
        val remoteServiceResponse = GetAllBanksByPaymentMethodIdUseCaseMock().getSuccessRemoteBankResponse()
        val expectedResponse = GetAllBanksByPaymentMethodIdUseCaseMock().getFullSuccessDataBankResponse()

        coEvery { bankSelectionRepositoryImpl.getBanksFromRemote(any()) } returns Resource.Success(remoteServiceResponse)

        // When
        val useCaseResponse = getAllBanksByPaymentMethodIdUseCase.getAllBanksByPaymentMethodId("master")

        // Then
        coVerify(exactly = 1) { bankSelectionRepositoryImpl.getBanksFromRemote(any()) }
        assertThat(useCaseResponse).isEqualTo(Resource.Success(expectedResponse))
    }

}