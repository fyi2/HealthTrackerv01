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
import kotlinx.android.synthetic.main.fragment_q4.*


/**
 * A simple [Fragment] subclass.
 */
class Q4Fragment : Fragment() {
    lateinit var seekBar : SeekBar
    lateinit var textStatus : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val RootView : View
        RootView = inflater.inflate(R.layout.fragment_q4, container, false)
        textStatus = RootView.findViewById(R.id.bipolarStatusID)
        seekBar = RootView.findViewById(R.id.bipolarSeekBarID)
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
                    0 -> sbResponse = "Great currently you are calm."
                    1 -> sbResponse = "Great currently you are calm."
                    2 -> sbResponse =  "Currently you felling excited."
                    3 -> sbResponse = "Be careful you only feel unstoppable."
                    4 -> sbResponse = "Be aware right now you are on a non-stop high, this to shall pass."
                    5 -> sbResponse = "You need to take it slow and not make any major decision, this is not you actual reality"
                    else -> "SYSTEM ERRPR SORRY"
                }
                bipolarStatusID.text = sbResponse
                mh.excitability = sbPosition
            }
        })
        return RootView
    }
}
