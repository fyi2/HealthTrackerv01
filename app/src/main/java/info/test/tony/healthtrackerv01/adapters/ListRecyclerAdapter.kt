package info.test.tony.healthtrackerv01.adapters

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import info.test.tony.healthtrackerv01.R
import info.test.tony.healthtrackerv01.activities.ShowDetailsActivity
import info.test.tony.healthtrackerv01.data.DETAILS_CODE
import info.test.tony.healthtrackerv01.data.Globals
import info.test.tony.healthtrackerv01.data.STARTSTATUS_CODE
import info.test.tony.healthtrackerv01.data.TrackerdBHandler
import info.test.tony.healthtrackerv01.models.HealthStatus
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// Custom Recycler Adapter

class ListRecyclerAdapter(context: Context, dailies: ArrayList<HealthStatus>): RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder>() {

    val context = context
    val dailies = dailies

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindViews(dailies[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder{
        // Create our View from XML file

        val view = LayoutInflater.from(context).inflate(R.layout.daily_list_item, parent, false )

        return ViewHolder(view, context, dailies)

    }

    override fun getItemCount(): Int {
        return dailies.count()
    }

    inner  class ViewHolder(itemView: View, context: Context, list: ArrayList<HealthStatus>) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var mcontext = context
        var mList = list

        var deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButtonID)
        var showButton = itemView.findViewById<ImageButton>(R.id.searchButtonID)
        var ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar3)

        fun bindViews(healthStatus: HealthStatus){
            var mood: RatingBar = itemView.findViewById<RatingBar>(R.id.ratingBar3)
            var date: TextView = itemView.findViewById<TextView>(R.id.statusID2)

            mood.rating = healthStatus.mood!!.toFloat()
            date.text = humanDate(healthStatus.entryDate!!)
            println("SET DATE FROM DATA: "+healthStatus.entryDate)

            deleteButton.setOnClickListener(this)
            showButton.setOnClickListener(this)
            ratingBar.setOnClickListener(this)

        }

        override fun onClick(view: View?){

            var mPosition: Int = adapterPosition
            var status = mList[mPosition]

            when(view!!.id){
                deleteButton.id -> {
                    deleteStatus(status.id!!)
                    mList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }
                showButton.id -> {
                    showDetails(view, status)
                    Toast.makeText(mcontext, "Show Button", Toast.LENGTH_LONG).show()}
                ratingBar.id -> {
                    Toast.makeText(mcontext, "Show Button", Toast.LENGTH_LONG).show()
                    ratingBar.isEnabled = false}
            }
        }


        fun deleteStatus(id: Int){
            var db: TrackerdBHandler = TrackerdBHandler(mcontext)
            db.deleteStatus(id)
        }

        fun humanDate(epoch: Long) : String{
            var calendar: Calendar = Calendar.getInstance()
            val dateFormat: DateFormat = SimpleDateFormat("MM/dd/yyy")
            println("RECEIVED : "+epoch)
            println("TRANSFORMED TO "+dateFormat.format(epoch))
            return dateFormat.format(epoch)
        }

    }
    fun showDetails(view: View?, status: HealthStatus){
        setGlobal(status)
        val intent: Intent = Intent(context, ShowDetailsActivity::class.java)
        intent.putExtra("main", "Search From Main Screen")
        startActivity(context, intent, Bundle())
    }
    fun setGlobal(status: HealthStatus){
        val mh = Globals.MentalHealth

        mh.alcoholDrugs = status.alcoholDrugs
        mh.anxiety = status.anxiety
        mh.depression = status.depression
        mh.entryDate = status.entryDate
        mh.excercise = status.excercise
        mh.excitability = status.excitability
        mh.getUpTime = status.getUpTime
        mh.irritable = status.irritable
        mh.meds = status.meds
        mh.mood = status.mood
        mh.weight = status.weight
        mh.smoking = status.smoking
        mh.sleep = status.sleep
        mh.naps = status.naps
    }

}