package com.sciencefox.starwars

import com.sciencefox.starwars.characterlisthome.CharacterSearchHomeContract
import com.sciencefox.starwars.characterlisthome.CharacterSearchHomePresenter
import com.sciencefox.starwars.model.Character
import com.sciencefox.starwars.model.CharacterResponseModel
import com.sciencefox.starwars.networking.NetworkService
import com.sciencefox.starwars.networking.RetrofitAPIClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterSearchHomePresenterTest {

    @Mock
    lateinit var presenter: CharacterSearchHomeContract.Presenter

    @Mock
    lateinit var view: CharacterSearchHomeContract.View

    @Mock
     lateinit var  requestInterface : RetrofitAPIClient

    @Mock
    lateinit var service: NetworkService

    val characterResponseModel = CharacterResponseModel(
        name = "n",
        birthYear = "m",
        height = "h",
        films = arrayListOf("asd"))

    val character = Character(
        name = "n",
        birthYear = "m",
        height = "h",
        films = arrayListOf(),
        crawl = arrayListOf()
    )


    @Before
    fun setup() {
        presenter = CharacterSearchHomePresenter(view)

    }

    /*@Test
    fun testLoadCharacters() {
        `when`(requestInterface.getRetrofitClient()).thenReturn(service)
        `when`(service.getCharacterList()).thenReturn(Single.just(
            CharacterResponse(arrayListOf())
        ))
        presenter.loadCharacters()
        verify(view).showLoader()
        verify(view).hideLoader()
        verify(view).handleCharacterResponse(CharacterResponse(arrayListOf()))
    }*/

    /*@Test
    fun testResolveCharacter(){
        presenter.resolveCharacter(CharacterResponseModel(
            name = "n",
            birthYear = "m",
            height = "h",
            films = arrayListOf("asd")
        ))
        verify(service).getFilms()
    }*/

    @Test
    fun testMapToCharacter() {
        Assert.assertEquals(character,presenter.mapToCharacter(characterResponseModel))
    }


}