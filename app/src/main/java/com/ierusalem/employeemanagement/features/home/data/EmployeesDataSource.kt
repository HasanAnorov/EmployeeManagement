package com.ierusalem.employeemanagement.features.home.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ierusalem.employeemanagement.features.home.domain.HomeRepository
import com.ierusalem.employeemanagement.features.home.presentation.employees.model.EmployeeModel

class EmployeesDataSource(
    private val repo: HomeRepository,
    private val searchQuery: String,
    val onRefresh: (Boolean) -> Unit
) : PagingSource<Int, EmployeeModel>() {
    override fun getRefreshKey(state: PagingState<Int, EmployeeModel>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EmployeeModel> {
        return try {
            onRefresh(true)
            Log.d("ahi3646", "load: ${params.key} ")
            val page = params.key ?: 1
            val response = repo.getEmployees(page, 9, searchQuery)
            if(response.isSuccessful){
                onRefresh(false)
                LoadResult.Page(
                    data = response.body()!!.results,
                    prevKey = null,
                    nextKey = if (response.body()!!.results.isNotEmpty()) page+1  else null
                )
            }else{
                onRefresh(false)
                LoadResult.Error(Throwable(response.errorBody().toString()))
            }
        } catch (error: Exception) {
            onRefresh(false)
            LoadResult.Error(error)
        }
    }
}