package com.xda727.native_view_as_flutter_widget.native_view_as_flutter_widget

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PictureInPictureParams
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.util.Log
import android.util.Rational
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import io.flutter.plugin.platform.PlatformView

@SuppressLint( "InflateParams")
@OptIn(UnstableApi::class)
internal class NativeView(private val context: Context, id: Int, creationParams: Map<String?, Any?>?) :
    PlatformView, LifecycleObserver {

    private var content: View = LayoutInflater.from(context).inflate(R.layout.player_activity, null)

    private var playerView: PlayerView = content.findViewById(R.id.exo_player_view)
    private var player: ExoPlayer = ExoPlayer.Builder(context).build().apply {
        setMediaItem(MediaItem.Builder().apply {
            setUri(
                "https://cdn.uzd.udevs.io/uzdigital/videos/bbc795b69faf3c4d1063777f2b220144/master.m3u8"
            )
        }.build())
        prepare()
        playWhenReady = true
    }

    override fun getView(): View {
        return content
    }

    override fun dispose() {
        player.release()
    }

    init {
        playerView.player = player
        ///
        val customPlayback = playerView.findViewById<RelativeLayout>(R.id.custom_playback)
        val rotateButton = customPlayback.findViewById<ImageView>(R.id.orientation)
        val pipButton  = customPlayback.findViewById<ImageView>(R.id.video_pip)
        val activity = context.getActivity()
        pipButton.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val params = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    PictureInPictureParams.Builder().setAspectRatio(Rational(16, 9))
                        .setAutoEnterEnabled(false).build()
                } else {
                    PictureInPictureParams.Builder().setAspectRatio(Rational(16, 9)).build()
                }
                activity?.enterPictureInPictureMode(params)
            } else {
                Toast.makeText(context, "This is my Toast message!", Toast.LENGTH_SHORT).show()
            }
        }

        rotateButton.setOnClickListener{
            Log.i("ROTATE","clicked")
            activity?.let {
                Log.i("ROTATE","found activity")
                val currentOrientation = it.requestedOrientation

                // Toggle between portrait and landscape
                val newOrientation = if (currentOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                } else {
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }

                it.requestedOrientation = newOrientation
                Log.i("ROTATE","success")
            }
        }


    }

    // Lifecycle event listeners
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.i("","NativeView resumed!")
        player.playWhenReady = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.i("","NativeView paused!")
        // Handle pause state
    }

}



fun Context.getActivity(): Activity? {
    var currentContext = this
    while (currentContext is android.content.ContextWrapper) {
        if (currentContext is Activity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    return null
}