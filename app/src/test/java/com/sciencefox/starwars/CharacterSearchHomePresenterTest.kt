package com.sciencefox.starwars

import com.sciencefox.starwars.characterlisthome.CharacterSearchHomeContract
import com.sciencefox.starwars.characterlisthome.CharacterSearchHomePresenter
import com.sciencefox.starwars.model.Character
import com.sciencefox.starwars.model.CharacterResponse
import com.sciencefox.starwars.model.CharacterResponseModel
import com.sciencefox.starwars.networking.NetworkService
import com.sciencefox.starwars.networking.RetrofitAPIClient
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
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

    private val characterResponseModel = CharacterResponseModel(
        name = "Skywalker",
        birthYear = "17BBY",
        height = "163",
        films = arrayListOf("A new hope"))

    private val character = Character(
        name = "Skywalker",
        birthYear = "17BBY",
        height = "163",
        films = arrayListOf(),
        crawl = arrayListOf()
    )


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CharacterSearchHomePresenter(view)

    }

   /* @Test
    fun testLoadCharacters() {
        `when`(service.getCharacterList()).thenReturn(
            Single.just(
            CharacterResponse(arrayListOf())
        ))
        presenter.loadCharacters()
        verify(view).showLoader()
        verify(view).hideLoader()
        verify(view).handleCharacterResponse(CharacterResponse(arrayListOf()))
    }

    @Test
    fun testResolveCharacter(){
        presenter.resolveCharacter(CharacterResponseModel(
            name = "n",
            birthYear = "m",
            height = "h",
            films = arrayListOf("asd")
        ))
        verify(service).getFilms().test()
    }*/

    @Test
    fun testMapToCharacter() {
        Assert.assertEquals(character,presenter.mapToCharacter(characterResponseModel))
    }

}