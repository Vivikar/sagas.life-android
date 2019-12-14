package com.iasahub.sagas_life

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.iasahub.sagas_life.databinding.ActivityMapsBinding
import kotlinx.android.synthetic.main.activity_timelapsefeed.*







class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {
    override fun onMarkerClick(p0: Marker?) = false


    lateinit var binding: ActivityMapsBinding

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private val myMarker: Marker? = null


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_maps)
        //setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setSupportActionBar(toolbar)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_map, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.feed -> {
            // do stuff
            //Toast.makeText(this, item.itemId, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, TimelapsefeedActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        // 1
        map.isMyLocationEnabled = true

// 2
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            // 3
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        //map = googleMap

        // Add a marker in Sydney and move the camera


        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 12.0f))
        //map.getUiSettings().setZoomControlsEnabled(true)
        //map.setOnMarkerClickListener(this)

        map = googleMap
        val myPlace = LatLng(50.44, 30.46)  // this is IASA
        map.addMarker(MarkerOptions().position(myPlace).title("Lyceum near KPI"))


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 12.0f))

        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener { marker ->
            if (marker.isInfoWindowShown) {
                //Toast.makeText(this, "hi", Toast.LENGTH_LONG).show()
            } else {
                marker.showInfoWindow()
                val intent = Intent(this, TimelapsePageActivity::class.java)
                intent.putExtra("TNAME", "Timelapse from maps")
                intent.putExtra("TDESCR", "#map #timelapse")
                intent.putExtra("TUSERPIC", R.drawable.user_icon.toString())
                intent.putExtra("TIMAGE", R.drawable.timelapse_1.toString())
                startActivity(intent)

            }
            true
        }



        setUpMap()


    }

}
