package thothlib.mobile.thothlib_mobile_app.services

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import thothlib.mobile.thothlib_mobile_app.infoClass.*

interface ThothLibs {

    @GET("/biblioteca/{idLivro}")
    fun getLivro(@Path("idLivro") idLivro:Int) : Call<Livro>

    @GET("/aluno")
    fun getStudants() : Call<Array<Studant>>

    @POST("/aluno")
    fun registerUser(@Body newUser: NewUser) : Call<Void>

    @POST("/autenticacao/{email}/{senha}")
    fun autentication(@Path("email") email:String, @Path("senha") senha:String) : Call<Int>

    @POST("/biblioteca/{idAdmin}")
    fun postBook(@Path("idAdmin") idAdmin:Int, @Body newBook: AddNewBook) : Call<Void>

    @GET("/aluno/{idUsuario}")
    fun getUsuario(@Path("idUsuario") idUserPerfil:Int) : Call<User>

    @GET("admin/{idAdmin}")
    fun getAdmin(@Path("idAdmin") idAdmin: Int) : Call<User>

    @DELETE("/autenticacao/{id}")
    fun logoutUser(@Path("id") id: Int) : Call<Void>

    companion object {

        var BASE_URL = "http://18.214.213.57:8090/"

        fun criar() : ThothLibs {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ThothLibs::class.java)
        }
    }
}