package com.example.roomitdevapp.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.roomitdevapp.APP
import com.example.roomitdevapp.R
import com.example.roomitdevapp.adapter.NoteAdapter
import com.example.roomitdevapp.databinding.FragmentStartBinding
import com.example.roomitdevapp.model.NoteModel
import java.lang.ref.WeakReference

interface StartFragmentDelegate{
    fun clickNote(noteModel: NoteModel)
}
class StartFragment : Fragment(), StartFragmentDelegate {
    lateinit var binding: FragmentStartBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NoteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }

    private fun initialization() {
        val viewModel = ViewModelProvider(this)[StartViewModel::class.java]
        viewModel.initDatabase()
        recyclerView = binding.rvNotes
        adapter = NoteAdapter()
        adapter.delegate = WeakReference(this)
        recyclerView.adapter = adapter
        viewModel.getAllNotes().observe(viewLifecycleOwner) {
            adapter.setList(it.asReversed())
        }

        binding.btnNext.setOnClickListener {
            APP.navController.navigate(R.id.action_startFragment_to_addNoteFragment)
        }
    }


//    companion object {
//        fun clickNote(noteModel: NoteModel) {
//            val bundle = Bundle()
//            bundle.putSerializable("note", noteModel)
//            APP.navController.navigate(R.id.action_startFragment_to_detailFragment, bundle)
//        }
//    }

    override fun clickNote(noteModel: NoteModel) {
        val bundle = Bundle()
        bundle.putSerializable("note", noteModel)
        APP.navController.navigate(R.id.action_startFragment_to_detailFragment, bundle)    }
}