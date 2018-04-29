package ir.paad.ztest

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class OfficeActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {

    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback
    private lateinit var tabLayoutInterface: TabLayoutInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_office)

        bottomSheetBehavior = BottomSheetBehavior.from(cl_bottomSheetLayout)
        if (bottomSheetBehavior is CustomBottomSheetBehavior) {
            (bottomSheetBehavior as CustomBottomSheetBehavior).setAllowUserDragging(false)
        }
        bottomSheetBehavior.setBottomSheetCallback(setBottomSheetCallback())

        ctbl.addTab(ctbl.newTab().setText("روی نقشه").setIcon(R.drawable.ic_map))
        ctbl.addTab(ctbl.newTab().setText("تخصص ها").setIcon(R.drawable.ic_category))
        tabLayoutInterface = TabLayoutInterface(this, supportFragmentManager, bottomSheetBehavior, ll_progress)
        ctbl.addOnTabSelectedListener(tabLayoutInterface)
    }

    private fun setBottomSheetCallback(): BottomSheetBehavior.BottomSheetCallback {
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
