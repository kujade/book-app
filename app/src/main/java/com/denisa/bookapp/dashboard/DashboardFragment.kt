package com.denisa.bookapp.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denisa.bookapp.MainViewModel
import com.denisa.bookapp.R
import com.denisa.bookapp.detail.DetailFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    private lateinit var auth: FirebaseAuth

    val TAG = this::class.java.name

    // todo: create detail fragment in similar way
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_dashboard, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(recycler_view) {
            adapter = BooksAdapter().apply {
                //two different ways of possible set up
                listOfBooks.add(Book("Elantris", "Brandon Sanderson"))
                listOfBooks.add(Book(title = "Dark Matter", author = "Blake Crouch"))
                listOfBooks.add(Book(title = "Children of Time", author = "Adrien Tchaikovski"))
            }
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(BookItemDecorator(context.resources.getDimensionPixelSize(R.dimen.book_item_margin)))
        }

        fabAddBook.setOnClickListener {
            activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.mainContent, DetailFragment())
                ?.commit()
        }
    }
}

