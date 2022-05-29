package thothlib.mobile.thothlib_mobile_app.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.Livro
import thothlib.mobile.thothlib_mobile_app.infoClass.ReservedBooks
import thothlib.mobile.thothlib_mobile_app.infoClass.Studant
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs
import thothlib.mobile.thothlib_mobile_app.infoClass.User
import java.lang.ClassCastException

class UserPerfilFragment : Fragment() {

    lateinit var tvNome: TextView
    lateinit var tvCpf: TextView
    lateinit var tvEmail: TextView
    lateinit var id:SharedPreferences
    private lateinit var listener: OnBookListSelected
    var reservedBooks: ArrayList<ReservedBooks> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = requireActivity().getSharedPreferences("idUser", AppCompatActivity.MODE_PRIVATE)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_perfil, container, false)

        tvNome = view.findViewById(R.id.tv_user_name)
        tvCpf = view.findViewById(R.id.tv_user_cpf)
        tvEmail = view.findViewById(R.id.tv_user_email)

        consultarUsuario(view)


        return view
    }

    fun consultarUsuario(v: View) {

        val idUsuario = id.getInt("id", 0)

        val getUsuario = ThothLibs.criar().getUsuario(idUsuario)

        getUsuario.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val usuario = response.body()
                    val body = response.body()

                    tvNome.text = "${usuario?.nome}"
                    tvCpf.text = "${usuario?.cpf}"
                    tvEmail.text = "${usuario?.email}"

                    if (reservedBooks.isNotEmpty()) reservedBooks.clear()

                    body?.livrosLidos?.forEach { book ->
                        reservedBooks.add(book)
                    }

                    val activity = activity as Context
                    val recyclerView = v.findViewById<RecyclerView>(R.id.book_recycler_view)
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = UserBookListAdapter()

                } else {
                    Toast.makeText (context, "Não encontrado", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    internal inner class UserBookListAdapter : RecyclerView.Adapter<UserPerfilFragment.ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
            ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.book_card_user_item, viewGroup, false
                )
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val userWithBooks = ReservedBooks(
                reservedBooks[position].id,
                reservedBooks[position].titulo,
                reservedBooks[position].descricao,
                reservedBooks[position].autor,
                reservedBooks[position].edicao,
                reservedBooks[position].editora,
                reservedBooks[position].infoPrateleira,
                reservedBooks[position].qtdResenhas,
                reservedBooks[position].qtdReservadosAgora,
                reservedBooks[position].qtdDisponiveis,
                reservedBooks[position].qtdEstoque,
                reservedBooks[position].linguagem,
                reservedBooks[position].corEtiqueta,
                reservedBooks[position].qtdReservadosTotal
                )

            viewHolder.bind(userWithBooks)
            viewHolder.itemView.findViewById<Button>(R.id.ll_book_card_button)
                .setOnClickListener {
                    listener.onBookCardSelected(userWithBooks)
                }
        }

        override fun getItemCount() = reservedBooks.size

    }

    internal inner class ViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(reservedBooks: ReservedBooks) {
            itemView.findViewById<AppCompatTextView>(R.id.book_content_title).text = reservedBooks.titulo
        }
    }

    interface OnBookListSelected {
        fun onBookCardSelected(reservedBooks: ReservedBooks)
    }

}