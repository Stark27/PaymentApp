package com.example.luismunoz.paymentapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.luismunoz.paymentapp.captureValues
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.usecase.GetPaymentMethodsUseCase
import com.example.luismunoz.paymentapp.viewmodel.mock.PaymentSelectionViewModelMock
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
class PaymentSelectionViewModelTest {

    @MockK
    private lateinit var getPaymentMethodsUseCase: GetPaymentMethodsUseCase
    private lateinit var paymentSelectionViewModel: PaymentSelectionViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        paymentSelectionViewModel = PaymentSelectionViewModel(getPaymentMethodsUseCase)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN an action WHEN paymentMethodObserver is triggered THEN emit a Loading and an Error value if get an exception response from useCase `() = runTest {

        // Given
        val expectedResponse = Exception()
        coEvery { getPaymentMethodsUseCase.getAllRemotePaymentMethod() }.throws(expectedResponse)

        // When
        paymentSelectionViewModel.paymentMethodObserver()

        // Then
        paymentSelectionViewModel.paymentMethodObserver().captureValues {
            assertThat(arrayListOf(Resource.Loading, Resource.Error(expectedResponse))).isEqualTo(values)
        }
    }

    @Test
    fun `GIVEN an action WHEN paymentMethodObserver is triggered THEN emit a Loading and an Error value if get an Error response from useCase `() = runTest {

        // Given
        val expectedResponse = Exception()
        coEvery { getPaymentMethodsUseCase.getAllRemotePaymentMethod() } returns Resource.Error(expectedResponse)

        // When
        paymentSelectionViewModel.paymentMethodObserver()

        // Then
        paymentSelectionViewModel.paymentMethodObserver().captureValues {
            assertThat(arrayListOf(Resource.Loading, Resource.Error(expectedResponse))).isEqualTo(values)
        }
    }

    @Test
    fun `GIVEN an action WHEN paymentMethodObserver is triggered THEN emit a Loading and a Success value if get a success response from useCase `() = runTest {

        // Given
        val expectedResponse = PaymentSelectionViewModelMock().getExpectedResponse()
        coEvery { getPaymentMethodsUseCase.getAllRemotePaymentMethod() } returns Resource.Success(expectedResponse)

        // When
        paymentSelectionViewModel.paymentMethodObserver()

        // Then
        paymentSelectionViewModel.paymentMethodObserver().captureValues {
            assertThat(arrayListOf(Resource.Loading, Resource.Success(expectedResponse))).isEqualTo(values)
        }
    }

}