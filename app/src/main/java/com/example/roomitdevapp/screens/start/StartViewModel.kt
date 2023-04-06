package com.example.roomitdevapp.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomitdevapp.REPOSITORY
import com.example.roomitdevapp.db.NoteDatabase
import com.example.roomitdevapp.db.repository.NoteRealization
import com.example.roomitdevapp.model.NoteModel

class StartViewModel(application: Application) : AndroidViewModel(application) {
    val context = application


    fun initDatabase() {
        val noteDao = NoteDatabase.getInstance(context).getNoteDao()
        REPOSITORY = NoteRealization(noteDao)
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return REPOSITORY.allNotes
    }


}