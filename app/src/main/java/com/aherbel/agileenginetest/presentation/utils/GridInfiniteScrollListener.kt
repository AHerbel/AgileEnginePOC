package com.aherbel.agileenginetest.presentation.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class GridInfiniteScrollListener(
    private val gridLayoutManager: GridLayoutManager?,
) : RecyclerView.OnScrollListener() {
    
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = gridLayoutManager?.childCount ?: 0
        val totalItemCount = gridLayoutManager?.itemCount ?: 0
        val firstVisibleItemPosition: Int = gridLayoutManager?.findFirstVisibleItemPosition() ?: 0
        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
            ) {
                loadMoreItems()
            }
        }
    }
    
    protected abstract fun loadMoreItems()
    
    abstract fun isLastPage(): Boolean
    
    abstract fun isLoading(): Boolean
    
}