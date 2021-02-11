package com.aherbel.agileenginetest.presentation.screens.imagelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import com.aherbel.agileenginetest.R
import com.aherbel.agileenginetest.databinding.FragmentImagesListBinding
import com.aherbel.agileenginetest.domain.model.ImagePlaceholder
import com.aherbel.agileenginetest.presentation.screens.imagedetail.ImageDetailFragment
import com.aherbel.agileenginetest.presentation.screens.imagelist.list.ImagesAdapter
import com.aherbel.agileenginetest.presentation.utils.GridInfiniteScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImagesListFragment : Fragment(R.layout.fragment_images_list) {
    
    private lateinit var binding: FragmentImagesListBinding
    
    private val imageListViewModel: ImageListViewModel by viewModel()
    
    private val imagesAdapter: ImagesAdapter = ImagesAdapter()
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentImagesListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = imageListViewModel
        }
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        
        observeImages()
        
        imageListViewModel.fetchNextImages()
    }
    
    private fun setupRecyclerView() {
        imagesAdapter.onImageClickListener = object : ImagesAdapter.OnImageClickListener {
            override fun onImageClicked(image: ImagePlaceholder) {
                goToImageDetail(image)
            }
        }
        
        binding.rvImages.apply {
            adapter = imagesAdapter
            
            val gridLayoutManager: GridLayoutManager? = layoutManager as? GridLayoutManager
            addOnScrollListener(object : GridInfiniteScrollListener(gridLayoutManager) {
    
                override fun loadMoreItems() = imageListViewModel.fetchNextImages()
                override fun isLastPage(): Boolean = imageListViewModel.isLastPage.value ?: false
                override fun isLoading(): Boolean = imageListViewModel.isLoading.value ?: false
    
            })
        }
        
    }
    
    private fun observeImages() {
        imageListViewModel.images.observe(viewLifecycleOwner, { images ->
            imagesAdapter.add(images)
        })
    }
    
    private fun goToImageDetail(image: ImagePlaceholder) {
        if (isAdded) {
            requireActivity().supportFragmentManager.commit {
                replace(R.id.container, ImageDetailFragment.newInstance(image.id), ImageDetailFragment.TAG)
                addToBackStack(ImageDetailFragment.TAG)
            }
        }
    }
    
    companion object {
        
        const val TAG = "ImagesListFragment"
    }
    
}