package com.example.luismunoz.paymentapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.luismunoz.paymentapp.util.EMPTY_STRING_VALUE
import com.example.luismunoz.paymentapp.util.ValidateAmountStatus
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
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
class AmountEnterViewModelTest {

    private lateinit var amountEnterViewModel: AmountEnterViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        amountEnterViewModel = AmountEnterViewModel()
        Dispatchers.setMain(Dispatchers.Default)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN a different than 0 amount value WHEN AmountEnterViewModel is called THEN return VALID_AMOUNT status`() = runTest {

        // Given
        val amountStringValue = 1500

        // When
        amountEnterViewModel.validateAmountInput(amountStringValue)

        // Then
        val statusResponse = amountEnterViewModel.amountValidate.value?.getContentIfNotHandled()
        assertThat(statusResponse).isEqualTo(ValidateAmountStatus.VALID_AMOUNT)
    }

    @Test
    fun `GIVEN a 1500001 amount value WHEN AmountEnterViewModel is called THEN return MAX_AMOUNT status`() {

        // Given
        val amountStringValue = 1500001

        // When
        amountEnterViewModel.validateAmountInput(amountStringValue)

        // Then
        val statusResponse = amountEnterViewModel.amountValidate.value?.getContentIfNotHandled()
        assertThat(statusResponse).isEqualTo(ValidateAmountStatus.MAX_AMOUNT)
    }

    @Test
    fun `GIVEN a 0 amount value WHEN AmountEnterViewModel is called THEN return MIN_AMOUNT status`() {

        // Given
        val amountStringValue = 0

        // When
        amountEnterViewModel.validateAmountInput(amountStringValue)

        // Then
        val statusResponse = amountEnterViewModel.amountValidate.value?.getContentIfNotHandled()
        assertThat(statusResponse).isEqualTo(ValidateAmountStatus.MIN_AMOUNT)
    }

}