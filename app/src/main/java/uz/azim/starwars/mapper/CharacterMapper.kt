package uz.azim.starwars.mapper

import uz.azim.starwars.R
import uz.azim.starwars.data.models.CharacterResponse
import uz.azim.starwars.domain.model.Character
import uz.azim.starwars.domain.model.CharacterType

fun CharacterResponse.toEntity(characterType: CharacterType): Character {
    return Character(
        name = this.name,
        height = this.height,
        mass = this.mass,
        eyeColor = this.eyeColor,
        films = this.films,
        characterType = characterType,
        characterImageRes = getImageResForCharacter(characterType),
        arrowRes = getArrowColorForCharacter(this.eyeColor)
    )
}

fun getArrowColorForCharacter(eyeColor: String): Int {
    return when (eyeColor) {
        "brown" -> R.drawable.ic_arrow_brown
        "yellow" -> R.drawable.ic_arrow_yellow
        else -> R.drawable.ic_arrow_blue
    }
}

private fun getImageResForCharacter(characterType: CharacterType): Int {
    return when (characterType) {
        CharacterType.HUMAN -> R.drawable.ic_human
        CharacterType.ALIEN -> R.drawable.ic_alien
        else -> R.drawable.ic_droid
    }
}
