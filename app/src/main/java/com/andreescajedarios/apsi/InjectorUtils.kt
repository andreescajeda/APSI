package com.andreescajedarios.apsi

import android.content.Context
import com.andreescajedarios.apsi.history.SearchStringDatabase
import com.andreescajedarios.apsi.history.SearchStringRepository
import com.andreescajedarios.apsi.history.SearchStringViewModelFactory

object InjectorUtils {

    fun provideSearchStringViewModelFactory(context: Context) : SearchStringViewModelFactory {
        val searchStringRepository = SearchStringRepository.getInstance(SearchStringDatabase.getInstance(context).searchStringDao())

        return SearchStringViewModelFactory(searchStringRepository)
    }
}