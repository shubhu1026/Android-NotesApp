package com.example.notesapp.UI.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentEditBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditFragment : Fragment() {

    val oldNote by navArgs<EditFragmentArgs>()
    lateinit var binding: FragmentEditBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.editTitleTextfield.setText(oldNote.data.title)
        binding.editDescriptionTextfield.setText(oldNote.data.description)

        binding.saveEditBtn.setOnClickListener {
            updateNote(it)
        }

        return binding.root
    }

    private fun updateNote(it: View?) {

        val titleText = binding.editTitleTextfield.text
        val descriptionText = binding.editDescriptionTextfield.text
        val title = titleText.toString()
        val description = descriptionText.toString()
        val d = Date()
        val noteDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.getTime())

        val note = Notes(
            oldNote.data.id,
            title = title,
            description = description,
            date = noteDate.toString()
        )

        viewModel.updateNotes(note)

        Toast.makeText(context, "Your changes have been saved.", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_home2)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            val bottomSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.delete_dialog)

            val textviewYes = bottomSheet.findViewById<TextView>(R.id.yes_dialog)
            val textviewNo = bottomSheet.findViewById<TextView>(R.id.no_dialog)

            textviewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNote.data.id!!)
                Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_home2)
            }

            textviewNo?.setOnClickListener { bottomSheet.dismiss() }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}