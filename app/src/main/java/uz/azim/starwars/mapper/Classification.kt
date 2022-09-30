package uz.azim.starwars.mapper

import uz.azim.starwars.domain.model.CharacterType

private const val DROID = "artificial"

fun getCharacterTypeFromClassification(classification: String): CharacterType {
    return when (classification) {
        DROID -> CharacterType.DROID
        else -> CharacterType.ALIEN
    }
}
