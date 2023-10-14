package com.areeb.areebassignment.presentation.movie.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.areeb.areebassignment.databinding.FragmentMovieDetailBinding
import com.areeb.areebassignment.domain.model.Movie
import com.areeb.areebassignment.presentation.util.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewsDetails()
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setViewsDetails() {
        val movie = arguments?.get("movie") as? Movie
        binding.movie = movie
    }

}