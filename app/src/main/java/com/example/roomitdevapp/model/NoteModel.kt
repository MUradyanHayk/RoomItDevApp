package com.example.roomitdevapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "_note_table")
class NoteModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    var title: String = "",
    @ColumnInfo
    var description: String = "",
):java.io.Serializable