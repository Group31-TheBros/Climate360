package com.example.climate360

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        val moreInfoBtn : Button
        val feel_f: TextView
        val feel_c: TextView
        val uv: TextView
        val humid: TextView

        init {
            location = itemView.findViewById(R.id.location)
            condition = itemView.findViewById(R.id.condition)
            temperature_f = itemView.findViewById(R.id.temperature_f)
            temperature_c = itemView.findViewById(R.id.temperature_c)
            image = itemView.findViewById(R.id.image)
            moreInfoBtn = itemView.findViewById(R.id.more_btn)
            feel_f = itemView.findViewById(R.id.feel_f)
            feel_c = itemView.findViewById(R.id.feel_c)
            uv = itemView.findViewById(R.id.uv)
            humid = itemView.findViewById(R.id.humidity)
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
        holder.feel_f.text = weather.feel_f
        holder.feel_c.text = weather.feel_c
        holder.uv.text = weather.uv
        holder.humid.text = weather.humid


        loadWeatherImage(holder.image, weather.imageUrl)

        val colorIndex = position % itemColors.size
        holder.itemView.setBackgroundResource(itemColors[colorIndex])

        var isMore = true;
        holder.moreInfoBtn.setOnClickListener{
            holder.moreInfoBtn.text = if (isMore) "Less" else "More";
            isMore = !isMore;
            holder.feel_f.visibility = if (holder.feel_f.visibility == View.GONE)
                View.VISIBLE else View.GONE
            holder.feel_c.visibility = if (holder.feel_c.visibility == View.GONE)
                View.VISIBLE else View.GONE
            holder.uv.visibility = if (holder.uv.visibility == View.GONE)
                View.VISIBLE else View.GONE
            holder.humid.visibility = if (holder.humid.visibility == View.GONE)
                View.VISIBLE else View.GONE
        }

    }

    private fun loadWeatherImage(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView.context)
            .load("https:$imageUrl")
            .centerCrop()
            .into(imageView)
    }
}
