package com.example.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.noteapp.db.NoteDatabase
import com.example.noteapp.db.NotesEntity
import com.example.noteapp.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<NotesEntity>>
    val repository : NotesRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getAllNotesDao()
        repository = NotesRepository(dao)
        allNotes = repository.allNotesEntity
    }

    fun deleteNote(notesEntity: NotesEntity ) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(notesEntity)
    }

    fun updateNote(notesEntity: NotesEntity ) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(notesEntity)
    }

    fun addNote(notesEntity: NotesEntity ) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(notesEntity)
    }
}