package com.example.fanogi

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FeedActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed);

        recyclerView = findViewById(R.id.recyclerView)
        val database = FirebaseDatabase.getInstance().reference




        var getData = object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val musics = ArrayList<Music>()
                for (i in snapshot.children){
                    var musicCover = i.child("image").getValue().toString()
                    var musicTitle = i.child("title").getValue().toString()
                    var musicAuthor = i.child("author").getValue().toString()
                    var link = i.child("link").getValue().toString()
                    musics.add(Music(musicCover, musicTitle, musicAuthor, link))
                }

                val adapter = MusicAdapter(musics)
                recyclerView.layoutManager = LinearLayoutManager(this@FeedActivity)
                recyclerView.adapter = adapter
            }

        }

        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)

    }
}