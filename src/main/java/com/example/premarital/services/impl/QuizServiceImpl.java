package com.example.premarital.services.impl;

import com.example.premarital.dtos.QuestionDTO;
import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.dtos.QuizAdviceDTO;
import com.example.premarital.dtos.QuizCreationDTO;
import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.dtos.QuizUpdateDTO;
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
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.services.QuizService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuestionRepository questionRepository;
    private final QuizUserAdviceRepository quizUserAdviceRepository;
    private final TherapistRepository therapistRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public QuizDTO createQuiz(Therapist therapist, QuizCreationDTO dto) {
        // Create quiz entity
        Quiz quiz = new Quiz();
        quiz.setTherapist(therapist);
        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        quiz.setQuizQuestions(new ArrayList<>());
        quiz.setAdvices(new ArrayList<>());
        
        quiz = quizRepository.save(quiz);
        entityManager.flush(); // Ensure quiz is saved to the database

        if (dto.getExistingQuestionIds() != null && !dto.getExistingQuestionIds().isEmpty()) {
            for (Long questionId : dto.getExistingQuestionIds()) {
                Question question = questionRepository.findById(questionId)
                        .orElseThrow(() -> new RuntimeException("Question not found: " + questionId));
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setQuiz(quiz);
                quizQuestion.setQuestion(question);
                entityManager.persist(quizQuestion);
            }
            entityManager.flush();
        }

        if (dto.getNewQuestions() != null && !dto.getNewQuestions().isEmpty()) {
            for (QuestionDTO questionDTO : dto.getNewQuestions()) {
                Question question = new Question();
                question.setQuestionText(questionDTO.getQuestionText());
                question.setForGender(questionDTO.getForGender());
                question.setQuestionOption(new ArrayList<>());
                
                entityManager.persist(question);
                entityManager.flush();
                
                if (questionDTO.getOptions() != null) {
                    for (QuestionOptionDTO optionDTO : questionDTO.getOptions()) {
                        QuestionOption option = new QuestionOption();
                        option.setQuestion(question); // Use saved question
                        option.setOptionText(optionDTO.getOptionText());
                        option.setPoint(optionDTO.getPoint());
                        
                        entityManager.persist(option);
                    }
                    
                    entityManager.flush();
                }
                
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setQuiz(quiz);
                quizQuestion.setQuestion(question);
                entityManager.persist(quizQuestion);
                entityManager.flush();
            }
        }

        if (dto.getAdvices() != null && !dto.getAdvices().isEmpty()) {
            for (QuizAdviceDTO adviceDTO : dto.getAdvices()) {
                QuizUserAdvice quizUserAdvice = new QuizUserAdvice();
                quizUserAdvice.setQuiz(quiz);
                quizUserAdvice.setAdviceText(adviceDTO.getAdviceText());
                quizUserAdvice.setFromPoint(adviceDTO.getFromPoint());
                quizUserAdvice.setToPoint(adviceDTO.getToPoint());
                entityManager.persist(quizUserAdvice);
            }
            entityManager.flush();
        }

        entityManager.clear();
        return QuizDTO.of(quizRepository.findById(quiz.getQuizId())
                .orElseThrow(() -> new RuntimeException("Failed to create quiz")));
    }

    @Override
    @Transactional(readOnly = true)
    public QuizDTO getQuizById(Long id) {
        return QuizDTO.of(quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizDTO> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(QuizDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizDTO> getQuizzesByTherapistEmail(String email) {
        return quizRepository.findAllByTherapist_User_Email(email).stream()
                .map(QuizDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public QuizDTO updateQuiz(Long id, QuizUpdateDTO dto, String therapistEmail) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));
        
        if (!quiz.getTherapist().getUser().getEmail().equals(therapistEmail)) {
            throw new RuntimeException("You do not have permission to update this quiz");
        }
        
        if (dto.getTitle() != null) {
            quiz.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            quiz.setDescription(dto.getDescription());
        }
        if (dto.getIsActive() != null) {
            quiz.setActive(dto.getIsActive());
        }

        if (dto.getRemoveQuestionIds() != null && !dto.getRemoveQuestionIds().isEmpty()) {
            quiz.getQuizQuestions().removeIf(quizQuestion -> 
                dto.getRemoveQuestionIds().contains(quizQuestion.getQuestion().getQuestionId()));
        }
        
        if (dto.getExistingQuestionIds() != null && !dto.getExistingQuestionIds().isEmpty()) {
            for (Long questionId : dto.getExistingQuestionIds()) {
                boolean alreadyAdded = quiz.getQuizQuestions().stream()
                        .anyMatch(qq -> qq.getQuestion().getQuestionId().equals(questionId));
                
                if (!alreadyAdded) {
                    Question question = questionRepository.findById(questionId)
                            .orElseThrow(() -> new RuntimeException("Question not found: " + questionId));
                    QuizQuestion quizQuestion = new QuizQuestion();
                    quizQuestion.setQuiz(quiz);
                    quizQuestion.setQuestion(question);
                    entityManager.persist(quizQuestion);
                }
            }
            entityManager.flush();
        }
        
        if (dto.getNewQuestions() != null && !dto.getNewQuestions().isEmpty()) {
            for (QuestionDTO questionDTO : dto.getNewQuestions()) {
                Question question = new Question();
                question.setQuestionText(questionDTO.getQuestionText());
                question.setForGender(questionDTO.getForGender());
                question.setQuestionOption(new ArrayList<>());
                
                entityManager.persist(question);
                entityManager.flush();
                
                if (questionDTO.getOptions() != null) {
                    for (QuestionOptionDTO optionDTO : questionDTO.getOptions()) {
                        QuestionOption option = new QuestionOption();
                        option.setQuestion(question); // Use saved question
                        option.setOptionText(optionDTO.getOptionText());
                        option.setPoint(optionDTO.getPoint());
                        
                        entityManager.persist(option);
                    }
                    
                    entityManager.flush();
                }
                
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setQuiz(quiz);
                quizQuestion.setQuestion(question);
                entityManager.persist(quizQuestion);
            }
            entityManager.flush();
        }
        
        if (dto.getAdvices() != null) {
            List<QuizUserAdvice> existingAdvices = new ArrayList<>(quiz.getAdvices());
            for (QuizUserAdvice advice : existingAdvices) {
                entityManager.remove(advice);
            }
            quiz.getAdvices().clear();
            entityManager.flush();
            
            for (QuizAdviceDTO adviceDTO : dto.getAdvices()) {
                QuizUserAdvice quizUserAdvice = new QuizUserAdvice();
                quizUserAdvice.setQuiz(quiz);
                quizUserAdvice.setAdviceText(adviceDTO.getAdviceText());
                quizUserAdvice.setFromPoint(adviceDTO.getFromPoint());
                quizUserAdvice.setToPoint(adviceDTO.getToPoint());
                entityManager.persist(quizUserAdvice);
            }
            entityManager.flush();
        }
        
        entityManager.clear();
        return QuizDTO.of(quizRepository.findById(quiz.getQuizId())
                .orElseThrow(() -> new RuntimeException("Failed to update quiz")));
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
