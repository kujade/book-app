package com.denisa.bookapp.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_dashboard, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        Log.i("Denisa", "On view created")
        with(recycler_view) {
            adapter = BooksAdapter()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(BookItemDecorator(context.resources.getDimensionPixelSize(R.dimen.book_item_margin)))
        }

        fabAddBook.setOnClickListener {
            activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.mainContent, DetailFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        viewModel.listOfBooksLiveData.observe(this, Observer { books ->
            with((recycler_view.adapter as BooksAdapter).listOfBooks) {
                clear()
                addAll(books)
                Log.i("Denisa", "live data observer called")
                recycler_view.adapter?.notifyDataSetChanged()
            }
        })
    }
}

//TODO:
// 1) add empty list image (svg) and hide it when recyclerview has values
// 2) add onClickListener to BookAdapter and open Detail Fragment with selected book and update existing item in arraylist

