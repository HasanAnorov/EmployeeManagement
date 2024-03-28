package com.ierusalem.employeemanagement.features.downloader

import android.app.DownloadManager
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import java.util.Locale

class AndroidDownloader(
    private val context: Context,
) : Downloader {

    private val preferenceHelper = PreferenceHelper(context)
    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        val uri  = Uri.parse(url)
            val name = uri.lastPathSegment
        val mimeType = getMimeType(uri)
        Log.d("ahi3646", "downloadFile mimeType: $mimeType")

        val request = DownloadManager
            .Request(url.toUri()).setMimeType(mimeType)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(name)
            .addRequestHeader("Authorization", ": Bearer ${preferenceHelper.getToken()}")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name)
        return downloadManager.enqueue(request)
    }

    private fun getMimeType(uri: Uri): String? {
        val mimeType: String? = if (ContentResolver.SCHEME_CONTENT == uri.scheme) {
            val cr: ContentResolver = context.contentResolver
            cr.getType(uri)
        } else {
            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(
                uri
                    .toString()
            )
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                fileExtension.lowercase(Locale.getDefault())
            )
        }
        return mimeType
    }
}