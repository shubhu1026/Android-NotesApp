package com.example.notesapp.UI.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.room.Database
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentAddDetailsBinding
import java.lang.String.format

import java.util.*

class AddDetailsFragment : Fragment() {

    lateinit var binding: FragmentAddDetailsBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddDetailsBinding.inflate(layoutInflater, container, false)

        binding.saveNoteBtn.setOnClickListener {
            createNote(it)
        }

        return binding.root
    }

    private fun createNote(it: View) {
        val titleText = binding.titleTextfield.text
        val descriptionText = binding.descriptionTextfield.text
        val title = titleText.toString()
        val description = descriptionText.toString()
        val d = Date()
        val noteDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.getTime())

        val note = Notes(
            null,
            title = title,
            description = description,
            date = noteDate.toString()
        )

        viewModel.addNotes(note)

        Toast.makeText(context, "Your Note has been saved.", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_addDetailsFragment_to_home2)

    }
}