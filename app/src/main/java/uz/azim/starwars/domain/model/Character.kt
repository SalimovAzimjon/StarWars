package uz.azim.starwars.domain.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class Character(
    val name: String,
    val height: String,
    val eyeColor: String,
    val mass: String,
    val films:List<String>,
    val characterType: CharacterType,
    @DrawableRes val characterImageRes:Int,
    @DrawableRes val arrowRes:Int
)
