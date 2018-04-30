package ir.paad.ztest.activitys

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ir.paad.ztest.*
import ir.paad.ztest.models.GroupModel
import ir.paad.ztest.utils.ApiClient
import ir.paad.ztest.utils.ApiInterface
import ir.paad.ztest.utils.StaticValues
import ir.paad.ztest.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var adapter: CategoryAdapter
    var drawerLayoutDefSelected = 0

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_more -> loadAllCategory()
            R.id.btn_fav -> openDrawerLayout()
            R.id.rl_exit -> finish()
            R.id.btn_tryAgain -> tryAgain()
            R.id.rl_drawer1 -> drawerClick(0)
            R.id.rl_drawer2 -> drawerClick(1)
            R.id.rl_drawer3 -> drawerClick(2)
            R.id.rl_drawer4 -> drawerClick(3)
            R.id.iv_menu -> openDrawerLayout()
        }
    }

    private fun drawerClick(position: Int) {
        closeDrawerLayout()
        drawerLayoutDefSelected = position
        when (position) {
            0 -> closeDrawerLayout()
            1 -> startActivity(Intent(this@MainActivity, FavActivity::class.java))
            2 -> startActivity(Intent(this@MainActivity, AboutUs::class.java))
            3 -> startActivity(Intent(this@MainActivity, ContactUs::class.java))
        }
    }


    private fun tryAgain() {
        initList()
    }


    private fun showNetErrLayout() {
        btn_more.visibility = View.GONE
        ll_netErr.visibility = View.VISIBLE
    }

    private fun hideNetErrLayout() {
        btn_more.visibility = View.VISIBLE
        ll_netErr.visibility = View.GONE
    }


    private fun openDrawerLayout() {
        drawerLayout.openDrawer(GravityCompat.END)
    }

    private fun closeDrawerLayout() {
        drawerLayout.closeDrawers()
    }

    private fun loadAllCategory() {
        adapter.flagLoadAll = true
        adapter.notifyItemRangeChanged(6, 14)
        btn_more.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setClickListeners()
        initList()
    }

    private fun setClickListeners() {
        rl_exit.setOnClickListener(this)
        iv_menu.setOnClickListener(this)
        rl_drawer1.setOnClickListener(this)
        rl_drawer2.setOnClickListener(this)
        rl_drawer3.setOnClickListener(this)
        rl_drawer4.setOnClickListener(this)
        btn_more.setOnClickListener(this)
        btn_tryAgain.setOnClickListener(this)
        btn_fav.setOnClickListener(this)
    }

    private fun initList() {
        if (Utils.isNetworkAvailable(this@MainActivity)) {
            getGroupCount() // from server
        } else {
            showNetErrLayout()
            Toast.makeText(this, "به اینترنت متصل نیستید", Toast.LENGTH_SHORT).show()
        }
    }

    fun showProgressLayout() {
        ll_progressLayout.visibility = View.VISIBLE
    }

    fun hideProgressLayout() {
        ll_progressLayout.visibility = View.GONE
    }

    lateinit var RESTResponse: Response<Callback<List<GroupModel>>>

    private fun getGroupCount() {
        showProgressLayout()
        val apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        val response = apiInterface.groupCount
        response.enqueue(object : Callback<List<GroupModel>> {
            override fun onResponse(call: Call<List<GroupModel>>?, response: Response<List<GroupModel>>?) {
                val list: List<GroupModel>? = response?.body()

                /*list?.forEach { groupModel: GroupModel ->
                    Log.e("GroupModel", groupModel.getGName() + " " + groupModel.count + " " + groupModel.id)
                }
                //Log.e("ResponseBody", response?.body().toString() + " bo")
                Realm.getDefaultInstance().executeTransactionAsync({ realm: Realm ->
                    // execute transaction here
                    *//*list?.forEach { model: GroupModel ->
                        //val realmModel = MappingUtils.convertGroupModel(model)
                        val modelRealm: GroupModel_Realm = realm.createObject(GroupModel_Realm::class.java, model.id)
                        modelRealm.name = model.gName
                        modelRealm.count = model.count
                        //modelRealm.id = model.id
                    }*//*

                }, {
                    // on success
                    Log.e(SUCCESS, "Realm")
                }, { error: Throwable? ->
                    // on errro
                    Log.e(ERR, error?.message + " Realm")
                })*/

                loadAdapter(list!!)
                hideProgressLayout()
                hideNetErrLayout()
            }

            override fun onFailure(call: Call<List<GroupModel>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "خطا در اتصال به سرور", Toast.LENGTH_SHORT).show()
                Log.e(ERR, t?.message + " aaa")
                hideProgressLayout()
                showNetErrLayout()
            }
        })
    }

    val ERR = "ERROR>>"
    val SUCCESS = "Successful"
    private fun loadAdapter(list: List<GroupModel>) {
        adapter = CategoryAdapter(list)
        rv_category.layoutManager = GridLayoutManager(this, 2)
        rv_category.adapter = adapter
    }

    inner class CategoryAdapter(gList: List<GroupModel>) : RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {

        var groupsList: List<GroupModel> = gList

        var arr = arrayListOf("مطب", "آزمایشگاه", "سونوگرافی", "بیمارستان", "سی تی اسکن", "رادیولوژی", "ام ار ای", "بینایی سنجی", "گفتار درمانی", "مرکز بهداشت", "انجمن های حمایتی", "داروخانه", "عینک سازی", "ساختمان پزشکان", "دانشکده ها", "سایر")
        var flagLoadAll = false


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
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

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            holder.titleTv.text = arr[position]
            if (groupsList.size > position) {
                holder.subTitle.text = "${groupsList.get(position).getCount()}" + "  مورد"
            } else {
                holder.subTitle.text = "0  مورد"
            }
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
                // start list activity
                val i = Intent(this@MainActivity, OfficeActivity::class.java)
                i.putExtra(StaticValues.CATEGORY, adapterPosition)
                startActivity(i)
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


