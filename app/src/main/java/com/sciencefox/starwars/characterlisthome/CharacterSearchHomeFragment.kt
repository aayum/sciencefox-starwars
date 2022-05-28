package com.sciencefox.starwars.characterlisthome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sciencefox.starwars.characterdetails.CharacterDetailsFragment
import com.sciencefox.starwars.R
import com.sciencefox.starwars.databinding.FragmentCharacterSearchHomeBinding
import com.sciencefox.starwars.extensions.hide
import com.sciencefox.starwars.extensions.show
import com.sciencefox.starwars.model.CharacterResponse
import com.sciencefox.starwars.model.Character

/**
 * A fragment representing first screen
 * to display list of characters and search bar
 */
class CharacterSearchHomeFragment : Fragment(), CharacterSearchHomeContract.View {

    private var _binding: FragmentCharacterSearchHomeBinding? = null
    private val binding get() = _binding
    lateinit var presenter: CharacterSearchHomeContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterSearchHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = CharacterSearchHomePresenter(this)

        _binding?.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
            }

            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    presenter.loadCharacters()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    presenter.loadCharacters()
                    return false
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun handleCharacterResponse(characterDetailList: CharacterResponse) {
        val adapter =
            CharacterListAdapter(characterDetailList.characterList) { characterDetailsModel ->
                presenter.handleItemClick(characterDetailsModel)
            }

        _binding?.recyclerView?.adapter = adapter
    }

    override fun openDetailsFragment(character: Character) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, CharacterDetailsFragment(character))
            ?.addToBackStack(getString(R.string.fragment_tag))
            ?.commit()
    }

    override fun showLoader() {
        _binding?.loader?.show()
    }

    override fun hideLoader() {
        _binding?.loader?.hide()
    }
}