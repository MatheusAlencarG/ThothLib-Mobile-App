package thothlib.mobile.thothlib_mobile_app.infoClass

import java.io.Serializable

data class Studant(
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
    val statusAtivo: Boolean,
    val dataInativacao: String?,
    val qtdReservadosAgora: String?
) : Serializable