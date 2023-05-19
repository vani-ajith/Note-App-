package com.example.noteapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.db.NotesEntity
import com.example.noteapp.viewmodel.NotesViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class AddNewNotes : AppCompatActivity() {

    private lateinit var textInputEditTextNoteTitle: TextInputEditText
    private lateinit var textInputEditTextNoteDescription: TextInputEditText
    private lateinit var materialButtonSaveNoteDetails: MaterialButton
    private lateinit var viewModel: NotesViewModel
    private var noteID = -1


    @SuppressLint("MissingInflatedId", "SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_notes)

        textInputEditTextNoteTitle = findViewById(R.id.editText_NoteTitle)
        textInputEditTextNoteDescription = findViewById(R.id.editText_noteDesc)
        materialButtonSaveNoteDetails = findViewById(R.id.button_save_note_details)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NotesViewModel::class.java]
        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("", -1)
            materialButtonSaveNoteDetails.text = "Update note"
            textInputEditTextNoteTitle.setText(noteTitle)
            textInputEditTextNoteDescription.setText(noteDescription)
        } else {
            materialButtonSaveNoteDetails.text = "Save note"
        }

        materialButtonSaveNoteDetails.setOnClickListener {

            val noteTitle = textInputEditTextNoteTitle.text.toString()
            val noteDescription = textInputEditTextNoteDescription.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val updateNote = NotesEntity(noteTitle, noteDescription, currentDate)
                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated....", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addNote(NotesEntity(noteTitle, noteDescription, currentDate))
                    Toast.makeText(this, "Note Added....", Toast.LENGTH_LONG).show()

                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }
    }
}