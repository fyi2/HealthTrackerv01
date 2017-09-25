package info.test.tony.healthtrackerv01.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import info.test.tony.healthtrackerv01.fragments.BodyFragment
import info.test.tony.healthtrackerv01.fragments.HeartFragment
import info.test.tony.healthtrackerv01.fragments.MindFragment
import android.view.View


class SectionPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): android.support.v4.app.Fragment {
        when(position){
            0 -> return MindFragment()
            1 -> return BodyFragment()
            2 -> return HeartFragment()
        }
        return null!!
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        when(position){
            0 -> return "MIND"
            1 -> return "BODY"
            2 -> return "HEART"
        }
        return null!!
    }

}