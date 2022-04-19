package thothlib.mobile.thothlib_mobile_app

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ThothLibs {
    @POST("livros")
    fun post(@Body novoLivro: AddNewBook): Call<Void>

    // Ajustar link

    companion object {
        var BASE_URL = "https://5f861cfdc8a16a0016e6aacd.mockapi.io/bandtec-api/"
        fun criar(): ThothLibs {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ThothLibs::class.java)
        }
    }
}