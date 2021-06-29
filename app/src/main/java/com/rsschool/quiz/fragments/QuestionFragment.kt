package com.rsschool.quiz.fragments

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
import com.rsschool.quiz.AnswerAccumulator
import com.rsschool.quiz.R
import com.rsschool.quiz.Router
import com.rsschool.quiz.databinding.FragmentQuizBinding
import com.rsschool.quiz.model.Question
import com.rsschool.quiz.utils.Answers
import com.rsschool.quiz.utils.QuestionList

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
        val position = arguments?.getInt("position", -1)!!
        val questionText = question.questionText
        val answers = question.answers

        //Setting initial state of views
        setupInitialViewState(position, questionText)

        //Setting up onClickListeners
        setupClickListeners(position)

        //Setting up
        setupNextButtonText(position)

        //this listener checks if something is checked and enables "next button"
        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            binding.nextButton.isEnabled = true
        }

        //Setting up previous choice if exists
        setupLastRadioButtonChoice(position, answers)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupInitialViewState(position: Int, questionText: String) {
        binding.toolbar.isEnabled = position != 0
        binding.toolbar.title = "Question ${position + 1}"

        binding.previousButton.isEnabled = position != 0
        binding.nextButton.isEnabled = false

        binding.question.text = questionText
    }

    private fun setupClickListeners(position: Int) {
        binding.nextButton.setOnClickListener {
            navigateToNextFragment(position)
        }
        binding.previousButton.setOnClickListener {
            navigateToPreviousFragment(position)
        }
        binding.toolbar.setOnClickListener {
            navigateToPreviousFragment(position)
        }
    }

    //Setting up buttons in fragment by it's position in adapter
    private fun setupNextButtonText(position: Int) {
        if (position == QuestionList.questions.lastIndex) {
            binding.nextButton.text = getString(R.string.submit_button_text)
        } else {
            binding.nextButton.text = getString(R.string.next_button_text)
        }
    }

    //restoring last user choice from answer accumulator
    //if there is too much fragments , so state of choice may be lost
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

    //get index of chosen radioButton is group
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
    }
}