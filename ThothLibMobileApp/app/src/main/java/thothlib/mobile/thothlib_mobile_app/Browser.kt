package thothlib.mobile.thothlib_mobile_app

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.activitys.Login
import thothlib.mobile.thothlib_mobile_app.fragments.*
import thothlib.mobile.thothlib_mobile_app.infoClass.GoogleBook
import thothlib.mobile.thothlib_mobile_app.infoClass.ReservedBook
import thothlib.mobile.thothlib_mobile_app.infoClass.Studant
import thothlib.mobile.thothlib_mobile_app.infoClass.User
import thothlib.mobile.thothlib_mobile_app.popups.BookDetailPopupFragment
import thothlib.mobile.thothlib_mobile_app.popups.UserPopupFragment
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs

class Browser : AppCompatActivity(),
    ListUserFragment.OnListSelected,
    UserPerfilFragment.OnBookListSelected,
    SeachBookFragment.OnSearchBookListSelected {

    lateinit var sideBar:LinearLayout;
    lateinit var id: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        sideBar = findViewById(R.id.side_bar);


        id = getSharedPreferences("idUser", AppCompatActivity.MODE_PRIVATE)

        userIsAdmin()

        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.replace(R.id.fragment_pages, UserPerfilFragment::class.java, null)
        trasaction.commit()

        setColorOnSideBar(findViewById(R.id.user_perfil))
    }

    fun toglleSideBar(V: View?) {
        val sideBarParam = sideBar.layoutParams as ViewGroup.MarginLayoutParams;


        if (sideBarParam.leftMargin == 0) {
            sideBarParam.setMargins(-990,0,0,0);
            sideBar.layoutParams = sideBarParam
        } else {
            sideBarParam.setMargins(0,0,0,0);
            sideBar.layoutParams = sideBarParam
        }
    }

    fun chooseFragment(element:View) {
        when (element.id) {
            R.id.user_perfil -> userPerfilFragment()
            R.id.search_book -> searchBookFragment()
            R.id.contact_us -> contactUsFragment()
            R.id.questions -> questionsFragment()
            R.id.list_user -> listUserFragment()
            R.id.add_book -> addBookFragment()
            R.id.reader_scanner -> readerScanner()
        }
        setColorOnSideBar(element)
    }

    fun setColorOnSideBar(element: View) {
        val listIds: ArrayList<Int> = arrayListOf(
            R.id.user_perfil,
            R.id.search_book,
            R.id.contact_us,
            R.id.questions,
            R.id.list_user,
            R.id.add_book,
            R.id.reader_scanner
        )
        val boxElement = findViewById<LinearLayout>(element.id)

        listIds.forEach { elementId ->
            if (element.id != elementId) {
                findViewById<LinearLayout>(elementId).setBackgroundColor(getColor(R.color.black_color))
            }
        }

        boxElement.setBackgroundColor(getColor(R.color.ligth_blue_color))
    }

    fun userIsAdmin() {

        val idUsuario = id.getInt("id", 0)
        val getAdmin = ThothLibs.criar().getUsuario(idUsuario)

        getAdmin.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.body()?.usuarioAdmin == 1) {
                    findViewById<LinearLayout>(R.id.list_user).visibility = View.VISIBLE;
                    findViewById<LinearLayout>(R.id.add_book).visibility = View.VISIBLE;
                    findViewById<LinearLayout>(R.id.reader_scanner).visibility = View.VISIBLE;
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun logout(v:View) {
        val idUsuario = id.getInt("id", 0)

        val getAdmin = ThothLibs.criar().logoutUser(idUsuario)

        getAdmin.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    loginActivity()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onSelected(studant: Studant) {
        var args = Bundle()
        args.putSerializable("userDetail", studant)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.user_detail_container, UserPopupFragment::class.java, args)
            .addToBackStack(null)
            .commit()

    }

    override fun onBookCardSelected(reservedBook: ReservedBook) {
        var args = Bundle()
        args.putSerializable("reservedBooksDetail", reservedBook)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.book_detail_container, BookDetailPopupFragment::class.java, args)
            .addToBackStack(null)
            .commit()
    }

    override fun OnSearchBookSelected(googleBook: GoogleBook) {
        var args = Bundle()
        args.putSerializable("searchBookDetail", googleBook)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_pages, InfoLivroFragment::class.java, args)
            .addToBackStack(null)
            .commit()
    }

    fun closePopup(v: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .remove(v)
            .addToBackStack(null)
            .commit()
    }

    fun userPerfilFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_pages, UserPerfilFragment::class.java, null)
            .addToBackStack(null)
            .commit()
        toglleSideBar(null)
    }

    fun searchBookFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_pages, SeachBookFragment::class.java, null)
            .addToBackStack(null)
            .commit()
        toglleSideBar(null)
    }

    fun contactUsFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_pages, ContactFragment::class.java, null)
            .addToBackStack(null)
            .commit()
        toglleSideBar(null)
    }

    fun questionsFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_pages, QuestionFragment::class.java, null)
            .addToBackStack(null)
            .commit()
        toglleSideBar(null)
    }

    fun listUserFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_pages, ListUserFragment::class.java, null)
            .addToBackStack(null)
            .commit()
        toglleSideBar(null)
    }

    fun addBookFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_pages, AddBookFragment::class.java, null)
            .addToBackStack(null)
            .commit()
        toglleSideBar(null)
    }

    fun readerScanner() {
        IntentIntegrator(this)
            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            .setBeepEnabled(false)
            .initiateScan()
    }

    fun loginActivity() {
        val loginPage = Intent(this, Login::class.java)
        startActivity(loginPage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

}