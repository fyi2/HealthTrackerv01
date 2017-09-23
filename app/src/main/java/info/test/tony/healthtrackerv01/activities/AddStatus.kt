package info.test.tony.healthtrackerv01.activities

import android.app.DatePickerDialog
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import info.test.tony.healthtrackerv01.Globals
import info.test.tony.healthtrackerv01.R
import info.test.tony.healthtrackerv01.fragments.Q1Fragment
import info.test.tony.healthtrackerv01.fragments.Q2Fragment
import info.test.tony.healthtrackerv01.fragments.Q3Fragment
import kotlinx.android.synthetic.main.fragment_q1.*
import kotlinx.android.synthetic.main.fragment_top.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddStatus : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_status)

        // Set the default date
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        var yesterdaySetting = sharedPreferences.getBoolean("yesterday", true)
        var calendarOffset = -1
        if(!yesterdaySetting){
            calendarOffset++
        }
        var calendar:Calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, calendarOffset)
        val dateFormat: DateFormat = SimpleDateFormat("MM/dd/yyy")
        statusDateID.setText("Date: "+dateFormat.format(calendar.time))
    }

    fun changeDate(view: View) {

        var currentDate: Calendar = Calendar.getInstance()
        var thisAYear = currentDate.get(Calendar.YEAR).toInt()
        var thisAMonth = currentDate.get(Calendar.MONTH).toInt()
        var thisADay = currentDate.get(Calendar.DAY_OF_MONTH).toInt()
        var statusButton: Button = findViewById(R.id.enterStatusID)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view2, thisYear, thisMonth, thisDay ->
            // Display Selected date in textbox
            thisAMonth = thisMonth + 1
            statusDateID.setText("Date: " + thisAMonth+"/"+thisDay + "/" +  thisYear)
        }, thisAYear, thisAMonth, thisADay)
        dpd.show()
        enterStatusID.requestFocus()
    }

/*    fun updateAnxiety(view: View){
        // Get the value from the anxietySeekBarID
        var anxietyNumber: Int = anxietySeekBarID.progress
        println("ANXIETY NUMBER $anxietyNumber")
        // reflect status in anxietyStatusID
        anxietyStatusID.text = anxietyNumber.toString()
    }
*/
    fun printGlobals() {
        val mh = Globals.MentalHealth

        println("GLOBAL VARIABLES: "+ mh.depression+", "+
                mh.irritable+", "+
                mh.anxiety+", "+
                mh.excitability+", "+
                mh.alcoholDrugs+", "+
                mh.meds+", "+
                mh.sleep+", "+
                mh.excercise+", "+
                mh.weight+", "+
                mh.smoking+", "+
                mh.mood+", "+
                mh.naps+", "+
                mh.getUpTime)
}


    fun loadQ1(view: View){
        printGlobals()
        //Start the questions and load the first one into Fragment 2
        var fm: FragmentManager = getFragmentManager()
        var ft: FragmentTransaction = fm.beginTransaction()

        ft.replace(R.id.fragment2, Q1Fragment())
        ft.commit()
    }
    fun loadQ2(view: View){
        printGlobals()

        var fm: FragmentManager = getFragmentManager()
        var ft: FragmentTransaction = fm.beginTransaction()

        ft.replace(R.id.fragment2, Q2Fragment())
        ft.commit()

    }
    fun loadQ3(view: View){
        printGlobals()

        var fm: FragmentManager = getFragmentManager()
        var ft: FragmentTransaction = fm.beginTransaction()

        ft.replace(R.id.fragment2, Q3Fragment())
        ft.commit()

    }
    fun loadQ4(view: View){
        printGlobals()

        var fm: FragmentManager = getFragmentManager()
        var ft: FragmentTransaction = fm.beginTransaction()

        ft.replace(R.id.fragment2, Q1Fragment())
        ft.commit()

    }
}
