package com.example.yourrecipekeeper.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yourrecipekeeper.*
import com.example.yourrecipekeeper.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var fragmentHomeBinding: FragmentHomeBinding? = null
    private lateinit var recipeArrayList: ArrayList<Recipe>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        fragmentHomeBinding = binding

        // Initiate and fill recipe arrayList with information from database
        recipeArrayList = ArrayList()

        val sampleRecipe1 = Recipe("Quesadillas",
            "- Cheese \n- Tortillas",
            "1.- Put tortilla in comal\n-2.- Add cheese on top\n3.- Enjoy")
        recipeArrayList.add(sampleRecipe1)

        val sampleRecipe2 = Recipe("Hot Water",
            "- Water \n- Cup",
            "1.- Put water in the cup\n-2.- Microwave for 1 minute\n3.- Enjoy")
        recipeArrayList.add(sampleRecipe2)

        val db = Firebase.firestore
        // Recipe: is your database collection name. It is case sensitive.
        db.collection("Recipe").get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (document in it.result) {
                    val recipe = Recipe(document.data.getValue("Title").toString(),
                        document.data.getValue("Ingredients").toString(),
                        document.data.getValue("Preparation").toString())
                    recipeArrayList.add(recipe)
                }
            }
            else {
                Snackbar.make(
                    requireActivity().findViewById(R.id.homeListView),"Couldn't connect to database.",
                    Snackbar.LENGTH_LONG).show()
            }
        }

        // Display in listview
        binding.homeListView.isClickable = true
        binding.homeListView.adapter = MyAdapter(requireActivity(), recipeArrayList)

        binding.homeListView.setOnItemClickListener { parent, view, position, id ->

            val title = recipeArrayList[position].title
            val ingredients = recipeArrayList[position].ingredients
            val preparation = recipeArrayList[position].preparation

            val i = Intent(requireActivity(), DisplayRecipe::class.java)
            i.putExtra("title", title)
            i.putExtra("ingredients", ingredients)
            i.putExtra("preparation", preparation)
            startActivity(i)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}