package com.example.climate360

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.climate360.databinding.WeatherItemBinding

class WeatherAdapter(private val context: Context, private val weatherList: List<WeatherModel>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val weatherImage: ImageView = binding.weatherImage
        val cityName: TextView = binding.cityName
        val temperature: TextView = binding.temperature
        val temperatureFahrenheit: TextView = binding.tempf
        val isDay: TextView = binding.isDay
        val locationRegion: TextView = binding.cityRegion
    }

    private fun mapIconToNightDrawable(iconValue: String): Int {
        return when (iconValue) {
            "113" -> R.drawable.night113
            "116" -> R.drawable.night116
            "119" -> R.drawable.night119
            "122" -> R.drawable.night122
            "143" -> R.drawable.night143
            "176" -> R.drawable.night176
            "179" -> R.drawable.night179
            "182" -> R.drawable.night182
            "185" -> R.drawable.night185
            "200" -> R.drawable.night200
            "227" -> R.drawable.night227
            "230" -> R.drawable.night230
            "248" -> R.drawable.night248
            "260" -> R.drawable.night260
            "263" -> R.drawable.night263
            "266" -> R.drawable.night266
            "281" -> R.drawable.night281
            "284" -> R.drawable.night284
            "293" ->R.drawable.night293
            "296" ->R.drawable.night296
            "299" ->R.drawable.night299
            "302" ->R.drawable.night302
            "305" ->R.drawable.night305
            "308" ->R.drawable.night308
            "311" ->R.drawable.night311
            "314" ->R.drawable.night314
            "317" ->R.drawable.night317
            "320" ->R.drawable.night320
            "323" ->R.drawable.night323
            "326" ->R.drawable.night326
            "329" ->R.drawable.night329
            "332" ->R.drawable.night332
            "335" ->R.drawable.night335
            "338" ->R.drawable.night338
            "350" ->R.drawable.night350
            "353" ->R.drawable.night353
            "356" ->R.drawable.night356
            "359" ->R.drawable.night359
            "362" ->R.drawable.night362
            "365" ->R.drawable.night365
            "368" ->R.drawable.night368
            "371" ->R.drawable.night371
            "374" ->R.drawable.night374
            "377" ->R.drawable.night377
            "386" ->R.drawable.night386
            "389" ->R.drawable.night389
            "392" ->R.drawable.night392
            "395" ->R.drawable.night395

            // Add more mappings as needed
            else -> R.drawable.night116 // Default drawable for unknown icons
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherModel = weatherList[position]

        holder.cityName.text = "${weatherModel.cityName}"
        holder.temperature.text = "${weatherModel.temperatureCelsius}°C"
        holder.temperatureFahrenheit.text = "${weatherModel.temperatureFahrenheit}°F"
        holder.locationRegion.text = "${weatherModel.locationRegion}"
        holder.isDay.text = "${weatherModel.isDay}"

        val nightImageResId = mapIconToNightDrawable(weatherModel.conditionIconUrl)

        Glide.with(context)
            .load(nightImageResId)
            .into(holder.weatherImage)
        Log.d("WeatherAdapter", "Loading image from URL: ${weatherModel.conditionIconUrl}")
    }

    override fun getItemCount() = weatherList.size
}
