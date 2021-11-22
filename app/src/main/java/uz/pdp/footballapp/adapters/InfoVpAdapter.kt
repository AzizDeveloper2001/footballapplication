package uz.pdp.footballapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import uz.pdp.footballapp.room.Entity.BaseEntity
import uz.pdp.footballapp.ui.InsideSecndFragment
import uz.pdp.footballapp.ui.MatchFragment

class InfoVpAdapter(var baseEntity: BaseEntity, fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return if(position==0){
            InsideSecndFragment.newInstance(baseEntity)
        } else {
            MatchFragment.newInstance(baseEntity)
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {

        return if(position==0){
            "Table"
        } else {
            "Matches"
        }
    }
}