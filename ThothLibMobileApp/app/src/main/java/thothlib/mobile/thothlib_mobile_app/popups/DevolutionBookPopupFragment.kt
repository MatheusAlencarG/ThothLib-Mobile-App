package thothlib.mobile.thothlib_mobile_app.popups

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.Browser
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.fragments.QRResponseErrorFragment
import thothlib.mobile.thothlib_mobile_app.fragments.QRResponseSuccessFragment
import thothlib.mobile.thothlib_mobile_app.fragments.UserPerfilFragment
import thothlib.mobile.thothlib_mobile_app.infoClass.ReservedBook
import thothlib.mobile.thothlib_mobile_app.infoClass.Studant
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs
import java.lang.ClassCastException

class DevolutionBookPopupFragment : Fragment() {

    private lateinit var listener: OnBookDevolution

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_devolution_book_popup, container, false)

        val reservedBook = arguments?.getSerializable("reservedBooksDetail") as ReservedBook

        view.findViewById<TextView>(R.id.popupTitle).text = reservedBook.titulo
        view.findViewById<TextView>(R.id.popupLocation2).text = reservedBook.infoPrateleira

        view.findViewById<Button>(R.id.devolution_button_popup).setOnClickListener {
            listener.bookDevolution(reservedBook)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnBookDevolution) {
            listener = context
        } else {
            throw ClassCastException("$context must implemented")
        }
    }

    interface OnBookDevolution {
        fun bookDevolution(reservedBook: ReservedBook)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val closePopup = view.findViewById<ImageView>(R.id.close_popup)

        closePopup?.setOnClickListener {
            (requireActivity() as Browser).closePopup(this)
        }
    }
}