package com.example.noteapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.db.NotesEntity

class NotesAdapter(val context: Context,
                   val noteClickDeleteInterface: NoteClickDeleteInterface,
                   val noteClickInterface: NoteClickInterface) : RecyclerView.Adapter<NotesAdapter.Viewholder>() {

    private val notesModelList = ArrayList<NotesEntity>()

    interface NoteClickDeleteInterface{
        fun onDeleteIconClick(notesEntity: NotesEntity)
    }

    interface NoteClickInterface{
        fun onNoteClick(notesEntity: NotesEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_notes,parent,false)
        return Viewholder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.textViewNoteName.text = notesModelList[position].noteTitle
        holder.textViewTimestamp.text = "Last updated : " + notesModelList[position].timestamp

        holder.imageViewDelete.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(notesModelList[position])
        }

        holder.itemView.setOnClickListener{
            noteClickInterface.onNoteClick(notesModelList[position])
        }

    }

    override fun getItemCount(): Int {
        return notesModelList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newNotesList : List<NotesEntity>){
        notesModelList.clear()
        notesModelList.addAll(newNotesList)
        notifyDataSetChanged()
    }

    class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val textViewNoteName : TextView = itemView.findViewById(R.id.tv_note_title)
        val textViewTimestamp : TextView = itemView.findViewById(R.id.tv_note_updated_timestamp)
        val imageViewDelete : ImageView = itemView.findViewById(R.id.iv_delete)

    }



}