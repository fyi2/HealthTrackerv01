package info.test.tony.healthtrackerv01.activities


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import info.test.tony.healthtrackerv01.R
import info.test.tony.healthtrackerv01.adapters.SectionMenuAdapter
import kotlinx.android.synthetic.main.activity_tabbed_report.*


class TabbedReport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var sectionAdapter: SectionMenuAdapter

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed_report)
//        setSupportActionBar(toolbar)

        sectionAdapter = SectionMenuAdapter(supportFragmentManager)
        viewPagerID.adapter = sectionAdapter
        tabs.setupWithViewPager(viewPagerID)
        tabs.setTabTextColors(Color.WHITE, Color.GREEN)
    }
    fun settingsHome(view: View) {
        var intent = Intent(this, MainActivity::class.java)
        var returnIntent = this.intent
        returnIntent.putExtra("return", "return from Tabbed Report")
        setResult(Activity.RESULT_OK, returnIntent)
        finish()

    }


}
