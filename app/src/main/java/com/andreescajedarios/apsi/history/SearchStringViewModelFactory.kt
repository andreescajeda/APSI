package com.andreescajedarios.apsi.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andreescajedarios.apsi.history.SearchStringRepository

class SearchStringViewModelFactory(private val searchStringRepository: SearchStringRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchStringViewModel(searchStringRepository) as T
    }
}