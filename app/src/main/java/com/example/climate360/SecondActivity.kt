package com.example.climate360

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

data class WeatherData(val location: String, val condition: String, val temperature_f: String, val temperature_c: String, val imageUrl: String)

class SecondActivity : AppCompatActivity() {
    private lateinit var weatherList: MutableList<WeatherData>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val buttonActivity2: Button = findViewById(R.id.button2)

        buttonActivity2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.weather_recycler_view)
        weatherList = mutableListOf()
        adapter = WeatherAdapter(weatherList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Set up an endless scrolling listener
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                /*
                // Load more data if the user is near the end of the list
                if (visibleItemCount + firstVisibleItem >= totalItemCount - 5) {
                    getWeather()
                }
                 */

            }
        })

        getWeather()
    }

    private fun getWeather() {
        val client = AsyncHttpClient()
        val listOfCities = listOf(
            "california", "new york", "philadelphia", "chicago", "boston",
            "london", "paris", "tokyo", "hong kong", "dubai"
        )
        val baseUrl = "https://api.weatherapi.com/v1/current.json"
        val apiKey = "e21cc8c788854b22960172017231211"

        for (city in listOfCities) {
            val url = "$baseUrl?key=$apiKey&q=$city&aqi=no"

            client.get(url, null, object : JsonHttpResponseHandler() {
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String?,
                    throwable: Throwable?
                ) {
                    Log.e("WeatherFetch", "Failed to fetch weather data")
                    Log.e("WeatherFetch", "Status Code: $statusCode")
                    Log.e("WeatherFetch", "Response Headers: $headers")
                    Log.e("WeatherFetch", "Response: $response")
                    Log.e("WeatherFetch", "Throwable: $throwable")
                }

                override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                    try {
                        if (json != null) {
                            val location = json.jsonObject.getJSONObject("location").getString("name")
                            val condition = json.jsonObject.getJSONObject("current").getJSONObject("condition").getString("text")
                            val temperature_f = (json.jsonObject.getJSONObject("current").getString("temp_f")) + " °F"
                            val temperature_c = (json.jsonObject.getJSONObject("current").getString("temp_c")) + " °C"
                            val imageUrl = json.jsonObject.getJSONObject("current").getJSONObject("condition").getString("icon")

                            val weather = WeatherData(location, condition, temperature_f, temperature_c, imageUrl,)
                            weatherList.add(weather)

                            runOnUiThread {
                                adapter.notifyDataSetChanged()
                            }

                            // Limit the number of items to 10
                            if (weatherList.size >= 10) {
                                return
                            }
                        } else {
                            Log.e("WeatherFetch", "Error parsing weather data: Invalid JSON format")
                        }
                    } catch (e: Exception) {
                        Log.e("WeatherFetch", "Error parsing weather data: $e")
                    }
                }
            })
        }
    }
}