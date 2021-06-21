package com.rsschool.quiz

import android.content.Context
import android.content.Intent
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
        answerAccumulator = Answers
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
        val resultText = StringBuilder("Your score is ").append(answerAccumulator?.getPoints())
        binding.tvYourScore.text = resultText.toString()

        binding.ibExit.setOnClickListener {

        }
        binding.ibRetry.setOnClickListener {
            answerAccumulator?.resetAnswers()
            router?.toStartFragment()
        }
        binding.ibShare.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, answerAccumulator?.getAnswers())
                type = "text/plain"
            }
            startActivity(intent)
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