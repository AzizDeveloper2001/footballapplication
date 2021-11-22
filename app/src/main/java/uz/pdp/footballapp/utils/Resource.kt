package uz.pdp.footballapp.utils

import uz.pdp.footballapp.room.Entity.BaseEntity

sealed class Resource {
    object Loadding:Resource()
    class Successs(var list:List<BaseEntity>):Resource()
    class Error(var messsage:String):Resource()

}