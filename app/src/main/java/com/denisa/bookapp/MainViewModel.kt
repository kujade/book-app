package com.denisa.bookapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denisa.bookapp.model.Book

class MainViewModel: ViewModel() {

    val listOfBooksLiveData = MutableLiveData<ArrayList<Book>>()

    init {
        val list = arrayListOf<Book>()
        listOfBooksLiveData.value = list
    }

    fun addBook(newBook: Book) {
        val currentList = listOfBooksLiveData.value
        currentList?.add(newBook)
        listOfBooksLiveData.value = currentList
    }


}