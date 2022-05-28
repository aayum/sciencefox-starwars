package com.sciencefox.starwars.characterdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sciencefox.starwars.databinding.FragmentCharacterDetailsBinding
import com.sciencefox.starwars.model.Character

/**
 * A fragment representing a character.
 */
class CharacterDetailsFragment(private val characterDetails: Character) : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding?.apply {
            name.text = characterDetails.name
            birth.text = characterDetails.birthYear
            height.text = characterDetails.height
            films.text = characterDetails.films.toString()
            crawl.text = characterDetails.crawl.toString()
        }

    }
}