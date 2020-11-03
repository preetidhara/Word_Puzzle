package com.example.wordpuzzle

import android.graphics.Color
import android.graphics.drawable.shapes.Shape
import android.icu.lang.UCharacter.DecompositionType.CIRCLE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wordpuzzle.databinding.FragmentScoreBinding
import nl.dionsegijn.konfetti.models.Shape.Companion.RECT
import nl.dionsegijn.konfetti.models.Size


class ScoreFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val binding:FragmentScoreBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_score,container,false)
        val scoreFragmentArgs:ScoreFragmentArgs by navArgs<ScoreFragmentArgs>()


        binding.viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(nl.dionsegijn.konfetti.models.Shape.Square, nl.dionsegijn.konfetti.models.Shape.Circle)
                .addSizes(Size(12))
                .setPosition(-50f, binding.viewKonfetti.width + 50f, -50f, -50f)
                .streamFor(300, 5000L)

        binding.scoreText.text= arguments?.let { ScoreFragmentArgs.fromBundle(it).score.toString() }

        binding.playAgainButton.setOnClickListener(){
            findNavController().navigate(R.id.action_scoreFragment_to_game)
        }

        return binding.root



    }

    //private fun loadConfeti() {




}
