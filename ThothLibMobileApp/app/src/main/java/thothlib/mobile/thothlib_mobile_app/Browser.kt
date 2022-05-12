package thothlib.mobile.thothlib_mobile_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class Browser : AppCompatActivity() {

    lateinit var sideBar:LinearLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        sideBar = findViewById(R.id.side_bar);

        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.replace(R.id.fragment_pages, UserPerfilFragment::class.java, null)
        trasaction.commit()
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
            R.id.info_livro -> infoBookFragment()
        }
    }

    fun userPerfilFragment() {
        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.replace(R.id.fragment_pages, UserPerfilFragment::class.java, null)
        trasaction.commit()
        toglleSideBar(null)
    }

    fun searchBookFragment() {
        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.replace(R.id.fragment_pages, SeachBookFragment::class.java, null)
        trasaction.commit()
        toglleSideBar(null)
    }

    fun contactUsFragment() {
        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.replace(R.id.fragment_pages, ContactFragment::class.java, null)
        trasaction.commit()
        toglleSideBar(null)
    }

    fun questionsFragment() {
        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.replace(R.id.fragment_pages, QuestionFragment::class.java, null)
        trasaction.commit()
        toglleSideBar(null)
    }

    fun listUserFragment() {
        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.replace(R.id.fragment_pages, ListUserFragment::class.java, null)
        trasaction.commit()
        toglleSideBar(null)
    }

    fun addBookFragment() {
        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.replace(R.id.fragment_pages, AddBookFragment::class.java, null)
        trasaction.commit()
        toglleSideBar(null)
    }

    fun infoBookFragment() {
        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.replace(R.id.fragment_pages, InfoLivroFragment::class.java, null)
        trasaction.commit()
        toglleSideBar(null)
    }

}