package uz.azim.starwars.ui.characters

import androidx.paging.PagingData
import app.cash.turbine.test
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uz.azim.starwars.domain.usecase.character.GetCharacterUseCase
import uz.azim.starwars.ui.DummyCharacter
import uz.azim.starwars.util.CoroutineTestRule
import uz.azim.starwars.utils.Resource

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var getCharacterUseCase: GetCharacterUseCase

    private lateinit var charactersViewModel: CharactersViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        charactersViewModel = CharactersViewModel(getCharacterUseCase)
    }

    @Test
    fun `get characters returns success`() = runTest {
        val expected = PagingData.from(DummyCharacter.provideDummyList())
        every { getCharacterUseCase.execute() } returns
                flowOf(expected)


        charactersViewModel.character.test {
            awaitItem()
            charactersViewModel.getCharacters()
            awaitItem()

            Assert.assertTrue(awaitItem() is Resource.Success)
        }
    }

}
