package com.example.roomitdevapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomitdevapp.model.NoteModel

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteModel: NoteModel)

    @Delete
    suspend fun delete(noteModel: NoteModel)

    @Query("SELECT * FROM _note_table")
    fun getAllNotes(): LiveData<List<NoteModel>>
}