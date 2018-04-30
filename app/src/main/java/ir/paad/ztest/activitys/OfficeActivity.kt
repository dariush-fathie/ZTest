package ir.paad.ztest.activitys

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import ir.paad.ztest.customClasses.CustomBottomSheetBehavior
import ir.paad.ztest.R
import ir.paad.ztest.utils.StaticValues
import ir.paad.ztest.customClasses.TabLayoutInterface
import ir.paad.ztest.adapters.GroupItemAdapter
import kotlinx.android.synthetic.main.activity_office.*
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class OfficeActivity : AppCompatActivity() {

    private var groupId = 0;
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback
    private lateinit var tabLayoutInterface: TabLayoutInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_office)

        if (intent != null) {
            groupId = intent.getIntExtra(StaticValues.CATEGORY, 0)
        }

        ctbl.addTab(ctbl.newTab().setText("روی نقشه").setIcon(R.drawable.ic_map))

        if (groupId == 0) {
            ctbl.addTab(ctbl.newTab().setText("تخصص ها").setIcon(R.drawable.ic_category))
        }

        initBottomSheet()
        tabLayoutInterface = TabLayoutInterface(this, supportFragmentManager, bottomSheetBehavior, ll_progress)
        ctbl.addOnTabSelectedListener(tabLayoutInterface)

        initList()

    }

    private fun initList(){
        rv_items.layoutManager = LinearLayoutManager(this)
        rv_items.adapter = GroupItemAdapter(this)
    }


    private fun initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(cl_bottomSheetLayout)
        if (bottomSheetBehavior is CustomBottomSheetBehavior) {
            (bottomSheetBehavior as CustomBottomSheetBehavior).setAllowUserDragging(false)
        }
        bottomSheetBehavior.setBottomSheetCallback(getBottomSheetCallback())

    }

    private fun getBottomSheetCallback(): BottomSheetBehavior.BottomSheetCallback {
        bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.e("slide", "$slideOffset")
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }
        }
        return bottomSheetCallback
    }


    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(e: String) {
        tabLayoutInterface.hideProgress()
    }

    override fun onBackPressed() {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            super.onBackPressed()
        }
    }


}
