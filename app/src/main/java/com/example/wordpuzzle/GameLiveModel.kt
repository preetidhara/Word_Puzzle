package com.example.wordpuzzle

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameLiveModel :ViewModel() {
    companion object{
        // This is the number of milliseconds in a second
        const val DONE=0L
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L
    }
    private lateinit var timer:CountDownTimer

    val _currentTime=MutableLiveData<Long>()
    val currentTime:LiveData<Long>
    get() = _currentTime

    val _eventFinished=MutableLiveData<Boolean>()
    val eventFinished:LiveData<Boolean>
    get() = _eventFinished

    val word = MutableLiveData<GameViewModel>()


    lateinit var words:ArrayList<GameViewModel>


    val _score= MutableLiveData<Int>()
    val score:LiveData<Int>
    get() = _score

    init {


        timer=object :CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value=(millisUntilFinished/ ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value=DONE
                _eventFinished.value=true

            }

        }
        timer.start()


        _eventFinished.value=false
        WordList()
        skipButton()
        onCorrect()
        //nextWord()
        onWrong()

        _score.value=0

    }



    fun nextWord() {
        if (words.isEmpty()){
            _eventFinished.value=true
        }
        else{
            word.value=words.removeAt(0)

        }
        _eventFinished.value=false
    }

     fun WordList() {
        words= arrayListOf(
                GameViewModel("PEA","OCK","C"),
                GameViewModel("EX","EPTION","C"),
                GameViewModel("REC","ANGLE","T"),
                GameViewModel("DOR","EMON","A"),
                GameViewModel("SHINC","AN","H"),
                GameViewModel("PY","HON","T"),
                GameViewModel("ENVI","OMNET","R"),
                GameViewModel("BEAUT","FUL","I"),
                GameViewModel("ENG","AND","L")
        )
        words.shuffle()

    }
    fun onWrong() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

     fun onCorrect() {
        _score.value=(score.value)?.plus(1)
        nextWord()
    }

     fun skipButton() {
        _score.value=(score.value)?.minus(1)
        nextWord()

    }
    fun onGameFinishComplete() {
        _eventFinished.value = false
    }


}