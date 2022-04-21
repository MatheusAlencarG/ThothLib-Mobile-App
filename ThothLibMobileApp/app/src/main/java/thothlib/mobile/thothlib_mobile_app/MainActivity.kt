package thothlib.mobile.thothlib_mobile_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun browsePage(V: View) {
        val registerScreen = Intent(this, Register::class.java)
        startActivity(registerScreen)
    }


}
