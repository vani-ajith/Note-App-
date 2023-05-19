package com.example.noteapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//model class

@Entity(tableName = "notesTable")
class NotesEntity(@ColumnInfo(name = "noteTitle") val noteTitle : String,
                  @ColumnInfo(name = "noteDescription") val noteDescription : String,
                  @ColumnInfo(name = "timestamp") val timestamp: String )

            {

                @PrimaryKey(autoGenerate = true)
                var id = 0

            }