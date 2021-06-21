package com.rsschool.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentShareBinding
import kotlin.system.exitProcess

class ShareFragment : Fragment() {
    private var _binding: FragmentShareBinding? = null
    private val binding get() = _binding!!
    private var answerAccumulator: AnswerAccumulator? = null
    private var router: Router? = null

    override fun onAttach(context: Context) {
        answerAccumulator = Answers
        if (context is Router) router = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShareBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.ibExit.setOnClickListener {
            exitProcess(0)
        }
        binding.ibRetry.setOnClickListener {
            answerAccumulator?.resetAnswers()
            router?.toStartFragment()
        }
        binding.ibShare.setOnClickListener {
            shareResults()
        }
        setupScore()


        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupScore() {
        val resultText =
            StringBuilder(getString(R.string.your_score)).append(answerAccumulator?.getPoints())
                .append(getString(R.string.out_of_score_string))
                .append(answerAccumulator?.getPossiblePoints())
        binding.tvYourScore.text = resultText.toString()
    }

    private fun shareResults() {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, answerAccumulator?.getAnswers())
            type = "text/plain"
        }
        startActivity(intent)
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