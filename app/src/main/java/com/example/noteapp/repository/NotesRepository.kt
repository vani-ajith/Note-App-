package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.db.NotesEntity
import com.example.noteapp.db.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    val allNotesEntity : LiveData<List<NotesEntity>> = notesDao.getAllNotes()

    suspend fun insert(notesEntity: NotesEntity){
        notesDao.insert(notesEntity)
    }

    suspend fun update(notesEntity: NotesEntity){
        notesDao.update(notesEntity)
    }

    suspend fun delete(notesEntity: NotesEntity){
        notesDao.delete(notesEntity)
    }
}