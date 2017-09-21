package info.test.tony.healthtrackerv01

import java.util.*

class Globals {
    companion object MentalHealth {
        var depression: Int? = null
        var irritable: Int? = null
        var anxiety: Int? = null
        var excitability: Int? = null
        var alcoholDrugs: Int? = null
        var meds: Int? = null
        var sleep: Double? = null
        var excercise: Int? = null
        var weight:Double? = null
        var smoking: Int? = null
        var mood: Double? = null
        var entryDate: Date? = null
        var naps: Int? = null
        var getUpTime: Int? = null
        var id: Int? = null

        // May need to set up individual getters
        fun returnDepression():Int {
            return depression!!
        }

    }
}