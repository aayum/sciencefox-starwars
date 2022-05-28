package com.sciencefox.starwars.characterlisthome

import com.sciencefox.starwars.model.Character
import com.sciencefox.starwars.model.CharacterResponseModel
import com.sciencefox.starwars.networking.RetrofitAPIClient
import io.reactivex.Observable
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

        Observable
            .fromIterable(character.films)
            .map {
                getFilms(it)
            }
            .subscribe({
                characterSelected = mapToCharacter(character)
            }, {
                // error
            },
                {
                    //on complete
                    view?.openDetailsFragment(characterSelected)
                })
            .dispose()
    }

    override fun getFilms(url: String) {
        requestInterface.getRetrofitClientForFilms(url).getFilms()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { film ->
                filmList.add(film.title.toString())
                crawlList.add(film.openingCrawl.toString())
            }
            .addTo(compositeDisposable)
    }

    override fun mapToCharacter(character: CharacterResponseModel): Character {
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
    }

    override fun detach() {
        compositeDisposable.clear()
    }

}