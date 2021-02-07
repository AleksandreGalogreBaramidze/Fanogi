package com.example.fanogi

import android.content.Context

class Music {
    var image = ""
    var title = ""
    var author = ""
    var link = ""

    constructor(image: String, title: String, author: String, link: String){
        this.image = image;
        this.title = title;
        this.author = author;
        this.link = link;
    }
}