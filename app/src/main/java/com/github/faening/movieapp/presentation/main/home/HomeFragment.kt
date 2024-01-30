package com.github.faening.movieapp.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.faening.movieapp.databinding.FragmentHomeBinding
import com.github.faening.movieapp.presentation.adapter.MoviesByGenreAdapter
import com.github.faening.movieapp.presentation.model.GenrePresentation
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val model: HomeViewModel by viewModels()
    private lateinit var moviesByGenreAdapter: MoviesByGenreAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        getAllGenres()
    }

    private fun initializeRecyclerView() {
        moviesByGenreAdapter = MoviesByGenreAdapter()
        with(binding.homeList) {
            setHasFixedSize(true)
            adapter = moviesByGenreAdapter
        }
    }

    private fun getAllGenres() {
        model.getAllGenres().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }

                is StateView.Success -> {
                    val genres = stateView.data ?: emptyList()
                    moviesByGenreAdapter.submitList(genres)
                    getMoviesByGenre(genres)
                }

                is StateView.Error -> {

                }
            }
        }
    }

    private fun getMoviesByGenre(genres: List<GenrePresentation>) {
        val genresMutableList = genres.toMutableList()

        genresMutableList.forEachIndexed { index, genre ->
            model.getMoviesByGenre(genre.id).observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {

                    }

                    is StateView.Success -> {
                        val movies = stateView.data ?: emptyList()
                        genresMutableList[index] = genre.copy(movies = movies)
                        lifecycleScope.launch {
                            delay(1000)
                            moviesByGenreAdapter.submitList(genresMutableList)
                        }
                    }

                    is StateView.Error -> {

                    }
                }
            }
        }
    }

}