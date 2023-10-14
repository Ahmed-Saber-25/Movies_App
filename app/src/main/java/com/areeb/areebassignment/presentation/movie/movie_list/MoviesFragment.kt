package com.areeb.areebassignment.presentation.movie.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.areeb.areebassignment.R
import com.areeb.areebassignment.databinding.FragmentMoviesBinding
import com.areeb.areebassignment.domain.model.Movie
import com.google.android.material.transition.platform.MaterialElevationScale
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.areeb.areebassignment.presentation.util.showRetry
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply {
            duration = 300L
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        initRecyclerView()
        handleLoadingStateAdapter()
        subscribeObserver()
        retry()
    }

    private fun initRecyclerView() {
        binding.moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            moviesListAdapter =
                MoviesListAdapter { movie: Movie ->

                    exitTransition = MaterialElevationScale(false).apply {
                        duration = 300L
                    }
                    reenterTransition = MaterialElevationScale(true).apply {
                        duration = 300L
                    }
                    findNavController().navigate(
                        MoviesFragmentDirections
                            .actionMoviesFragmentToMovieDetailFragment(movie = movie)
                    )
                }

            adapter = moviesListAdapter

            binding.moviesRecyclerView.adapter = moviesListAdapter.withLoadStateFooter(
                footer = MovieLoadingStateAdapter(moviesListAdapter::retry)
            )
        }
    }

    private fun subscribeObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    uiState.movies?.let {
                        moviesListAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun handleLoadingStateAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesListAdapter.addLoadStateListener { loadState ->
                    if (loadState.source.refresh is LoadState.Loading) {
                        if (moviesListAdapter.snapshot().isEmpty()) {
                            binding.shimmerLayout.startShimmer()
                        }

                        activity?.showRetry(false, null)

                    } else {
                        binding.shimmerLayout.apply {
                            stopShimmer()
                            visibility = View.GONE
                        }

                        val error = when {
                            loadState.source.prepend is LoadState.Error -> loadState.source.prepend as LoadState.Error
                            loadState.source.append is LoadState.Error -> loadState.source.append as LoadState.Error
                            loadState.source.refresh is LoadState.Error -> loadState.source.refresh as LoadState.Error
                            else -> null
                        }
                        error?.let {
                            if (moviesListAdapter.snapshot().isEmpty()) {
                                activity?.showRetry(true, "Check Network Connection!")
                            }
                        }
                    }
                    binding.count = moviesListAdapter.itemCount.toString()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesListAdapter.loadStateFlow
                    .distinctUntilChangedBy { it.refresh }
                    .filter { it.refresh is LoadState.NotLoading }
            }
        }
    }

    private fun retry() {
        requireActivity().findViewById<Button>(R.id.btn_retry).setOnClickListener {
            moviesListAdapter.retry()
        }
    }

}























