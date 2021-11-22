package uz.pdp.footballapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.pdp.footballapp.fragments.AllFragment


import uz.pdp.footballapp.room.Entity.BaseEntity

class VpAdapter(var baseEntity: BaseEntity, fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {

           return AllFragment.newInstance(baseEntity)

    }

    override fun getPageTitle(position: Int): CharSequence? {

        return if(position==0){
            "All"
        } else if(position==1) {
            "Home"
        } else {
            "Away"
        }
    }
}