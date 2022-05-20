package com.example.luismunoz.paymentapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.luismunoz.paymentapp.captureValues
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.usecase.GetAllAvailableFeeByIssuerIdUseCase
import com.example.luismunoz.paymentapp.viewmodel.mock.FeeSelectionViewModelMock
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FeeSelectionViewModelTest {

    @MockK
    private lateinit var getAllAvailableFeeByIssuerIdUseCase: GetAllAvailableFeeByIssuerIdUseCase

    private lateinit var feeSelectionViewModel: FeeSelectionViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        feeSelectionViewModel = FeeSelectionViewModel(getAllAvailableFeeByIssuerIdUseCase)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN a amountValue, paymentMethodId and issuerId value WHEN getAllFeeObserver is triggered THEN emit a Loading and an Error value if get an exception from repository`() = runTest {

        // Given
        val exception = Exception()
        coEvery { getAllAvailableFeeByIssuerIdUseCase.getAllAvailableFeeByIssuerId(any(), any(), any()) }.throws(exception)

        // When
        feeSelectionViewModel.getAllFeeObserver("15000", "argencard", "4")

        // Then
        feeSelectionViewModel.getAllFeeObserver("15000", "argencard", "4").captureValues {
            assertThat(arrayListOf(Resource.Loading, Resource.Error(exception))).isEqualTo(values)
        }
    }

    @Test
    fun `GIVEN a amountValue, paymentMethodId and issuerId value WHEN getAllFeeObserver is triggered THEN emit a Loading and an Error value if get an Error response from repository`() = runTest {

        // Given
        val exception = Exception()
        coEvery { getAllAvailableFeeByIssuerIdUseCase.getAllAvailableFeeByIssuerId(any(), any(), any()) } returns Resource.Error(exception)

        // When
        feeSelectionViewModel.getAllFeeObserver("15000", "argencard", "4")

        // Then
        feeSelectionViewModel.getAllFeeObserver("15000", "argencard", "4").captureValues {
            assertThat(arrayListOf(Resource.Loading, Resource.Error(exception))).isEqualTo(values)
        }
    }

    @Test
    fun `GIVEN a amountValue, paymentMethodId and issuerId value WHEN getAllFeeObserver is triggered THEN emit a Loading and a Success value if get a Success response from repository`() = runTest {

        // Given
        val expectedResponse = FeeSelectionViewModelMock().getExpectedDataFeeResponse()
        coEvery { getAllAvailableFeeByIssuerIdUseCase.getAllAvailableFeeByIssuerId(any(), any(), any()) } returns Resource.Success(expectedResponse)

        // When
        feeSelectionViewModel.getAllFeeObserver("15000", "argencard", "4")

        // Then
        feeSelectionViewModel.getAllFeeObserver("15000", "argencard", "4").captureValues {
            assertThat(arrayListOf(Resource.Loading, Resource.Success(expectedResponse))).isEqualTo(values)
        }
    }

}