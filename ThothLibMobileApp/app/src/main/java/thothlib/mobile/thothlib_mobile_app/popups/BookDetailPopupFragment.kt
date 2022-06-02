package thothlib.mobile.thothlib_mobile_app.popups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import thothlib.mobile.thothlib_mobile_app.Browser
import thothlib.mobile.thothlib_mobile_app.fragments.QRCodeFragment
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.ReservedBook

class BookDetailPopupFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_detail_popup, container, false)

        val booksDetail = arguments?.getSerializable("reservedBooksDetail") as ReservedBook


        view.findViewById<TextView>(R.id.popupTitle).text = booksDetail.titulo
        if (booksDetail.qtdEstoque <= 0) {
            view.findViewById<TextView>(R.id.popupStatus2).text = getString(R.string.unavailable)
            view.findViewById<TextView>(R.id.popupStatus2).setTextColor(resources.getColor(R.color.red))
        }
        view.findViewById<TextView>(R.id.popupLocation2).text = "${getString(R.string.prateleira_book)} ${booksDetail.infoPrateleira}"

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val closePopup = view.findViewById<ImageView>(R.id.close_popup)
        val devolutionButton = view.findViewById<Button>(R.id.devolution_button_popup)

        closePopup?.setOnClickListener {
            (requireActivity() as Browser).closePopup(this)
        }

        devolutionButton?.setOnClickListener {
            val trasaction = requireActivity().supportFragmentManager.beginTransaction()
            trasaction.replace(R.id.fragment_pages, QRCodeFragment::class.java, null)
            trasaction.addToBackStack(null)
            trasaction.commit()
        }
    }
}