package com.ierusalem.employeemanagement.core.downloader

interface Downloader {
    fun downloadFile(url: String): Long
}