package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.media.MediaPlayer // Import MediaPlayer
import java.util.Random

class MainActivity : AppCompatActivity() {
    lateinit var diceImage: ImageView
    private lateinit var mediaPlayer: MediaPlayer // Declare MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button_res)
        rollButton.setOnClickListener {
            rollDice()
        }

        diceImage = findViewById(R.id.dice_image)

        // Initialize MediaPlayer with the audio file
        mediaPlayer = MediaPlayer.create(this, R.raw.roll_sound)
    }

    private fun rollDice() {
        val resultText: TextView = findViewById(R.id.text_res)
        val randomText = Random().nextInt(6) + 1

        val drawableResource = when (randomText) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)
        resultText.text = randomText.toString()

        // Play the sound
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepare() // Prepare the media player for a new playback
        }
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release the MediaPlayer resources when the activity is destroyed
        mediaPlayer.release()
    }
}
