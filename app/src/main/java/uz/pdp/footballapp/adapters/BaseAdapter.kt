package uz.pdp.footballapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.pdp.footballapp.databinding.StandingBinding
import uz.pdp.footballapp.room.Entity.BaseEntity
import uz.pdp.footballapp.room.Entity.TeamEntity

class BaseAdapter(var listener:BaseAdapter.onClick):ListAdapter<BaseEntity,BaseAdapter.Vh>(MyDiffUtill()){
     inner class Vh(var itemRvBinding: StandingBinding):
             RecyclerView.ViewHolder(itemRvBinding.root){

                 fun onBind(baseEntity: BaseEntity){
                     itemRvBinding.standingTitle.text=baseEntity.name
                     itemRvBinding.country.visibility= View.GONE
                     itemRvBinding.all.setOnClickListener {
                         listener.onitemClick(baseEntity)
                     }
                     Picasso.get().load(baseEntity.logo).into(itemRvBinding.image)
                     val miniAdapter=MiniAdapter()
                     var list=ArrayList<TeamEntity>()
                   for(i in 0..3){
                       if(i<baseEntity.teamEntities.size){
                           list.add(baseEntity.teamEntities[i])
                       }

                   }
                     miniAdapter.submitList(list)
                     itemRvBinding.rv.adapter=miniAdapter

                 }

             }
    class MyDiffUtill:DiffUtil.ItemCallback<BaseEntity>(){
        override fun areItemsTheSame(oldItem: BaseEntity, newItem: BaseEntity): Boolean {
            return oldItem.luid==newItem.luid
        }

        override fun areContentsTheSame(oldItem: BaseEntity, newItem: BaseEntity): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(StandingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }
    interface onClick{
        fun onitemClick(baseEntity: BaseEntity)
    }
}