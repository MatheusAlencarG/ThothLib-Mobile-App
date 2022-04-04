package thothlib.mobile.thothlib_mobile_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Login : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun browsePage(botao: View) {
        val registerScreen = Intent(this, Register::class.java)
        val perfilScreen = Intent(this, UserPerfil::class.java)
        val homeScreen = Intent(this, MainActivity::class.java)

        when (botao.id) {
            R.id.entrar -> startActivity(perfilScreen)
            R.id.cadastrar -> startActivity(registerScreen)
            R.id.home -> startActivity(homeScreen)
        }

    }

}