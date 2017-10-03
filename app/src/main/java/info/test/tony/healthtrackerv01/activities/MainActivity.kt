package info.test.tony.healthtrackerv01.activities
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import info.test.tony.healthtrackerv01.R
import info.test.tony.healthtrackerv01.adapters.ListRecyclerAdapter
import info.test.tony.healthtrackerv01.data.*
import info.test.tony.healthtrackerv01.models.DailyStatus
import info.test.tony.healthtrackerv01.models.HealthStatus
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.daily_list_item.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var dbHandler: TrackerdBHandler? = null
    var recyclerAdapter: ListRecyclerAdapter? = null
    //var dailyStatusList : ArrayList<DailyStatus>? = null
    var healthStatus: ArrayList<HealthStatus>? = null
    var healthStatusItems: ArrayList<HealthStatus>? = null
    var layoutManager : RecyclerView.LayoutManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHandler = TrackerdBHandler(this)

        healthStatus = ArrayList<HealthStatus>()
        healthStatusItems = ArrayList()
        layoutManager = LinearLayoutManager(this)
        recyclerAdapter = ListRecyclerAdapter(this, healthStatusItems!!)

        // Set up Recyler View
        recyclerViewID.layoutManager = layoutManager
        recyclerViewID.adapter = recyclerAdapter

        // load data
        healthStatus = dbHandler!!.readAllStatus()
        healthStatus!!.reverse()

        for(h in healthStatus!!.iterator()){
            val health = HealthStatus()

            health.entryDate = h.entryDate
            health.alcoholDrugs = h.alcoholDrugs
            health.anxiety = h.anxiety
            health.depression = h.depression
            health.excercise = h.excercise
            health.excitability = h.excitability
            health.getUpTime = h.getUpTime
            health.id = h.id
            health.irritable = h.irritable
            health.meds = h.meds
            health.mood = h.mood
            health.naps = h.naps
            health.sleep = h.sleep
            health.smoking = h.smoking
            health.weight = h.weight

            healthStatusItems!!.add(health)
        }

        recyclerAdapter!!.notifyDataSetChanged()





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Attach the menu to the main activity
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // What to do when a specific menu item is selected.
        if(item?.itemId == R.id.appSettings) {
            val intent = Intent(applicationContext, AppSettings::class.java)
            intent.putExtra("main", "From the main screen")
            // startActivity(intent) // Used for simple intents
            startActivityForResult(intent, SETTINGS_CODE)
        }
        return super.onOptionsItemSelected(item)
    }

    // Used on the way back

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // requestCode = Integer we decide on
        //resultCode = Android Code
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SETTINGS_CODE){
            if(resultCode == Activity.RESULT_OK){
                var result = data!!.extras.get("return").toString()
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show()
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }
        }
        if(requestCode == REPORT_CODE){
            if(resultCode == Activity.RESULT_OK){
                var result = data!!.extras.get("return").toString()
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }
        }
        if(requestCode == TEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                var result = data!!.extras.get("return").toString()
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }
        }
        if(requestCode == STARTSTATUS_CODE){
            if(resultCode == Activity.RESULT_OK){
                var result = data!!.extras.get("return").toString()
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }
        }
        if(requestCode == DETAILS_CODE){
            if(resultCode == Activity.RESULT_OK){
                var result = data!!.extras.get("return").toString()
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun reportHome(view: View) {
        val intent = Intent(applicationContext, MainReport::class.java)
        intent.putExtra("main", "From Main Screen")
        // startActivity(intent) // Used for simple intents
        startActivityForResult(intent, REPORT_CODE)
    }

    fun startStatus(view: View) {
        val intent = Intent(applicationContext, AddStatus::class.java)
        intent.putExtra("main", "From Main Screen")
        // startActivity(intent) // Used for simple intents
        startActivityForResult(intent, STARTSTATUS_CODE)
    }
    fun testHome(view: View){
        val intent = Intent(applicationContext, TestActivity::class.java)
        intent.putExtra("main", "From Main Screen")
        startActivityForResult(intent, TEST_CODE)
    }
    fun showStatus(view: View){
        val intent = Intent(applicationContext, TestActivity::class.java)
        intent.putExtra("main", "From Main Screen show icon")
        startActivityForResult(intent, DETAILS_CODE)
    }
}
