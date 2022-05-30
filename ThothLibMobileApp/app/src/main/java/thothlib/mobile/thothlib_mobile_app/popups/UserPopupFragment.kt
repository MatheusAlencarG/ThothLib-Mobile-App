package thothlib.mobile.thothlib_mobile_app.popups

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.Browser
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.Studant
import thothlib.mobile.thothlib_mobile_app.infoClass.User
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs

class UserPopupFragment : Fragment() {

    lateinit var id: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = requireActivity().getSharedPreferences("idUser", AppCompatActivity.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_popup, container, false)

        val studant = arguments?.getSerializable("userDetail") as Studant

        view.findViewById<TextView>(R.id.user_detail_name).text = studant.nome
        view.findViewById<TextView>(R.id.user_detail_email).text = studant.email
        if (!studant.statusAtivo) {
            view.findViewById<TextView>(R.id.user_detail_status).text = getString(R.string.status_user_inactive)
        }

        getBookOfUser(view, studant.id)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val closePopup = view.findViewById<ImageView>(R.id.close_popup)

        closePopup?.setOnClickListener {
            (requireActivity() as Browser).closePopup(this)
        }
    }

    fun getBookOfUser(v:View, idUser:Int) {

        val getUsuario = ThothLibs.criar().getUsuario(idUser)

        getUsuario.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val book = response.body()?.livrosLidos

                    if (book?.size!! <= 0 ) return

                    view?.findViewById<TextView>(R.id.user_detail_reserved_book)?.text = book.last().titulo
                    view?.findViewById<TextView>(R.id.user_detail_when_reserved)?.text = book.last().linguagem
                    view?.findViewById<TextView>(R.id.user_detail_when_give_back)?.text = book.last().corEtiqueta
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}