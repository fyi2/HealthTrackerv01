package info.test.tony.healthtrackerv01.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.*
import info.test.tony.healthtrackerv01.data.Globals
import info.test.tony.healthtrackerv01.R
import info.test.tony.healthtrackerv01.adapters.QuestionFragmentAdapter
import info.test.tony.healthtrackerv01.data.*
import info.test.tony.healthtrackerv01.models.HealthStatus
import kotlinx.android.synthetic.main.fragment_top.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class AddStatus : AppCompatActivity() {
    lateinit var pager: ViewPager
    val mh = Globals.MentalHealth
    var dbHandler: TrackerdBHandler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_status)

        //  Set up the boolean array that tracks if a screen is needed based on the settings
        // and set the fragment tracker to 0
        setRequiredScreens()
        setdatabaseDefaults()
        mh.currentLoadedFragment = 0

        // Set the default date
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        var yesterdaySetting = sharedPreferences.getBoolean("yesterday", true)
        var calendarOffset = -1
        if (!yesterdaySetting) {
            calendarOffset++
        }
        var calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, calendarOffset)
        val dateFormat: DateFormat = SimpleDateFormat("MM/dd/yyy")
        statusDateID.setText("Date: " + dateFormat.format(calendar.time))


        // Add in the recyclerAdapter
        val adapter = QuestionFragmentAdapter(supportFragmentManager)
        pager = findViewById<View>(R.id.swipe_area) as ViewPager

        pager.adapter = adapter
        numberOfScreens(adapter)
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
        var medicationSetting = sharedPreferences.getInt("medication", 1)
        // set special cases: Excitability, Alcohol, Naps, GetUpTime, Weight, Smoking
        if (!biPolarSetting) {
            mh.switchStatus[BIPOLAR_POSN] = 0

        }
        if (!napsSetting) {
            mh.switchStatus[NAPS_POSN] = 0
        }
        if (!getUpSetting) {
            mh.switchStatus[GET_UP_POSN] = 0
        }
        if (!smokingSetting) {
            mh.switchStatus[SMOKING_POSN] = 0
        }
        if (!alcoholSetting) {
            mh.switchStatus[ALCOHOL_DRUGS_POSN] = 0
        }
        if ((weighDaySetting == 0) || (day != weighDaySetting + 1)) {
            mh.switchStatus[WEIGHT_POSN] = 0
        }
        if(medicationSetting == 0){
            mh.switchStatus[MEDS_POSN] = 0
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
            thisADay = thisDay
            thisAYear = thisYear
            statusDateID.setText("Date: " + thisAMonth + "/" + thisDay + "/" + thisYear)
        }, thisAYear, thisAMonth, thisADay)
        dpd.show()
        currentDate.set(thisAYear,thisAMonth, thisADay)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        mh.entryDate = currentDate.timeInMillis
    }


    // Debugging function
    fun printGlobals() {
        val mh = Globals.MentalHealth

        println("GLOBAL VARIABLES: " + mh.anxiety + ", " +
                mh.irritable + ", " +
                mh.depression + ", " +
                mh.excitability + ", " +
                mh.alcoholDrugs + ", " +
                mh.meds + ", " +
                mh.naps + ", " +
                mh.getUpTime + ", " +
                mh.sleep + ", " +
                mh.excercise + ", " +
                mh.weight + ", " +
                mh.smoking + ", " +
                mh.mood)
    }


    fun loadNext(view: View) {
// Load the next screen, the state is being tracked in mh.currentLoadedFragment
        pager.setCurrentItem(mh.currentLoadedFragment)

    }

    fun numberOfScreens(adapter: QuestionFragmentAdapter) {
        // Now we have worked out the screen flow based on settings, tell the system the number of screens we will use
        val mh = Globals.MentalHealth
        val switches = mh.switchStatus
        var pages = IntArray(NUM_QUESTIONS, { it * 1 })
        var screenNumber = 0

        // build actual screen Array and
        for (i in 0..NUM_QUESTIONS - 1) {
            if (switches[i] == 1) {
                pages[screenNumber] = i
                screenNumber++
            }
        }
        mh.maxScreens = screenNumber // Reset the maximum number of screens
        adapter.notifyDataSetChanged()
    }

    fun setdatabaseDefaults(){
    // Set the default values incase the user just skips through the screens
        mh.depression = 1
        mh.irritable = 1
        mh.anxiety = 1
        mh.excitability = 1
        mh.alcoholDrugs = 0
        mh.meds = 1
        mh.sleep = 6.5
        mh.excercise = 20
        mh.weight = 295.0
        mh.smoking = 0
        mh.mood = 2.5
        mh.naps = 0
        mh.getUpTime = 0
    }
    fun setMeds(view: View) {
        // Disable buttons not needed
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        var medicationSetting = sharedPreferences.getInt("medication", 1)
        var tb1 : ToggleButton = findViewById(R.id.toggleButton3)
        var tb2 : ToggleButton = findViewById(R.id.toggleButton4)
        var tb1Value = 0
        var tb2Value = 0
        // if 1 disable PM, if 2 disable AM
        if(medicationSetting == 1){
            tb2.isEnabled = false
        }
        if (medicationSetting == 2){
            tb1.isEnabled = false
        }
        // get value
        if ((tb1.isChecked) && (tb1.isEnabled)) {
            tb1Value = 1
        }
        if((tb2.isChecked) && (tb2.isEnabled)){
            tb2Value = 2
        }
        mh.meds = tb1Value + tb2Value
    }

    fun setAlcohol(view: View) {
        // Set the alcohol in take
        view as RadioButton
        var isChecked: Boolean = view.isChecked

        when (view.id) {
            R.id.daNone -> if (isChecked) {
                mh.alcoholDrugs = 0
            }
            R.id.daSome -> if (isChecked) {
                mh.alcoholDrugs = 1
            }
            R.id.daModerate -> if (isChecked) {
                mh.alcoholDrugs = 2
            }
            R.id.daExcess -> if (isChecked) {
                mh.alcoholDrugs = 3
            }
        }
    }

    fun setGut(view: View) {
        // Set the Get Up Time
        view as RadioButton
        var isChecked: Boolean = view.isChecked

        when (view.id) {
            R.id.gutNone -> if (isChecked) {
                mh.getUpTime = 0
            }
            R.id.gutSome -> if (isChecked) {
                mh.getUpTime = 1
            }
            R.id.gutModerate -> if (isChecked) {
                mh.getUpTime = 2
            }
            R.id.gutExcess -> if (isChecked) {
                mh.getUpTime = 3
            }
        }

    }
    fun napCount(view: View) {
        view as EditText
        var napCount : EditText = findViewById(R.id.editInteger_7)
        if(napCount.text.toString() != "") {
            mh.naps = napCount.text.toString().toInt()
            printGlobals()
        } else {
            mh.naps = -1
        }
    }
    fun sleepTime(view: View) {
        view as EditText
        var sleepHours : EditText = findViewById(R.id.editDouble_9)
        if(sleepHours.text.toString() != ""){
            mh.sleep = sleepHours.text.toString().toDouble()
            printGlobals()

        } else {
            sleepHours.requestFocus()
        }
    }
    fun excerciseTime(view: View) {
        view as EditText
        var exMinutes : EditText = findViewById(R.id.editInteger_10)
        if(exMinutes.text.toString() != "") {
            mh.excercise = exMinutes.text.toString().toInt()
            printGlobals()
        } else {
            exMinutes.requestFocus()
        }
    }
    fun weight(view: View) {
        view as EditText
        var weightLbs : EditText = findViewById(R.id.editDouble_11)
        if(weightLbs.text.toString() != "") {
            mh.weight = weightLbs.text.toString().toDouble()
            printGlobals()
        } else {
            weightLbs.requestFocus()
        }
    }
    fun cigaretteCount(view: View) {
        view as EditText
        var cigarettes : EditText = findViewById(R.id.editInteger_12)
        if(cigarettes.text.toString() !=  "") {
            mh.smoking = cigarettes.text.toString().toInt()
            printGlobals()
        } else {
            cigarettes.requestFocus()
        }
    }
    fun moodStars (view: View){
        view as RatingBar
        var mood : RatingBar = findViewById(R.id.ratingBar2)
        mh.mood = mood.rating.toDouble()
    }
    fun updateDatabase(view: View) {
        dbHandler = TrackerdBHandler(this)
        printGlobals()
        var intent = Intent(this, MainActivity::class.java)
        var returnIntent = this.intent
        // open the database
        var healthStatus = HealthStatus()
        // Write the record
        healthStatus.depression = mh.depression
        healthStatus.irritable = mh.irritable
        healthStatus.anxiety = mh.anxiety
        healthStatus.excitability = mh.excitability
        healthStatus.alcoholDrugs = mh.alcoholDrugs
        healthStatus.meds = mh.meds
        healthStatus.sleep = mh.sleep
        healthStatus.excercise = mh.excercise
        healthStatus.weight = mh.weight
        healthStatus.smoking = mh.smoking
        healthStatus.mood = mh.mood as Double
        healthStatus.naps = mh.naps
        healthStatus.getUpTime = mh.getUpTime
        healthStatus.entryDate = mh.entryDate

        // close database
        var insert = dbHandler!!.createStatus(healthStatus)
        // return from intent

        returnIntent.putExtra("return", "return from Adding a Status")
        setResult(Activity.RESULT_OK, returnIntent)
        finish()

    }

}


