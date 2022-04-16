package thothlib.mobile.thothlib_mobile_app

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ThothLibs {

    var complmentBASE_URL: String
        get() = "biblioteca/"
        set(value) = TODO()

    @GET("/{idLivro}")
    fun get(@Path("idLivro") idLivro:Int) : Call<Livro>

    companion object {
        var BASE_URL = "https://10.0.2.2:8080/biblioteca/"
        // Para testar via cabo USB numa API local: "http://10.0.2.2:8080/"

        fun criar() : ThothLibs {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ThothLibs::class.java)
        }
    }
}