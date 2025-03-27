package com.example.premarital.services.impl;

import com.example.premarital.dtos.QuestionDTO;
import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.dtos.QuizAdviceDTO;
import com.example.premarital.dtos.QuizCreationDTO;
import com.example.premarital.models.Question;
import com.example.premarital.models.QuestionOption;
import com.example.premarital.models.Quiz;
import com.example.premarital.models.QuizQuestion;
import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.models.Therapist;
import com.example.premarital.repositories.QuestionRepository;
import com.example.premarital.repositories.QuizQuestionRepository;
import com.example.premarital.repositories.QuizRepository;
import com.example.premarital.repositories.QuizUserAdviceRepository;
import com.example.premarital.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuestionRepository questionRepository;
    private final QuizUserAdviceRepository quizUserAdviceRepository;


    @Override
    public Quiz createQuiz(Therapist therapist, QuizCreationDTO dto) {
        Quiz quiz = new Quiz();
        quiz.setTherapist(therapist);
        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        quiz.setQuizQuestions(new ArrayList<>());
        quiz.setAdvices(new ArrayList<>());

        if (dto.getExistingQuestionIds() != null && !dto.getExistingQuestionIds().isEmpty()) {
            for (Long questionId : dto.getExistingQuestionIds()) {
                Question question = questionRepository.findById(questionId)
                        .orElseThrow(() -> new RuntimeException("Question not found: " + questionId));
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setQuiz(quiz);
                quizQuestion.setQuestion(question);
                quiz.getQuizQuestions().add(quizQuestion);
            }
        }

        if (dto.getNewQuestions() != null && !dto.getNewQuestions().isEmpty()) {
            for (QuestionDTO questionDTO : dto.getNewQuestions()) {
                Question question = new Question();
                question.setQuestionText(questionDTO.getQuestionText());
                question.setForGender(questionDTO.getForGender());
                question.setQuestionOption(new ArrayList<>());

                for (QuestionOptionDTO optionDTO : questionDTO.getOptions()) {
                    QuestionOption option = new QuestionOption();
                    option.setQuestion(question);
                    option.setOptionText(optionDTO.getOptionText());
                    option.setPoint(optionDTO.getPoint());
                    question.getQuestionOption().add(option);
                }

                // Link the question to the quiz via QuizQuestion
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setQuiz(quiz);
                quizQuestion.setQuestion(question);
                quiz.getQuizQuestions().add(quizQuestion);
            }
        }

        if (dto.getAdvices() != null && !dto.getAdvices().isEmpty()) {
            for (QuizAdviceDTO adviceDTO : dto.getAdvices()) {
                QuizUserAdvice quizUserAdvice = toQuizUserAdvice(quiz, adviceDTO);
                quiz.getAdvices().add(quizUserAdvice);
            }
        }

        return quizRepository.save(quiz);
    }


    private QuizUserAdvice toQuizUserAdvice(Quiz quiz, QuizAdviceDTO dto) {
        QuizUserAdvice quizUserAdvice = new QuizUserAdvice();
        quizUserAdvice.setQuiz(quiz);
        quizUserAdvice.setAdviceText(dto.getAdviceText());
        quizUserAdvice.setFromPoint(dto.getFromPoint());
        quizUserAdvice.setToPoint(dto.getToPoint());
        return quizUserAdvice;
    }
}
