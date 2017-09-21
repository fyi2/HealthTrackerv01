package info.test.tony.healthtrackerv01.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import info.test.tony.healthtrackerv01.R
import info.test.tony.healthtrackerv01.data.HealthTrackerDatabaseHandler
import info.test.tony.healthtrackerv01.models.HealthStatus

class MainReport : AppCompatActivity() {

    val REPORT1_CODE: Int = 4 // Activity Intent Code
    var dbHandler: HealthTrackerDatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_report)
    }

    fun settingsHome(view: View) {
        var intent = Intent(this, MainActivity::class.java)
        var returnIntent = this.intent
        returnIntent.putExtra("return", "return from Report")
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
        // startActivity(intent) Used for simple intent calls.
    }

    fun runReports(view: View) {
        val intent = Intent(applicationContext, TabbedReport::class.java)
        intent.putExtra("main", "From Main REPORT Screen")
        startActivityForResult(intent, REPORT1_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // requestCode = Integer we decide on
        //resultCode = Android Code
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REPORT1_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                var result = data!!.extras.get("return").toString()
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show()
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun addRecord(view: View){
        dbHandler = HealthTrackerDatabaseHandler(this)

        var healthStatus = HealthStatus()
        healthStatus.depression = 1
        healthStatus.irritable = 1
        healthStatus.anxiety = 1
        healthStatus.excitability = 1
        healthStatus.alcoholDrugs = 1
        healthStatus.meds = 1
        healthStatus.sleep = 7.0
        healthStatus.excercise = 20
        healthStatus.weight = 200.0
        healthStatus.smoking = 0
        healthStatus.mood = 2.5
        healthStatus.naps = 1
        healthStatus.getUpTime = 0

        dbHandler!!.createStatus(healthStatus)

        // and read the first record to test
        var healthStatii: HealthStatus = dbHandler!!.readStatus(1)
        println("DEPRESSION = "+healthStatii.depression)


    }
}
