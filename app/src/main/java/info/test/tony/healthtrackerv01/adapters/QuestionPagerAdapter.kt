package info.test.tony.healthtrackerv01.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import info.test.tony.healthtrackerv01.Globals
import info.test.tony.healthtrackerv01.data.NUM_QUESTIONS
import info.test.tony.healthtrackerv01.fragments.*
import java.util.*


class QuestionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    val mh = Globals.MentalHealth

    override fun getItem(position: Int): Fragment {
        println("QUESTION PAGER ADAPTER called")
        var myPosition: Int = position
        mh.currentLoadedFragment = myPosition
        myPosition = myMapPosition(position)
        // Save this in the global tracker
        when(myPosition) {
            0 -> return Q1Fragment()
            1 -> return Q2Fragment()
            2 -> return Q3Fragment()
            3 -> return Q4Fragment()
            4 -> return Q5Fragment()
            5 -> return Q6Fragment()
            6 -> return Q7Fragment()
            7 -> return Q8Fragment()
            8 -> return Q9Fragment()
            9 -> return Q10Fragment()
            10 -> return Q11Fragment()
            11 -> return Q12Fragment()
            12 -> return Q13Fragment()
            13 -> return Q14Fragment()

        }
        return null!!
    }

    override fun getCount(): Int {
        return NUM_QUESTIONS // Manual need a programatic solution for 20 Questions
    }

    fun myMapPosition(position: Int): Int{
        val mh = Globals.MentalHealth
        val switches = mh.switchStatus
        var newPosition: Int = position
        var pages = IntArray(NUM_QUESTIONS, {it*1 })
        var screenNumber = 0

        // build actual screen Array and
        for(i in 0..NUM_QUESTIONS-1){
            if(switches[i] == 1){
                pages[screenNumber] = i
                screenNumber++
            }
        }
        for(i in screenNumber..NUM_QUESTIONS-1){
            pages[i] = NUM_QUESTIONS - 1
        }
        // Map requested posn to actual screen posn
        newPosition = pages[position]
        // return new position
        return newPosition
    }

}