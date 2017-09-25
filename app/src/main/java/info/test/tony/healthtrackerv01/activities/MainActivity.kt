package info.test.tony.healthtrackerv01.activities
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import info.test.tony.healthtrackerv01.Globals
import info.test.tony.healthtrackerv01.R
import info.test.tony.healthtrackerv01.data.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}
