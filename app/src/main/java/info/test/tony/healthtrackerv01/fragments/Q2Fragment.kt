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
import kotlinx.android.synthetic.main.fragment_q2.*


/**
 * A simple [Fragment] subclass.
 */
class Q2Fragment : Fragment()  {
    lateinit var seekBar : SeekBar
    lateinit var textStatus : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val RootView : View
        RootView = inflater.inflate(R.layout.fragment_q2, container, false)
        textStatus = RootView.findViewById(R.id.irritabilityStatusID)
        seekBar = RootView.findViewById(R.id.irritabilitySeekBarID)
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
                var sbResponse = "Current ly you have moderate irritability $sbPosition"

                when(sbPosition){
                    0 -> sbResponse = "Great no irritability at all."
                    1 -> sbResponse = "Great no irritability at all."
                    2 -> sbResponse =  "Currently you fell mildly irritated."
                    3 -> sbResponse = "Currently you are moderately irritable."
                    4 -> sbResponse = "Right now you are struggling with bad irritability remember to pause before you act."
                    5 -> sbResponse = "Right now you are struggling with severe irritability try to reduce stimulation and center yourself."
                    else -> "SYSTEM ERRPR SORRY"
                }
                irritabilityStatusID.text = sbResponse
                mh.irritable = sbPosition
            }
        })
        return RootView
    }

}

