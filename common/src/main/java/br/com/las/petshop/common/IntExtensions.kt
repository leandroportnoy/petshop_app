package br.com.las.petshop.common

import android.content.Context
import androidx.core.text.HtmlCompat

fun String.stripHtmlOut(): String =
    HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
        .replace("\\s+$".toRegex(), "")

