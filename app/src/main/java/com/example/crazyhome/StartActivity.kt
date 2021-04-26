package com.example.crazyhome

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Debug
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity


class StartActivity : AppCompatActivity() {


    private var progressBar: ProgressBar? = null

    private  var timer:CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

    }

    override fun onStart() {
        super.onStart()
        progressBar = findViewById(R.id.progressBar)
        startProgress()


    }

    override fun finish() {
        timer?.onFinish()
        timer?.cancel()
        timer = null
        super.finish()

    }


    fun goToMain(){
        var intent:Intent = Intent(StartActivity@this,MainActivity::class.java)
        startActivity(intent)
    }

    fun startProgress() {

        var totalMS:Long = 4000
        var interval:Long = 40
        progressBar?.max = 1000
        progressBar?.progress = 0
        timer = object : CountDownTimer(totalMS, interval) {
            override fun onFinish() {
                progressBar?.progress = progressBar?.max!!
                goToMain()
            }

            override fun onTick(millisUntilFinished: Long) {
                progressBar?.progress = progressBar?.progress?.plus((interval*1000/totalMS).toInt())!!;
                Log.v("aaaa","当前进度是:${progressBar?.progress}")
            }
        }
        timer?.start()
    }

}