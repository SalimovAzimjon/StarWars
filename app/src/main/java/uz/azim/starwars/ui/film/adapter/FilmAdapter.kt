package uz.azim.starwars.ui.film.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.azim.starwars.R
import uz.azim.starwars.databinding.ItemFilmBinding
import uz.azim.starwars.domain.model.Film

class FilmAdapter(private val onCharacterClick: (Film) -> Unit) :
    ListAdapter<Film, FilmAdapter.FilmViewHolder>(FilmDiffUtil()) {

    class FilmViewHolder(view: View, private val onCharacterClick: (Film) -> Unit) :
        RecyclerView.ViewHolder(view) {

        private val binding by viewBinding(ItemFilmBinding::bind)

        fun onBind(film: Film) {
            binding.apply {
                tvFilmName.text = film.title
                tvFilmReleaseDate.text = film.releaseDate
            }
            binding.root.setOnClickListener {
                onCharacterClick.invoke(film)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false),
            onCharacterClick
        )
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}

class FilmDiffUtil : DiffUtil.ItemCallback<Film>() {

    override fun areItemsTheSame(oldItem: Film, newItem: Film) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Film, newItem: Film) = oldItem == newItem

}
