package thothlib.mobile.thothlib_mobile_app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.json.JSONObject
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.ReservedBook

class QRResponseSuccessFragment : Fragment() {

    private lateinit var listener: OnReserveMoreSuccessPage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qr_response_success, container, false)

        val reservedBook = arguments?.getString("qrCodeResponse") as String

        val reservedBookSON = JSONObject(reservedBook)

        if (reservedBookSON.get("status") == "RESERVADO") {
            view.findViewById<TextView>(R.id.title).text = getString(R.string.text_screen_retirada)
            view.findViewById<TextView>(R.id.description).text = getString(R.string.text_screen_sucess_msg_retirada)
        } else {
            view.findViewById<TextView>(R.id.title).text = getString(R.string.text_screen_devolucao)
            view.findViewById<TextView>(R.id.description).text = getString(R.string.text_screen_sucess_msg_devolucao)
        }

        view.findViewById<Button>(R.id.btn_reservar).setOnClickListener {
            listener.reserveMoreSuccessPage()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnReserveMoreSuccessPage) {
            listener = context
        } else {
            throw ClassCastException("$context must implemented")
        }
    }

    interface OnReserveMoreSuccessPage {
        fun reserveMoreSuccessPage()
    }
}