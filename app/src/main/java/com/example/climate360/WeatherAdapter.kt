package com.example.climate360

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class WeatherAdapter(private val weatherList: MutableList<WeatherData>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private val itemColors = intArrayOf(
        R.color.background_color_1,
        R.color.background_color_2,
        R.color.background_color_3,
        R.color.background_color_4,
        R.color.background_color_5,
        R.color.background_color_6,
        R.color.background_color_7,
        R.color.background_color_8,
        R.color.background_color_9,
        R.color.background_color_10,
    )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val location: TextView
        val condition: TextView
        val temperature_f: TextView
        val temperature_c: TextView
        val image: ImageView

        init {
            location = itemView.findViewById(R.id.location)
            condition = itemView.findViewById(R.id.condition)
            temperature_f = itemView.findViewById(R.id.temperature_f)
            temperature_c = itemView.findViewById(R.id.temperature_c)
            image = itemView.findViewById(R.id.image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = weatherList[position]

        holder.location.text = weather.location
        holder.condition.text = weather.condition
        holder.temperature_f.text = weather.temperature_f
        holder.temperature_c.text = weather.temperature_c

        loadWeatherImage(holder.image, weather.imageUrl)

        val colorIndex = position % itemColors.size
        holder.itemView.setBackgroundResource(itemColors[colorIndex])

    }

    private fun loadWeatherImage(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView.context)
            .load("https:$imageUrl")
            .centerCrop()
            .into(imageView)
    }
}
