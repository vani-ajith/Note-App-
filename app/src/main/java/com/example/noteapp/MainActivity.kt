package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.adapter.NotesAdapter
import com.example.noteapp.db.NotesEntity
import com.example.noteapp.viewmodel.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() ,NotesAdapter.NoteClickDeleteInterface,NotesAdapter.NoteClickInterface {


    private lateinit var floatingActionBtn : FloatingActionButton
    private lateinit var recyclerViewNotes: RecyclerView
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var arrayListNotes: ArrayList<NotesEntity>
    private lateinit var notesViewModel: NotesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionBtn = findViewById(R.id.fa_add);
        floatingActionBtn.setOnClickListener{
            val intent = Intent(this,AddNewNotes::class.java);
            startActivity(intent)
        }

        arrayListNotes = ArrayList()


        recyclerViewNotes = findViewById(R.id.rv_notes)
        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        notesAdapter = NotesAdapter(this,this,this)
        recyclerViewNotes.adapter = notesAdapter
        notesViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NotesViewModel::class.java]
        notesViewModel.allNotes.observe(this, Observer { list ->

            list?.let {
                notesAdapter.updateList(it)
            }
        })

    }

    override fun onDeleteIconClick(notesEntity: NotesEntity) {
        notesViewModel.deleteNote(notesEntity)
        Toast.makeText(this, " ${notesEntity.noteTitle} is deleted",Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(notesEntity: NotesEntity) {
        val intent = Intent(this,AddNewNotes::class.java);
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",notesEntity.noteTitle)
        intent.putExtra("noteDescription",notesEntity.noteDescription)
        intent.putExtra("noteId",notesEntity.id)
        startActivity(intent)
        this.finish()
    }
}