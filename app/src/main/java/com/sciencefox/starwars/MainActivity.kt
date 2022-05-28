package com.sciencefox.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sciencefox.starwars.characterlisthome.CharacterSearchHomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchHomeFragment()
    }

    private fun launchHomeFragment() {
        this.supportFragmentManager.beginTransaction()
            .replace(R.id.container, CharacterSearchHomeFragment())
            .commit()
    }
}