package uz.pdp.footballapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.pdp.footballapp.R
import uz.pdp.footballapp.adapters.MiniAdapter
import uz.pdp.footballapp.databinding.FragmentAllBinding
import uz.pdp.footballapp.room.Entity.BaseEntity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AllFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: BaseEntity? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1)as BaseEntity
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding:FragmentAllBinding
    lateinit var miniAdapter: MiniAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentAllBinding.inflate(inflater,container,false)
        binding.apply {
            miniAdapter= MiniAdapter()
            rv.adapter=miniAdapter
            miniAdapter.submitList(param1?.teamEntities)
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: BaseEntity) =
            AllFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}