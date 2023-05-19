package com.example.noteapp.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notesEntity: NotesEntity)

    @Update
    suspend fun update(notesEntity: NotesEntity)

    @Delete
    suspend fun delete(notesEntity: NotesEntity)

    @Query("SELECT * FROM notesTable ORDER BY id ASC")
    fun getAllNotes() : LiveData<List<NotesEntity>>
}