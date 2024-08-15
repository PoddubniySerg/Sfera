package ru.zavod.feature_chats.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PagingItemsSource<T : Any>(private val loadData: suspend (Int) -> List<T>) :
    PagingSource<Int, T>() {

    companion object {
        private const val START_OFFSET = 0
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? =
        null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: START_OFFSET
        return kotlin.runCatching {
            loadData(page)
        }.fold(
            onSuccess = { items ->
                LoadResult.Page(
                    data = items,
                    prevKey = getPrevKey(params.key),
                    nextKey = getNextKey(items, page)
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }

    private fun getPrevKey(key: Int?): Int? {
        return key?.let {
            if (it > START_OFFSET) {
                it - 1
            } else {
                null
            }
        }
    }

    private fun <T> getNextKey(items: List<T>, page: Int): Int? {
        return if (items.isNotEmpty()) {
            page + 1
        } else {
            null
        }
    }
}