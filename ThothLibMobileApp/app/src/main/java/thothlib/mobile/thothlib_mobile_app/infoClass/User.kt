package thothlib.mobile.thothlib_mobile_app.infoClass

data class User (
    val id: Int,
    val nome: String,
    val cpf: String,
    val email: String,
    val telefone: String,
    val usuarioAdmin: Int,
    val pontos: Int,
    val qtdLivrosLidos: Int,
    val qtdResenhas: Int,
    val livrosReservados: Int,
    val livrosLidos: List<ReservedBooks>,
)