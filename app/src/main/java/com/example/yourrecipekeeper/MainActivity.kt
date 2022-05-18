package com.example.yourrecipekeeper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.yourrecipekeeper.databinding.ActivityMainBinding
import com.example.yourrecipekeeper.fragments.HelpFragment
import com.example.yourrecipekeeper.fragments.HomeFragment
import com.example.yourrecipekeeper.fragments.PreferencesFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferencesFragment = PreferencesFragment()
        val homeFragment = HomeFragment()
        val helpFragment = HelpFragment()

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.preferences_menu_item -> makeCurrentFragment(preferencesFragment)
                R.id.help_menu_item -> makeCurrentFragment(helpFragment)
                R.id.home_menu_item -> makeCurrentFragment(homeFragment)
                R.id.add_menu_item -> startActivity(Intent(this, AddRecipe::class.java))
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}