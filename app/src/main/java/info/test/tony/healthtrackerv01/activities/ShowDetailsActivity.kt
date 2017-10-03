package info.test.tony.healthtrackerv01.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import info.test.tony.healthtrackerv01.R
import info.test.tony.healthtrackerv01.data.Globals
import info.test.tony.healthtrackerv01.models.HealthStatus
import kotlinx.android.synthetic.main.activity_show_details.*

class ShowDetailsActivity : AppCompatActivity() {
    val mh = Globals.MentalHealth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)
        anxietyDetailViewID.text = "Anxiety Level was "+mh.anxiety
        irritabilityDetailViewID.text = "Irritability Level was "+mh.irritable
        depressionDetailViewID.text = "Depression level was "+mh.depression
        excitabilityDetailViewID.text = "Manic excitement level was "+mh.excitability
        alcoholDetailViewID.text = "Drug and Alcohol level was "+mh.alcoholDrugs
        medicationDetailViewID.text = "Medication adherance level was "+mh.meds
        napsDetailViewID.text = "Number of naps during the day was "+mh.naps
        sleepDetailViewID.text = "Number of hours slept "+mh.sleep
        excerciseDetailViewID.text = "Number of minutes excercised "+mh.excercise
        weightDetailViewID.text = "Weight (if entered) "+mh.weight
        smokingDetailViewID.text = "Number of cigarettes smoked "+mh.smoking
        moodDetailViewID.text = "Overall Mood "+mh.mood
    }
    fun homeScreen(view: View) {
        var intent = Intent(this, MainActivity::class.java)
        var returnIntent = this.intent
        returnIntent.putExtra("return", "return from Show details")
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

}
