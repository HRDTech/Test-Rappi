package com.solucioneshr.soft.testrappi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solucioneshr.soft.testrappi.R
import com.solucioneshr.soft.testrappi.data.ConfigApp
import com.solucioneshr.soft.testrappi.data.Movie
import com.squareup.picasso.Picasso


class MovieAdapter(var listener: MovieFragment): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var data : ArrayList<Movie>?=null

    interface MovieListener{
        fun onItemDeleted(movie: Movie, position: Int)
    }

    fun setData(list: ArrayList<Movie>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_model, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
        /*holder.itemView.img_delete.setOnClickListener {
            item?.let { it1 ->
                listener.onItemDeleted(it1, position)
            }
        }*/
    }


    fun addData(postModel: Movie) {
        data?.add(0,postModel)
        notifyItemInserted(0)
    }

    fun removeData(position: Int) {
        data?.removeAt(position)
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(item: Movie?) {
            itemView.findViewById<TextView>(R.id.textItemTitle).text = item?.title
            itemView.findViewById<TextView>(R.id.textItemBody).text  = item?.release_date
            itemView.findViewById<TextView>(R.id.textRand).text = item?.popularity?.toUInt().toString()
            var urlImg = ConfigApp.IMGURL + item?.poster_path
            Picasso.get().load(urlImg).into(itemView.findViewById<ImageView>(R.id.imgPoster))
        }

    }
}