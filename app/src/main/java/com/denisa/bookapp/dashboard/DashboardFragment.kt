package com.denisa.bookapp.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denisa.bookapp.MainViewModel
import com.denisa.bookapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment()  {

    lateinit var viewModel: MainViewModel
    private lateinit var auth: FirebaseAuth

    val TAG = this::class.java.name

    // todo: create detail fragment in similar way
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.fragment_dashboard, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(recycler_view) {
            adapter = BooksAdapter().apply {
                listOfBooks.add(Book("Hello", "Angelina"))
                listOfBooks.add(Book(title = "Title2", author = "Denisa"))
            }
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(BookItemDecorator(context.resources.getDimensionPixelSize(R.dimen.book_item_margin)))
        }

        fabAddBook.setOnClickListener {

        }
    }
}

