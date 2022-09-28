package com.myapps.rockpaperscissor

import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.myapps.rockpaperscissor.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(){

    private val gameArray = arrayOf(R.drawable.paper_right, R.drawable.rock_right, R.drawable.scissor_right)
    private var gameNumber : Int = 0
    private var choice:Int=-1
    private var cpuScore=0
    private var isGameStarted=false
    private var isGameOver=true
    private var playerScore=0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        choice()
        background(choice)
        playGame()



    }

    private fun starTimer() {


        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                "00:${millisUntilFinished / 1000}".also { binding.timer.text = it }
                    .also { binding.timer.text = it }
                isGameOver=false

            }

            override fun onFinish() {
                "00:00".also { binding.timer.text = it }
                if (!isGameOver){
                    showDialog()
                }

            }
        }.start()
    }

    private fun choice(){

        binding.paperPlayer.setOnClickListener {
            choice=0
            background(choice)
        }
        binding.rockPlayer.setOnClickListener {
            choice=1
            background(choice)
        }
        binding.scissorPlayer.setOnClickListener {
            choice=2
            background(choice)
        }
        binding.random.setOnClickListener {
            choice=-1
            background(choice)
        }


    }
    private fun playGame(){
        binding.playGame.setOnClickListener {
            onClick(choice)
            if (!isGameStarted){
                starTimer()
            }
            isGameStarted=true
        }

    }
    private fun showDialog(){
        val dialog=Dialog(this)
        dialog.setContentView(R.layout.dialog)
        val winner= dialog.findViewById<TextView>(R.id.winner)
        val score= dialog.findViewById<TextView>(R.id.match_score)
        val restart=dialog.findViewById<Button>(R.id.retry)
        "Score\n$playerScore - $cpuScore".also { score.text = it }
        if (playerScore>cpuScore){
            "Player Win".also { winner.text = it }
        }
        else if(playerScore<cpuScore){
            "Cpu Win".also { winner.text = it }
        }
        else{
            "Draw".also { winner.text = it }
        }

        restart.setOnClickListener{
            restartGame()
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()



    }
    private fun restartGame(){
        isGameStarted=false
        cpuScore=0
        playerScore=0
        choice=-1
        background(choice)
        "Score\n$playerScore - $cpuScore".also { binding.scoreTv.text = it }
        binding.gameResult.text=""
    }
     private fun onClick(viewId:Int) {

        var id = viewId
         if (id==-1){
             id=Random().nextInt(3)

         }

        when(id){
            0 -> {
                gameNumber = 0
                binding.playerChoice.setImageResource(R.drawable.paper_left)
                computerPlay()
            }
            1 -> {
                gameNumber = 1
                binding.playerChoice.setImageResource(R.drawable.rock_left)
                computerPlay()
            }
           2 -> {
                gameNumber = 2
                binding.playerChoice.setImageResource(R.drawable.scissor_left)
                computerPlay()
            }
        }
    }
    private fun computerPlay() {
        val imageId = (gameArray.indices).random()
        binding.cpuChoice.setImageResource(gameArray[imageId])
        checkWinner(imageId)
    }

    private fun checkWinner(imageId : Int) {
        if(gameNumber ==0 && imageId == 0) {
            showWinner(2)
        } else if(gameNumber == 0 && imageId == 1){
            showWinner(0)
        } else if(gameNumber == 0 && imageId == 2){
            showWinner(1)
        } else if(gameNumber == 1 && imageId == 0){
            showWinner(1)
        } else if(gameNumber == 1 && imageId == 1){
            showWinner(2)
        } else if(gameNumber == 1 && imageId == 2){
            showWinner(0)
        } else if(gameNumber == 2 && imageId == 0){
            showWinner(0)
        } else if(gameNumber == 2 && imageId == 1){
            showWinner(1)
        } else if(gameNumber == 2 && imageId == 2){
            showWinner(2)
        }
    }

    private fun showWinner(result: Int) {

        when (result) {
            0 -> {
                binding.gameResult.setText(R.string.player_won)
                playerScore++

            }
            1 -> {
                binding.gameResult.setText(R.string.cpu_won)
                cpuScore++

            }
            else -> { binding.gameResult.setText(R.string.draw)}

        }
        "Score\n$playerScore - $cpuScore".also { binding.scoreTv.text = it }

    }
    private fun background(int :Int){
        when(int) {
            -1 -> {
                binding.paperPlayer.setBackgroundResource(R.drawable.transparent)
                binding.rockPlayer.setBackgroundResource(R.drawable.transparent)
                binding.scissorPlayer.setBackgroundResource(R.drawable.transparent)
                binding.random.setBackgroundResource(R.drawable.backgorund)
            }
            0 -> {
                binding.paperPlayer.setBackgroundResource(R.drawable.backgorund)
                binding.rockPlayer.setBackgroundResource(R.drawable.transparent)
                binding.scissorPlayer.setBackgroundResource(R.drawable.transparent)
                binding.random.setBackgroundResource(R.drawable.transparent)
            }
            1 -> {
                binding.paperPlayer.setBackgroundResource(R.drawable.transparent)
                binding.rockPlayer.setBackgroundResource(R.drawable.backgorund)
                binding.scissorPlayer.setBackgroundResource(R.drawable.transparent)
                binding.random.setBackgroundResource(R.drawable.transparent)
            }
            2 -> {
                binding.paperPlayer.setBackgroundResource(R.drawable.transparent)
                binding.rockPlayer.setBackgroundResource(R.drawable.transparent)
                binding.scissorPlayer.setBackgroundResource(R.drawable.backgorund)
                binding.random.setBackgroundResource(R.drawable.transparent)
            }
        }
    }


}