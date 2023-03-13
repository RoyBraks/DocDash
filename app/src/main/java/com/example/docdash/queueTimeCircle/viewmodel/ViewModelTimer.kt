package com.example.docdash.queueTimeCircle.viewmodel

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docdash.notifications.viewmodel.NotificationViewModel
import com.example.docdash.queueTimeCircle.utils.Utility
import com.example.docdash.queueTimeCircle.utils.Utility.formatTime

val timeRemaining = MutableLiveData<Long>(0)
class ViewModelTimer : ViewModel() {

    var emergencyAdded = false

    val notificationViewModel = NotificationViewModel(application = Application())

    //  Door de mutable live data is alles live te veranderen. De tijd wordt dus
    // op het scherm live afgeteld.

    private var queueTimeTimer: CountDownTimer? = null

    private val _time = MutableLiveData(Utility.TOTAL_TIME.formatTime())
    val time: LiveData<String> = _time

    private val _progress = MutableLiveData(1.00F)
    val progress: LiveData<Float> = _progress

    private val _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean> = _isPlaying

    private val _listPosition = MutableLiveData(1)
    var listPosition: LiveData<Int?> = _listPosition

    // functie om de countdown te starten
    fun handleCountDownTimer(){
        startTimer()
    }

    private fun startTimer() {

        // is nu aftellen is waar
        _isPlaying.value = true

        // Hier wordt de ingebouwde CountDownTimer aangeroepen.
        queueTimeTimer = object : CountDownTimer(Utility.TOTAL_TIME, 1000) {

            // Dit gebeurt elke tick in de countdown.
            override fun onTick(millisRemaining: Long) {

                // True als er nog 40 minuten over zijn
                val fortyMinutesLeft: Boolean = millisRemaining <= 2400000

                // Check list position (if every patient took 15 minutes)
                val listPosition = when ((millisRemaining + 900000) / 900000) {
                    in (0..1) -> 1
                    in (1..2) -> 2
                    in (2..3) -> 3
                    in (3..4) -> 4
                    else -> 5
                }

                // totale progressie van 40 minuten naar 0 voor de aftellende cirkel in het design.
                val progressValue: Float = if (fortyMinutesLeft){
                    millisRemaining.toFloat() / 2400000
                } else {
                    1.00f
                }

                handleTimerValues(millisRemaining.formatTime(), progressValue, true, listPosition)
            }
            // als de timer klaar is:
            override fun onFinish() {
                notificationViewModel.deleteAll()
                return
            }
        }.start()
    }
    private fun handleTimerValues(text: String, progress: Float, isPlaying: Boolean, listPosition: Int?) {
        _time.value = text
        _progress.value = progress
        _isPlaying.value = isPlaying
        _listPosition.value = listPosition
    }
}