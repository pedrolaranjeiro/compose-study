package com.ecommerce.demo.app.viewmodels

import com.ecommerce.demo.data.models.CartItem
import com.ecommerce.demo.data.models.Product
import com.ecommerce.demo.domain.ClearCartUseCase
import com.ecommerce.demo.domain.GetCartUseCase
import com.ecommerce.demo.domain.SaveProductToCartUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CartViewModelTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    lateinit var saveProductToCart: SaveProductToCartUseCase

    @MockK
    lateinit var getCartUseCase: GetCartUseCase

    @MockK
    lateinit var clearCartUseCase: ClearCartUseCase

    lateinit var cut: CartViewModel


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockKAnnotations.init(this, relaxUnitFun = true)
        cut = CartViewModel(
            saveProductToCart,
            getCartUseCase,
            clearCartUseCase,
            Dispatchers.Unconfined
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `Given a cart with products When get cart The return cart items`() {
        // Given
        val expected = cartList
        every { getCartUseCase.call() } returns flow {
            emit(expected)
        }

        // When
        cut.getCart()

        // Then
        assertEquals(expected, cut.cartItems.value)
        verify { getCartUseCase.call() }
    }

    @Test
    fun `Given a product When add to Cart The cart is updated`() {

        // Given
        val product = mockk<Product>()
        val expected = listOf(
            CartItem(
                id = "id",
                name = "name",
                price = "price",
                quantity = 1
            )
        )
        every { saveProductToCart.call(product) } returns flow {
            emit(expected)
        }

        // When
        cut.addToCart(product)

        // Then
        assertEquals(expected, cut.cartItems.value)
        verify { saveProductToCart.call(product) }
    }

    @Test
    fun `Given a cart with products When clearing the cart The cart is emptied`() {
        // Given
        val product = mockk<Product>()
        val items = cartList
        every { saveProductToCart.call(product) } returns flow {
            emit(items)
        }

        every { clearCartUseCase.call() } returns flow {
            emit(Unit)
        }

        cut.addToCart(product)

        // When

        cut.clearCart()

        // Then
        assertTrue(cut.cartItems.value.isEmpty())
        verify { clearCartUseCase.call() }
    }


    private val cartList = listOf(
        CartItem(
            id = "id",
            name = "name",
            price = "price",
            quantity = 1
        ), CartItem(
            id = "id",
            name = "name",
            price = "price",
            quantity = 1
        ), CartItem(
            id = "id",
            name = "name",
            price = "price",
            quantity = 1
        )
    )

}


