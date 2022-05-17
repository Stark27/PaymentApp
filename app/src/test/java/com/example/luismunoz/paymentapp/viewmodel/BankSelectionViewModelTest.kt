package com.example.luismunoz.paymentapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.luismunoz.paymentapp.captureValues
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.usecase.GetAllBanksByPaymentMethodIdUseCase
import com.example.luismunoz.paymentapp.viewmodel.mock.BankSelectionViewModelMock
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
class BankSelectionViewModelTest {

    @MockK
    private lateinit var getAllBanksByPaymentMethodIdUseCase: GetAllBanksByPaymentMethodIdUseCase

    private lateinit var bankSelectionViewModel: BankSelectionViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        bankSelectionViewModel = BankSelectionViewModel(getAllBanksByPaymentMethodIdUseCase)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN a paymentMethodId value WHEN BankSelectionViewModel is triggered THEN emit a Loading and an Error value if get an exception response from useCase`() = runTest {

        // Given
        val exception = Exception()
        coEvery { getAllBanksByPaymentMethodIdUseCase.getAllBanksByPaymentMethodId(any()) }.throws(exception)

        // When
        bankSelectionViewModel.getAllBanks("master")

        // Then
        bankSelectionViewModel.bankObserver.captureValues {
            assertThat(arrayListOf(Resource.Loading, Resource.Error(exception))).isEqualTo(values)
        }
    }

    @Test
    fun `GIVEN a paymentMethodId value WHEN BankSelectionViewModel is triggered THEN emit a Loading and an Error value if get an Error response from useCase`() = runTest {

        // Given
        val exception = Exception()
        coEvery { getAllBanksByPaymentMethodIdUseCase.getAllBanksByPaymentMethodId(any()) } returns Resource.Error(exception)

        // When
        bankSelectionViewModel.getAllBanks("master")

        // Then
        bankSelectionViewModel.bankObserver.captureValues {
            assertThat(arrayListOf(Resource.Loading, Resource.Error(exception))).isEqualTo(values)
        }
    }

    @Test
    fun `GIVEN a paymentMethodId value WHEN BankSelectionViewModel is triggered THEN emit a Loading and a Success value if get a success response from useCase`() = runTest {

        // Given
        val useCaseResponse = BankSelectionViewModelMock().getFullDataBankResponse()
        coEvery { getAllBanksByPaymentMethodIdUseCase.getAllBanksByPaymentMethodId(any()) } returns Resource.Success(useCaseResponse)

        // When
        bankSelectionViewModel.getAllBanks("master")

        // Then
        bankSelectionViewModel.bankObserver.captureValues {
            assertThat(arrayListOf(Resource.Loading, Resource.Success(useCaseResponse))).isEqualTo(values)
        }
    }

}