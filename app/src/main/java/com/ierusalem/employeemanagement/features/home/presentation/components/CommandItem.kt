package com.ierusalem.employeemanagement.features.home.presentation.components

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