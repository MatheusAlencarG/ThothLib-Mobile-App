package thothlib.mobile.thothlib_mobile_app.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.ReservedBook
import thothlib.mobile.thothlib_mobile_app.infoClass.Studant
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs

class QRCodeFragment : Fragment() {

    lateinit var id: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qr_code, container, false)

        id = requireActivity().getSharedPreferences("idUser", AppCompatActivity.MODE_PRIVATE)

        val reservedBook = arguments?.getSerializable("reservedBooksDetail") as ReservedBook

        if (reservedBook.status == "RESERVADO") {
            view.findViewById<TextView>(R.id.withdraw_title).text = getString(R.string.text_screen_retirada)
            view.findViewById<TextView>(R.id.withdraw_description).text = getString(R.string.qr_code_description_withdraw)
        } else {
            view.findViewById<TextView>(R.id.withdraw_title).text = getString(R.string.text_screen_devolucao)
            view.findViewById<TextView>(R.id.withdraw_description).text = getString(R.string.qr_code_description_devolution)
        }

        renderImage(view)

        view.findViewById<Button>(R.id.qr_code_go_back_btn).setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_pages, UserPerfilFragment::class.java, null)
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    fun renderImage(view: View) {
        val idUsuario = id.getInt("id", 0)
        val reservedBook = arguments?.getSerializable("reservedBooksDetail") as ReservedBook

        val urlImage =
            """https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl={"id":$idUsuario,"tombo":"${reservedBook.tombo}","localization":"${reservedBook.infoPrateleira}","status":"${reservedBook.status}"}"""
        val QRCodeImageView = view.findViewById<ImageView>(R.id.qr_code_image_qr_code)

        Picasso.with(view.context).load(urlImage).into(QRCodeImageView)
    }

}