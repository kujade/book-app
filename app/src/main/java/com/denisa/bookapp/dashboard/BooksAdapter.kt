package com.denisa.bookapp.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denisa.bookapp.R
import com.denisa.bookapp.model.Book
import kotlinx.android.synthetic.main.book_item.view.*

class BooksAdapter: RecyclerView.Adapter<BooksAdapter.BookVH>() {

    val listOfBooks = arrayListOf<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookVH =
        BookVH(LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false))

    override fun getItemCount() = listOfBooks.size

    override fun onBindViewHolder(holder: BookVH, position: Int) {
        val book = listOfBooks[position]

        with(holder.itemView) {
            book_title.text = book.title
            book_author.text = book.author
            book_genre.text = book.genre
        }
    }

        class BookVH(view: View) : RecyclerView.ViewHolder(view)
    }