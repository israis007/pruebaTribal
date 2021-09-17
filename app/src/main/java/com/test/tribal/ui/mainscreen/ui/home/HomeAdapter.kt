package com.test.tribal.ui.mainscreen.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.tribal.R
import com.test.tribal.databinding.ItemHomeBinding
import com.test.tribal.models.Photos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeAdapter (
    private val context: Context,
    private var list: List<Photos>,
    private val onClick : HomeViewListener
        ) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private lateinit var binding: ItemHomeBinding

    interface HomeViewListener {
        fun onClickLeague(player: Photos)
    }

    inner class HomeViewHolder (
        private val binding: ItemHomeBinding
            ) : RecyclerView.ViewHolder(binding.root) {
                fun setData(photos: Photos){
                    with(binding){
                        itemHomeApiv.setOnClickListener {
                            onClick.onClickLeague(photos)
                        }

                        updateImage(binding.itemHomeApiv, photos.urls.REGULAR)

                        executePendingBindings()
                    }
                }
            }

    fun onRefresh(list: List<Photos>){
        this.list = list
        notifyDataSetChanged()
    }

    fun onAddItem(item: Photos){
        val arrayList = ArrayList<Photos>()
            arrayList.addAll(this.list)
        arrayList.add(item)
        this.list = arrayList.toList()
        //notifyItemInserted(arrayList.size - 1)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemHomeBinding.inflate(LayoutInflater.from(context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.setData(list[position])

    override fun getItemCount(): Int =
        list.size

    private fun updateImage(aciv: AppCompatImageView, url: String){
        GlobalScope.launch(Dispatchers.Main){
            Glide.with(context)
                .load(url)
                .placeholder(android.R.drawable.stat_sys_download_done)
                .error(android.R.drawable.stat_sys_upload_done)
                .fitCenter()
                .into(aciv)
        }
    }

}