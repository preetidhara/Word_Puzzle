package com.example.wordpuzzle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.wordpuzzle.databinding.FragmentTitleBinding


class Title : Fragment() {


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                           savedInstanceState: Bundle?):View? {
       val binding:FragmentTitleBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_title,container,false)

        binding.playButtton.setOnClickListener(){
            it.findNavController().navigate(TitleDirections.actionTitle2ToGame())
        }

        return binding.root


    }
}