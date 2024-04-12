package com.ierusalem.employeemanagement.features.notification

data class SendMessageDto(
    val to: String?,
    val notification: NotificationBody
)

data class NotificationBody(
    val title: String,
    val deadline: String
)