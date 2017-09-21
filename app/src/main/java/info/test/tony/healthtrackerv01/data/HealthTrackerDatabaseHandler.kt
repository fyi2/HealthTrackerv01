package info.test.tony.healthtrackerv01.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.DateFormat
import java.util.*

import info.test.tony.healthtrackerv01.models.*



class HealthTrackerDatabaseHandler(context: Context):
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_TRACKER_TABLE = "CREATE TABLE "+ TABLE_NAME+" ("+ KEY_ID+ " INTEGER PRIMARY KEY, "+
                KEY_DEPRESSION+ " INTEGER, "+
                KEY_ANXIETY+ " INTEGER, "+
                KEY_IRRITABLE+ " INTEGER, "+
                KEY_ALCOHOLDRUGS+ " INTEGER, "+
                KEY_ENTRYDATE+ " DATE, "+
                KEY_WEIGHT+ " DOUBLE PRECISION, "+
                KEY_EXCERCISE+ " INTEGER, "+
                KEY_EXCITABILITY+ " INTEGER, "+
                KEY_GETUPTIME+ " INTEGER, "+
                KEY_MEDS+ " INTEGER, "+
                KEY_MOOD+ " DOUBLE PRECISION, "+
                KEY_NAPS+ " INTEGER, "+
                KEY_SLEEP+ " DOUBLE PRECISION, "+
                KEY_SMOKING+ " INTEGER )"

        db?.execSQL(CREATE_TRACKER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        //create table again
        onCreate(db)
    }
    // CRUD
    fun createStatus(healthStatus: HealthStatus) {
        var db: SQLiteDatabase = writableDatabase
        var values: ContentValues = ContentValues()

        values.put(KEY_ALCOHOLDRUGS, healthStatus.alcoholDrugs)
        values.put(KEY_ANXIETY, healthStatus.anxiety)
        values.put(KEY_DEPRESSION, healthStatus.depression)
        values.put(KEY_ENTRYDATE, healthStatus.entryDate)
        values.put(KEY_EXCERCISE, healthStatus.excercise)
        values.put(KEY_EXCITABILITY, healthStatus.excitability)
        values.put(KEY_GETUPTIME, healthStatus.getUpTime)
        values.put(KEY_IRRITABLE, healthStatus.irritable)
        values.put(KEY_MEDS, healthStatus.meds)
        values.put(KEY_MOOD, healthStatus.mood)
        values.put(KEY_NAPS, healthStatus.naps)
        values.put(KEY_SLEEP, healthStatus.sleep)
        values.put(KEY_SMOKING, healthStatus.smoking)
        values.put(KEY_WEIGHT, healthStatus.weight)

        db.insert(TABLE_NAME, null, values)
        println("DATA SUCCESS INSERT")

        db.close()

    }
    fun readStatus(id: Int): HealthStatus {
        var db: SQLiteDatabase = readableDatabase
        var cursor: Cursor = db.query(TABLE_NAME, arrayOf(KEY_ALCOHOLDRUGS, KEY_ANXIETY,
                KEY_DEPRESSION, KEY_ENTRYDATE, KEY_EXCERCISE, KEY_EXCITABILITY, KEY_GETUPTIME,
                KEY_ID, KEY_IRRITABLE, KEY_MEDS, KEY_MOOD, KEY_NAPS, KEY_SLEEP, KEY_SMOKING,
                KEY_WEIGHT), KEY_ID +"=?", arrayOf(id.toString()),null, null, null)

        if(cursor!=null)
            cursor.moveToFirst()

        var healthStatus = HealthStatus()
        healthStatus.depression = cursor.getInt(cursor.getColumnIndex(KEY_DEPRESSION))
        healthStatus.irritable = cursor.getInt(cursor.getColumnIndex(KEY_IRRITABLE))
        healthStatus.anxiety = cursor.getInt(cursor.getColumnIndex(KEY_ANXIETY))
        healthStatus.excitability = cursor.getInt(cursor.getColumnIndex(KEY_EXCITABILITY))
        healthStatus.alcoholDrugs = cursor.getInt(cursor.getColumnIndex(KEY_ALCOHOLDRUGS))
        healthStatus.meds = cursor.getInt(cursor.getColumnIndex(KEY_MEDS))
        healthStatus.sleep = cursor.getDouble(cursor.getColumnIndex(KEY_SLEEP))
        healthStatus.excercise = cursor.getInt(cursor.getColumnIndex(KEY_EXCERCISE))
        healthStatus.weight = cursor.getDouble(cursor.getColumnIndex(KEY_WEIGHT))
        healthStatus.smoking = cursor.getInt(cursor.getColumnIndex(KEY_SMOKING))
        healthStatus.mood = cursor.getDouble(cursor.getColumnIndex(KEY_MOOD))
        healthStatus.entryDate = cursor.getLong(cursor.getColumnIndex(KEY_ENTRYDATE))
        healthStatus.naps = cursor.getInt(cursor.getColumnIndex(KEY_NAPS))
        healthStatus.getUpTime = cursor.getInt(cursor.getColumnIndex(KEY_GETUPTIME))

        var dateFormat: java.text.DateFormat = DateFormat.getDateInstance()
        var formattedDate = dateFormat.format(Date(cursor.
                getLong(cursor.getColumnIndex(KEY_ENTRYDATE))).time)

        return healthStatus
    }
    fun readAllStatus(): ArrayList<HealthStatus> {
        var db: SQLiteDatabase = readableDatabase
        var list: ArrayList<HealthStatus> = ArrayList()
        var selectAll = "SELECT * FROM "+ TABLE_NAME
        var cursor: Cursor = db.rawQuery(selectAll, null)

        // loop through chores
        if(cursor.moveToFirst()) {
            do {
                var healthStatus = HealthStatus()

                healthStatus.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                healthStatus.depression = cursor.getInt(cursor.getColumnIndex(KEY_DEPRESSION))
                healthStatus.irritable = cursor.getInt(cursor.getColumnIndex(KEY_IRRITABLE))
                healthStatus.anxiety = cursor.getInt(cursor.getColumnIndex(KEY_ANXIETY))
                healthStatus.excitability = cursor.getInt(cursor.getColumnIndex(KEY_EXCITABILITY))
                healthStatus.alcoholDrugs = cursor.getInt(cursor.getColumnIndex(KEY_ALCOHOLDRUGS))
                healthStatus.meds = cursor.getInt(cursor.getColumnIndex(KEY_MEDS))
                healthStatus.sleep = cursor.getDouble(cursor.getColumnIndex(KEY_SLEEP))
                healthStatus.excercise = cursor.getInt(cursor.getColumnIndex(KEY_EXCERCISE))
                healthStatus.weight = cursor.getDouble(cursor.getColumnIndex(KEY_WEIGHT))
                healthStatus.smoking = cursor.getInt(cursor.getColumnIndex(KEY_SMOKING))
                healthStatus.mood = cursor.getDouble(cursor.getColumnIndex(KEY_MOOD))
                healthStatus.entryDate = cursor.getLong(cursor.getColumnIndex(KEY_ENTRYDATE))
                healthStatus.naps = cursor.getInt(cursor.getColumnIndex(KEY_NAPS))
                healthStatus.getUpTime = cursor.getInt(cursor.getColumnIndex(KEY_GETUPTIME))

                list.add(healthStatus)
            } while (cursor.moveToNext())
        }
        return list
    }

    fun updateStatus(healthStatus: HealthStatus): Int {
        var db: SQLiteDatabase = writableDatabase
        var values: ContentValues = ContentValues()

        values.put(KEY_ALCOHOLDRUGS, healthStatus.alcoholDrugs)
        values.put(KEY_ANXIETY, healthStatus.anxiety)
        values.put(KEY_DEPRESSION, healthStatus.depression)
        values.put(KEY_ENTRYDATE, healthStatus.entryDate)
        values.put(KEY_EXCERCISE, healthStatus.excercise)
        values.put(KEY_EXCITABILITY, healthStatus.excitability)
        values.put(KEY_GETUPTIME, healthStatus.getUpTime)
        values.put(KEY_IRRITABLE, healthStatus.irritable)
        values.put(KEY_MEDS, healthStatus.meds)
        values.put(KEY_MOOD, healthStatus.mood)
        values.put(KEY_NAPS, healthStatus.naps)
        values.put(KEY_SLEEP, healthStatus.sleep)
        values.put(KEY_SMOKING, healthStatus.smoking)
        values.put(KEY_WEIGHT, healthStatus.weight)

        // Update a Row
        return db.update(TABLE_NAME, values, KEY_ID+"=?", arrayOf(healthStatus.id.toString()))



    }
    fun deleteStatus(healthStatus: HealthStatus){
        var db: SQLiteDatabase = writableDatabase

        db.delete(TABLE_NAME, KEY_ID+"=?", arrayOf(healthStatus.id.toString()))
        db.close()
    }

    fun getStatusCount(): Int {
        var db: SQLiteDatabase = readableDatabase
        var countQuery = "SELECT * FROM "+ TABLE_NAME
        var cursor: Cursor = db.rawQuery(countQuery, null)

        return cursor.count

    }
}
