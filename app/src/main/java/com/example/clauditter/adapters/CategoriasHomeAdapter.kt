package com.example.clauditter.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clauditter.MovieDetailsActivity
import com.example.clauditter.R
import com.example.clauditter.ui.clases.MovieList
import com.example.clauditter.Listeners.OnRecyclerClickListener
import com.example.clauditter.Listeners.RecyclerPreviewListener


class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){
    val titleList:TextView=view.findViewById(R.id.lbl_tituloLista)
    val recyclerMovies:RecyclerView=view.findViewById(R.id.recyclerListaHome)
    val context=view.context
}

class CategoriasHomeAdapter(private var ListOfLists:List<MovieList>):
    RecyclerView.Adapter<MovieViewHolder>()
{
    private val TAG="CategoriasHomeAdap"

    //this is called by the layout when a new view is needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.lista_home_recyclerview,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(ListOfLists.isNotEmpty()) ListOfLists.size else 1
    }
    fun loadNewData(newLists:List<MovieList>){
        ListOfLists=newLists
        notifyDataSetChanged()
    }

    fun getList(position:Int):MovieList?{
        return if(ListOfLists.isNotEmpty())ListOfLists[position] else null
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if(ListOfLists.isEmpty()){
        }else{
            val list=ListOfLists[position]
            holder.titleList.text=list.title
            /**RECYCLER ADAPTER */
             val previewAdapter=MoviePreviewAdapter(ArrayList())
            val  horizontalLayout = LinearLayoutManager(
                null,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            holder.recyclerMovies.layoutManager= horizontalLayout
            //TODO NO CRER UN NUEVO ADAPTER, GUARDARLO EN EL HOLDER
            holder.recyclerMovies.adapter=previewAdapter
            //agregamos las acciones de cuando se le de click
            holder.recyclerMovies.addOnItemTouchListener(RecyclerPreviewListener(holder.context,holder.recyclerMovies,previewAdapter))
            previewAdapter.loadNewData(list.moviesToShow)
        }

    }
}
