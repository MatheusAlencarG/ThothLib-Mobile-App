package thothlib.mobile.thothlib_mobile_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPerfil : AppCompatActivity() {

    lateinit var sideBar: LinearLayout;
    lateinit var tvNome: TextView
    lateinit var tvCpf: TextView
    lateinit var tvEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_perfil)

        sideBar = findViewById(R.id.side_bar);
        tvNome = findViewById(R.id.tv_user_name)
        tvCpf = findViewById(R.id.tv_user_cpf)
        tvEmail = findViewById(R.id.tv_user_email)

        consultarUsuario()
        renderBookCards()
    }

    fun consultarUsuario() {
        // id do luis
        val id = 2

        val getUsuario = ThothLibs.criar("usuario").getUsuario(id)

        // Callback do pacote retrofit2
        getUsuario.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) { // se o status da resposta é 2xx
                    val usuario = response.body()
                    tvNome.text = "${usuario?.nome}"
                    tvCpf.text = "${usuario?.cpf}"
                    tvEmail.text = "${usuario?.email}"
                } else {
                    Toast.makeText(baseContext, "Não encontrado", Toast.LENGTH_SHORT).show()
                }
                println("${response} response")
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun renderBookCards() {
        val transaction = supportFragmentManager.beginTransaction()
        var i = 3

        while (i >= 0) {
            Toast.makeText(this, "uashduash", Toast.LENGTH_SHORT).show()

            transaction.add(R.id.ll_book_card, BookCardFragment::class.java, null)
            --i
        }

        transaction.commit()
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