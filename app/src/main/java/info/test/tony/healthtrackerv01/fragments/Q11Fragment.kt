package info.test.tony.healthtrackerv01.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import info.test.tony.healthtrackerv01.R


/**
 * A simple [Fragment] subclass.
 */
class Q11Fragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val RootView : View
        RootView = inflater.inflate(R.layout.fragment_q11, container, false)
        return RootView    }

}// Required empty public constructor
