package com.example.retropractice.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retropractice.Model.Data
import com.example.retropractice.R
import com.example.retropractice.Utilities.getCurrDate
import com.example.retropractice.Utilities.regexToString
import com.example.retropractice.Utilities.toMonth
import java.util.*

class dailyItemRecyclerViewAdapter(val context: Context, val dailyDataList: List<Data>, val dailyItemClick: (Data)->Unit) : RecyclerView.Adapter<dailyItemRecyclerViewAdapter.RvHolder>() {

    inner class RvHolder(itemView: View, val itemClick: (Data) -> Unit) : RecyclerView.ViewHolder(itemView) { // so we create one <- view holder

        // in charge of binding? so lets bind these values (we need image and name here)
        val iconImg = itemView.findViewById<ImageView>(R.id.iconImg) //grab imgVw ref
        val dayText = itemView.findViewById<TextView>(R.id.dayText)//grab textVw ref
        val lowTemp = itemView.findViewById<TextView>(R.id.lowTempText)
        val highTemp = itemView.findViewById<TextView>(R.id.highTempText)


        // function to pass in the category to bind it to to the ui elements
        /**
         * <h1>bindCategory</h1>
         * bind Category sets each items image and name
         * based on the category passed into it.
         * <b>
         *     Sets the onclick listener to a lambda
         * @param dataItem
         * @param context
         */
        fun bindCategory(dataItem:Data, context: Context){ // need context to find resource based on the name


            val zuluTime = getCurrDate(dataItem.time.toLong())
            println(zuluTime)
            val regexYear = """(2\d\d\d)"""
            val regexMonth= """-(\d\d)-"""
            val regexDay = """([1-9]?[0-9]) """
            var year:String = ""
            var month:String = ""
            var day:String = ""

            year = regexToString(zuluTime,regexYear)
            month = regexToString(zuluTime,regexMonth)
            day = regexToString(zuluTime,regexDay)

            val cal = Calendar.getInstance()
            cal[Calendar.YEAR] = year.toInt()
            cal[Calendar.MONTH] = toMonth(month)
            cal[Calendar.DAY_OF_MONTH] = day.toInt()
            val dateRepresentation = cal.time


            val resourceId = context.resources.getIdentifier(dataItem.icon, "drawable",context.packageName) //?
            iconImg.setImageResource(resourceId)
           // dayText.text = dataItem.time.toString() // i need to convert this from UNIX
            println("-----\n${dateRepresentation}\n----")
            dayText.text = regexToString(dateRepresentation.toString(),"""([A-Z][a-z][a-z])""" )
            highTemp.text = "${dataItem.temperatureHigh}°"
            lowTemp.text = "${dataItem.temperatureLow}°"

            itemView.setOnClickListener { dailyItemClick(dataItem) } // set onclick for each item?
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.daily_list_item, parent, false
        )
        return RvHolder(view, dailyItemClick)

    }

    override fun getItemCount(): Int {
        return dailyDataList.count()
    }

    override fun onBindViewHolder(myViewHolder: RvHolder, position: Int) {
        myViewHolder.bindCategory(dailyDataList[position], context)
    }
}