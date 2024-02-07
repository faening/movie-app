package com.github.faening.movieapp.ui.fragment.movie_genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.faening.movieapp.databinding.FragmentMovieGenreBinding
import com.github.faening.movieapp.utils.initializeToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieGenreFragment : Fragment() {
    private val binding by lazy { FragmentMovieGenreBinding.inflate(layoutInflater) }
    private val genreId by lazy { MovieGenreFragmentArgs.fromBundle(requireArguments()).genreId }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar(binding.movieGenreToolbar)
    }
}