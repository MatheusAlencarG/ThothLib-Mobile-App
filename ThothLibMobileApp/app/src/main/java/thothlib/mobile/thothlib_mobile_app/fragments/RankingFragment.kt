package thothlib.mobile.thothlib_mobile_app.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.RankingUser
import thothlib.mobile.thothlib_mobile_app.infoClass.ReservedBook
import thothlib.mobile.thothlib_mobile_app.infoClass.Studant
import thothlib.mobile.thothlib_mobile_app.infoClass.User
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs

class RankingFragment : Fragment() {
    var rankingUser: ArrayList<RankingUser> = arrayListOf()
    var rankingUserOrdened: List<RankingUser> = arrayListOf()
    var imagensArr: ArrayList<Int> = arrayListOf(
        R.mipmap.medal1,
        R.mipmap.medal2,
        R.mipmap.medal3
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)
        consultarUsuario(view)

        return view
    }

    private fun ordenarUsuarios(view: View?) {
        rankingUserOrdened = rankingUser.sortedByDescending { it.pontos }
    }

    fun consultarUsuario(v: View) {
        val getUsuario = ThothLibs.criar().getUserRanking()

        getUsuario.enqueue(object : Callback<Array<RankingUser>> {
            override fun onResponse(call: Call<Array<RankingUser>>, response: Response<Array<RankingUser>>) {
                if (response.isSuccessful) {
                    val usuario = response.body()

                    if (rankingUser.isNotEmpty()) rankingUser.clear()

                    usuario?.forEach { user ->
                        rankingUser.add(user)
                    }
                    ordenarUsuarios(view)
                    val activity = activity as Context
                    val recyclerView = v.findViewById<RecyclerView>(R.id.user_recycler_view)
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = RankingUserAdapter()

                } else {
                    Toast.makeText (context, "Não encontrado", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Array<RankingUser>>, t: Throwable) {
                Toast.makeText(context, "Erro na API ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    internal inner class RankingUserAdapter : RecyclerView.Adapter<RankingFragment.ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
            ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.user_ranking_item, viewGroup, false
                )
            )

        override fun onBindViewHolder(viewHolder: RankingFragment.ViewHolder, position: Int) {
            var userRanking : RankingUser
            if (position <= 2){
                 userRanking = RankingUser(
                    rankingUserOrdened[position].nome,
                    rankingUserOrdened[position].pontos,
                    imagensArr[position],
                    rankingUserOrdened[position].qtdLivrosLidos
                )
            }else{
                userRanking = RankingUser(
                    rankingUserOrdened[position].nome,
                    rankingUserOrdened[position].pontos,
                    null,
                    rankingUserOrdened[position].qtdLivrosLidos
                )
            }
            viewHolder.bind(userRanking, position)
        }
        override fun getItemCount() = rankingUser.size
    }

    internal inner class ViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(rankingUser: RankingUser, position: Int) {
            itemView.findViewById<AppCompatTextView>(R.id.tv_ranking_userName).text = rankingUser.nome
            itemView.findViewById<AppCompatTextView>(R.id.rankingLvl).text = rankingUser.qtdLivrosLidos.toString()

            if (position <= 2){
                itemView.findViewById<AppCompatImageView>(R.id.user_content_image).setImageResource(
                    rankingUser.imagem!!
                )
            }
        }
    }
}