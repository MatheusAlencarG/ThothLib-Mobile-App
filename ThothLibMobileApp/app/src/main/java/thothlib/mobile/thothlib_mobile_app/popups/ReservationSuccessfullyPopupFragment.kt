package thothlib.mobile.thothlib_mobile_app.popups

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import thothlib.mobile.thothlib_mobile_app.Browser
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.GoogleBook
import java.lang.ClassCastException

class ReservationSuccessfullyPopupFragment : Fragment() {

    private lateinit var listener: OnReserveBookSuccessSelected

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservation_successfully_popup, container, false)
        val googleBook = arguments?.getSerializable("reserveBookSuccessDetail") as GoogleBook

        val imageViewInfoBookPopup = view.findViewById<ImageView>(R.id.popupImage)
        if (googleBook.thumbnail.isEmpty()) {
            imageViewInfoBookPopup.setImageResource(R.mipmap.fotoindisponivel)
        } else {
            Picasso.with(view.context).load(googleBook.thumbnail).into(imageViewInfoBookPopup)
        }

        view.findViewById<Button>(R.id.go_to_perfil_btn).setOnClickListener{
            listener.reserveBookSuccessSelected(googleBook)
        }

        return view
    }

    interface OnReserveBookSuccessSelected {
        fun reserveBookSuccessSelected(googleBook: GoogleBook)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnReserveBookSuccessSelected) {
            listener = context
        } else {
            throw ClassCastException("$context must implemented")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val closePopup = view.findViewById<ImageView>(R.id.close_popup)

        closePopup?.setOnClickListener {
            (requireActivity() as Browser).closePopup(this)
        }
    }
}