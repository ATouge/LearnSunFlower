package com.touge.learnsunflower.adapter

import android.text.SpannableStringBuilder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.italic
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.touge.learnsunflower.R

/**
 * @Author Touge
 * @Date 2020/4/20 20:33
 * @Description
 */
@BindingAdapter("app:imageFromUrl")
fun imageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }
}

@BindingAdapter("app:goneIf")
fun goneIf(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("app:wateringText")
fun wateringText(textView: TextView, waterInterval: Int) {
    val resources = textView.context.resources
    val quantityString = resources.getQuantityString(R.plurals.watering_needs_suffix,
            waterInterval, waterInterval)
    textView.text = SpannableStringBuilder()
            .bold { append(resources.getString(R.string.watering_needs_prefix)) }
            .append(" ")
            .italic { append(quantityString) }
}