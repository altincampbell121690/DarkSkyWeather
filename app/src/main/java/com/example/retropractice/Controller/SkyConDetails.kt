package com.example.retropractice.Controller

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.retropractice.Model.Data
import com.example.retropractice.R
import com.example.retropractice.Utilities.EXTRA_DATA
import com.example.retropractice.Utilities.dayOfWeek
import com.example.retropractice.Utilities.getDateRepresentation
import com.example.retropractice.Utilities.regexToString
import com.thbs.skycons.library.*
import kotlinx.android.synthetic.main.activity_sky_con_details.*


class SkyConDetails : AppCompatActivity() {

    lateinit var data : Data
    lateinit var skyCon: SkyconView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sky_con_details)

      //  val layour1 = ConstraintLayout(this)
         val params: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams( ConstraintLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        data = intent.getParcelableExtra(EXTRA_DATA)
//        val layout = LinearLayout(this)
//        layout.orientation = LinearLayout.VERTICAL
//        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
//        )
//        params.gravity = Gravity.CENTER_HORIZONTAL
//        layout.setLayoutParams(params)



        //Using these params, you can control view attributes
        //attributres include boolean isStatic,boolean isAnimated, int strokeColor , int backgroundColor
        //Using these params, you can control view attributes
//attributres include boolean isStatic,boolean isAnimated, int strokeColor , int backgroundColor
        //ffffff
        //000000
        println("\n-----ICON----\n${data.icon}\n-------ICON-----\n")
        when(data.icon){
            "clear-day" ->{
                 skyCon =
                    SunView(this, false, true, Color.parseColor("#000000"), Color.parseColor("#ffffff"))
            }
            "cloud", "cloudy"->{
                skyCon =
                    CloudView(this, false, true, Color.parseColor("#000000"), Color.parseColor("#ffffff"))

            }
            "thunderstorm" ->{
                skyCon =
                    CloudThunderView(this, false, true, Color.parseColor("#000000"), Color.parseColor("#ffffff"))
            }
            "clear-night" ->{
                skyCon =
                    MoonView(this, false, true, Color.parseColor("#000000"), Color.parseColor("#ffffff"))
            }
            "partly-cloudy-day" ->{
                skyCon =
                    CloudSunView(this, false, true, Color.parseColor("#000000"), Color.parseColor("#ffffff"))
            }
            "partly-night-day" ->{
                skyCon =
                    CloudMoonView(this, false, true, Color.parseColor("#000000"), Color.parseColor("#ffffff"))
            }
            "rain" ->{
                skyCon =
                    CloudHvRainView(this, false, true, Color.parseColor("#000000"), Color.parseColor("#ffffff"))
            }
            "wind", "fog" ->{
                skyCon =
                    WindView(this, false, true, Color.parseColor("#000000"), Color.parseColor("#ffffff"))
            }

            "snow", "sleet","hail" ->{
                skyCon =
                    CloudSnowView(this, false, true, Color.parseColor("#000000"), Color.parseColor("#ffffff"))
            }
            else->{
                skyCon =
                    CloudSnowView(this, false, true, Color.parseColor("#000000"), Color.parseColor("#ffffff"))
            }
        }


//        val windView =
//            WindView(this, false, true, Color.parseColor("#000000"), Color.parseColor("#ffffff"))

        //Using these params, you can control width & height of the icon
        //Using these params, you can control width & height of the icon
        params.width = 1100
        params.height = 1100


        skyCon.layoutParams = params


       // layout.addView(windView)

        //this.setContentView(layout)
       // layout.id = LinearLayout.generateViewId()
        //skyConContainer.addView(layout)

        skyCon.id = View.generateViewId()
        skyConContainer.addView(skyCon)
       val constraintSet = ConstraintSet()
        constraintSet.clone(skyConContainer)

        println(skyCon.id)
        constraintSet.connect(skyCon.id, ConstraintSet.RIGHT, skyConContainer.id, ConstraintSet.RIGHT, 0)
        constraintSet.connect(skyCon.id, ConstraintSet.LEFT, skyConContainer.id, ConstraintSet.LEFT, 0)
        constraintSet.connect(skyCon.id, ConstraintSet.TOP, skyConContainer.id, ConstraintSet.TOP, 0)
        constraintSet.applyTo(skyConContainer)
        val dateRep = getDateRepresentation(data.time.toLong()).toString()
        val day = dayOfWeek(regexToString(dateRep,"""([A-Z][a-z][a-z])""" ))
        skyConDayTxt.text = day
        skyConSummaryTxt.text = data.summary
        skyConHighTempText.text = "High: ${data.temperatureHigh}°"
        skyConLowTempTxt.text = "Low: ${data.temperatureLow}°"


    }
}
