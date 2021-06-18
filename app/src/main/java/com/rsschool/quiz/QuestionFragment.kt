package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.core.view.*
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuestionFragment() : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var router:Router?=null
    override fun onAttach(context: Context) {
        if (context is Router){
            router = context
        }
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater)
        val view = binding.root
        //TODO theme changer
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val question = arguments?.getSerializable("question") as Question
        val state = arguments?.getInt("state", -1)
        binding.nextButton.setOnClickListener {
            router?.nextFragment()
        }
        binding.previousButton.setOnClickListener {
            router?.prevFragment()
        }
        when (state) {
            FIRST_QUESTION -> binding.previousButton.isEnabled = false
            LAST_QUESTION -> binding.nextButton.text = "SUBMIT"
            else -> {
                binding.nextButton.isEnabled = true
                binding.nextButton.text = "Next"
            }
        }
        val questionText = question.questionText
        binding.question.text = questionText
        val answers = question.answers
        for (i in 0 until binding.radioGroup.size) {
            val radioButton: RadioButton = binding.radioGroup[i] as RadioButton
            if (i < answers.size) {
                radioButton.text = answers[i]
            } else {
                radioButton.isVisible = false
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        router=null
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun getInstance(question: Question): Fragment {
            val fragment = QuestionFragment()
            val bundle = bundleOf("question" to question)
            fragment.arguments = bundle
            return fragment
        }

        @JvmStatic
        fun getInstance(question: Question, state: Int): Fragment {
            val fragment = getInstance(question)
            fragment.arguments?.putInt("state", state)
            return fragment
        }

        const val FIRST_QUESTION = 0
        const val LAST_QUESTION = 1
        //const val CASUAL_QUESTION =2
    }
}