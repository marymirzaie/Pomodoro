package com.mmb.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

data class Dispatcher(
    val iO: CoroutineDispatcher,
    val main: CoroutineDispatcher,
)
