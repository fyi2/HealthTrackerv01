package info.test.tony.healthtrackerv01.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar

import info.test.tony.healthtrackerv01.R
import kotlinx.android.synthetic.main.fragment_q13.*
import info.test.tony.healthtrackerv01.data.Globals



/**
 * A simple [Fragment] subclass.
 */
class Q13Fragment : Fragment() {
    val mh = Globals.MentalHealth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val RootView : View
        RootView = inflater.inflate(R.layout.fragment_q13, container, false)

        // Set a click Listener for the Rating Bar as it does NOT respond to 'onClick'
        val rb = RootView.findViewById<RatingBar>(R.id.ratingBar2)
        rb.setOnRatingBarChangeListener { ratingBar, fl, b ->
            mh.mood = ratingBar.rating.toDouble()

        }
        return RootView    }

}// Required empty public constructor
