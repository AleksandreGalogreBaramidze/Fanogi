package com.example.fanogi

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.internal.ContextUtils.getActivity


class MusicAdapter(private val musics: List<Music>) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.musicCover)
        val playBut: ImageView = itemView.findViewById(R.id.playButton)
        val musicTitle: TextView = itemView.findViewById(R.id.musicTitle)
        val musicAuthor: TextView = itemView.findViewById(R.id.musicAuthor)
        val coverURL: TextView = itemView.findViewById(R.id.URL)
        val link: TextView = itemView.findViewById(R.id.musicLink)

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
        holder.link.text = p.link

        Glide.with(holder.imageView.getContext())
                .load(p.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView)

        holder.playBut.setOnClickListener {
            holder.playBut.getContext().startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("${p.link}")))
        }
    }
}