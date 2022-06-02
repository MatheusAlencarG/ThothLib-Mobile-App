package thothlib.mobile.thothlib_mobile_app.infoClass

import java.io.Serializable

data class GoogleBook(
    val idLivro: Int,
    val title: String,
    val publishedDate: String,
    val description: String,
    val amount: Double,
    val capa: String,
    val smallThumbnail: String,
    val thumbnail: String,
    val autor: String,
    val disponivel: String,
    val avaliacao: String,
    val forSale: String
) : Serializable