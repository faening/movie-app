package com.github.faening.movieapp.ui.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.faening.movieapp.databinding.FragmentMovieDetailsBinding
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.utils.StateView
import com.github.faening.movieapp.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private val binding by lazy { FragmentMovieDetailsBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieDetails()
    }

    private fun getMovieDetails() {
        viewModel.getMovieDetails(movieId = 0).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {}

                is StateView.Success -> {
                    stateView.data?.let { configData(it) }
                }

                is StateView.Error -> {}
            }
        }
    }

    private fun configData(movie: Movie) {
        binding.apply {
            // Set data to views
        }
    }

}