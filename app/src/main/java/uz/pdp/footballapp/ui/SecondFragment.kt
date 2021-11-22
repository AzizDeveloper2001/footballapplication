package uz.pdp.footballapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import uz.pdp.footballapp.R
import uz.pdp.footballapp.adapters.InfoVpAdapter
import uz.pdp.footballapp.databinding.FragmentSecondBinding
import uz.pdp.footballapp.room.Entity.BaseEntity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SecondFragment : Fragment(R.layout.fragment_second) {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding:FragmentSecondBinding
    lateinit var baseEntity: BaseEntity
    lateinit var infoVpAdapter: InfoVpAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentSecondBinding.inflate(inflater,container,false)
         binding.backk.setOnClickListener {
             findNavController().popBackStack()
         }
        baseEntity=arguments?.getSerializable("base") as BaseEntity
        infoVpAdapter=InfoVpAdapter(baseEntity,childFragmentManager)
        binding.vp.adapter=infoVpAdapter
        binding.tablayout.setupWithViewPager(binding.vp)
        Picasso.get().load(baseEntity.logo).into(binding.image)
        binding.title.text="${baseEntity.name} 2021-22"
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}