package thothlib.mobile.thothlib_mobile_app.infoClass

data class User (
    val nome: String,
    val cpf: String,
    val email: String,
    val telefone: String,
    val senha: String,
    val usuarioAdmin: Int,
    val pontos: Int,
    val qtdLivrosLidos: Int,
    val qtdResenhas: Int,
    val livrosLidos: List<ReservedBooks>,
)