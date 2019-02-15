package au.com.digio.glidecropper

import java.io.Serializable

class Product(
        val imageRes: Int,
        val title: String,
        val description: String,
        val colour: Int
) : Serializable
