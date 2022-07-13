package com.example.notesapp.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Model.Notes
import com.example.notesapp.UI.Fragments.HomeDirections
import com.example.notesapp.databinding.FragmentAddDetailsBinding
import com.example.notesapp.databinding.NoteItemBinding

class NotesAdapter(requireContext: Context, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.notesViewHolder {
        return notesViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesAdapter.notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.cardTitle.text = data.title
        holder.binding.cardDescription.text = data.description
        holder.binding.cardDate.text = data.date

        holder.binding.root.setOnClickListener {

            val action = HomeDirections.actionHome2ToEditFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = notesList.size
}
