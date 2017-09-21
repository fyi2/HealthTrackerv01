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
class MindFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_mind, container, false)
    }

}// Required empty public constructor
