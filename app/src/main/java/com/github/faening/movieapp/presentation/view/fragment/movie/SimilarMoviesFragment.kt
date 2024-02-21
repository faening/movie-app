package com.github.faening.movieapp.presentation.view.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.github.faening.movieapp.MainGraphDirections
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentSimilarMoviesBinding
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.presentation.view.adapter.MovieAdapter
import com.github.faening.movieapp.presentation.viewmodel.SimilarMoviesViewModel
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimilarMoviesFragment : Fragment() {

    private val binding by lazy { FragmentSimilarMoviesBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SimilarMoviesViewModel>()
    private val movieAdapter by lazy {
        MovieAdapter(
            context = requireContext(),
            layoutInflater = R.layout.genre_movie_item
        ) { movieId ->
            movieId?.let {
                val action = MainGraphDirections.actionGlobalMovieDetailsFragment(it)
                findNavController().navigate(action)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt(ARG_MOVIE_ID) ?: return
        fetchAndObservesimilarMovies(movieId)
    }

    private fun fetchAndObservesimilarMovies(movieId: Int) {
        viewModel.getSimilarMovies(movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                }

                is StateView.Success -> {
                    stateView.data?.let { movies ->
                        setupAndPopulateSimilarMoviesRecyclerView(movies)
                    }
                }

                is StateView.Error -> {

                }
            }
        }
    }

    private fun setupAndPopulateSimilarMoviesRecyclerView(movies: List<Movie>) {
        binding.similarMoviesRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = movieAdapter.also { it.submitList(movies) }
        }
    }

    companion object {
        private const val ARG_MOVIE_ID = "movieId"

        fun newInstance(movieId: Int): SimilarMoviesFragment {
            val fragment = SimilarMoviesFragment()
            val args = Bundle()
            args.putInt(ARG_MOVIE_ID, movieId)
            fragment.arguments = args
            return fragment
        }
    }
}