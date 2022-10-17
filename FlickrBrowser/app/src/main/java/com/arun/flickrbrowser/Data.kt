package com.arun.flickrbrowser

import android.util.Log
import java.io.*

class Data(
    var title: String,
    var author: String,
    var authorId: String,
    var link: String,
    var tags: String,
    var image: String
) : Serializable{

    companion object{
        private const val serialObjectUID = 1L
    }

    override fun toString(): String {
        return "Data(title: $title, author: $author, authorId: $authorId, link: $link, tags: $tags, image: $image"
    }

    @Throws(IOException::class)
    private fun writeObject(out: ObjectOutputStream){
        out.writeUTF(title)
        out.writeUTF(author)
        out.writeUTF(authorId)
        out.writeUTF(link)
        out.writeUTF(image)
        out.writeUTF(tags)
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(inStream: ObjectInputStream){
        title = inStream.readUTF()
        author = inStream.readUTF()
        authorId = inStream.readUTF()
        link = inStream.readUTF()
        image = inStream.readUTF()
        tags = inStream.readUTF()
    }

    @Throws(ObjectStreamException::class)
    private fun readObjectNoData(){
        Log.d("Serial", "No Data Found")
    }
}