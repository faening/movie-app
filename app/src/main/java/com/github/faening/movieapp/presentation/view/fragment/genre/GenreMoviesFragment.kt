package com.github.faening.movieapp.presentation.view.fragment.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ferfalk.simplesearchview.SimpleSearchView
import com.github.faening.movieapp.MainGraphDirections
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentGenreMoviesBinding
import com.github.faening.movieapp.presentation.view.adapter.MovieAdapter
import com.github.faening.movieapp.presentation.viewmodel.MovieGenreViewModel
import com.github.faening.movieapp.utils.StateView
import com.github.faening.movieapp.utils.hideKeyboard
import com.github.faening.movieapp.utils.initializeToolbar
import com.github.faening.movieapp.utils.setComponentVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreMoviesFragment : Fragment() {

    private val binding by lazy { FragmentGenreMoviesBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MovieGenreViewModel>()
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
    private val args by lazy { GenreMoviesFragmentArgs.fromBundle(requireArguments()) }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeToolbar(toolbar = binding.moviGenreToolbar, title = args.genreName, showBackButton = true)
        initializeRecyclerView()
        initializeSearchView()
        getMoviesByGenre()
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search_view, menu)
        val item = menu.findItem(R.id.action_search)
        binding.movieGenreSearchView.setMenuItem(item)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initializeRecyclerView() {
        with(binding.movieGenreRecyclerView) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun initializeSearchView() {
        binding.movieGenreSearchView.setOnQueryTextListener(object : SimpleSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                hideKeyboard()
                if (query.isNotEmpty()) searchMovies(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextCleared(): Boolean {
                return false
            }
        })

        binding.movieGenreSearchView.setOnSearchViewListener(object : SimpleSearchView.SearchViewListener {
            override fun onSearchViewShown() { }

            override fun onSearchViewShownAnimation() { }

            override fun onSearchViewClosed() {
                getMoviesByGenre()
            }

            override fun onSearchViewClosedAnimation() { }
        })
    }

    private fun getMoviesByGenre() {
        viewModel.getMoviesByGenre(args.genreId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    setComponentVisibility(binding.movieGenreProgressBar, true)
                    setComponentVisibility(binding.movieGenreRecyclerView, false)
                }

                is StateView.Success -> {
                    movieAdapter.submitList(stateView.data ?: emptyList())
                    setComponentVisibility(binding.movieGenreProgressBar, false)
                    setComponentVisibility(binding.movieGenreRecyclerView, true)
                }

                is StateView.Error -> {
                    setComponentVisibility(binding.movieGenreProgressBar, false)
                }
            }
        }
    }

    private fun searchMovies(query: String) {
        viewModel.searchMovies(query).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    setComponentVisibility(binding.movieGenreProgressBar, true)
                    setComponentVisibility(binding.movieGenreRecyclerView, false)

                }

                is StateView.Success -> {
                    movieAdapter.submitList(stateView.data ?: emptyList())
                    setComponentVisibility(binding.movieGenreProgressBar, false)
                    setComponentVisibility(binding.movieGenreRecyclerView, true)
                }

                is StateView.Error -> {
                    setComponentVisibility(binding.movieGenreProgressBar, false)
                }
            }
        }
    }

}