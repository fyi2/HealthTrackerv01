package info.test.tony.healthtrackerv01.models



class HealthStatus() {
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
    var entryDate: Long? = null
    var naps: Int? = null
    var getUpTime: Int? = null
    var id: Int? = null

    constructor(depression: Int, irritability:Int, anxiety:Int, excitability:Int, alcoholDrugs: Int,
                meds:Int, sleep:Double, excercise:Int, weight:Double, smoking:Int, mood:Double, entryDate: Long,
                naps:Int, getUpTime:Int, id:Int): this(){

        this.depression = depression
        this.irritable = irritable
        this.anxiety = anxiety
        this.excitability = excitability
        this.alcoholDrugs = alcoholDrugs
        this.meds = meds
        this.sleep = sleep
        this.excercise = excercise
        this.weight = weight
        this.smoking = smoking
        this.mood = mood
        this.entryDate = entryDate
        this.naps = naps
        this.getUpTime = getUpTime
        this.id = id
    }
}