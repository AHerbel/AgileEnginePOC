package com.aherbel.agileenginetest.presentation.screens.imagedetail

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.Coil
import coil.load
import com.aherbel.agileenginetest.R
import com.aherbel.agileenginetest.databinding.FragmentImageDetailBinding
import com.reservando.android.presentation.mvvm.observeEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageDetailFragment : Fragment(R.layout.fragment_image_detail) {
    
    private lateinit var binding: FragmentImageDetailBinding
    
    private val imageDetailViewModel: ImageDetailViewModel by viewModel()
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentImageDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = imageDetailViewModel
        }
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        observeImage()
        observeOnShare()
        
        val imageId = arguments?.getString(IMAGE_ID).orEmpty()
        imageDetailViewModel.getImage(imageId)
    }
    
    private fun observeImage() {
        imageDetailViewModel.image.observe(viewLifecycleOwner, { image ->
            binding.image.load(image.fullPicture) {
                crossfade(200)
            }
        })
    }
    
    private fun observeOnShare() {
        imageDetailViewModel.onShare.observeEvent(viewLifecycleOwner) { image ->
            val text = with(image) { "$author - $fullPicture" }
            share(text, image.tags.orEmpty())
        }
    }
    
    private fun share(text: String, subject: String = ""): Boolean {
        return try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, text)
            startActivity(Intent.createChooser(intent, null))
            true
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            false
        }
    }
    
    companion object {
        
        private const val IMAGE_ID = "IMAGE_ID"
        
        const val TAG = "ImageDetailFragment"
        
        fun newInstance(imageId: String): ImageDetailFragment {
            return ImageDetailFragment().apply {
                arguments = Bundle().apply { putString(IMAGE_ID, imageId) }
            }
        }
    }
    
}