package com.iosgallery.android

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.iosgallery.android.adapter.PhotoAdapter
import com.iosgallery.android.adapter.RecentDayAdapter
import com.iosgallery.android.adapter.PersonAdapter
import com.iosgallery.android.databinding.ActivityMainBinding
import com.iosgallery.android.model.Photo
import com.iosgallery.android.model.RecentDay
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var recentDayAdapter: RecentDayAdapter
    private lateinit var personAdapter: PersonAdapter
    
    private val photos = mutableListOf<Photo>()
    private val recentDays = mutableListOf<RecentDay>()
    
    companion object {
        private const val PERMISSION_REQUEST_CODE = 123
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        checkPermissionAndLoadPhotos()
    }
    
    private fun setupUI() {
        // Setup photo grid
        photoAdapter = PhotoAdapter(photos) { photo, position ->
            openImageViewer(photos, position)
        }
        binding.rvPhotos.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = photoAdapter
        }
        
        // Setup recent days horizontal list
        recentDayAdapter = RecentDayAdapter(recentDays) { recentDay ->
            // Handle recent day click
        }
        binding.rvRecentDays.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = recentDayAdapter
        }
        
        // Setup people & pets horizontal list
        personAdapter = PersonAdapter(emptyList()) { person ->
            // Handle person click
        }
        binding.rvPeopleAndPets.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = personAdapter
        }
        
        // Setup click listeners
        binding.tvSelect.setOnClickListener {
            // Handle select mode
        }
        
        binding.ivSearch.setOnClickListener {
            // Handle search
        }
        
        binding.btnGrantPermission.setOnClickListener {
            checkPermissionAndLoadPhotos()
        }
    }
    
    private fun checkPermissionAndLoadPhotos() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        
        Dexter.withContext(this)
            .withPermission(permission)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    binding.layoutPermission.visibility = View.GONE
                    loadPhotosFromGallery()
                }
                
                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    binding.layoutPermission.visibility = View.VISIBLE
                }
                
                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }
    
    private fun loadPhotosFromGallery() {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.DATA
        )
        
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        
        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateTakenColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            
            photos.clear()
            val recentDaysMap = mutableMapOf<String, MutableList<Photo>>()
            
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val dateTaken = cursor.getLong(dateTakenColumn)
                val path = cursor.getString(dataColumn)
                
                val photo = Photo(
                    id = id,
                    name = name,
                    path = path,
                    dateTaken = Date(dateTaken)
                )
                
                photos.add(photo)
                
                // Group photos by date for recent days
                val dateKey = SimpleDateFormat("MMM dd", Locale.getDefault()).format(photo.dateTaken)
                if (!recentDaysMap.containsKey(dateKey)) {
                    recentDaysMap[dateKey] = mutableListOf()
                }
                recentDaysMap[dateKey]?.add(photo)
            }
            
            // Create recent days list
            recentDays.clear()
            recentDaysMap.entries.take(10).forEach { entry ->
                val coverPhoto = entry.value.firstOrNull()
                if (coverPhoto != null) {
                    recentDays.add(RecentDay(entry.key, coverPhoto, entry.value.size))
                }
            }
            
            // Update UI
            runOnUiThread {
                binding.tvItemCount.text = getString(R.string.photos_count, photos.size)
                photoAdapter.notifyDataSetChanged()
                recentDayAdapter.notifyDataSetChanged()
            }
        }
    }
    
    private fun openImageViewer(photos: List<Photo>, position: Int) {
        val intent = Intent(this, ImageViewerActivity::class.java).apply {
            putExtra("position", position)
            putParcelableArrayListExtra("photos", ArrayList(photos))
        }
        startActivity(intent)
    }
}