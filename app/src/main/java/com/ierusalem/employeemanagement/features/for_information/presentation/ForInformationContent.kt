package com.ierusalem.employeemanagement.features.for_information.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.for_information.domain.ForInformationData
import com.ierusalem.employeemanagement.ui.components.EmptyScreen
import com.ierusalem.employeemanagement.ui.components.WorkItem
import com.ierusalem.employeemanagement.core.utils.Constants

@Composable
fun ForInformationContent(
    data: List<ForInformationData>,
    isLoading: Boolean,
    onPullRefresh: () -> Unit,
    onItemClick:(Int) ->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        @Suppress("DEPRECATION")
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isLoading),
            onRefresh = { onPullRefresh() }
        ) {
            if (data.isEmpty()) {
                EmptyScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    itemsIndexed(data.sortedBy { it.isSeen }) { index, command ->
                        var image = command.image
                        if (image.startsWith("/media")){
                            image =  "${Constants.BASE_URL}${command.image}"
                        }
                        WorkItem(
                            modifier = Modifier,
                            image = image,
                            position = command.position ?: stringResource(id = R.string.not_given),
                            fullName = "${command.fullName} ",
                            description = command.text,
                            deadline = null,
                            onItemClick = { onItemClick(command.id) },
                            isSeen = command.isSeen
                        )
                        if (index < data.size - 1) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(vertical = 4.dp)
                                    .clip(RoundedCornerShape(1.dp))
                                    .background(MaterialTheme.colorScheme.outline)
                            )
                        }
                    }
                }
            }
        }
    }
}