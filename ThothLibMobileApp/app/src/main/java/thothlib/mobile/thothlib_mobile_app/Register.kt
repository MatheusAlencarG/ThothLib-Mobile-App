package thothlib.mobile.thothlib_mobile_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun browsePage(botao: View) {
        val loginScreen = Intent(this, Login::class.java)

        when (botao.id) {
            R.id.cadastrar -> startActivity(loginScreen)
            R.id.logar -> startActivity(loginScreen)
        }
    }




}