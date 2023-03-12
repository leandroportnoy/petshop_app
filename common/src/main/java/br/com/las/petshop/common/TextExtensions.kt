package br.com.las.petshop.common

fun shortDescription(text: String) = if (text.length > 30) "${text.subSequence(0,29)}..." else text
