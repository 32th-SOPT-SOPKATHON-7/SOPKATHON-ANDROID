package com.solosol.a32th_sopt_sopkathon_7_android

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.solosol.a32th_sopt_sopkathon_7_android.api.ApiFactory.soptService
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingActivity
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ActivityMainBinding
import com.solosol.a32th_sopt_sopkathon_7_android.extension.showToast
import com.solosol.a32th_sopt_sopkathon_7_android.roomdb.AppDatabase
import com.solosol.a32th_sopt_sopkathon_7_android.util.TimeUtil
import com.solosol.a32th_sopt_sopkathon_7_android.util.TimeUtil.calculateTimeDifference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import org.w3c.dom.Comment


class MainActivity : BaseViewBindingActivity<ActivityMainBinding>(), MapView.POIItemEventListener {
    private val ACCESS_FINE_LOCATION = 1000

    private lateinit var mapView: MapView
    private var previouslySelectedMarker: MapPOIItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (checkLocationService()) {
            permissionCheck()
        } else {
            Toast.makeText(this, "GPS를 켜주세요", Toast.LENGTH_SHORT).show()
        }

        mapView = MapView(this)


        binding.mapView.addView(mapView) // 카카오 지도 뷰
        moveInit()
        initData()

    }

    private fun initData() {
        try {
            lifecycleScope.launch(Dispatchers.IO) {
                val roomResponse = AppDatabase.getInstance(this@MainActivity)!!.subwayDao().selectAllSubway()
                withContext(Dispatchers.Main) {
                    val serverResponse = soptService.getAllStationLove()
                    if (serverResponse.isSuccessful) {
                        val time = serverResponse.body()?.data?.hotPost?.createdAt ?: ""
                        binding.tvAlarmTitle.text = serverResponse.body()?.data?.hotPost?.title.toString()
                        if (time.isNotEmpty()) {
                            Log.d("TIMETEST", calculateTimeDifference(time))
                            binding.tvAlarmTime.text = calculateTimeDifference(time)
                        }

                        binding.ivSearch.setOnClickListener {
                            startActivity(Intent(this@MainActivity,SubwaySearchActivity::class.java))
                        }


                        binding.btMove.setOnClickListener {
                            startActivity(
                                Intent(this@MainActivity, CommentActivity::class.java).apply {
                                    putExtra("postId", serverResponse.body()?.data?.hotPost?.postId ?:0)
                                }
                            )
                        }


                        roomResponse.forEach { db ->
                            serverResponse.body()?.data?.stations?.forEach { server ->
                                if (db.name == server?.stationName) {
                                    when {
                                        (server?.totalLikeCnt ?: 0) >= 50 -> {
                                            addBigMarker(db.name ?: "", db.code ?: 0, db.lat ?: 0.0, db.lng ?: 0.0, (server?.totalLikeCnt ?: 0).toString())
                                        }

                                        (server?.totalLikeCnt ?: 0) > 10 -> {
                                            addMarker(db.name ?: "", db.code ?: 0, db.lat ?: 0.0, db.lng ?: 0.0, (server?.totalLikeCnt ?: 0).toString())
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        } catch (e: Exception) {
            showToast("하핳 잠시 서버가 아픕니다 조금만 기다려 주세요")
        }

        initView()
    }

    private fun initView() {
        binding.fbCurrentLocation.setOnClickListener {
            moveCameraToCurrentLocation()
        }

        binding.ivClose.setOnClickListener {
            binding.clAlarm.isVisible = false
        }
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun permissionCheck() {
        val preference = getPreferences(MODE_PRIVATE)
        val isFirstCheck = preference.getBoolean("isFirstPermissionCheck", true)
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) { // 권한이 없는 상태
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) { // 권한 거절
                val builder = AlertDialog.Builder(this)
                builder.setMessage("현재 위치를 확인하시려면 위치 권한을 허용해주세요.")
                builder.setPositiveButton("확인") { _, _ ->
                    ActivityCompat.requestPermissions(
                        this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION
                    )
                }
                builder.setNegativeButton("취소") { dialog, which ->

                }
                builder.show()
            } else {
                if (isFirstCheck) { // 최초 권한 요청
                    preference.edit().putBoolean("isFirstPermissionCheck", false).apply()
                    ActivityCompat.requestPermissions(
                        this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION
                    )
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("현재 위치를 확인하시려면 설정에서 위치 권한을 허용해주세요.")
                    builder.setPositiveButton("설정으로 이동") { dialog, which ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName"))
                        startActivity(intent)
                    }
                    builder.setNegativeButton("취소") { dialog, which ->

                    }
                    builder.show()
                }
            }
        } else {

        }

    }

    // 권한 요청
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "위치 권한이 승인되었습니다", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "위치 권한이 거절되었습니다", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onPOIItemSelected(mapView: MapView?, mapPOIItem: MapPOIItem) {
        Log.e("CurrentItemName", mapPOIItem.itemName)
        Log.e("previouslySelectedMarker", previouslySelectedMarker.toString())
        if (mapPOIItem.itemName == "MyLocation") {
            return
        }
        startActivity(Intent(this, PostingListActivity::class.java).apply {
            putExtra("subway_name", mapPOIItem.itemName)
        })
    }

    private fun addMarker(itemName: String, tag: Int, latitude: Double, longitude: Double, scrapCount: String) {
        val marker = MapPOIItem().apply {
            this.itemName = itemName
            this.tag = tag
            this.mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
            val customBitmap = createCustomMarkerBitmap(scrapCount)
            customImageBitmap = customBitmap
            markerType = MapPOIItem.MarkerType.CustomImage
            setCustomImageAnchor(0.5f, 1.0f)
        }
        setMapView(marker)
    }

    private fun addLocationMarker(itemName: String, tag: Int, latitude: Double, longitude: Double) {
        val marker = MapPOIItem().apply {
            this.itemName = itemName
            this.tag = tag
            this.mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
            val customBitmap = createLocationMarkerBitmap()
            customImageBitmap = customBitmap
            markerType = MapPOIItem.MarkerType.CustomImage
            setCustomImageAnchor(0.5f, 1.0f)
        }

        setMapView(marker)
    }

    private fun addBigMarker(itemName: String, tag: Int, latitude: Double, longitude: Double, scrapCount: String) {
        val marker = MapPOIItem().apply {
            this.itemName = itemName
            this.tag = tag
            this.mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
            val customBitmap = createBigCustomMarkerBitmap(scrapCount)
            customImageBitmap = customBitmap
            markerType = MapPOIItem.MarkerType.CustomImage
            setCustomImageAnchor(0.5f, 1.0f)
        }

        setMapView(marker)
    }

    private fun setMapView(marker: MapPOIItem) {
        mapView.apply {
            addPOIItem(marker)
            setPOIItemEventListener(this@MainActivity)
        }
    }

    // GPS가 켜져있는지 확인
    private fun checkLocationService(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun createCustomMarkerBitmap(name: String): Bitmap {
        val customMarkerView = LayoutInflater.from(this).inflate(R.layout.custom_balloon, null)

        val textView = customMarkerView.findViewById<TextView>(R.id.name)
        textView.text = name

        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        customMarkerView.layout(0, 0, customMarkerView.measuredWidth, customMarkerView.measuredHeight)

        val bitmap = Bitmap.createBitmap(customMarkerView.measuredWidth, customMarkerView.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        customMarkerView.draw(canvas)

        return bitmap
    }

    private fun createLocationMarkerBitmap(): Bitmap {
        val customMarkerView = LayoutInflater.from(this).inflate(R.layout.custom_my_location, null)

        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        customMarkerView.layout(0, 0, customMarkerView.measuredWidth, customMarkerView.measuredHeight)

        val bitmap = Bitmap.createBitmap(customMarkerView.measuredWidth, customMarkerView.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        customMarkerView.draw(canvas)

        return bitmap
    }

    private fun createBigCustomMarkerBitmap(name: String): Bitmap {
        val customMarkerView = LayoutInflater.from(this).inflate(R.layout.custom_selected_balloon, null)

        val textView = customMarkerView.findViewById<TextView>(R.id.name)
        textView.text = name

        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        customMarkerView.layout(0, 0, customMarkerView.measuredWidth, customMarkerView.measuredHeight)

        val bitmap = Bitmap.createBitmap(customMarkerView.measuredWidth, customMarkerView.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        customMarkerView.draw(canvas)

        return bitmap
    }

    // 현재 사용자 위치추적
    @SuppressLint("MissingPermission")
    private fun getMyCoordinate(): Pair<Double, Double>? {
        val lm: LocationManager = getSystemService(Context.LOCATION_SERVICE) as? LocationManager ?: return null
        val userNowLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) //위도 , 경도
        val uLatitude = userNowLocation?.latitude
        val uLongitude = userNowLocation?.longitude
        return Pair(uLatitude ?: 0.0, uLongitude ?: 0.0)
    }

    private fun moveCameraToCurrentLocation() {
        val myLocation = getMyCoordinate()
        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                myLocation?.first ?: 0.0, getMyCoordinate()?.second ?: 0.0
            ), false
        )
        addLocationMarker("MyLocation", 0, myLocation?.first ?: 0.0, myLocation?.second ?: 0.0)
        mapView.setZoomLevel(2, false)
    }

    private fun moveInit() {
        val myLocation = getMyCoordinate()
        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                37.481072, 126.882343
            ), false
        )
        addLocationMarker("MyLocation", 0, myLocation?.first ?: 0.0, myLocation?.second ?: 0.0)
        mapView.setZoomLevel(2, false)
    }



    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {}

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?, p2: MapPOIItem.CalloutBalloonButtonType?) {}

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {}

}