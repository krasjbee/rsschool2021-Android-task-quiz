package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentShareBinding

class ShareFragment : Fragment() {
    private var _binding: FragmentShareBinding? = null
    private val binding get() = _binding!!
    private var answerAccumulator: AnswerAccumulator? = null
    private var router: Router? = null

    override fun onAttach(context: Context) {
        if (context.applicationContext is AnswerAccumulator) answerAccumulator =
            context.applicationContext as AnswerAccumulator
        if (context is Router) router = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShareBinding.inflate(inflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.ibExit.setOnClickListener {
            Int
        }
        binding.ibRetry.setOnClickListener {
            answerAccumulator?.resetAnswers()
            router?.toStartFragment()
        }
        binding.ibShare.setOnClickListener {

        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        answerAccumulator = null
        router = null
        super.onDetach()
    }
}