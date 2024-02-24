package com.github.faening.movieapp.presentation.view.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.faening.movieapp.databinding.FragmentMovieCommentsBinding
import com.github.faening.movieapp.presentation.viewmodel.MovieCommentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieCommentsFragment : Fragment() {

    private val binding by lazy { FragmentMovieCommentsBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MovieCommentsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

}