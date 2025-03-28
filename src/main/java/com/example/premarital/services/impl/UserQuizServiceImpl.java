package com.example.premarital.services.impl;

import com.example.premarital.dtos.QuizSubmissionDTO;
import com.example.premarital.dtos.UserQuizHistoryDTO;
import com.example.premarital.models.Question;
import com.example.premarital.models.QuestionOption;
import com.example.premarital.models.Quiz;
import com.example.premarital.models.QuizQuestion;
import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.models.User;
import com.example.premarital.models.UserAnswer;
import com.example.premarital.models.UserQuizHistory;
import com.example.premarital.repositories.QuizRepository;
import com.example.premarital.repositories.UserQuizHistoryRepository;
import com.example.premarital.repositories.UserRepository;
import com.example.premarital.services.UserQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserQuizServiceImpl implements UserQuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final UserQuizHistoryRepository userQuizHistoryRepository;

    @Override
    public UserQuizHistoryDTO submit(String userEmail, QuizSubmissionDTO quizSubmissionDTO) {

        User user = userRepository.findByEmail(userEmail).orElse(null);
        Quiz quiz = quizRepository.findById(quizSubmissionDTO.getQuizId()).orElseThrow(() -> new RuntimeException("Quiz not found: " + quizSubmissionDTO.getQuizId()));

        List<UserAnswer> userAnswers = new ArrayList<>();
        AtomicInteger point = new AtomicInteger();

        Map<Long, Question> questionMap = quiz.getQuizQuestions().stream()
                        .map(QuizQuestion::getQuestion)
                        .collect(Collectors.toMap(Question::getQuestionId, question -> question));

        Map<Long, QuestionOption> questionOptionMap = quiz.getQuizQuestions().stream()
                        .flatMap(quizQuestion -> quizQuestion.getQuestion().getQuestionOption().stream())
                        .collect(Collectors.toMap(QuestionOption::getId, questionOption -> questionOption));

        quizSubmissionDTO.getSubmissions().forEach(submission -> {
            UserAnswer userAnswer = UserAnswer.builder()
                    .question(questionMap.get(submission.getQuestionId()))
                    .questionOption(questionOptionMap.get(submission.getOptionId()))
                    .build();

            point.addAndGet(userAnswer.getQuestionOption().getPoint());
            userAnswers.add(userAnswer);
        });

        QuizUserAdvice advice = getQuizUserAdvice(quiz, point.get());

        UserQuizHistory userQuizHistory = UserQuizHistory.builder()
                .user(user)
                .quiz(quiz)
                .userAnswer(userAnswers)
                .quizPoint(point.get())
                .quizUserAdvice(advice)
                .build();

        UserQuizHistory persistedEntity = userQuizHistoryRepository.save(userQuizHistory);

        return UserQuizHistoryDTO.of(persistedEntity);
    }

    @Override
    public List<UserQuizHistoryDTO> histories(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<UserQuizHistory> histories = userQuizHistoryRepository.findAllByUser_Email(userEmail);

        return histories.stream()
                .map(UserQuizHistoryDTO::of)
                .toList();
    }

    private QuizUserAdvice getQuizUserAdvice(Quiz quiz, int point) {
        return quiz.getAdvices().stream()
                .filter(advice -> point >= advice.getFromPoint() && point <= advice.getToPoint())
                .findFirst()
                .orElse(null);
    }
}
