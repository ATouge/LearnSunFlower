package com.touge.learnsunflower.utilities

import android.content.Context
import java.nio.charset.Charset

/**
 * @Author Touge
 * @Date 2020/5/6 21:12
 * @Description
 */
fun readJson(
        context: Context,
        fileName: String = "plants.json"
): String {
    val inputStream = context.assets.open(fileName)
    val buffer = ByteArray(inputStream.available())
    inputStream.run {
        read(buffer)
        close()
        return String(buffer, Charset.defaultCharset())
    }
}