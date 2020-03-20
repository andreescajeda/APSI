package com.andreescajedarios.apsi.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class SearchString(
    @PrimaryKey val searchString: String
)