package thothlib.mobile.thothlib_mobile_app

data class Livro(
  val id:Integer,
  val titulo:String,
  val descricao:String,
  val autor:String,
  val edicao:String,
  val editora:String,
  val infoPrateleira:String,
  val qtdResenhas:Int,
  val qtdReservadosAgora:Int,
  val qtdDisponiveisval:Int,
  val qtdEstoqueval :Int,
  val fkTbBibliotecaval :Int,
  val linguagemval :String,
  val corEtiquetaval :String,
  val qtdReservadosTotal:Int
)
