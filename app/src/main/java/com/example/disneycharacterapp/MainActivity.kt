package com.example.disneycharacterapp

import DisneyAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var disneyList: MutableList<Triple<String?,String?,String?>>
    private lateinit var rvDisney: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDisney = findViewById(R.id.disney_list)
        disneyList = mutableListOf()

        getDisneyImageURL()
    }

    private fun getDisneyImageURL() {

        val client = AsyncHttpClient()
        val url = "https://api.disneyapi.dev/characters"
        client[url, object : JsonHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {

                val charactersArray = json?.jsonObject?.getJSONArray("data")

                for (i in 0 until (charactersArray?.length() ?: 0)) {
                    val characterObject = charactersArray?.getJSONObject(i)
                    val imageUrl = characterObject?.getString("imageUrl")
                    val name = characterObject?.getString("name")
                    var films = characterObject?.getString("films")
                    disneyList.add(Triple(name, films, imageUrl))
                }

                val adapter = DisneyAdapter(disneyList)
                rvDisney.adapter = adapter
                rvDisney.layoutManager = LinearLayoutManager(this@MainActivity)
                rvDisney.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("API Error", errorResponse)
            }

        }]

    }
}