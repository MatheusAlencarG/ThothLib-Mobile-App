package thothlib.mobile.thothlib_mobile_app.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import thothlib.mobile.thothlib_mobile_app.R

class ExtendBookPopupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_extend_book_popup, container, false)

        return view
    }

}