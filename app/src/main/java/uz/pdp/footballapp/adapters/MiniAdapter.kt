package uz.pdp.footballapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.pdp.footballapp.databinding.ItemBinding
import uz.pdp.footballapp.databinding.StandingBinding
import uz.pdp.footballapp.room.Entity.TeamEntity

class MiniAdapter : ListAdapter<TeamEntity, MiniAdapter.Vh>(MyDiffUtill()){

    inner class Vh(var itemRvBinding: ItemBinding):
        RecyclerView.ViewHolder(itemRvBinding.root){

        fun onBind(baseEntity: TeamEntity){
            itemRvBinding.team.text=baseEntity.name
            itemRvBinding.d.text=baseEntity.draw.toString()
            itemRvBinding.l.text=baseEntity.lose.toString()
            itemRvBinding.ga.text=baseEntity.ga.toString()
            itemRvBinding.pts.text=baseEntity.pts.toString()
            itemRvBinding.gd.text=baseEntity.gd.toString()
            if(baseEntity.logo.isNotEmpty()){
                Picasso.get().load(baseEntity.logo).into(itemRvBinding.flag)
            } else {
                itemRvBinding.flag.visibility= View.INVISIBLE
            }



        }

    }
    class MyDiffUtill: DiffUtil.ItemCallback<TeamEntity>(){
        override fun areItemsTheSame(oldItem: TeamEntity, newItem: TeamEntity): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: TeamEntity, newItem: TeamEntity): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }


}