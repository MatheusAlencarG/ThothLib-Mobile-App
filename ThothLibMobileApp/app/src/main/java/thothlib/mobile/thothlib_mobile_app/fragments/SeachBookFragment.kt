package thothlib.mobile.thothlib_mobile_app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Downloader
import com.squareup.picasso.Picasso
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.GoogleBook
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeachBookFragment : Fragment() {

    private lateinit var listener: OnSearchBookListSelected

    var googleBookList: ArrayList<GoogleBook> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_seach_book, container, false)

        view.findViewById<LinearLayout>(R.id.search_books_google).setOnClickListener {
            getGoogleBooks(view)
        }

        return view
    }

    fun getGoogleBooks(view: View) {
        val searchBarrValue = view.findViewById<EditText>(R.id.search_bar_books).text.toString()

        val getGoogleBooks = ThothLibs.criar().getGoogleBooks(searchBarrValue)

        getGoogleBooks.enqueue(object : Callback<Array<GoogleBook>> {
            override fun onResponse(
                call: Call<Array<GoogleBook>>,
                response: Response<Array<GoogleBook>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()

                    if (googleBookList.isNotEmpty()) googleBookList.clear()

                    body?.forEach { googleBook ->
                        googleBookList.add(googleBook)
                    }

                    val activity = activity as Context
                    val recyclerView = view.findViewById<RecyclerView>(R.id.book_card_recycler_item)
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = GoogleBookListAdapter()

                } else {
                    Toast.makeText(context, "Nenhum aluno cadastrado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Array<GoogleBook>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Erro na API", Toast.LENGTH_SHORT).show()
            }
        })
    }

    internal inner class GoogleBookListAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
            ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.book_card_item, viewGroup, false
                )
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val googleBook = GoogleBook(
                googleBookList[position].idLivro,
                googleBookList[position].title,
                googleBookList[position].publishedDate,
                googleBookList[position].description,
                googleBookList[position].amount,
                googleBookList[position].capa,
                googleBookList[position].smallThumbnail,
                googleBookList[position].thumbnail,
                googleBookList[position].autor,
                googleBookList[position].disponivel,
                googleBookList[position].avaliacao,
                googleBookList[position].forSale
            )

            viewHolder.bind(googleBook)
            viewHolder.itemView.findViewById<Button>(R.id.search_book_button)
                .setOnClickListener {
                    listener.OnSearchBookSelected(googleBook)
                }
        }

        override fun getItemCount() = googleBookList.size

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnSearchBookListSelected) {
            listener = context
        } else {
            throw ClassCastException("$context must implemented")
        }
    }

    internal inner class ViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(googleBook: GoogleBook) {
            val imageViewBookCard = itemView.findViewById<AppCompatImageView>(R.id.search_book_image)

            itemView.findViewById<AppCompatTextView>(R.id.search_book_title).text = googleBook.title

            if (googleBook.thumbnail.isEmpty()) {
                imageViewBookCard.setImageResource(R.mipmap.fotoindisponivel)
            } else {
                Picasso.with(itemView.context).load(googleBook.thumbnail).into(imageViewBookCard)
            }
        }
    }

    interface OnSearchBookListSelected {
        fun OnSearchBookSelected(googleBook: GoogleBook)
    }

}