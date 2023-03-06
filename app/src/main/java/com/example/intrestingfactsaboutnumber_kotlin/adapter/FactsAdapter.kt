package com.example.intrestingfactsaboutnumber_kotlin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.intrestingfactsaboutnumber_kotlin.databinding.ItemFactBinding
import com.example.intrestingfactsaboutnumber_kotlin.db.FactsEntity
import javax.inject.Inject

class FactsAdapter @Inject constructor() : RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    private lateinit var binding: ItemFactBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemFactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(item: FactsEntity) {
            binding.apply {
                tvFactNumber.text = item.number.toString()
                tvFact.text = item.fact

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((FactsEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (FactsEntity) -> Unit) {
        onItemClickListener = listener
    }

    private val differCallback = object : DiffUtil.ItemCallback<FactsEntity>() {
        override fun areItemsTheSame(oldItem: FactsEntity, newItem: FactsEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FactsEntity, newItem: FactsEntity): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
}