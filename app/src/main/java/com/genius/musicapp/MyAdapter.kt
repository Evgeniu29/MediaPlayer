package com.genius.musicapp

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.musicplayer.ViewModel.DataViewModel
import com.genius.musicapp.data.Data
import com.squareup.picasso.Picasso

class MyAdapter(val context: Context, var dataList: List<Data>)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private lateinit var dataViewModel: DataViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)

        return MyViewHolder(itemView);
    }


    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData = dataList[position]
        holder.title.text = currentData.title

        Picasso.get().load(currentData.album.cover_medium).into(holder.image);

        val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())

        holder.play.setOnClickListener {
            mediaPlayer.start()
        }
        holder.pause.setOnClickListener {
            mediaPlayer.stop()
        }

        holder.like.setOnClickListener {

        }
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image : ImageView
        val title : TextView
        val play:  ImageButton
        val pause: ImageButton
        val like : ImageButton

        init
        {
            image = itemView.findViewById(R.id.musicImage)
            title = itemView.findViewById(R.id.musicTitle)
            play  = itemView.findViewById(R.id.btnPlay)
            pause = itemView.findViewById(R.id.btnPause)
            like =  itemView.findViewById(R.id.favourite)

        }
    }

    fun setData(dataList: ArrayList<Data>)
    {
        this.dataList=dataList

        notifyDataSetChanged()
    }




    }


