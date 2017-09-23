package info.test.tony.healthtrackerv01.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import info.test.tony.healthtrackerv01.R
import kotlinx.android.synthetic.main.activity_app_settings.*


class AppSettings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_settings)

        // Test showing how to grab data from the calling intent
        var data = intent.extras
        if (data != null){
            var test = data.get("main")
            Toast.makeText(this, test.toString(), Toast.LENGTH_SHORT).show()
        }

        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
// Initialize app with defaults if they do not exist
        var biPolarDefault = true
        var napsDefault = false
        var getUpDefault = true
        var smokingDefault = false
        var alcoholDefault = false
        var yesterdayDefault = true
        var medicationDefault = 1
        var weighDayDefault = 0
        var initialized = true

        if(!sharedPreferences.contains("initialized")){
            sharedPreferences.edit().putBoolean("bipolar", biPolarDefault).apply()
            sharedPreferences.edit().putBoolean("naps", napsDefault).apply()
            sharedPreferences.edit().putBoolean("getup", getUpDefault).apply()
            sharedPreferences.edit().putBoolean("smoking", smokingDefault).apply()
            sharedPreferences.edit().putBoolean("alcohol", alcoholDefault).apply()
            sharedPreferences.edit().putBoolean("yesterday", yesterdayDefault).apply()
            sharedPreferences.edit().putInt("medication", medicationDefault).apply()
            sharedPreferences.edit().putInt("weight", weighDayDefault).apply()
            sharedPreferences.edit().putString("initialized", "initialized").apply()
            println("PRINT:Initializing Preferences")
            println(sharedPreferences.all)
        } else {
            println("PRINT:Preferences already there")
            println(sharedPreferences.all)
        }
        // Read the preferences and set the respective widgets
        var yesterdaySetting = sharedPreferences.getBoolean("yesterday", true)
        var medicationSetting = sharedPreferences.getInt("medication", 1)
        var biPolarSetting = sharedPreferences.getBoolean("bipolar", true)
        var napsSetting = sharedPreferences.getBoolean("naps", true)
        var getUpSetting = sharedPreferences.getBoolean("getup", true)
        var smokingSetting = sharedPreferences.getBoolean("smoking", true)
        var alcoholSetting = sharedPreferences.getBoolean("alcohol", true)
        var weighDaySetting = sharedPreferences.getInt("weight", 0)

        // set Default Day
        var yesterdayBox : RadioButton =  findViewById(R.id.yesterdayID)
        var todayBox : RadioButton =  findViewById(R.id.todayID)
        if(!yesterdaySetting){
            todayBox.isChecked = true
        } else {
            yesterdayBox.isChecked = true
        }
        // Set Weigh Day
        when(weighDaySetting){
            0 -> {
                val weighNone: RadioButton = findViewById(R.id.weighNoneID)
                weighNone.isChecked = true
            }
            1 -> {
                val weighMonday: RadioButton = findViewById(R.id.weighMondayID)
                weighMonday.isChecked = true
            }
            2 -> {
                val weighTuesday: RadioButton = findViewById(R.id.weighTuesdayID)
                weighTuesday.isChecked = true
            }
            3 -> {
                val weighWednesday: RadioButton = findViewById(R.id.weighWednesdayID)
                weighWednesday.isChecked = true
            }
            4 -> {
                val weighThursday: RadioButton = findViewById(R.id.weightThursdayID)
                weighThursday.isChecked = true
            }
            5 -> {
                val weighFriday: RadioButton = findViewById(R.id.weighFridayID)
                weighFriday.isChecked = true
            }
        }
        println(sharedPreferences.all)
        // Set the Medication
        var amBox: CheckBox = findViewById(R.id.amID)
        var pmBox: CheckBox = findViewById(R.id.pmID)
        when (medicationSetting){
            0 -> {
                amBox.isChecked = false
                pmBox.isChecked = false
            }
            1 -> {
                amBox.isChecked = true
                pmBox.isChecked = false
            }
            2 -> {
                amBox.isChecked = false
                pmBox.isChecked = true
            }
            3 -> {
                amBox.isChecked = true
                pmBox.isChecked = true
            }
        }
        println(sharedPreferences.all)
        // set BiPolar
        var bipolarBox: Switch = findViewById(R.id.bipolarID)
        bipolarBox.isChecked = biPolarSetting

        // set Naps
        var napsBox: Switch = findViewById(R.id.napID)
        napsBox.isChecked = napsSetting

        // set BiPolar
        var getUpBox: Switch = findViewById(R.id.getUpID)
        getUpBox.isChecked = getUpSetting

        // set BiPolar
        var smokingBox: Switch = findViewById(R.id.smokingID)
        smokingBox.isChecked = smokingSetting

        // set BiPolar
        var alcoholBox: Switch = findViewById(R.id.alcoholDrugsID)
        alcoholBox.isChecked = alcoholSetting

        println(sharedPreferences.all)



    }
    fun onRadioButtonClicked (view: View) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        view as RadioButton
        var isChecked: Boolean = view.isChecked

        when (view.id) {
            R.id.yesterdayID -> if(isChecked){
                sharedPreferences.edit().putBoolean("yesterday", true).apply()
                println("Yesterday Checked")
                println(sharedPreferences.all)
            } else{
                sharedPreferences.edit().putBoolean("yesterday", false).apply()
            }
            R.id.todayID -> if(isChecked){
                sharedPreferences.edit().putBoolean("yesterday", false).apply()
                println("Today Checked")
                println(sharedPreferences.all)
            }
        }
    }
    fun onCheckBoxClicked(view: View){
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        view as CheckBox
        var isChecked: Boolean = view.isChecked
        var medicationValue = 0
        if(amID.isChecked){
            medicationValue++
        }
        if (pmID.isChecked){
            medicationValue += 2
        }
        sharedPreferences.edit().putInt("medication", medicationValue).apply()
        println("medication vale is $medicationValue")
        println(sharedPreferences.all)
    }
    // Set the weight
    fun setWeighDay(view: View) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        var clickedButton : RadioButton = view as RadioButton
        val clickedButtonID: Int = clickedButton.id

        println("ENTERED Set Weight")
        println(sharedPreferences.all)
        when(clickedButtonID){
            weighNoneID.id -> sharedPreferences.edit().putInt("weight", 0).apply()
            weighMondayID.id  -> sharedPreferences.edit().putInt("weight", 1).apply()
            weighTuesdayID.id  -> sharedPreferences.edit().putInt("weight", 2).apply()
            weighWednesdayID.id  -> sharedPreferences.edit().putInt("weight", 3).apply()
            weightThursdayID.id  -> sharedPreferences.edit().putInt("weight", 4).apply()
            weighFridayID.id  -> sharedPreferences.edit().putInt("weight", 5).apply()
            else -> println("NO MATCH THE VALUE IS $clickedButtonID")
        }
        println("CHANGED WEIGHT DAY")
        println(sharedPreferences.all)
    }


    fun bipolarSwitch(view: View){
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        view as Switch
        var isChecked: Boolean = view.isChecked
        var switchState: Boolean = false

        if (view.isChecked){
            switchState = true
        }
        sharedPreferences.edit().putBoolean("bipolar", switchState).apply()
        println("Bipolar is $switchState")
        println(sharedPreferences.all)
    }
    fun napsSwitch(view: View){
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        view as Switch
        var isChecked: Boolean = view.isChecked
        var switchState: Boolean = false

        if (view.isChecked){
            switchState = true
        }
        sharedPreferences.edit().putBoolean("naps", switchState).apply()
        println("Nap is $switchState")
        println(sharedPreferences.all)
    }
    fun getUpSwitch(view: View){
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        view as Switch
        var isChecked: Boolean = view.isChecked
        var switchState: Boolean = false

        if (view.isChecked){
            switchState = true
        }
        sharedPreferences.edit().putBoolean("getUp", switchState).apply()
        println("Get Up is $switchState")
        println(sharedPreferences.all)
    }
    fun smokingSwitch(view: View){
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        view as Switch
        var isChecked: Boolean = view.isChecked
        var switchState: Boolean = false

        if (view.isChecked){
            switchState = true
        }
        sharedPreferences.edit().putBoolean("smoking", switchState).apply()
        println("Smoking is $switchState")
        println(sharedPreferences.all)
    }
    fun alcoholSwitch(view: View){
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        view as Switch
        var isChecked: Boolean = view.isChecked
        var switchState: Boolean = false

        if (view.isChecked){
            switchState = true
        }
        sharedPreferences.edit().putBoolean("alcohol", switchState).apply()
        println("Alcohol is $switchState")
        println(sharedPreferences.all)
    }
    fun resetPreferences(view: View) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("info.test.tony.healthtrackerv01", android.content.Context.MODE_PRIVATE)
        println(sharedPreferences.all)
        sharedPreferences.edit().clear().commit()
        println("NOW I HAVE reset the preferences")
        println(sharedPreferences.all)
    }
    fun settingsHome(view: View) {
        var intent = Intent(this, MainActivity::class.java)
        var returnIntent = this.intent
        returnIntent.putExtra("return", "return from Settings")
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
        // startActivity(intent) Used for simple intent calls.
    }
    fun settingsHelp(view: View) {
        var messageView: View = layoutInflater.inflate(R.layout.about, null, false)

        var builder :AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setView(messageView)
        builder.setPositiveButton("OK", {_,_ -> })
        builder.create()
        builder.show()

    }
}
