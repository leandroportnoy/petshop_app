package br.com.las.petshop.features

import br.com.las.petshop.data.data.Item

fun List<Item>.concat() = this.joinToString("; ") { "${it.id} - ${it.description}" }
