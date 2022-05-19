package thothlib.mobile.thothlib_mobile_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import thothlib.mobile.thothlib_mobile_app.activitys.Login

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun browsePage(V: View) {
        val registerScreen = Intent(this, Login::class.java)
        startActivity(registerScreen)
    }


}
