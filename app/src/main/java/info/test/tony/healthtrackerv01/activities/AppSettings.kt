package info.test.tony.healthtrackerv01.activities

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Switch
import android.widget.Toast
import info.test.tony.healthtrackerv01.R
import kotlinx.android.synthetic.main.activity_app_settings.*


class AppSettings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_settings)

        // Test showing how to grab data from the calling intent
        var data = intent.extras
        if (data != null){
            var test = data.get("test")
            Toast.makeText(this, test.toString(), Toast.LENGTH_SHORT).show()
        }

        val sharedPreferences: SharedPreferences = getSharedPreferences("com.example.tony.healthmonitorv01", android.content.Context.MODE_PRIVATE)
// Initialize app with defaults if they do not exist
        var biPolarDefault = true
        var yesterdayDefault = true
        var medicationDefault = 1
        var initialized = true

        if(!sharedPreferences.contains("initialized")){
            sharedPreferences.edit().putBoolean("bipolar", biPolarDefault).apply()
            sharedPreferences.edit().putBoolean("yesterday", yesterdayDefault).apply()
            sharedPreferences.edit().putInt("medication", medicationDefault).apply()
            sharedPreferences.edit().putString("initialized", "initialized").apply()
            sharedPreferences.edit()
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

        // set Default Day
        var yesterdayBox : RadioButton =  findViewById(R.id.yesterdayID)
        var todayBox : RadioButton =  findViewById(R.id.todayID)
        if(!yesterdaySetting){
            todayBox.isChecked = true
        } else {
            yesterdayBox.isChecked = true
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


    }
    fun onRadioButtonClicked (view: View) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("com.example.tony.healthmonitorv01", android.content.Context.MODE_PRIVATE)
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
        val sharedPreferences: SharedPreferences = getSharedPreferences("com.example.tony.healthmonitorv01", android.content.Context.MODE_PRIVATE)
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
    fun onSwitchClicked(view: View){
        val sharedPreferences: SharedPreferences = getSharedPreferences("com.example.tony.healthmonitorv01", android.content.Context.MODE_PRIVATE)
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
    /* fun killPrefs(view: View) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("com.example.tony.healthmonitorv01", android.content.Context.MODE_PRIVATE)
        println(sharedPreferences.all)
        sharedPreferences.edit().clear().commit()
        println("Now I have")
        println(sharedPreferences.all)
    } */
    fun settingsHome(view: View) {
        var intent = Intent(this, MainActivity::class.java)
        var returnIntent = this.intent
        returnIntent.putExtra("return", "return from Settings")
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
        // startActivity(intent) Used for simple intent calls.
    }
}
