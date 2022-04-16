package thothlib.mobile.thothlib_mobile_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoLivro : AppCompatActivity() {

    lateinit var tvAutor: TextView
    lateinit var tvPrateleira: TextView
    lateinit var tvDataPubli: TextView
    lateinit var tvAvaliacao: TextView
    lateinit var tvDescricao: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_livro)

        tvAutor = findViewById(R.id.tv_autor)
        tvPrateleira = findViewById(R.id.tv_prateleira)
        tvDataPubli = findViewById(R.id.tv_data_public)
        tvAvaliacao = findViewById(R.id.tv_avaliacao)
        tvDescricao = findViewById(R.id.tv_description)

    }

    fun teste(v: View){

        val id = 2

        // objeto do endpoint de GET /filmes/{id}
        val getLivro = ThothLibs.criar().get(id)

        tvAutor.text = "Mudou"

    }

    fun consultarLivro(v: View) {
        val id = 2

        // objeto do endpoint de GET /filmes/{id}
        val getLivro = ThothLibs.criar().get(id)

        // Callback do pacote retrofit2
        getLivro.enqueue(object : Callback<Livro> {
            override fun onResponse(call: Call<Livro>, response: Response<Livro>) {
                if (response.isSuccessful) { // se o status da resposta é 2xx
                    val livro = response.body()
                    tvAutor.text = "${livro?.autor}"
                    tvPrateleira.text = "${livro?.infoPrateleira}"
                    tvDataPubli.text = "10/08/2022"
                    tvAvaliacao.text = "10/10"
                    tvDescricao.text = "${livro?.descricao}"
                } else {
                    Toast.makeText(baseContext, "Não encontrado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Livro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}