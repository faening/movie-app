package com.github.faening.movieapp.presentation.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.faening.movieapp.MainGraphDirections
import com.github.faening.movieapp.databinding.FragmentHomeBinding
import com.github.faening.movieapp.presentation.model.GenrePresentation
import com.github.faening.movieapp.presentation.view.adapter.GenreWithMoviesAdapter
import com.github.faening.movieapp.presentation.viewmodel.HomeViewModel
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<HomeViewModel>()
    private val genreWithMoviesAdapter by lazy {
        GenreWithMoviesAdapter(
            { movieId ->
                movieId?.let {
                    val action =  MainGraphDirections.actionGlobalMovieDetailsFragment(it)
                    findNavController().navigate(action)
                }
            },
            { genreId, genreName ->
                val action = HomeFragmentDirections.actionMenuHomeToMovieGenreFragment(genreId, genreName)
                findNavController().navigate(action)
            },
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        getAllGenres()
    }

    private fun initializeRecyclerView() {
        with(binding.homeList) {
            setHasFixedSize(true)
            adapter = genreWithMoviesAdapter
        }
    }

    private fun getAllGenres() {
        viewModel.getAllGenres().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    // TODO: Not yet implemented
                }

                is StateView.Success -> {
                    val genres = stateView.data ?: emptyList()
                    genreWithMoviesAdapter.submitList(genres)
                    getMoviesByGenre(genres)
                }

                is StateView.Error -> {
                    // TODO: Not yet implemented
                }
            }
        }
    }

    private fun getMoviesByGenre(genres: List<GenrePresentation>) {
        val genresMutableList = genres.toMutableList()

        genresMutableList.forEachIndexed { index, genre ->
            viewModel.getMoviesByGenre(genre.id).observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {
                        // TODO: Not yet implemented
                    }

                    is StateView.Success -> {
                        val movies = stateView.data?.take(10) ?: emptyList()
                        genresMutableList[index] = genre.copy(movies = movies)
                        lifecycleScope.launch {
                            delay(1000)
                            genreWithMoviesAdapter.submitList(genresMutableList)
                        }
                    }

                    is StateView.Error -> {
                        // TODO: Not yet implemented
                    }
                }
            }
        }
    }

}