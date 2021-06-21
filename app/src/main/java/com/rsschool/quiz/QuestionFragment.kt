package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuestionFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var router: Router? = null
    private var accumulator: AnswerAccumulator? = null
    private lateinit var question: Question

    override fun onAttach(context: Context) {
        if (context is Router) {
            router = context
        }
        accumulator = Answers
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        question = arguments?.getSerializable("question") as Question
        val state = arguments?.getInt("state", -1)
        val position = arguments?.getInt("position", -1)!!
        val questionText = question.questionText
        val answers = question.answers

        binding.nextButton.setOnClickListener {
            navigateToNextFragment(position)
        }
        binding.previousButton.setOnClickListener {
            navigateToPreviousFragment(position)
        }

        binding.nextButton.isEnabled = false
        binding.toolbar.title = "Question ${position + 1}"
        binding.question.text = questionText

        setupFragmentByState(state)

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            binding.nextButton.isEnabled = true
        }

        setupLastRadioButtonChoice(position, answers)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupFragmentByState(state: Int?) {
        when (state) {
            FIRST_QUESTION -> binding.previousButton.isEnabled = false
            LAST_QUESTION -> binding.nextButton.text = getString(R.string.submit_button_text)
            else -> binding.nextButton.text = getString(R.string.next_button_text)
        }
    }

    private fun setupLastRadioButtonChoice(position: Int, answers: List<String>) {
        for (i in 0 until binding.radioGroup.size) {
            val radioButton: RadioButton = binding.radioGroup[i] as RadioButton
            val id = accumulator?.getSelectedAnswerId(position)
            if (i < answers.size) {
                radioButton.text = answers[i]
                radioButton.isChecked = radioButton.id == id
            } else {
                radioButton.isVisible = false
            }
        }
    }

    private fun navigateToNextFragment(position: Int) {
        router?.toNextFragment()
        accumulator?.addAnswerToMap(
            position,
            binding.radioGroup.checkedRadioButtonId,
            getAnswerIndex()
        )
    }

    private fun navigateToPreviousFragment(position: Int) {
        router?.toPrevFragment()
        accumulator?.addAnswerToMap(
            position,
            binding.radioGroup.checkedRadioButtonId,
            getAnswerIndex()
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        router = null
        accumulator = null
        super.onDetach()
    }

    private fun getAnswerIndex(): Int {
        var checkIndex: Int = -1
        binding.radioGroup.forEachIndexed { index, view ->
            val radioButton = view as RadioButton
            if (radioButton.isChecked) checkIndex = index
        }
        return checkIndex
    }

    companion object {
        @JvmStatic
        fun getInstance(question: Question, position: Int): Fragment {
            val fragment = QuestionFragment()
            val bundle = bundleOf("question" to question, "position" to position)
            fragment.arguments = bundle
            return fragment
        }

        @JvmStatic
        fun getInstance(question: Question, state: Int, position: Int): Fragment {
            val fragment = getInstance(question, position)
            fragment.arguments?.putInt("state", state)
            return fragment
        }

        const val FIRST_QUESTION = 0
        const val LAST_QUESTION = 1
    }
}