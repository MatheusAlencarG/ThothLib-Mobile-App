package thothlib.mobile.thothlib_mobile_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPerfilFragment : Fragment() {

    lateinit var tvNome: TextView
    lateinit var tvCpf: TextView
    lateinit var tvEmail: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_perfil, container, false)

        tvNome = view.findViewById(R.id.tv_user_name)
        tvCpf = view.findViewById(R.id.tv_user_cpf)
        tvEmail = view.findViewById(R.id.tv_user_email)

        consultarUsuario()
        renderBookCards()

        return view
    }

    fun consultarUsuario() {
        // id do luis
        val id = 2

        val getUsuario = ThothLibs.criar("usuario").getUsuario(id)

        // Callback do pacote retrofit2
        getUsuario.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) { // se o status da resposta é 2xx
                    val usuario = response.body()
                    tvNome.text = "${usuario?.nome}"
                    tvCpf.text = "${usuario?.cpf}"
                    tvEmail.text = "${usuario?.email}"
                } else {
                    Toast.makeText(context, "Não encontrado", Toast.LENGTH_SHORT).show()
                }
                println("${response} response")
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun renderBookCards() {
        val transaction = (this.context as AppCompatActivity).supportFragmentManager.beginTransaction()

        var i = 3

        while (i >= 0) {
            Toast.makeText(activity, "uashduash", Toast.LENGTH_SHORT).show()

            transaction.add(R.id.ll_book_card, BookCardFragment::class.java, null)
            --i
        }

        transaction.commit()
    }
}