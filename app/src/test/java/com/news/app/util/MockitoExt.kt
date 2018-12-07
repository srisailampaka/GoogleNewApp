package com.news.app.util

import org.mockito.Mockito
/**
 * Returns Mockito.any() as nullable type to avoid java.lang.IllegalStateException when
 * null is returned.
 */
fun <T> any(): T = Mockito.any<T>()

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
