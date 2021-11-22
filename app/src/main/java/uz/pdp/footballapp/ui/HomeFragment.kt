package uz.pdp.footballapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.pdp.footballapp.R
import uz.pdp.footballapp.adapters.BaseAdapter
import uz.pdp.footballapp.databinding.FragmentHomeBinding
import uz.pdp.footballapp.repositories.FootballRepository
import uz.pdp.footballapp.retrofit.ApiClient
import uz.pdp.footballapp.room.AppDatabase
import uz.pdp.footballapp.room.Entity.BaseEntity
import uz.pdp.footballapp.utils.NetworkHelper
import uz.pdp.footballapp.utils.Resource
import uz.pdp.footballapp.viewmodels.BaseModelFactory
import uz.pdp.footballapp.viewmodels.BaseVeiwModel


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var repository:FootballRepository
    lateinit var networkHelper: NetworkHelper
    lateinit var appDatabase: AppDatabase
    private  val TAG = "HomeFragment"
    lateinit var baseVeiwModel: BaseVeiwModel
    lateinit var baseAdapter: BaseAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkHelper= NetworkHelper(requireContext())
        appDatabase= AppDatabase.getInstance(requireContext())
        repository= FootballRepository(ApiClient.apiService,appDatabase.leaugesDao(),appDatabase.baseDao())
        baseVeiwModel=ViewModelProvider(this,BaseModelFactory(networkHelper,repository))[BaseVeiwModel::class.java]
        baseAdapter= BaseAdapter(object :BaseAdapter.onClick{
            override fun onitemClick(baseEntity: BaseEntity) {
                var bundle=Bundle()
                bundle.putSerializable("base",baseEntity)
                findNavController().navigate(R.id.secondFragment,bundle)
            }

        })
        binding.rv.adapter=baseAdapter
       loadui()
        binding.swipe.setOnRefreshListener {
            loadui()
        }


    }
    fun loadui(){
        lifecycleScope.launch {
            baseVeiwModel.fetchdata().collect {
                when(it){
                    is Resource.Successs->{
                        binding.swipe.isRefreshing = false
                        binding.nodata.visibility=View.GONE
                        binding.rv.visibility=View.VISIBLE
                        baseAdapter.submitList(it.list)
                        Log.d(TAG, "onViewCreated: $it")

                    }
                    is Resource.Error->{
                        binding.swipe.isRefreshing = false
                        binding.nodata.visibility=View.VISIBLE
                        binding.rv.visibility=View.GONE
                        Log.d(TAG, "onViewCreated: ${it.messsage}")
                    }
                    is Resource.Loadding->{
                        binding.swipe.isRefreshing = true
                        binding.nodata.visibility=View.GONE
                        Log.d(TAG, "onViewCreated: Loadding")
                    }
                }
            }
        }
    }

}