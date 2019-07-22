package com.denisa.bookapp.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.denisa.bookapp.MainViewModel
import com.denisa.bookapp.R
import com.denisa.bookapp.Utils
import com.denisa.bookapp.model.Book
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_detail, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        setUpSpinner()
        create_button.setOnClickListener {
            val newBook = Book(
                title = edit_name.text.toString(),
                author = edit_author.text.toString(),
                genre = spinner_genre.selectedItem.toString()
            )
            viewModel.addBook(newBook)
            removeFragment()
            activity?.let { Utils.hideKeyboard(it) }
        }


    }

    private fun removeFragment() {
        activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(this)
            ?.commit()
    }



    private fun setUpSpinner() {
        val spinnerListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedGenre = parent?.getItemAtPosition(position)
                Log.i("Denisa", selectedGenre.toString())
            }

        }

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.genres_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner_genre.adapter = adapter
            }
        }

        spinner_genre.onItemSelectedListener = spinnerListener
    }
}