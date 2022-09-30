package uz.azim.starwars.ui.characters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.azim.starwars.R
import uz.azim.starwars.databinding.ItemCharacterBinding
import uz.azim.starwars.domain.model.Character

class CharactersAdapter(private val onCharacterClick: (Character) -> Unit) :
    PagingDataAdapter<Character, CharactersAdapter.CharacterViewHolder>(CharactersDiffUtil()) {

    class CharacterViewHolder(view: View, private val onCharacterClick: (Character) -> Unit) :
        RecyclerView.ViewHolder(view) {

        private val binding by viewBinding(ItemCharacterBinding::bind)

        fun onBind(character: Character?) {
            character?.let {
                binding.apply {
                    imgCharacter.setImageResource(character.characterImageRes)
                    tvCharacterName.text = character.name
                    tvCharacterHeight.text = root.context.getString(R.string.height, character.height)
                    tvCharacterMass.text = root.context.getString(R.string.mass, character.mass)
                    imgArrow.setImageResource(character.arrowRes)
                }
                binding.root.setOnClickListener {
                    onCharacterClick.invoke(character)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false),
            onCharacterClick
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}

class CharactersDiffUtil : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Character, newItem: Character) =
        oldItem.height == newItem.height && oldItem.mass == newItem.mass

}
