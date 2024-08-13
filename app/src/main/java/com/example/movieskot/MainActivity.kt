package com.example.movieskot

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.movieskot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG = "a5f1751c"

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init view
        binding.apply {
            btnSearch.setOnClickListener {
                //validate input
                if (etSearch.text.toString().isEmpty()){
                    Toast.makeText(this@MainActivity, "Enter the name of movie", Toast.LENGTH_SHORT).show()
                }
                else{
                    // request to server
                    val queue = Volley.newRequestQueue(this@MainActivity)

                    //api
                    val url = "http://www.omdbapi.com/?&apikey=" + TAG+"&t="+etSearch.text.toString()

                    val jsonObjectRequest = JsonObjectRequest(url, {response->
                        val title = response.getString("Title")
                        val plot = response.getString("Plot")
                        val poster = response.getString("Poster")
                        //set data
                        Glide.with(this@MainActivity)
                            .load(poster)
                            .into(movieCover)

                        moviesTitle.text = title.toString()
                        moviesDesc.text = plot.toString()

                    },{error->
                        Toast.makeText(this@MainActivity, "error is: "+error.message.toString(), Toast.LENGTH_SHORT).show()
                    })
                    queue.add(jsonObjectRequest)

                    showMore.setOnClickListener {
                        //run animation
                    }
                }
            }
        }
    }
}