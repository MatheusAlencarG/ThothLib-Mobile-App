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
    fun registerUser(@Body newUser:User) : Call<Void>

    @POST("{email}/{senha}")
    fun autentication(@Path("email") email:String, @Path("senha") senha:String) : Call<Void>
  
    @POST("livros")
    fun post(@Body novoLivro: AddNewBook): Call<Void>

    companion object {

        var BASE_URL = "http://10.0.2.2:8080/"
        // Para testar via cabo USB numa API local: "http://10.0.2.2:8080/"

        fun criar(complementBase_URL:String) : ThothLibs {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("${BASE_URL}${complementBase_URL}/")
                .build()
            return retrofit.create(ThothLibs::class.java)
        }
    }
}