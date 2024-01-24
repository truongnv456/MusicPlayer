package navigationcomponentturtorialcom.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    // now we want to change the seekbar position when the song is playing\
    // to do this we need to create a runnable object and a handler

    lateinit var runnable: Runnable
    private var handler = Handler()

    private lateinit var playBtn: ImageButton
    private lateinit var seekBar: SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        // Create a new media object
        // but before let's create a raw folder inside our res directory and import music
        val mediaPlayer = MediaPlayer.create(this, R.raw.trentinhbanduoitinhyeu)
        // Now let's create our play button event
        seekBar.progress = 0
        // and now we will add the maximum volumn of our seekbar the duration of the music
        seekBar.max = mediaPlayer.duration

        // now create our play button event
        playBtn.setOnClickListener {
            // first check that the media player is not playing
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                playBtn.setImageResource(R.drawable.ic_baseline_pause_24)
            } else {
                mediaPlayer.pause()
                playBtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }
        }

        // Now we will add the seekbar event
        // when we change our seek bar progress the song will change the position
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                // now whe change the position of seekbar the music will go to
                // that position
                if (changed) {
                    mediaPlayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
        // now if we want that music finish to play the seekbar will back to
        // 0 and the button image change
        mediaPlayer.setOnCompletionListener {
            playBtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekBar.progress = 0
        }

    }

    fun initView() {
        playBtn = findViewById(R.id.play_btn)
        seekBar = findViewById(R.id.seekbar)
    }
}