package com.example.luismunoz.paymentapp.domain.usecase

import com.example.luismunoz.paymentapp.data.source.remote.FeeSelectionRepositoryImpl
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.usecase.mock.GetAllAvailableFeeByIssuerIdUseCaseMock
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllAvailableFeeByIssuerIdUseCaseTest {

    @MockK
    private lateinit var feeSelectionRepositoryImpl: FeeSelectionRepositoryImpl

    private lateinit var getAllAvailableFeeByIssuerIdUseCase: GetAllAvailableFeeByIssuerIdUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAllAvailableFeeByIssuerIdUseCase = GetAllAvailableFeeByIssuerIdUseCase(feeSelectionRepositoryImpl)
    }

    @Test
    fun `GIVEN an amountValue, paymentMethodId and issuerId value WHEN GetAllAvailableFeeByIssuerIdUseCase is triggered THEN returns an Error value if get a Error response from remote repository`() = runBlocking {

        // Given
        val exception = Exception()
        coEvery { feeSelectionRepositoryImpl.getAvailableFeeFromRemote(any(), any(), any()) } returns Resource.Error(exception)

        // When
        val useCaseResponse = getAllAvailableFeeByIssuerIdUseCase.getAllAvailableFeeByIssuerId("1000", "argencard", "4")

        // Then
        coVerify(exactly = 1) { feeSelectionRepositoryImpl.getAvailableFeeFromRemote(any(), any(), any()) }
        assertThat(useCaseResponse).isEqualTo(Resource.Error(exception))
    }

    @Test
    fun `GIVEN an amountValue, paymentMethodId and issuerId value WHEN GetAllAvailableFeeByIssuerIdUseCase is triggered THEN returns a Error value if get null response from remote repository`() = runBlocking {

        // Given
        val repositoryResponse = GetAllAvailableFeeByIssuerIdUseCaseMock().getNullRemoteRepositoryResponse()
        val expectedResponse = Exception()

        coEvery { feeSelectionRepositoryImpl.getAvailableFeeFromRemote(any(), any(), any()) } returns Resource.Success(repositoryResponse)

        // When
        val useCaseResponse = getAllAvailableFeeByIssuerIdUseCase.getAllAvailableFeeByIssuerId("15000", "argencard", "4")

        // Then
        coVerify(exactly = 1) { feeSelectionRepositoryImpl.getAvailableFeeFromRemote(any(), any(), any()) }
        assertThat(useCaseResponse).isInstanceOf(Resource.Error(expectedResponse)::class.java)
    }

    @Test
    fun `GIVEN an amountValue, paymentMethodId and issuerId value WHEN GetAllAvailableFeeByIssuerIdUseCase is triggered THEN returns a Success value if get some empty values`() = runBlocking {

        // Given
        val repositoryResponse = GetAllAvailableFeeByIssuerIdUseCaseMock().getEmptyValuesRemoteRepositoryResponse()
        val expectedResponse = GetAllAvailableFeeByIssuerIdUseCaseMock().getEmptyValuesDataFee()

        coEvery { feeSelectionRepositoryImpl.getAvailableFeeFromRemote(any(), any(), any()) } returns Resource.Success(repositoryResponse)

        // When
        val useCaseResponse = getAllAvailableFeeByIssuerIdUseCase.getAllAvailableFeeByIssuerId("15000", "argencard", "4")

        // Then
        coVerify(exactly = 1) { feeSelectionRepositoryImpl.getAvailableFeeFromRemote(any(), any(), any()) }
        assertThat(useCaseResponse).isEqualTo(Resource.Success(expectedResponse))
    }

    @Test
    fun `GIVEN an amountValue, paymentMethodId and issuerId value WHEN GetAllAvailableFeeByIssuerIdUseCase is triggered THEN returns a Success value if get a Success response from remote repository`() = runBlocking {

        // Given
        val repositoryResponse = GetAllAvailableFeeByIssuerIdUseCaseMock().getFullRemoteRepositoryResponse()
        val expectedResponse = GetAllAvailableFeeByIssuerIdUseCaseMock().getFullDataFee()

        coEvery { feeSelectionRepositoryImpl.getAvailableFeeFromRemote(any(), any(), any()) } returns Resource.Success(repositoryResponse)

        // When
        val useCaseResponse = getAllAvailableFeeByIssuerIdUseCase.getAllAvailableFeeByIssuerId("15000", "argencard", "4")

        // Then
        coVerify(exactly = 1) { feeSelectionRepositoryImpl.getAvailableFeeFromRemote(any(), any(), any()) }
        assertThat(useCaseResponse).isEqualTo(Resource.Success(expectedResponse))
    }

}