package ir.paad.ztest

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.greenrobot.eventbus.EventBus

class MapFragment : Fragment(), OnMapReadyCallback, View.OnClickListener {
    override fun onClick(v: View?) {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            ivToggle.rotation = 180f
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            ivToggle.rotation = 0f
        }
    }

    lateinit var map: GoogleMap
    lateinit var mapList: RecyclerView
    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    lateinit var bottomSheetLayout: ConstraintLayout
    lateinit var ivToggle: AppCompatImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)
        initMap()
        initViews(v)
        return v
    }


    private fun initViews(v: View) {
        ivToggle = v.findViewById(R.id.iv_toggle)
        ivToggle.setOnClickListener(this)
        mapList = v.findViewById(R.id.rv_mapList)
        bottomSheetLayout = v.findViewById(R.id.cl_mapBottomSheetLayout)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
        if (bottomSheetBehavior is CustomBottomSheetBehavior) {
            (bottomSheetBehavior as CustomBottomSheetBehavior).setAllowUserDragging(false)
        }
        mapList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mapList.adapter = HListAdapter(activity as Context)
        LinearSnapHelper().attachToRecyclerView(mapList)
    }

    private fun initMap() {
        var mapFragment: SupportMapFragment? = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        if (mapFragment == null) {
            val fm = fragmentManager
            val ft: FragmentTransaction
            if (fm != null) {
                ft = fm.beginTransaction()
                mapFragment = SupportMapFragment.newInstance()
                ft.replace(R.id.map, mapFragment).commit()
            }
        }
        if (mapFragment != null) {
            mapFragment.getMapAsync(this)
        } else {
            // TODO: 4/29/2018 show error dialog to user
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val sydney = LatLng(35.311339, 46.995957)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 19f))
        Handler().postDelayed({ EventBus.getDefault().post("loaded") }, 150)
    }


}
