package uz.pdp.footballapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.pdp.footballapp.repositories.FootballRepository
import uz.pdp.footballapp.utils.NetworkHelper
import java.lang.Exception

class BaseModelFactory(var networkHelper: NetworkHelper,var repository: FootballRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BaseVeiwModel::class.java)){
            return BaseVeiwModel(networkHelper,repository) as T
        }
        throw  Exception("Error")
    }
}