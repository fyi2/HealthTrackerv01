package info.test.tony.healthtrackerv01.activities

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.Button
import info.test.tony.healthtrackerv01.Globals
import info.test.tony.healthtrackerv01.R
import info.test.tony.healthtrackerv01.adapters.QuestionPagerAdapter
import info.test.tony.healthtrackerv01.data.*
import kotlinx.android.synthetic.main.fragment_top.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddStatus : AppCompatActivity() {
    lateinit var pager : ViewPager
    val mh = Globals.MentalHealth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_status)

        //  Set up the boolean array that tracks if a screen is needed based on the settings
        // and set the fragment tracker to 0
        setRequiredScreens()
        mh.currentLoadedFragment = 0

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


        // Add in the adapter
        val adapter = QuestionPagerAdapter(supportFragmentManager)
        pager = findViewById<View>(R.id.swipe_area) as ViewPager

        pager.adapter = adapter
    }

    // Set Up Global screen mapping
    fun setRequiredScreens() {
        // read current settings

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        println("SHARED PREFERENCES + ${sharedPreferences.all}")
        var biPolarSetting = sharedPreferences.getBoolean("bipolar", true)
        var napsSetting = sharedPreferences.getBoolean("naps", true)
        var getUpSetting = sharedPreferences.getBoolean("getup", true)
        var smokingSetting = sharedPreferences.getBoolean("smoking", true)
        var alcoholSetting = sharedPreferences.getBoolean("alcohol", true)
        var weighDaySetting = sharedPreferences.getInt("weight", 0)
        // set special cases: Excitability, Alcohol, Naps, GetUpTime, Weight, Smoking
        if(!biPolarSetting){
            mh.switchStatus[BIPOLAR_POSN] = 0
//            println("TURNING Bipolar OFF")
        }
        if(!napsSetting){
            mh.switchStatus[NAPS_POSN] = 0
//            println("TURNING naps OFF")
        }
        if(!getUpSetting){
            mh.switchStatus[GET_UP_POSN] = 0
//            println("TURNING get up OFF")
        }
        if(!smokingSetting) {
            mh.switchStatus[SMOKING_POSN]=0
//            println("TURNING smoking OFF")
        }
        if(!alcoholSetting){
            mh.switchStatus[ALCOHOL_DRUGS_POSN] = 0
//            println("TURNING alcohol OFF")
        }
        if(day != weighDaySetting){
            mh.switchStatus[WEIGHT_POSN] = 0
//            println("TURNING weight OFF")
        }
    }


    fun changeDate(view: View) {

        var currentDate: Calendar = Calendar.getInstance()
        var thisAYear = currentDate.get(Calendar.YEAR).toInt()
        var thisAMonth = currentDate.get(Calendar.MONTH).toInt()
        var thisADay = currentDate.get(Calendar.DAY_OF_MONTH).toInt()

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view2, thisYear, thisMonth, thisDay ->
            // Display Selected date in textbox
            thisAMonth = thisMonth + 1
            statusDateID.setText("Date: " + thisAMonth+"/"+thisDay + "/" +  thisYear)
        }, thisAYear, thisAMonth, thisADay)
        dpd.show()
    }



    // Debugging function
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


    fun loadNext(view: View){
// Load the next screen, the state is being tracked in mh.currentLoadedFragment
        pager.setCurrentItem(mh.currentLoadedFragment)

    }



 //***********************************************************************************************//
/*   Keep because this shows how to swap out fragments
//************************************************************************************************//
    fun loadQ1(view: View){
        printGlobals()
        //Start the questions and load the first one into Fragment 2
        var fm: FragmentManager = getFragmentManager()
        var ft: FragmentTransaction = fm.beginTransaction()

        ft.replace(R.id.fragment2, Q1Fragment())
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
*/
}
