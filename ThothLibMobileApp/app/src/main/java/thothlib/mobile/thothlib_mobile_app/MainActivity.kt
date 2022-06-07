package thothlib.mobile.thothlib_mobile_app

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import thothlib.mobile.thothlib_mobile_app.activitys.Login

class MainActivity : AppCompatActivity() {
    lateinit var id:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id = getSharedPreferences("idUser", AppCompatActivity.MODE_PRIVATE)
        verificarLogin(id)
    }

    fun browsePage(V: View) {
        val registerScreen = Intent(this, Login::class.java)
        startActivity(registerScreen)
    }

    fun verificarLogin(id: SharedPreferences?) {
        if (id?.getInt("id", 0) != 0) {
            val registerScreen = Intent(this, Browser::class.java)
            startActivity(registerScreen)
        }
    }

}
