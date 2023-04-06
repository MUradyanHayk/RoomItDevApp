package com.example.roomitdevapp.screens.detail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.roomitdevapp.APP
import com.example.roomitdevapp.R
import com.example.roomitdevapp.databinding.FragmentDetailBinding
import com.example.roomitdevapp.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    lateinit var currentNote: NoteModel

    lateinit var binding: FragmentDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        currentNote = arguments?.getSerializable("note") as NoteModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }

    private fun initialization() {
        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        binding.tvTitle.text = currentNote.title
        binding.tvDescription.text = currentNote.description

        binding.btnDelete.setOnClickListener {
            viewModel.delete(currentNote) {
                onBack()
            }
        }

        binding.btnBack.setOnClickListener {
            onBack()
        }
    }

    private fun onBack() {
        lifecycleScope.launch(Dispatchers.Main) {
            APP.navController.navigate(R.id.action_detailFragment_to_startFragment)
        }
    }
}