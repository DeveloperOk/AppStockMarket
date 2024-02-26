package com.enterprise.appstockmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enterprise.appstockmarket.databinding.ActivityMainBinding
import com.enterprise.appstockmarket.view.fragment.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        launchMainFragment()
    }

    private fun launchMainFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val mainFragment = MainFragment()
        transaction.replace(R.id.frameLayout, mainFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}