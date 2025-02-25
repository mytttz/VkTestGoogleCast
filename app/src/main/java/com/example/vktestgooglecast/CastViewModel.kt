package com.example.vktestgooglecast

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaLoadRequestData
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManager
import com.google.android.gms.cast.framework.media.RemoteMediaClient
import kotlinx.coroutines.launch

class CastViewModel : ViewModel() {
    private var sessionManager: SessionManager? = null
    private var castSession: CastSession? = null

    fun initializeCast(context: Context) {
        sessionManager = CastContext.getSharedInstance(context).sessionManager
    }

    fun playVideo(videoUrl: String) {
        viewModelScope.launch {
            castSession = sessionManager?.currentCastSession ?: return@launch
            val remoteMediaClient: RemoteMediaClient =
                castSession?.remoteMediaClient ?: return@launch

            val mediaInfo = MediaInfo.Builder(videoUrl)
                .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
                .setContentType("video/mp4")
                .build()

            val mediaLoadRequestData = MediaLoadRequestData.Builder()
                .setMediaInfo(mediaInfo)
                .build()

            remoteMediaClient.load(mediaLoadRequestData)
        }
    }
}