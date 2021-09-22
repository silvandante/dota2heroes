package com.walker.finalappdotaheros.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.walker.finalappdotaheros.R
import com.walker.finalappdotaheros.databinding.FragmentHercardListBinding
import com.walker.finalappdotaheros.models.Hero
import com.walker.finalappdotaheros.utils.Status
import com.walker.finalappdotaheros.viewmodels.HeroViewModel
import org.koin.android.ext.android.inject


class HeroCardFragment : Fragment() {

    private var columnCount = 1

    private lateinit var binding: FragmentHercardListBinding
    private val heroViewModel: HeroViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_hercard_list,
            container,
            false
        )

        registerLoading()
        registerObservers()
        registerListeners()
        heroViewModel.getHeroesList()

        return binding.root
    }

    private fun registerListeners(){
        binding.refreshButton.setOnClickListener {
            registerLoading()
            heroViewModel.getHeroesList()
            binding.errorContainer.isVisible = false
        }
    }

    private fun registerLoading() {
        binding.progressBar.isVisible = true
    }

    private fun registerObservers() {
        heroViewModel.heroesLiveData.observe(viewLifecycleOwner, { result ->
            binding.progressBar.isVisible = result.status == Status.LOADING

            when(result.status) {
                Status.ERROR -> {
                    binding.errorContainer.isVisible = true
                    binding.noContentText.text = result.message
                }
                Status.SUCCESS -> {
                    binding.noContentText.isVisible = false
                    registerListAdapter(result.data ?: listOf())
                }
                else -> {
                    //Nothing here
                }
            }
        })
    }

    private fun registerListAdapter(listHeroes: List<Hero>) {
        with(binding.list) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = MyHeroCardRecyclerViewAdapter(
                        listHeroes,
                        context,
                        likeHero = { heroId ->
                            heroViewModel.likeThisHero(heroId)
                        },
                        dislikeHero = { heroId ->
                            heroViewModel.dislikeThisHero(heroId)
                        },
                        isHeroLiked = { heroId ->
                            return@MyHeroCardRecyclerViewAdapter heroViewModel.isHeroLiked(heroId)
                        }
            )
            isVisible = true
        }
    }

}