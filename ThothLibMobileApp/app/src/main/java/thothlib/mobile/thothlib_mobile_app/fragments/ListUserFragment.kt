package thothlib.mobile.thothlib_mobile_app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thothlib.mobile.thothlib_mobile_app.R
import thothlib.mobile.thothlib_mobile_app.infoClass.Studant
import thothlib.mobile.thothlib_mobile_app.services.ThothLibs

class ListUserFragment : Fragment() {

    private lateinit var listener: OnListSelected

    var userStudant: ArrayList<Studant> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_user, container, false)

        this.getStudantsUser(view)

        return view
    }

    fun getStudantsUser(v: View) {

        val getStudants = ThothLibs.criar().getStudants()

        getStudants.enqueue(object : Callback<Array<Studant>> {
            override fun onResponse(call: Call<Array<Studant>>, response: Response<Array<Studant>>) {
                if (response.isSuccessful) {
                    val body = response.body()

                    if (userStudant.isNotEmpty()) userStudant.clear()

                    body?.forEach { studant ->
                        userStudant.add(studant)
                    }

                    val activity = activity as Context
                    val recyclerView = v.findViewById<RecyclerView>(R.id.user_recycler_view)
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = UserListAdapter()

                } else {
                    Toast.makeText(context, "Nenhum aluno cadastrado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Array<Studant>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Erro na API", Toast.LENGTH_SHORT).show()
            }
        })
    }

    internal inner class UserListAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
            ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.user_item, viewGroup, false
                )
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            var dataInativacao: String? = userStudant[position].dataInativacao

            if (dataInativacao == null) {
                dataInativacao = "Sem pendências"
            }

            val studant = Studant(
                userStudant[position].id,
                userStudant[position].nome,
                userStudant[position].cpf,
                userStudant[position].email,
                userStudant[position].telefone,
                userStudant[position].usuarioAdmin,
                userStudant[position].pontos,
                userStudant[position].qtdLivrosLidos,
                userStudant[position].qtdResenhas,
                userStudant[position].livrosReservados,
                userStudant[position].statusAtivo,
                dataInativacao,
                userStudant[position].qtdReservadosAgora
            )
            viewHolder.bind(studant)
            viewHolder.itemView.setOnClickListener {
                listener.onSelected(studant)
            }
        }

        override fun getItemCount() = userStudant.size

    }

    internal inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(studant: Studant) {
            itemView.findViewById<AppCompatTextView>(R.id.user_content_name).text = studant.nome
            itemView.findViewById<AppCompatTextView>(R.id.user_content_email).text = studant.email
            itemView.findViewById<AppCompatTextView>(R.id.user_content_date).text = studant.dataInativacao
        }
    }

    interface OnListSelected {
        fun onSelected(studant: Studant)
    }
}