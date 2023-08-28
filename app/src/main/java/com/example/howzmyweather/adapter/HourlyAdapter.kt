package com.example.howzmyweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.howzmyweather.R
import com.example.howzmyweather.databinding.ForecastWeatherHourlyItemBinding
import com.example.howzmyweather.model.ForecastResponse
import com.example.howzmyweather.util.dayConverter

class HourlyAdapter(private val hourlyList: ArrayList<ForecastResponse.Forecast>) :
    RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {

    class HourlyViewHolder(var view: ForecastWeatherHourlyItemBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ForecastWeatherHourlyItemBinding>(
            inflater,
            R.layout.forecast_weather_hourly_item,
            parent,
            false
        )
        return HourlyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hourlyList.size
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.view.forecast = hourlyList[position]
        holder.view.textViewForecastTime.text = dayConverter((hourlyList[position].dt).toLong())
        holder.view.textViewForecastTemperature.text = hourlyList[position].main!!.temp.toInt().toString()
        holder.view.textViewForecastFeelsTemperature.text = hourlyList[position].main!!.feelsLike.toInt().toString()
    }

    fun updateHourlyList(newHourlyList: List<ForecastResponse.Forecast>){
        hourlyList.clear()
        hourlyList.addAll(newHourlyList)
        notifyDataSetChanged()
    }
}