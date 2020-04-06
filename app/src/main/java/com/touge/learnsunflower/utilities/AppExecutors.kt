@file:JvmName("AppExecutors")

package com.touge.learnsunflower.utilities

import java.util.concurrent.Executors


/**
 * @Author Touge
 * @Date 2020/4/6 16:49
 * @Description 单一线程池
 *
 */

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun runOnIoThread(f: Runnable) {
    IO_EXECUTOR.execute(f)
}

