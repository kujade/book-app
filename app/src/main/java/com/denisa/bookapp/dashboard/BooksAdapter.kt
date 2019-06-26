package com.denisa.bookapp.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.denisa.bookapp.R
import kotlinx.android.synthetic.main.book_item.view.*

class BooksAdapter: RecyclerView.Adapter<BooksAdapter.BookVH>() {

    val listOfBooks = arrayListOf<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookVH =
        BookVH(LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false))

    override fun getItemCount() = listOfBooks.size

    override fun onBindViewHolder(holder: BookVH, position: Int) {
        val book = listOfBooks[position]

        holder.title.text = book.title
        holder.author.text= book.author
        holder.genre.text=book.genre


    }

    class BookVH(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.book_title
        val author: TextView = view.book_author
        val genre : TextView = view.book_genre
    }
}