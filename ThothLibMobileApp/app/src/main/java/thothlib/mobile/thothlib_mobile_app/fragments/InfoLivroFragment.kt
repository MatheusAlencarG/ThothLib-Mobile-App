package thothlib.mobile.thothlib_mobile_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.infoClass.Livro
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs

class InfoLivroFragment : Fragment() {

    lateinit var tvAutor: TextView
    lateinit var tvPrateleira: TextView
    lateinit var tvDataPubli: TextView
    lateinit var tvAvaliacao: TextView
    lateinit var tvDescricao: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info_livro, container, false)

        tvAutor = view.findViewById(R.id.tv_autor)
        tvPrateleira = view.findViewById(R.id.tv_prateleira)
        tvDataPubli = view.findViewById(R.id.tv_data_public)
        tvAvaliacao = view.findViewById(R.id.tv_avaliacao)
        tvDescricao = view.findViewById(R.id.tv_description)

        consultarLivro();

        return view
    }

    fun consultarLivro() {
        val id = 2

        // objeto do endpoint de GET /filmes/{id}
        val getLivro = ThothLibs.criar().getLivro(id)

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
                    Toast.makeText(context, "Não encontrado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Livro>, t: Throwable) {
                Toast.makeText(context, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}