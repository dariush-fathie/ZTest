package ir.paad.ztest.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.paad.ztest.R

class GroupItemAdapter(ctx:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val context =ctx
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.group_item , parent ,false)
        return ItemHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       // Glide.with(context).load("").apply(RequestOptions().centerCrop()).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return 30
    }


    internal inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        init {

        }

    }


}
