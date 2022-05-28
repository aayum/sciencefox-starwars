package com.sciencefox.starwars.characterlisthome

import com.sciencefox.starwars.model.Character
import com.sciencefox.starwars.model.CharacterResponse
import com.sciencefox.starwars.model.CharacterResponseModel

/**
 * Contract representing first screen
 * contains contract for view and presenter
 */
interface CharacterSearchHomeContract {

    interface View {
        fun showLoader()
        fun hideLoader()
        fun handleCharacterResponse(characterDetailList: CharacterResponse)
        fun openDetailsFragment(character: Character)
    }

    interface Presenter {
        fun loadCharacters()
        fun getFilms(url: String)
        fun resolveCharacter(character: CharacterResponseModel)
        fun mapToCharacter(character: CharacterResponseModel): Character
        fun handleItemClick(character: CharacterResponseModel)
        fun detach()
    }
}