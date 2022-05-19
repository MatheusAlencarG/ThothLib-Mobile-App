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
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.NewUser
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs

class Register : AppCompatActivity() {

    lateinit var etNome:EditText
    lateinit var etCPF:EditText
    lateinit var etEmail:EditText
    lateinit var etPhone:EditText
    lateinit var etPassword:EditText
    lateinit var tvRegisterResponse:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNome = findViewById(R.id.et_name)
        etCPF = findViewById(R.id.et_cpf)
        etEmail = findViewById(R.id.et_email)
        etPhone = findViewById(R.id.et_phone)
        etPassword = findViewById(R.id.et_password)
        tvRegisterResponse = findViewById(R.id.tv_register_response)
    }

    fun registerUser(v:View) {
        val newUser = NewUser(
            etNome.text.toString(),
            etCPF.text.toString(),
            etEmail.text.toString(),
            etPhone.text.toString(),
            etPassword.text.toString()
        )


        val postUser = ThothLibs.criar().registerUser(newUser)
        // Toast.makeText(baseContext, "${newUser}${postUser.request()}", Toast.LENGTH_SHORT).show()

        postUser.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    browsePage(findViewById(R.id.logar));
                    Toast.makeText(baseContext, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Tetse ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na API", Toast.LENGTH_SHORT).show()
                tvRegisterResponse.setVisibility(View.VISIBLE)
                tvRegisterResponse.text = getString(R.string.error_API)
            }

        })

    }

    fun browsePage(botao: View) {
        val loginScreen = Intent(this, Login::class.java)

        when (botao.id) {
            R.id.cadastrar -> startActivity(loginScreen)
            R.id.logar -> startActivity(loginScreen)
        }
    }

}