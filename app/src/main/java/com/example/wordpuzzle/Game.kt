package com.example.wordpuzzle

import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.wordpuzzle.databinding.FragmentGameBinding


class Game : Fragment() {

     private lateinit var ViewModel:GameLiveModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding:FragmentGameBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_game,container,false)

        //var answer_gap=binding.textAnswerGap.text.toString().toUpperCase()

        ViewModel= ViewModelProviders.of(this).get(GameLiveModel::class.java)
        //binding.lifecycleOwner = this


        ViewModel.currentTime.observe(viewLifecycleOwner, Observer {
            time->
            binding.textTimer.text= DateUtils.formatElapsedTime(time)
        })

        ViewModel.word.observe(viewLifecycleOwner, Observer {
            binding.textAnswerBox1.text=ViewModel.word.value?.Text1
            binding.textAnswerBox2.text=ViewModel.word.value?.Text2
        })

        ViewModel.score.observe(viewLifecycleOwner, Observer {
            newscore->
            binding.textScore.text=newscore.toString()
        })

        ViewModel.eventFinished.observe(viewLifecycleOwner, Observer {
            hasFinished->
            if (hasFinished){
                val CurrentScore=ViewModel.score.value?:0
                val action =GameDirections.actionGameToScoreFragment(CurrentScore)
                findNavController().navigate(action)
                ViewModel.onGameFinishComplete()


            }
        })


        binding.btnSkip.setOnClickListener(){
            ViewModel.skipButton()
        }

        binding.btnOK.setOnClickListener(){
           var answer_gap= binding.textAnswerGap.text.toString().toUpperCase()
            if (answer_gap == ViewModel.word.value?.CorrectAns){
                    binding.textAnswerGap.text= null
                     ViewModel.onCorrect()
                }
            else
            {
                binding.textAnswerGap.text= null
                ViewModel.onWrong()

            }

        }


        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

     fun checkAnswer() {


    }


}


