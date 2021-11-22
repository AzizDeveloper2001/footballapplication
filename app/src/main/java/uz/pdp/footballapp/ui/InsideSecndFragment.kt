package uz.pdp.footballapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.pdp.footballapp.adapters.VpAdapter
import uz.pdp.footballapp.databinding.FragmentInsideSecndBinding
import uz.pdp.footballapp.room.Entity.BaseEntity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class InsideSecndFragment : Fragment() {

    private var param1: BaseEntity? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as BaseEntity
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding:FragmentInsideSecndBinding
    lateinit var vpAdapter: VpAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding=FragmentInsideSecndBinding.inflate(inflater,container,false)
        binding.apply {

            vpAdapter= VpAdapter(param1!!,childFragmentManager)
            vp.adapter=vpAdapter
            tablayout.setupWithViewPager(vp)
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: BaseEntity) =
            InsideSecndFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)

                }
            }
    }
}