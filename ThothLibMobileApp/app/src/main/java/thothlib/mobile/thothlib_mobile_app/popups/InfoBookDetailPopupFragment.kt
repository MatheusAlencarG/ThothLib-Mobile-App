package thothlib.mobile.thothlib_mobile_app.popups

import android.content.Context
import android.content.SharedPreferences
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.Browser
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.fragments.InfoLivroFragment
import thothlib.mobile.thothlib_mobile_app.infoClass.GoogleBook
import thothlib.mobile.thothlib_mobile_app.infoClass.Livro
import thothlib.mobile.thothlib_mobile_app.infoClass.Studant
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs
import java.lang.ClassCastException

class InfoBookDetailPopupFragment : Fragment() {

    lateinit var googleBook: GoogleBook
    var listTombo: ArrayList<String> = arrayListOf()
    lateinit var id: SharedPreferences
    private lateinit var listener: OnReserveBookSelected

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = requireActivity().getSharedPreferences("idUser", AppCompatActivity.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info_book_detail_popup, container, false)
        googleBook = arguments?.getSerializable("infoBookPopup") as GoogleBook

        bodyConstructor(view, googleBook)

        view.findViewById<Button>(R.id.devolution_button_popup).setOnClickListener{
            if (listTombo.isNotEmpty()) {
                reserveBook()
            } else {
                Toast.makeText(context, "Livro Indisponível", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
    fun reserveBook() {
        val idUsuario = id.getInt("id", 0)
        val tombo = listTombo.last()

        val getLivro = ThothLibs.criar().reserveBook(idUsuario, tombo)

        getLivro.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    listener.reservedBookSelected(googleBook)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


    }

    fun bodyConstructor(view: View, googleBook: GoogleBook) {
        view.findViewById<TextView>(R.id.popupTitle).text = googleBook.title

        val imageViewInfoBookPopup = view.findViewById<ImageView>(R.id.popupImage)
        if (googleBook.thumbnail.isEmpty()) {
            imageViewInfoBookPopup.setImageResource(R.mipmap.fotoindisponivel)
        } else {
            Picasso.with(view.context).load(googleBook.thumbnail).into(imageViewInfoBookPopup)
        }

        view.findViewById<TextView>(R.id.popupStatus2).text = googleBook.disponivel
        consultarLivro(view, googleBook)
    }

    fun consultarLivro(view: View, googleBook: GoogleBook) {
        val id = googleBook.idLivro

        val getLivro = ThothLibs.criar().getLivro(id)

        getLivro.enqueue(object : Callback<Livro> {
            override fun onResponse(call: Call<Livro>, response: Response<Livro>) {
                if (response.isSuccessful) {
                    val livro = response.body()
                    view.findViewById<TextView>(R.id.popupLocation2).text = livro?.infoPrateleira

                    if(listTombo.isNotEmpty()) listTombo.clear()

                    livro?.tombosDisponiveis?.forEach { tombo ->
                        listTombo.add(tombo)
                    }

                    println("tombosAqui $listTombo")

                } else {
                    Toast.makeText(context, "Livro não encontrado, entrar em contato", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Livro>, t: Throwable) {
                Toast.makeText(context, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val closePopup = view.findViewById<ImageView>(R.id.close_popup)

        closePopup?.setOnClickListener {
            (requireActivity() as Browser).closePopup(this)
        }
    }

    interface OnReserveBookSelected {
        fun reservedBookSelected(googleBook: GoogleBook)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnReserveBookSelected) {
            listener = context
        } else {
            throw ClassCastException("$context must implemented")
        }
    }
}