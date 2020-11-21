package com.example.digitalturbine.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.digitalturbine.R
import com.example.digitalturbine.ui.fragment.AdListFragment
import com.example.digitalturbine.utilities.FragmentUtils
import com.example.digitalturbine.utilities.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!NetworkUtil.haveInternet(this)) {
            Toast.makeText(this, R.string.guide_connection_error, Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setContentView(R.layout.activity_fragment)
        val fragment: AdListFragment = AdListFragment.newInstance()
        FragmentUtils.showFragment(this, fragment, fragment.javaClass.canonicalName)
    }

    override fun onResume() {
        super.onResume()
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) != null) {
            return
        }
    }
}