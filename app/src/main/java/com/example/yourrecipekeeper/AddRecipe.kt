package com.example.yourrecipekeeper

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_recipe.*

class AddRecipe : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        cancelBtnId.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        saveBtnId.setOnClickListener {

            if (recipeTitleInputId.text.trim().toString().isNotBlank() &&
                    ingredientsInputId.text.trim().toString().isNotBlank() &&
                    preparationInputId.text.trim().toString().isNotBlank()){

                addNewRecipe()
                startActivity(Intent(this, MainActivity::class.java))

            } else {

                Snackbar.make(findViewById(R.id.saveBtnId),"Please fill out empty fields.",
                    Snackbar.LENGTH_LONG).show()

            }

        }

    }

    private fun addNewRecipe() {
        // create new recipe
        val recipe = hashMapOf(
            "Title" to recipeTitleInputId.text.toString().trim(),
            "Ingredients" to ingredientsInputId.text.toString().trim(),
            "Preparation" to preparationInputId.text.toString().trim()
        )
        // add a new document with a generated ID
        db.collection("Recipe").add(recipe)
            .addOnSuccessListener {
                documentReference -> Log.d(TAG, "Document Snapshot added with ID: ${documentReference.id}.")}
            .addOnFailureListener {
                e -> Log.w(TAG, "Error adding document.", e)
            }
    }

}