package thothlib.mobile.thothlib_mobile_app.infoClass

data class ReservedBooks (
    val id: Int,
    val titulo: String,
    val descricao: String,
    val autor: String,
    val edicao: String,
    val editora: String,
    val infoPrateleira : String,
    val qtdResenhas: Int,
    val qtdReservadosAgora: Int,
    val qtdDisponiveis: Int,
    val qtdEstoque: Int,
    val fkTbBiblioteca: Int,
    val linguagem: String,
    val corEtiqueta: String,
    val qtdReservadosTotal : Int
)