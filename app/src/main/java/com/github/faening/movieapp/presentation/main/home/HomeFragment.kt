package com.github.faening.movieapp.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.faening.movieapp.databinding.FragmentHomeBinding
import com.github.faening.movieapp.presentation.adapter.MoviesByGenreAdapter
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.AndroidEntryPoint

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
                    moviesByGenreAdapter.submitList(stateView.data)
                }

                is StateView.Error -> {

                }
            }
        }
    }

}