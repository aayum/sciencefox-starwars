package com.sciencefox.starwars.characterlisthome

import android.util.Log
import com.sciencefox.starwars.model.Character
import com.sciencefox.starwars.model.CharacterResponseModel
import com.sciencefox.starwars.model.FilmsResponse
import com.sciencefox.starwars.networking.RetrofitAPIClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CharacterSearchHomePresenter(
    private val view: CharacterSearchHomeContract.View?
) : CharacterSearchHomeContract.Presenter {

    private var filmList: ArrayList<String> = arrayListOf()
    private var crawlList: ArrayList<String> = arrayListOf()
    private var characterSelected = Character()
    private var compositeDisposable = CompositeDisposable()
    private val requestInterface = RetrofitAPIClient()

    override fun loadCharacters() {
        requestInterface.getRetrofitClient().getCharacterList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view?.showLoader()
            }
            .subscribe { characterList ->
                view?.apply {
                    hideLoader()
                    handleCharacterResponse(characterList)
                }

            }
            .addTo(compositeDisposable)
    }

    override fun resolveCharacter(character: CharacterResponseModel) {
        filmList = arrayListOf()
        crawlList = arrayListOf()
        for (film in character.films) {
            getFilms(film)
        }
    }

    override fun getFilms(url: String) {
        requestInterface.getRetrofitClientForFilms(url).getFilms()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { filmList ->
                handleFilms(filmList)
            }
            .addTo(compositeDisposable)
    }

    override fun mapToCharacter(character: CharacterResponseModel): Character {
        Log.d("CHARCATERRRRR", "$character  $filmList $crawlList")
        return Character(
            name = character.name,
            birthYear = character.birthYear,
            height = character.height,
            films = filmList,
            crawl = crawlList
        )
    }

    override fun handleItemClick(character: CharacterResponseModel) {
        resolveCharacter(character)
        characterSelected = mapToCharacter(character)
        view?.openDetailsFragment(characterSelected)
    }

    override fun detach() {
        compositeDisposable.clear()
    }

    private fun handleFilms(films: FilmsResponse) {
        filmList.add(films.title.toString())
        crawlList.add(films.openingCrawl.toString())
        Log.d("FILMLIST", "  ${filmList} ")
        Log.d("CRAWLLIST", "   ${crawlList}")

    }
}