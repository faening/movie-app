package com.github.faening.movieapp.ui.fragment.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentMovieDetailsBinding
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.utils.StateView
import com.github.faening.movieapp.utils.initializeToolbar
import com.github.faening.movieapp.utils.setComponentVisibility
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
        initializeListeners()

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

    @SuppressLint("SetTextI18n")
    private fun configData(movie: Movie) {
        binding.apply {
            Glide
                .with(requireContext())
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(this.movieDetailsCover)

            this.movieDetailsTitle.text = movie.title

            movie.voteAverage?.let {
                setComponentVisibility(this.movieDetailsVoteAverage, true)
                setComponentVisibility(this.movieDetailsVoteAverageIcon, true)
                this.movieDetailsVoteAverage.text = String.format("%.1f", it)
            }

            movie.releaseDate?.let {
                setComponentVisibility(this.movieDetailsReleaseDate, true)
                this.movieDetailsReleaseDate.text = it.substring(0, 4)
            }

            movie.adult?.let {
                val parentalRatingAdult = getString(R.string.movie_details_parental_rating_adult)
                val parentalRatingGeneral = getString(R.string.movie_details_parental_rating_general)
                setComponentVisibility(this.movieDetailsParentalRating, true)
                this.movieDetailsParentalRating.text = if (it) parentalRatingAdult else parentalRatingGeneral
            }

            movie.productionCountries?.get(0)?.name?.let {
                setComponentVisibility(this.movieDetailsProductionCountry, true)
                this.movieDetailsProductionCountry.text = it
            }

            val genresText = getString(R.string.movie_details_genres)
            this.movieDetailsGenres.text = "${genresText}: ${ movie.genres?.joinToString { it.name ?: ", " } }"

            this.movieDetailsOverview.text = movie.overview
        }
    }

    private fun initializeListeners() {
        buttonExpandOverviewListener()
        movieDetailScrollListener()
    }

    private fun buttonExpandOverviewListener() {
        binding.movieDetailsOverviewLinkExpand.setOnClickListener {
            binding.movieDetailsOverview.maxLines = if (binding.movieDetailsOverview.maxLines == 3) Int.MAX_VALUE else 3
            binding.movieDetailsOverviewLinkExpand.text = if (binding.movieDetailsOverview.maxLines == 3) getString(R.string.movie_details_overview_link_expand) else getString(R.string.movie_details_overview_link_collapse)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun movieDetailScrollListener() {
        binding.movieDetailsScroll.setOnTouchListener { _, event ->
            binding.movieDetailsInfoScroll.isHorizontalScrollBarEnabled = (event.action == MotionEvent.ACTION_UP)
            binding.movieDetailsGenresScroll.isHorizontalScrollBarEnabled = (event.action == MotionEvent.ACTION_UP)
            false
        }
    }

}