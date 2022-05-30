package thothlib.mobile.thothlib_mobile_app.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.R
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

        val urlImage = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl={id:1,tombo:2}"
        val QRCodeImageView = view.findViewById<ImageView>(R.id.qr_code_image_qr_code)

        Picasso.with(view.context).load(urlImage).into(QRCodeImageView)

        view.findViewById<Button>(R.id.qr_code_go_back_btn).setOnClickListener{
            val trasaction = requireActivity().supportFragmentManager.beginTransaction()
            trasaction.replace(R.id.fragment_pages, UserPerfilFragment::class.java, null)
            trasaction.addToBackStack(null)
            trasaction.commit()
        }

        return view
    }

    fun reserveBook() {
        val idUsuario = id.getInt("id", 0)

        val reserveBook = ThothLibs.criar().reserveBook(idUsuario, "222222")

        reserveBook.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {

                } else {
                    Toast.makeText(
                        context,
                        "Erro interno, entre em contato com o suporte",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}