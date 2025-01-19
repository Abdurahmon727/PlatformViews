package com.xda727.native_view_as_flutter_widget.native_view_as_flutter_widget

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import io.flutter.plugin.platform.PlatformView

@SuppressLint("ResourceType", "InflateParams")
@OptIn(UnstableApi::class)
internal class NativeView(context: Context, id: Int, creationParams: Map<String?, Any?>?) :
    PlatformView {

    private var content: View

    private var playerView: PlayerView
    private var player: ExoPlayer

    override fun getView(): View {
        return content
    }

    override fun dispose() {
        player.release()
    }

    init {
        content = LayoutInflater.from(context).inflate(R.layout.layout, null)
        playerView = content.findViewById(R.id.playerView)
        player = ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.Builder().apply {
                setUri(
                    "https://cdn.uzd.udevs.io/uzdigital/videos/bbc795b69faf3c4d1063777f2b220144/master.m3u8"
                )
            }.build())
            prepare()
            playWhenReady = true
        }

        playerView.player = player

    }
}