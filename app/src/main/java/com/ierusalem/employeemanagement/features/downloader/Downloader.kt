package com.ierusalem.employeemanagement.features.downloader

interface Downloader {
    fun downloadFile(url: String): Long
}