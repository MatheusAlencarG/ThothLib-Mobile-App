package thothlib.mobile.thothlib_mobile_app.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import thothlib.mobile.thothlib_mobile_app.Browser
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.Studant

class UserPopupFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val closePopup = view.findViewById<ImageView>(R.id.close_popup)

        closePopup?.setOnClickListener {
            (requireActivity() as Browser).closePopup(this)
        }
    }
}