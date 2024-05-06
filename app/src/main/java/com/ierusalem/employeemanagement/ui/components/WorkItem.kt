package com.ierusalem.employeemanagement.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun WorkItem(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
    fullName: String,
    position: String,
    description: String,
    image: String,
    deadline: String? = null
) {
    Column(
        modifier = modifier
            .clickable {
                onItemClick()
            }
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            content = {
                GlideImage(
                    imageModel = { image },
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .size(48.dp)
                        .clip(CircleShape),
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.CenterStart,
                        contentDescription = null
                    ),
                    failure = {
                        Image(
                            modifier = Modifier
                                .size(52.dp),
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = null
                        )
                    }
                )

                Column(
                    modifier = Modifier
                        .weight(1F)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = fullName,
                        maxLines = 1,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 4.dp),
                        text = position,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelSmall
                    )
                }

            }
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = stringResource(R.string.work_description),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier
                .padding(top = 0.dp),
            text = description,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            maxLines = 1,
            lineHeight = 20.sp,
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.titleSmall,
            overflow = TextOverflow.Ellipsis
        )
        if(deadline != null){
            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = stringResource(R.string.deadline),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier
                    .padding(top = 0.dp),
                text = deadline,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Composable
@Preview
fun ExpandableCardPreview() {
    EmployeeManagementTheme {
        WorkItem(
            position = "Senior Dev",
            fullName = "Hasan Anorov",
            deadline = "2024-2-12",
            image = "",
            description = "This is a description of the task which you have requested for",
            onItemClick = {}
        )
    }
}

@Composable
@Preview
fun ExpandableCardPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        WorkItem(
            position = "Senior Dev",
            fullName = "Hasan Anorov",
            deadline = "2024-2-12",
            image = "",
            description = "This is a description of the task which you have requested for",
            onItemClick = {}
        )
    }
}