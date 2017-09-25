package info.test.tony.healthtrackerv01.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import info.test.tony.healthtrackerv01.Globals

import info.test.tony.healthtrackerv01.R
import kotlinx.android.synthetic.main.fragment_q3.*



/**
 * A simple [Fragment] subclass.
 */
class Q3Fragment : Fragment()  {
    lateinit var seekBar : SeekBar
    lateinit var textStatus : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val RootView : View
        RootView = inflater.inflate(R.layout.fragment_q3, container, false)
        textStatus = RootView.findViewById(R.id.depressionStatusID)
        seekBar = RootView.findViewById(R.id.depressionSeekBarID)
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
                var sbResponse = "Current ly you have moderate depression $sbPosition"

                when(sbPosition){
                    0 -> sbResponse = "Great no depression at all."
                    1 -> sbResponse = "Great no depression at all."
                    2 -> sbResponse =  "Currently you fell mildly depressed."
                    3 -> sbResponse = "Currently you are moderately depressed."
                    4 -> sbResponse = "Right now you are struggling with bad depression remember you are a human BEing not a human DOing."
                    5 -> sbResponse = "Right now you are struggling with severe depression be gentle this to shall pass."
                    else -> "SYSTEM ERRPR SORRY"
                }
                depressionStatusID.text = sbResponse
                mh.depression = sbPosition
            }
        })
        return RootView
    }

    fun setTextView(value:String){
        depressionStatusID.text = value.toString()
    }

}

