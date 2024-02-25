package com.github.faening.movieapp.presentation.view.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.faening.movieapp.databinding.FragmentMovieReviewsBinding
import com.github.faening.movieapp.domain.model.MovieReview
import com.github.faening.movieapp.presentation.view.adapter.MovieReviewsAdapter
import com.github.faening.movieapp.presentation.viewmodel.MovieReviewsViewModel
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieReviewsFragment : Fragment() {

    private val binding by lazy { FragmentMovieReviewsBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MovieReviewsViewModel>()
    private val movieReviewsAdapter by lazy { MovieReviewsAdapter(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt(MovieReviewsFragment.ARG_MOVIE_ID) ?: return

        setupAndPopulateMovieReviewsRecyclerView()
        fetchAndObserveMovieReviews(movieId)
    }

    private fun setupAndPopulateMovieReviewsRecyclerView() {
        binding.movieReviewsList.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = movieReviewsAdapter
        }
    }

    private fun fetchAndObserveMovieReviews(movieId: Int) {
        viewModel.getMovieReviews(movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }

                is StateView.Success -> {
                    stateView.data?.let { reviews ->
                        movieReviewsAdapter.submitList(reviews)
                    }
                }

                is StateView.Error -> {

                }
            }
        }
    }

    companion object {
        private const val ARG_MOVIE_ID = "movieId"

        fun newInstance(movieId: Int): MovieReviewsFragment {
            val fragment = MovieReviewsFragment()
            val args = Bundle()
            args.putInt(ARG_MOVIE_ID, movieId)
            fragment.arguments = args
            return fragment
        }
    }

}