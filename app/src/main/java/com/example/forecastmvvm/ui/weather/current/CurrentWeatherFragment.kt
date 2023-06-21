package com.example.forecastmvvm.ui.weather.current

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.forecastmvvm.R
import com.example.forecastmvvm.data.network.ApixuWeatherApiService
import com.example.forecastmvvm.data.network.ConnectivityInterceptorImpl
import com.example.forecastmvvm.data.network.WeatherNetworkDataSourceImpl
import com.example.forecastmvvm.databinding.FragmentCurrentWeatherBinding
import com.example.forecastmvvm.ui.base.ScopedFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.util.concurrent.locks.Condition

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private var binding : FragmentCurrentWeatherBinding? = null
    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        bindUI()
        // TODO: Use the ViewModel
        /*val apiService = ApixuWeatherApiService(ConnectivityInterceptorImpl(this.requireContext()))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        weatherNetworkDataSource.downloadCurrentWeather.observe(viewLifecycleOwner, Observer{
            val textView = view?.findViewById<TextView>(R.id.textView)
            textView?.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            weatherNetworkDataSource.fetchCurrentWeather("New York")
        }*/
    }

    private fun bindUI() = launch{
        val currentWeather = viewModel.weather.await()

        val weatherLocation = viewModel.weatherLocation.await()

        weatherLocation.observe(viewLifecycleOwner, Observer { location ->
            if(location == null) return@Observer
            updateLocation(location.name)

        })

        currentWeather.observe(viewLifecycleOwner, Observer {
            if(it == null) return@Observer
            val group_loading = view?.findViewById<Group>(R.id.group_loading)
            group_loading?.visibility = View.GONE
            updateDateToToday()
            updateTemperatures(it.temperature,it.feelslike)
            updateCondition(it.weatherDescriptions)
            updatePrecipitation(it.precip)
            updateWind(it.windDir, it.windSpeed)
            updateVisibility(it.visibility)

            val condition_icon = view?.findViewById<ImageView>(R.id.imageView_condition_icon)

            Glide.with (this@CurrentWeatherFragment)
                .load(it.weatherIcons)
                .into(condition_icon!!)

        })
    }

    private fun updateLocation(location: String){
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday(){
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double){
        val unitAbbreviation = if (viewModel.isImperial) "C" else "F"
        val textView_temperature = view?.findViewById<TextView>(R.id.textView_temperature)
        textView_temperature?.text = "$temperature$unitAbbreviation"
        val textView_feels_like_temp = view?.findViewById<TextView>(R.id.textView_feels_like_temperature)
        textView_feels_like_temp?.text = "Feels like :$feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition: String){
        val textView_condition_text = view?.findViewById<TextView>(R.id.textView_condition)
        textView_condition_text?.text = condition
    }

    private fun updatePrecipitation(precipitationVolume: Double){
        val unitAbbreviation = if (viewModel.isImperial) "C" else "F"
        val textView_precipitation = view?.findViewById<TextView>(R.id.textView_precipitation)
        textView_precipitation?.text = "Precipitation : $precipitationVolume $unitAbbreviation"
    }

    private fun updateWind(windDir : String, windSpeed : Double){
        val textView_wind = view?.findViewById<TextView>(R.id.textView_precipitation)
        textView_wind?.text = "Wind : $windDir $windSpeed kph"
    }
    private fun updateVisibility(visibility: Double){
        val textView_visibility = view?.findViewById<TextView>(R.id.textView_visibility)
        textView_visibility?.text = "Visibility : $visibility km"
    }

}