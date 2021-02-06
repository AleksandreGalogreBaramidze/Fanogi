package com.example.fanogi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MusicAdapter(private val musics: List<Music>) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.musicCover)
        val musicTitle: TextView = itemView.findViewById(R.id.musicTitle)
        val musicAuthor: TextView = itemView.findViewById(R.id.musicAuthor)
        val coverURL: TextView = itemView.findViewById(R.id.URL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.music_item, parent, false);

        return MusicViewHolder(itemView)
    }

    override fun getItemCount(): Int = musics.size

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val p = musics[position]

        holder.coverURL.text = p.image
        holder.musicTitle.text = p.title
        holder.musicAuthor.text = p.author
        Glide.with(holder.imageView.getContext())
                .load(p.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView)

    }
}