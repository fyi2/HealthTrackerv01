package info.test.tony.healthtrackerv01.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import info.test.tony.healthtrackerv01.data.Globals

import info.test.tony.healthtrackerv01.R


/**
 * A simple [Fragment] subclass.
 */
class Q1Fragment : Fragment()  {
    lateinit var seekBar : SeekBar
    lateinit var anxietyStatus : TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val RootView : View
        RootView = inflater!!.inflate(R.layout.fragment_q1, container, false)
        anxietyStatus = RootView.findViewById(R.id.anxietyStatusID)
        seekBar = RootView.findViewById(R.id.anxietySeekBarID)
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                return
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                return
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                val mh = Globals.MentalHealth
                var sbPosition = p0!!.progress
                var sbResponse = "Current ly you have moderate anxiety $sbPosition"

                when(sbPosition){
                    0 -> sbResponse = "Great no anxiety at all."
                    1 -> sbResponse = "Great no anxiety at all."
                    2 -> sbResponse =  "Currently you are mildly anxious."
                    3 -> sbResponse = "Currently you are moderately anxious."
                    4 -> sbResponse = "Right now you are struggling with bad anxiety remember to breath."
                    5 -> sbResponse = "Right now you are struggling with severe anxiety try to breath and center yourself."
                    else -> "SYSTEM ERRPR SORRY"
                }
                anxietyStatus.text = sbResponse
                mh.anxiety = sbPosition
            }
        })
        return RootView
    }

}

