package com.ierusalem.employeemanagement.features.home.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ContentAlpha
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response.File

//@Composable
//fun ExpandableCard(
//    modifier: Modifier = Modifier,
//    title: String,
//    from: String,
//    files: List<File> = listOf(),
//    titleFontSize: TextUnit = 12.sp,
//    titleFontWeight: FontWeight = FontWeight.Bold,
//    description: String,
//    descriptionFontSize: TextUnit = 12.sp,
//    descriptionFontWeight: FontWeight = FontWeight.Normal,
//    descriptionMaxLines: Int = 4,
//    shape: Shape = ShapeDefaults.Medium,
//    padding: Dp = 6.dp
//) {
//    var expandedState by remember { mutableStateOf(false) }
//    val rotationState by animateFloatAsState(
//        targetValue = if (expandedState) 180f else 0f, label = "expandable item"
//    )
//
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .animateContentSize(
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = LinearOutSlowInEasing
//                )
//            ),
//        shape = shape,
//        onClick = {
//            expandedState = !expandedState
//        }
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(padding)
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Column(modifier = Modifier.weight(6F)) {
//                    Row {
//                        Text(
//                            modifier = Modifier,
//                            text = stringResource(R.string.work_description),
//                            fontSize = titleFontSize,
//                            fontWeight = titleFontWeight,
//                            maxLines = 1,
//                            style = MaterialTheme.typography.titleSmall,
//                            color = MaterialTheme.colorScheme.outline,
//                            overflow = TextOverflow.Ellipsis
//                        )
//                        Text(
//                            modifier = Modifier.weight(6f),
//                            text = title,
//                            fontSize = titleFontSize,
//                            fontWeight = titleFontWeight,
//                            maxLines = 1,
//                            style = MaterialTheme.typography.titleSmall,
//                            overflow = TextOverflow.Ellipsis
//                        )
//                    }
//                    Row(
//                        modifier = Modifier.padding(top = 2.dp)
//                    ) {
//                        Text(
//                            modifier = Modifier,
//                            text = stringResource(R.string.from),
//                            fontSize = titleFontSize,
//                            fontWeight = titleFontWeight,
//                            maxLines = 1,
//                            color = MaterialTheme.colorScheme.outline,
//                            style = MaterialTheme.typography.titleSmall,
//                            overflow = TextOverflow.Ellipsis
//                        )
//                        Text(
//                            modifier = Modifier.weight(6f),
//                            text = from,
//                            fontSize = titleFontSize,
//                            fontWeight = titleFontWeight,
//                            maxLines = 1,
//                            style = MaterialTheme.typography.titleSmall,
//                            overflow = TextOverflow.Ellipsis
//                        )
//                    }
//                }
//                IconButton(
//                    modifier = Modifier
//                        .weight(1f)
//                        .alpha(ContentAlpha.medium)
//                        .rotate(rotationState),
//                    onClick = {
//                        expandedState = !expandedState
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.ArrowDropDown,
//                        contentDescription = "Drop-Down Arrow"
//                    )
//                }
//            }
//            if (expandedState) {
//                LazyColumn {
//                    items(files) {
////                        FileItem(file = it)
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//@Preview
//fun ExpandableCardPreview() {
//    ExpandableCard(
//        title = "My Title",
//        from = "Hasan",
//        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
//                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
//                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
//                "ullamco laboris nisi ut aliquip ex ea commodo consequat."
//    )
//}
//