package com.denisa.bookapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denisa.bookapp.model.Book

class MainViewModel : ViewModel() {

    var selectedBook: Book? = null
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

    fun getNextId(): Int {
         listOfBooksLiveData.value?.let { list ->
             return if (list.isEmpty()) {
                 // TODO: if list is empty return 1
                 0
             } else {
                 // get max id
                 list.maxBy { it.id }
                 // add 1 and return
                 0
             }
         }
        return 1
    }


}