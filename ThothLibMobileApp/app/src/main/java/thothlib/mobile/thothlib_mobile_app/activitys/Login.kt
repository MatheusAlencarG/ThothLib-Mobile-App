package thothlib.mobile.thothlib_mobile_app.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.Browser
import thothlib.mobile.thothlib_mobile_app.MainActivity
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout


class Login : AppCompatActivity() {

    lateinit var etEmail:EditText
    lateinit var etSenha:EditText
    lateinit var tvAuthResponse:TextView
    lateinit var id:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.et_email)
        etSenha = findViewById(R.id.et_senha)
        tvAuthResponse = findViewById(R.id.tv_auth_Response)

        id = getSharedPreferences("idUser", AppCompatActivity.MODE_PRIVATE)
        verificarLogin(id)
    }

    fun verificarLogin(id: SharedPreferences?) {
        if (id?.getInt("id", 0) != 0) {
            browsePage(findViewById(R.id.entrar));
        }
    }

    fun autentication(v:View) {
        val email = etEmail.text.toString()
        val senha = etSenha.text.toString()
        var idUser:SharedPreferences
        val getAutentication = ThothLibs.criar().autentication(email, senha)

        getAutentication.enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                idUser = getSharedPreferences("idUser", MODE_PRIVATE)

                if (response.isSuccessful) {
                    val editor = idUser.edit()
                    response.body()?.let { editor.putInt("id", it) }
                    editor.commit()

                    browsePage(findViewById(R.id.entrar));
                } else {
                    tvAuthResponse.setVisibility(View.VISIBLE)
                    tvAuthResponse.text = getString(R.string.auth_response)
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(baseContext, "Erro na API", Toast.LENGTH_SHORT).show()
                tvAuthResponse.setVisibility(View.VISIBLE)
                tvAuthResponse.text = getString(R.string.error_API)
            }
        })
    }

    fun browsePage(botao: View) {
        val registerScreen = Intent(this, Register::class.java)
        val perfilScreen = Intent(this, Browser::class.java)
        val homeScreen = Intent(this, MainActivity::class.java)

        when (botao.id) {
            R.id.entrar -> startActivity(perfilScreen)
            R.id.cadastrar -> startActivity(registerScreen)
            R.id.home -> startActivity(homeScreen)
        }

    }

}