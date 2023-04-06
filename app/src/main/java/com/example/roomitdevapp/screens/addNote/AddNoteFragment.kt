package com.example.roomitdevapp.screens.addNote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.roomitdevapp.APP
import com.example.roomitdevapp.R
import com.example.roomitdevapp.databinding.FragmentAddNoteBinding
import com.example.roomitdevapp.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }

    private fun initialization() {
        val viewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]
        binding.btnAddNote.setOnClickListener {
            val title = binding.edtAddTitle.text.toString()
            val description = binding.edtAddDesc.text.toString()
            viewModel.insert(NoteModel(title = title, description = description)) {
                onBack()
            }
        }
        binding.btnBack.setOnClickListener {
            onBack()
        }
    }

    fun onBack() {
        lifecycleScope.launch(Dispatchers.Main) {
            APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)
        }
    }
}