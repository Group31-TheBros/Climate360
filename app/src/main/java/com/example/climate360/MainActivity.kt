package com.example.climate360

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.climate360.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler.JSON
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherRV: RecyclerView
    private lateinit var loadMoreButton: Button
    private lateinit var adapter: WeatherAdapter
    private val weatherModelList: MutableList<WeatherModel> = mutableListOf()

    private val apiKey = "dfd3772c7131495b97f30000230811"
    private val countryCode = "US"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherRV = binding.weatherlist
        loadMoreButton = binding.loadMoreButton

        adapter = WeatherAdapter(this, weatherModelList)
        weatherRV.adapter = adapter
        weatherRV.layoutManager = LinearLayoutManager(this@MainActivity)
        weatherRV.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                LinearLayoutManager.VERTICAL
            )
        )
        val cityList = mutableListOf(
            "London",
            "New York",
            "Paris",
            "Tokyo",
            "Texas",
            "Hopewell",
            "Boston",
            "Virginia",
            "North Carolina",
            "Florida",
            "Brookyn",
            "Queens",
            "Alaska",
            "California"
        )


        // Load initial weather data
        val initialCityList = mutableListOf("London", "New York", "Paris", "Tokyo","New Mexico","Nevada","Ohio","Pennsylvania","Utah","West Virginia","Tennessee","Wyoming")
        for (city in initialCityList) {
            getWeather(city)
        }

        loadMoreButton.setOnClickListener {
            // Load more weather data when the button is clicked
            val moreCityList = mutableListOf("Texas", "Hopewell", "Boston", "Virginia", "North Carolina", "Florida", "Brooklyn", "Queens", "Alaska", "California")
            for (city in moreCityList) {
                getWeather(city)
            }
        }
    }

    private fun getWeather(city: String) {
        val apiUrl = "https://api.weatherapi.com/v1/current.json?key=$apiKey&q=$city,$countryCode&lang=en&days=1"

        val client = AsyncHttpClient()
        client.get(apiUrl, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.d("weather", "response successful")
                Log.d("weather", json.toString())

                val weatherModel = parseWeatherModel(json, city)
                if (weatherModel != null) {
                    weatherModelList.add(weatherModel)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(
                    "weather",
                    "request failed, status code: $statusCode, response: $response",
                    throwable
                )
            }
        })
    }

        private fun parseWeatherModel(json: JSON, city: String): WeatherModel? {
            return try {
                val current = json.jsonObject.optJSONObject("current")
                val location = json.jsonObject.optJSONObject("location")
                val condition = current?.optJSONObject("condition")
                val iconUrl = condition?.optString("icon")
                val tempCelsius = current?.optDouble("temp_c")
                val tempFahrenheit = current?.optDouble("temp_f")
                val locationName = location?.optString("name")
                val locationRegion = location?.optString("region")
                val isDay = current?.optInt("is_day").toString()

                if (iconUrl != null && tempCelsius != null && tempFahrenheit != null && locationName != null && locationRegion != null) {
                    WeatherModel(
                        city,
                        tempCelsius,
                        tempFahrenheit,
                        iconUrl,
                        locationName,
                        locationRegion,
                        isDay
                    )
                } else {
                    null
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error parsing weather model from JSON", e)
                null
            }
        }

    }

