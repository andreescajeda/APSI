package com.andreescajedarios.apsi.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

// Observador para "SearchString".
class SearchStringViewModel(private val searchStringRepository: SearchStringRepository) : ViewModel() {

    private val searchStrings: LiveData<MutableList<SearchString>> = searchStringRepository.selectAll()

    fun insert(searchString: SearchString) {
        searchStringRepository.insert(searchString)
    }

    fun selectAll(): LiveData<MutableList<SearchString>> {
        return searchStrings
    }
}