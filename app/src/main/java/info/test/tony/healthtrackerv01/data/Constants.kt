package info.test.tony.healthtrackerv01.data


val DATABASE_VERSION : Int = 1
val DATABASE_NAME: String = "healthtracker.db"
val TABLE_NAME: String = "tracker"

//Column names
val KEY_ID: String = "id"
val KEY_DEPRESSION:String = "depression"
val KEY_IRRITABLE:String = "irritable"
val KEY_ANXIETY:String = "anxiety"
val KEY_EXCITABILITY:String = "excitability"
val KEY_ALCOHOLDRUGS: String = "alcohol_drugs"
val KEY_MEDS:String = "meds"
val KEY_SLEEP:String = "sleep"
val KEY_EXCERCISE:String = "excercise"
val KEY_WEIGHT:String = "weight"
val KEY_SMOKING:String = "smoking"
val KEY_MOOD:String = "mood"
val KEY_ENTRYDATE:String = "entry_date"
val KEY_NAPS:String = "naps"
val KEY_GETUPTIME:String = "get_up_time"


// Request code for returning values from other activities
val SETTINGS_CODE: Int = 1 // can be any number
val REPORT_CODE: Int = 2
val TEST_CODE: Int = 3
val STARTSTATUS_CODE: Int = 4
val DETAILS_CODE: Int = 5

// Number of Question
val NUM_QUESTIONS = 14

// Positions of optional parameters
val BIPOLAR_POSN = 3
val ALCOHOL_DRUGS_POSN = 4
val MEDS_POSN = 5
val NAPS_POSN = 6
val GET_UP_POSN = 7
val WEIGHT_POSN = 10
val SMOKING_POSN = 11
