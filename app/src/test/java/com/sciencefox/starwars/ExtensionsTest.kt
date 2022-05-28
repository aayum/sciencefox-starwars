package com.sciencefox.starwars

import android.view.View
import com.nhaarman.mockitokotlin2.verify
import com.sciencefox.starwars.extensions.hide
import com.sciencefox.starwars.extensions.show
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExtensionsTest {

    @Mock
    lateinit var view : View

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testViewShow() {
        view.show()
        verify(view).visibility = View.VISIBLE
    }

    @Test
    fun testViewHide() {
        view.hide()
        verify(view).visibility = View.GONE
    }
}