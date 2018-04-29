package ir.paad.ztest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main2.*

class ListActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        more.setOnClickListener(this)

        rv_1.layoutManager = LinearLayoutManager(this)
        rv_1.adapter = Adapter()
        rv_1.addItemDecoration(DividerItemDecoration(this , RecyclerView.VERTICAL))

    }


    internal inner class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(this@ListActivity).inflate(R.layout.textlayout , parent , false)
            return ItemHolder(view)
        }
    }

    inner class ItemHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        init {

        }
    }

}