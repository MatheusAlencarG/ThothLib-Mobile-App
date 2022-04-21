package thothlib.mobile.thothlib_mobile_app

data class AddNewBook(
    val id: Int,
    val titulo: String,
    val autor: String,
    val editora: String,
    val edicao: String,
    val descricao: String,
    val quantidade: Number
)