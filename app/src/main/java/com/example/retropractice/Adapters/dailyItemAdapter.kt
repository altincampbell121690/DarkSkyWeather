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

class dailyItemAdapter(val context: Context, val dailyDataList: List<Data>, val dailyItemClick: (Data)->Unit) : RecyclerView.Adapter<dailyItemAdapter.RvHolder>() {

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
            val resourceId = context.resources.getIdentifier(dataItem.icon, "drawable",context.packageName) //?
            iconImg.setImageResource(resourceId)
            dayText.text = dataItem.time.toString() // i need to convert this from UNIX
            highTemp.text = dataItem.temperatureHigh.toString()
            lowTemp.text = dataItem.temperatureLow.toString()

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