package com.sciencefox.starwars.characterlisthome

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sciencefox.starwars.databinding.ItemCharacterListBinding
import com.sciencefox.starwars.model.CharacterResponseModel

/**
 * Adapter that can display a Character Item.
 */
class CharacterListAdapter(
    private val list: List<CharacterResponseModel>,
    private val onClick: (CharacterResponseModel) -> Unit
) : RecyclerView.Adapter<CharacterListAdapter.HoldingViewHolder>() {

    class HoldingViewHolder(val binding: ItemCharacterListBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldingViewHolder {
        return HoldingViewHolder(
            ItemCharacterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: HoldingViewHolder, position: Int) {
        holder.binding.characterName.text = list[position].name

        holder.itemView.setOnClickListener {
            onClick.invoke(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
