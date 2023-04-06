package com.example.roomitdevapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomitdevapp.R
import com.example.roomitdevapp.model.NoteModel
import com.example.roomitdevapp.screens.start.StartFragment
import com.example.roomitdevapp.screens.start.StartFragmentDelegate
import java.lang.ref.WeakReference

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var listNote = emptyList<NoteModel>()

    var delegate: WeakReference<StartFragmentDelegate>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemTitle.text = listNote[position].title
//
//        holder.item.setOnClickListener {
//            StartFragment.clickNote(listNote[position])
//        }
    }

    override fun onViewAttachedToWindow(holder: NoteViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.item.setOnClickListener {
            delegate?.get()?.clickNote(listNote[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: NoteViewHolder) {
        holder.item.setOnClickListener(null)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>) {
        listNote = list
        notifyDataSetChanged()
    }

    class NoteViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        lateinit var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.item_title)
        }
    }

}

