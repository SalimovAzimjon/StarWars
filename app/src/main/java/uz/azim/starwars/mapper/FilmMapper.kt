package uz.azim.starwars.mapper

import uz.azim.starwars.data.models.FilmResponse
import uz.azim.starwars.domain.model.Film
import java.text.SimpleDateFormat
import java.util.*

fun FilmResponse.toEntity(): Film {
    return Film(
        title = this.title,
        releaseDate = formatDate(this.releaseDate)
    )
}

fun formatDate(releaseDate: String): String {
    val oldFormat = SimpleDateFormat("yyyy-MM-d", Locale.getDefault())
    val date = oldFormat.parse(releaseDate)
    return if (date != null) {
        SimpleDateFormat("MMMM d, yyyy", Locale.getDefault()).format(date)
    } else {
        releaseDate
    }
}
