package ir.paad.ztest

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var adapter: CategoryAdapter

    override fun onClick(v: View?) {
        loadAllCategory()
    }

    private fun loadAllCategory() {
        adapter.flagLoadAll = true
        adapter.notifyItemRangeChanged(6, 14)
        btn_more.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CategoryAdapter()
        rv_category.layoutManager = GridLayoutManager(this, 2)
        rv_category.adapter = adapter
        btn_more.setOnClickListener(this)
    }


    inner class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {

        var arr = arrayListOf("مطب", "آزمایشگاه", "سونوگرافی", "بیمارستان", "سی تی اسکن", "رادیولوژی", "ام ار ای", "بینایی سنجی", "گفتار درمانی", "مرکز بهداشت", "انجمن های حمایتی", "داروخانه", "عینک سازی", "ساختمان پزشکان", "دانشکده ها", "سایر")
        var flagLoadAll = false


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ItemHolder {
            val view: View = LayoutInflater.from(this@MainActivity).inflate(R.layout.main_category_item, parent, false)
            return ItemHolder(view)
        }

        override fun getItemCount(): Int {
            if (flagLoadAll) {
                rv_category.overScrollMode = View.OVER_SCROLL_IF_CONTENT_SCROLLS
                return arr.size
            }
            rv_category.overScrollMode = View.OVER_SCROLL_NEVER
            return 6

        }

        override fun onBindViewHolder(holder: CategoryAdapter.ItemHolder, position: Int) {
            holder.titleTv.text = arr[position]
            holder.subTitle.text = "$position" + "  مورد"
            setAnimation(holder.container, position)


            when (position) {
                1 -> holder.imageView.setImageResource(R.drawable.ic_doctor)
                2 -> holder.imageView.setImageResource(R.drawable.ic_lab)
                3 -> holder.imageView.setImageResource(R.drawable.ic_sonography)
                4 -> holder.imageView.setImageResource(R.drawable.ic_hospital)
                5 -> holder.imageView.setImageResource(R.drawable.ic_ctscan)
                6 -> holder.imageView.setImageResource(R.drawable.ic_radiology)
            }
        }

        protected fun setAnimation(viewToAnimate: View, position: Int) {
            if (position > 6) {
                val a = ObjectAnimator.ofFloat(viewToAnimate, "translationY", 200f, 100f, 50f, 0f)
                a.duration = 400
                a.start()
            }
        }


        inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
            override fun onClick(v: View?) {
                if (adapterPosition == 0) {
                    // start officeActivity
                    startActivity(Intent(this@MainActivity, OfficeActivity::class.java))
                } else {
                    // start default listActivity
                    val i = Intent(this@MainActivity, ListActivity::class.java)
                    i.putExtra(StaticValues.CATEGORY, adapterPosition)
                    startActivity(i)
                }
            }

            val imageView: AppCompatImageView = itemView.findViewById(R.id.iv_mainCategoryImage)
            val titleTv: AppCompatTextView = itemView.findViewById(R.id.tv_mainCategoryTitle)
            val subTitle: AppCompatTextView = itemView.findViewById(R.id.tv_mainCategorySubTitle)
            val container: CardView = itemView.findViewById(R.id.cv_mainCategoryContainer)

            init {
                container.setOnClickListener(this)

            }
        }

    }


}


