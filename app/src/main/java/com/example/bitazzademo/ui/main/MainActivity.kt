package com.example.bitazzademo.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.bitazzademo.R
import com.example.bitazzademo.databinding.ActivityMainBinding
import com.example.bitazzademo.databinding.CustomActionbarLayoutBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        navController = Navigation.findNavController(this, R.id.navHostFragmentContainer)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun setCustomActionBar(
        context: Context,
        title: String,
        labelEnd: String?,
        onClickMenu: (() -> Unit) = {}
    ) {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)

        val binding = CustomActionbarLayoutBinding.inflate(layoutInflater)
        val params = ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )

        binding.tvActionBarTitle.text = title

        labelEnd?.let {
            binding.tvActionBarEnd.apply {
                visibility = View.VISIBLE
                text = it
                setOnClickListener {
                    onClickMenu.invoke()
                }
            }
        }

        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(binding.root, params)
        val colorDrawable = ColorDrawable(ContextCompat.getColor(context, R.color.colorBlack191919))
        actionBar?.setBackgroundDrawable(colorDrawable)

        supportActionBar?.apply { setDisplayHomeAsUpEnabled(false) }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}