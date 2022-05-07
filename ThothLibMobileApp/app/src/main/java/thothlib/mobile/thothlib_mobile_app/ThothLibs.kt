package thothlib.mobile.thothlib_mobile_app

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ThothLibs {

    @GET("{idLivro}")
    fun getLivro(@Path("idLivro") idLivro:Int) : Call<Livro>

    @POST("/aluno")
    fun registerUser(@Body newUser:NewUser) : Call<Void>

    @POST("{email}/{senha}")
    fun autentication(@Path("email") email:String, @Path("senha") senha:String) : Call<Void>

    @POST("{idAdmin}")
    fun postBook(@Path("idAdmin") idAdmin:Int, @Body newBook: AddNewBook) : Call<Void>

    @GET("{idUsuario}")
    fun getUsuario(@Path("idUsuario") idUserPerfil:Int) : Call<User>

    companion object {

        var BASE_URL = "http://18.214.213.57:8080/"

        fun criar(complementBase_URL:String) : ThothLibs {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("${BASE_URL}${complementBase_URL}/")
                .build()
            return retrofit.create(ThothLibs::class.java)
        }
    }
}