package info.test.tony.healthtrackerv01.activities

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import info.test.tony.healthtrackerv01.R
import kotlinx.android.synthetic.main.fragment_top.*
import java.util.*

class AddStatus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_status)
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
}
