package info.test.tony.healthtrackerv01

import android.support.v4.app.Fragment
import info.test.tony.healthtrackerv01.data.NUM_QUESTIONS
import info.test.tony.healthtrackerv01.fragments.Q1Fragment
import info.test.tony.healthtrackerv01.fragments.Q2Fragment
import info.test.tony.healthtrackerv01.fragments.Q3Fragment
import java.util.*
import kotlin.collections.ArrayList

class Globals {
    companion object MentalHealth {
        var depression: Int? = 1
        var irritable: Int? = 1
        var anxiety: Int? = 1
        var excitability: Int? = 1
        var alcoholDrugs: Int? = 0
        var meds: Int? = 1
        var sleep: Double? = 6.5
        var excercise: Int? = 20
        var weight:Double? = 295.0
        var smoking: Int? = 0
        var mood: Double? = 2.5
        var entryDate: Date? = null
        var naps: Int? = 0
        var getUpTime: Int? = 0
        var id: Int? = null
        var switchStatus = IntArray(NUM_QUESTIONS, { 1 })
        var currentLoadedFragment = 0



        // May need to set up individual getters
        fun returnDepression():Int {
            return depression!!
        }

    }

}