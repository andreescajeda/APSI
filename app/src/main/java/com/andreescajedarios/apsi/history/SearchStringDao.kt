package com.andreescajedarios.apsi.history

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andreescajedarios.apsi.history.SearchString

@Dao
interface SearchStringDao {

    @Insert
    fun insert(searchString: SearchString)

    @Query("SELECT * FROM history")
    fun selectAll(): LiveData<MutableList<SearchString>>
}