package thothlib.mobile.thothlib_mobile_app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.infoClass.Livro
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.GoogleBook
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs
import java.lang.ClassCastException

class InfoLivroFragment : Fragment() {

    private lateinit var listener: OnBookInfoBookSelected

    lateinit var googleBook: GoogleBook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info_livro, container, false)
        googleBook = arguments?.getSerializable("searchBookDetail") as GoogleBook

        consultarLivro(view, googleBook)
        bodyConstructor(view)

        view.findViewById<Button>(R.id.reserve_button_info_livro).setOnClickListener {
            listener.selectInfoBook(googleBook)
        }

        return view
    }

    interface OnBookInfoBookSelected {
        fun selectInfoBook(googleBook: GoogleBook)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnBookInfoBookSelected) {
            listener = context
        } else {
            throw ClassCastException("$context must implemented")
        }
    }

    fun bodyConstructor(view: View) {

        view.findViewById<TextView>(R.id.info_book_title).text = googleBook.title
        view.findViewById<TextView>(R.id.tv_autor).text = googleBook.autor
        view.findViewById<TextView>(R.id.tv_avaliacao).text = googleBook.avaliacao
        view.findViewById<TextView>(R.id.tv_description).text = googleBook.description
        view.findViewById<TextView>(R.id.tv_data_public).text = googleBook.publishedDate.replace("-", "/")

        val imageViewInfoBook = view.findViewById<AppCompatImageView>(R.id.info_book_image)
        if (googleBook.thumbnail.isEmpty()) {
            imageViewInfoBook.setImageResource(R.mipmap.fotoindisponivel)
        } else {
            Picasso.with(view.context).load(googleBook.thumbnail).into(imageViewInfoBook)
        }
    }

    fun consultarLivro(view: View, googleBook: GoogleBook) {
        val id = googleBook.idLivro

        val getLivro = ThothLibs.criar().getLivro(id)

        getLivro.enqueue(object : Callback<Livro> {
            override fun onResponse(call: Call<Livro>, response: Response<Livro>) {
                if (response.isSuccessful) {
                    val livro = response.body()
                    view.findViewById<TextView>(R.id.tv_prateleira).text = livro?.infoPrateleira
                } else {
                    Toast.makeText(context, "Livro não encontrado, entrar em contato", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Livro>, t: Throwable) {
                Toast.makeText(context, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}