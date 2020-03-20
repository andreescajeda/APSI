package com.andreescajedarios.apsi.history

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class SearchStringRepository(private val searchStringDao: SearchStringDao) {

    companion object {

        @Volatile
        private var instance: SearchStringRepository? = null

        fun getInstance(searchStringDao: SearchStringDao) = instance ?: synchronized(this) {
            instance ?: SearchStringRepository(searchStringDao).also { instance = it }
        }
    }

    fun insert(searchString: SearchString) {
        InsertSearchStringAsyncTask(searchStringDao).execute(searchString)
    }

    fun selectAll() : LiveData<MutableList<SearchString>> {
        return searchStringDao.selectAll()
    }

    class InsertSearchStringAsyncTask internal constructor(private val searchStringDao: SearchStringDao) : AsyncTask<SearchString, Unit, Unit>() {

        override fun doInBackground(vararg searchString: SearchString) {
            searchStringDao.insert(searchString[0])

            return
        }
    }
}