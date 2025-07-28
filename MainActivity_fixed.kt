package com.iosgallery.android

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.iosgallery.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var photoAdapter: PhotoAdapter
    private val PERMISSION_REQUEST_CODE = 1001
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        checkPermissions()
    }
    
    private fun setupRecyclerView() {
        photoAdapter = PhotoAdapter(emptyList()) { photo ->
            // Handle photo click
            Toast.makeText(this, "Photo clicked", Toast.LENGTH_SHORT).show()
        }
        
        binding.recyclerViewPhotos.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = photoAdapter
        }
    }
    
    private fun checkPermissions() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSION_REQUEST_CODE)
        } else {
            loadPhotos()
        }
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadPhotos()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun loadPhotos() {
        // For now, show placeholder
        val placeholderPhotos = listOf(
            Photo("placeholder1", "Placeholder 1"),
            Photo("placeholder2", "Placeholder 2"),
            Photo("placeholder3", "Placeholder 3"),
            Photo("placeholder4", "Placeholder 4"),
            Photo("placeholder5", "Placeholder 5"),
            Photo("placeholder6", "Placeholder 6")
        )
        
        photoAdapter.updatePhotos(placeholderPhotos)
    }
}

data class Photo(
    val path: String,
    val title: String
)