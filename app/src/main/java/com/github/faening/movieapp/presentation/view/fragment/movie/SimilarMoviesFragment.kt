package com.github.faening.movieapp.presentation.view.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.faening.movieapp.databinding.FragmentSimilarMoviesBinding

class SimilarMoviesFragment : Fragment() {

    private val binding by lazy { FragmentSimilarMoviesBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

}