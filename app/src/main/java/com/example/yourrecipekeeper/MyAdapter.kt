package com.example.yourrecipekeeper

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter (private val context : Activity, private var arrayOfRecipes : ArrayList<Recipe>) : ArrayAdapter<Recipe>(context,
                R.layout.list_item, arrayOfRecipes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item, null)

        val imageView : ImageView = view.findViewById(R.id.recipeIconId)
        val recipeTitle : TextView = view.findViewById(R.id.recipeTitleHomeId)

        imageView.setImageResource(R.drawable.ic_baseline_fastfood_24)
        recipeTitle.text = arrayOfRecipes[position].title

        return view
    }

}