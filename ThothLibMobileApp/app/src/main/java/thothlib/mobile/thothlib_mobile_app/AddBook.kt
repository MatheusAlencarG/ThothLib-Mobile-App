package thothlib.mobile.thothlib_mobile_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Response

class AddBook : AppCompatActivity() {

    lateinit var etTitulo: EditText
    lateinit var etAutor: EditText
    lateinit var etEditora: EditText
    lateinit var etEdicao: EditText
    lateinit var etDescricao: EditText
    lateinit var etQuantidade: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        etTitulo = findViewById(R.id.et_titulo)
        etAutor = findViewById(R.id.et_autor)
        etEditora = findViewById(R.id.et_editora)
        etEdicao = findViewById(R.id.et_edicao)
        etDescricao = findViewById(R.id.et_descricao)
        etQuantidade = findViewById(R.id.et_quantidade)
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

        val postLivro = ThothLibs.criar("biblioteca").postBook(2, novoLivro)

        postLivro.enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(baseContext, "Livro Cadastrado!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Erro / ${response.errorBody()} / ${response.message()} / ${response.body()}", Toast.LENGTH_SHORT)
                            .show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(baseContext, "Erro na API", Toast.LENGTH_SHORT).show()
            }
        })
    }
}