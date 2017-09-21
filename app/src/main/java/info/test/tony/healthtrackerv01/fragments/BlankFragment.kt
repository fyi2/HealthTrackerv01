package info.test.tony.healthtrackerv01.fragments


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import info.test.tony.healthtrackerv01.R


/**
 * A simple [Fragment] subclass.
 */
class BlankFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

}// Required empty public constructor
