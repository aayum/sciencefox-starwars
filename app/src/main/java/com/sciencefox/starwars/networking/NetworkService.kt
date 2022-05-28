package com.sciencefox.starwars.networking

import com.sciencefox.starwars.model.CharacterResponse
import com.sciencefox.starwars.model.FilmsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {

    @GET("people")
    fun getCharacterList() : Single<CharacterResponse>


    @GET(" ")
    fun getFilms() : Single<FilmsResponse>

}
