package thothlib.mobile.thothlib_mobile_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.infoClass.AddNewBook
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs

class AddBookFragment : Fragment() {

    lateinit var etTitulo: EditText
    lateinit var etAutor: EditText
    lateinit var etEditora: EditText
    lateinit var etEdicao: EditText
    lateinit var etDescricao: EditText
    lateinit var etQuantidade: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_book, container, false)
        etTitulo = view.findViewById(R.id.et_titulo)
        etAutor = view.findViewById(R.id.et_autor)
        etEditora = view.findViewById(R.id.et_editora)
        etEdicao = view.findViewById(R.id.et_edicao)
        etDescricao = view.findViewById(R.id.et_descricao)
        etQuantidade = view.findViewById(R.id.et_quantidade)

        val registerBookButton: Button = view.findViewById(R.id.register_book)

        registerBookButton.setOnClickListener {
            salvarLivro(view)
        }

        return view
    }

    fun salvarLivro(V: View) {

        val novoLivro = AddNewBook(
            etAutor.text.toString(),
            etDescricao.text.toString(),
            etEdicao.text.toString(),
            etEditora.text.toString(),
            etQuantidade.text.toString().toInt(),
            etTitulo.text.toString()
        )

        val postLivro = ThothLibs.criar().postBook(2, novoLivro)

        postLivro.enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Livro Cadastrado!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Erro / ${response.errorBody()} / ${response.message()} / ${response.body()}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Erro na API", Toast.LENGTH_SHORT).show()
            }
        })
    }
}