package thothlib.mobile.thothlib_mobile_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class UserPerfil : AppCompatActivity() {

    lateinit var sideBar: LinearLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_perfil)

        sideBar = findViewById(R.id.side_bar);

    }

    fun toglleSideBar(V: View) {
        val sideBarParam = sideBar.layoutParams as ViewGroup.MarginLayoutParams;


        if (sideBarParam.leftMargin == 0) {
            sideBarParam.setMargins(-990,0,0,0);
            sideBar.layoutParams = sideBarParam
        } else {
            sideBarParam.setMargins(0,0,0,0);
            sideBar.layoutParams = sideBarParam
        }
    }
}