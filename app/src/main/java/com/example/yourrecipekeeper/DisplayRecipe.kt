package com.example.yourrecipekeeper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.yourrecipekeeper.databinding.ActivityAddRecipeBinding
import com.example.yourrecipekeeper.databinding.ActivityDisplayRecipeBinding
import kotlinx.android.synthetic.main.activity_display_recipe.*

class DisplayRecipe : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val ingredients = intent.getStringExtra("ingredients")
        val preparation = intent.getStringExtra("preparation")

        binding.displayRecipeTitleId.text = title
        binding.displayIngredientsId.text = ingredients
        binding.displayPreparationId.text = preparation

        backBtnId.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}