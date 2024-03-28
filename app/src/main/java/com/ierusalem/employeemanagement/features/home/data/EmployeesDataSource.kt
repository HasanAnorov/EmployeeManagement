package com.ierusalem.employeemanagement.features.home.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ierusalem.employeemanagement.features.home.domain.HomeRepository
import com.ierusalem.employeemanagement.features.home.presentation.employees.model.Result

class EmployeesDataSource(
    private val repo: HomeRepository
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val page = params.key ?: 1
            val response = repo.getEmployees(page, 15)
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = null,
                nextKey = if (response.body()!!.results.isNotEmpty()) response.body()!!.totalPages + 1 else null
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }
}