package ir.paad.ztest.customClasses

import android.content.Context
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import ir.paad.ztest.fragments.MapFragment
import ir.paad.ztest.R
import ir.paad.ztest.fragments.TFragment

class TabLayoutInterface(ctx: Context, fm: FragmentManager, behavior: BottomSheetBehavior<ConstraintLayout>, progressLayout: LinearLayout) : TabLayout.OnTabSelectedListener {

    val fragmentManager = fm
    val bottomSheetBehavior = behavior
    val progressLL = progressLayout
    val TAG = "TabLayoutInterface"

    override fun onTabSelected(tab: TabLayout.Tab) {
        Log.e(TAG, "${tab.position}")
        openBottomLayout(tab.position)
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {
        // remove color filter from preSelected tab
    }

    override fun onTabReselected(tab: TabLayout.Tab) {
        Log.e("RESELECT", "${tab.position}")
        openBottomLayout(tab.position)
    }

    private fun openBottomLayout(tab: Int) {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        showProgress()
        Handler().postDelayed({
            loadFragment(tab)
        }, 450)
    }

    fun closeBottomLayout(tab: Int) {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun loadFragment(i: Int) {
        if (i == 0) {
            fragmentManager.beginTransaction().replace(R.id.fl_sheetContainer, MapFragment()).commit()
        } else {
            fragmentManager.beginTransaction().replace(R.id.fl_sheetContainer, TFragment()).commit()
        }
    }

    fun showProgress() {
        progressLL.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressLL.visibility = View.GONE
    }


}