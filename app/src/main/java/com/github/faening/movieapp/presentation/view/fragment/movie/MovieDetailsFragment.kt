package com.github.faening.movieapp.presentation.view.fragment.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentMovieDetailsBinding
import com.github.faening.movieapp.domain.model.Cast
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.presentation.view.adapter.CastAdapter
import com.github.faening.movieapp.presentation.view.adapter.ViewPagerAdapter
import com.github.faening.movieapp.presentation.viewmodel.MovieDetailsViewModel
import com.github.faening.movieapp.utils.StateView
import com.github.faening.movieapp.utils.initializeToolbar
import com.github.faening.movieapp.utils.setComponentVisibility
import com.github.faening.movieapp.utils.setShapeableImageViewBottomRoundedCorners
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val binding by lazy { FragmentMovieDetailsBinding.inflate(layoutInflater) }
    private val args by lazy { MovieDetailsFragmentArgs.fromBundle(requireArguments()) }
    private val castAdapter by lazy { CastAdapter(requireContext()) }
    private val viewModel by viewModels<MovieDetailsViewModel>()
    private var isMovieDetailsLoading = false
    private var isMovieCreditsLoading = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeToolbar(toolbar = binding.movieDetailsToolbar, showBackButton = true, backButtonLight = true)
        setShapeableImageViewBottomRoundedCorners(binding.movieDetailsCover, 48f)

        fetchAndObserveMovieDetails()
        fetchAndObserveMovieCredits()
        setupViewListeners()
        setupTabLayout()
    }

    private fun fetchAndObserveMovieDetails() {
        viewModel.getMovieDetails(movieId = args.movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    isMovieDetailsLoading = true
                    updateProgressBarVisibility()
                }

                is StateView.Success -> {
                    isMovieDetailsLoading = false
                    updateProgressBarVisibility()
                    updateMovieDetailsViewVisibility()

                    stateView.data?.let { configData(it) }
                }

                is StateView.Error -> {
                    isMovieCreditsLoading = false
                    updateProgressBarVisibility()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun configData(movie: Movie) {
        binding.apply {
            Glide
                .with(requireContext())
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(movieDetailsCover)

            this.movieDetailsTitle.text = movie.title

            movie.voteAverage?.let {
                setComponentVisibility(movieDetailsVoteAverage, true)
                setComponentVisibility(movieDetailsVoteAverageIcon, true)
                movieDetailsVoteAverage.text = String.format("%.1f", it)
            }

            movie.releaseDate?.let {
                setComponentVisibility(movieDetailsReleaseDate, true)
                movieDetailsReleaseDate.text = it.substring(0, 4)
            }

            movie.adult?.let {
                val parentalRatingAdult = getString(R.string.movie_details_parental_rating_adult)
                val parentalRatingGeneral = getString(R.string.movie_details_parental_rating_general)
                setComponentVisibility(movieDetailsParentalRating, true)
                movieDetailsParentalRating.text = if (it) parentalRatingAdult else parentalRatingGeneral
            }

            movie.productionCountries?.get(0)?.name?.let {
                setComponentVisibility(movieDetailsProductionCountry, true)
                movieDetailsProductionCountry.text = it
            }

            movie.genres?.let {
                setComponentVisibility(movieDetailsGenresScroll, true)
                val genresText = getString(R.string.movie_details_genres)
                movieDetailsGenres.text = "${genresText}: ${movie.genres.joinToString { it.name ?: ", " }}"
            }

            movie.overview?.let {
                setComponentVisibility(movieDetailsOverview, true)
                setComponentVisibility(movieDetailsOverviewLinkExpand, true)
                this.movieDetailsOverview.text = movie.overview
            }
        }
    }

    private fun fetchAndObserveMovieCredits() {
        viewModel.getMovieCredits(movieId = args.movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    isMovieCreditsLoading = true
                    updateProgressBarVisibility()
                }

                is StateView.Success -> {
                    isMovieCreditsLoading = false
                    updateProgressBarVisibility()
                    updateMovieDetailsViewVisibility()

                    stateView.data?.let { movieCredits ->
                        movieCredits.cast?.let { castList ->
                            setupAndPopulateCastRecyclerView(castList)
                        }
                    }
                }

                is StateView.Error -> {
                    isMovieCreditsLoading = false
                    updateProgressBarVisibility()
                }
            }
        }
    }

    private fun updateProgressBarVisibility() {
        setComponentVisibility(binding.movieDetailsProgressbar, isMovieDetailsLoading || isMovieCreditsLoading)
    }

    private fun updateMovieDetailsViewVisibility() {
        setComponentVisibility(binding.movieDetailsContent, !(isMovieDetailsLoading || isMovieCreditsLoading))
    }

    private fun setupAndPopulateCastRecyclerView(casts: List<Cast>) {
        binding.movieDetailsCastRecyclerView.apply {
            setComponentVisibility(this, true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = castAdapter.also { it.submitList(casts) }
        }
    }

    private fun setupViewListeners() {
        setupExpandOverviewButtonListener()
    }

    private fun setupExpandOverviewButtonListener() {
        binding.movieDetailsOverviewLinkExpand.setOnClickListener {
            toggleOverviewTextAndButton()
        }
    }

    private fun toggleOverviewTextAndButton() {
        binding.movieDetailsOverview.maxLines = if (binding.movieDetailsOverview.maxLines == 3) Int.MAX_VALUE else 3
        binding.movieDetailsOverviewLinkExpand.text =
            if (binding.movieDetailsOverview.maxLines == 3) getString(R.string.movie_details_overview_link_expand)
            else getString(R.string.movie_details_overview_link_collapse)
    }

    private fun setupTabLayout() {
        val viewPagerAdapter = ViewPagerAdapter(requireActivity())

        viewPagerAdapter.addFragment(
            fragment = MovieTrailerFragment(),
            titleResId = R.string.movie_trailer_tab_layout_title
        )

        viewPagerAdapter.addFragment(
            fragment = SimilarMoviesFragment.newInstance(args.movieId),
            titleResId = R.string.similar_movies_tab_layout_title
        )

        viewPagerAdapter.addFragment(
            fragment = MovieCommentsFragment(),
            titleResId = R.string.movie_comments_tab_layout_title
        )

        with(binding) {
            movieDetailsViewPager.adapter = viewPagerAdapter
            movieDetailsViewPager.offscreenPageLimit = viewPagerAdapter.itemCount

            TabLayoutMediator(movieDetailsTabLayout, movieDetailsViewPager) { tab, position ->
                tab.text = getString(viewPagerAdapter.getTitleResId(position))
            }.attach()
        }
    }

}