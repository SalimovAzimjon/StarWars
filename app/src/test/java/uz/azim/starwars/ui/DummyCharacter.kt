package uz.azim.starwars.ui

import uz.azim.starwars.domain.model.Character
import uz.azim.starwars.domain.model.CharacterType

object DummyCharacter {

    fun provideDummyList() = listOf(
        Character(
            name = "Name",
            height = "170",
            mass = "11",
            eyeColor = "blue",
            films = listOf("film1", "film2"),
            characterType = CharacterType.ALIEN,
            characterImageRes = 12,
            arrowRes = 112
        ),
        Character(
            name = "Name",
            height = "170",
            mass = "11",
            eyeColor = "blue",
            films = listOf("film1", "film2"),
            characterType = CharacterType.ALIEN,
            characterImageRes = 12,
            arrowRes = 112
        ),
        Character(
            name = "Name",
            height = "170",
            mass = "11",
            eyeColor = "blue",
            films = listOf("film1", "film2"),
            characterType = CharacterType.ALIEN,
            characterImageRes = 12,
            arrowRes = 112
        )
    )

}
