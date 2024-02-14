package com.github.faening.movieapp.ui.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.github.faening.movieapp.databinding.FragmentMovieDetailsBinding
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.utils.StateView
import com.github.faening.movieapp.utils.initializeToolbar
import com.github.faening.movieapp.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val binding by lazy { FragmentMovieDetailsBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MovieDetailsViewModel>()
    private val args by lazy { MovieDetailsFragmentArgs.fromBundle(requireArguments()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar(toolbar = binding.movieDetailsToolbar, showBackButton = true, backButtonLight = true)
        getMovieDetails()
    }

    private fun getMovieDetails() {
        viewModel.getMovieDetails(movieId = args.movieId).observe(viewLifecycleOwner) { stateView ->
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
            Glide
                .with(requireContext())
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(this.movieDetailsCover)

            this.movieDetailsTitle.text = movie.title
        }
    }

}