package com.arun.youtubeplayer

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "TZE9gVF1QbA"
const val YOUTUBE_PLAYLIST_ID = "PLUaQmDAYhFGCHUfSJc5pANqmLJN5w1JjE"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        player?.setPlaybackEventListener(playbackEventListeners)
        player?.setPlayerStateChangeListener(playerStateChangeListener)
        if(!wasRestored){
            player?.loadVideo(YOUTUBE_VIDEO_ID)
        }else{
            player?.play()
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?, initializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0
        if (initializationResult?.isUserRecoverableError == true) {
            initializationResult.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            Toast.makeText(
                this,
                "Error was initializing the YouTube Player $initializationResult",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val playbackEventListeners = object: YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "Video was started playing...", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "Video has paused...", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onSeekTo(skip: Int) {
        }
    }
    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener{
        override fun onLoading() {
            Toast.makeText(this@YoutubeActivity, "Loading the video", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {
            Toast.makeText(this@YoutubeActivity, "Video has loaded", Toast.LENGTH_SHORT).show()
        }

        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "Ad being started", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "You've start watching this video", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "You've finish this video", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }
}