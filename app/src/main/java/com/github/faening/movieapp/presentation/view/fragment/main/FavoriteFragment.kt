package com.github.faening.movieapp.presentation.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.faening.movieapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private val binding by lazy { FragmentFavoriteBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }
}