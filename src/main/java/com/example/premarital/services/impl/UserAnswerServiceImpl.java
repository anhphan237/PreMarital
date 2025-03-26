package com.example.premarital.services.impl;

import com.example.premarital.dtos.UserAnswerDTO;
import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.mappers.UserAnswerMapper;
import com.example.premarital.models.UserAnswer;
import com.example.premarital.repositories.QuestionOptionRepository;
import com.example.premarital.repositories.UserAnswerRepository;
import com.example.premarital.services.UserAnswerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAnswerServiceImpl implements UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;
    private final UserAnswerMapper userAnswerMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserAnswerServiceImpl.class);
    private final QuestionOptionRepository questionOptionRepository;

    @Override
    public Page<UserAnswerDTO> getUserAnswers(Pageable pageable) {
        Page<UserAnswer> userAnswers = userAnswerRepository.findUserAnswersByIsActiveTrue(pageable);
        if (userAnswers.isEmpty()) {
            logger.warn("No user answers found in the system");
        }
        return userAnswers.map(userAnswerMapper::toDTO);
    }

    @Override
    @Transactional
    public void createUserAnswer(UserAnswerDTO dto) {
        if (dto.getId() != null) {
            throw new InvalidDataException("ID must be null when creating a new user answer");
        }

        if (dto.getQuestionOptionId() == null || dto.getQuestionOptionId() <= 0 || !questionOptionRepository.existsById(dto.getQuestionOptionId())) {
            throw new InvalidDataException("Question Option ID " + dto.getQuestionOptionId() + " is invalid or not found");
        }

        if (dto.getUserQuizHistoryId() == null || dto.getUserQuizHistoryId() <= 0 || !userAnswerRepository.existsById(dto.getUserQuizHistoryId())) {
            throw new InvalidDataException("User Quiz History ID" + dto.getUserQuizHistoryId() + " is invalid or not found");
        }

        try {
            UserAnswer userAnswer = userAnswerMapper.toEntity(dto);
            userAnswerRepository.save(userAnswer);
            logger.info("User answer created successfully with ID: {}", userAnswer.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while creating user answer: {}", e.getMessage(), e);
            throw new InvalidDataException("Invalid user answer data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating user answer: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create user answer", e);
        }
    }

    @Override
    public UserAnswerDTO getUserAnswerById(Long id) {
        return userAnswerRepository.findById(id)
                .map(userAnswerMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("User Answer with ID " + id + " not found"));
    }

    @Override
    @Transactional
    public boolean deleteUserAnswerById(Long id) {
        UserAnswer userAnswer = userAnswerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User Answer with ID " + id + " not found"));

        if (!userAnswer.getIsActive()) {
            logger.warn("User answer with ID {} is already inactive", id);
            return false;
        }

        try {
            userAnswer.setIsActive(false);
            userAnswerRepository.save(userAnswer);
            logger.info("User answer with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating user answer with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate user answer", e);
        }
    }

    @Override
    @Transactional
    public boolean updateUserAnswer(Long id, UserAnswerDTO dto) {
        if (dto.getId() != null) {
            throw new InvalidDataException("ID must be null when updating user answer");
        }

        if (!userAnswerRepository.existsById(id)) {
            throw new EntityNotFoundException("User Answer ID " + id + " not found");
        }

        if (dto.getQuestionOptionId() == null || dto.getQuestionOptionId() <= 0 || !questionOptionRepository.existsById(dto.getQuestionOptionId())) {
            throw new InvalidDataException("Question Option ID " + dto.getQuestionOptionId() + " is invalid or not found");
        }

        if (dto.getUserQuizHistoryId() == null || dto.getUserQuizHistoryId() <= 0 || !userAnswerRepository.existsById(dto.getUserQuizHistoryId())) {
            throw new InvalidDataException("User Quiz History ID" + dto.getUserQuizHistoryId() + " is invalid or not found");
        }

        try {
            UserAnswer updatedUserAnswer = userAnswerMapper.toEntityWithId(id, dto);
            userAnswerRepository.save(updatedUserAnswer);
            logger.info("User answer with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating user answer with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating user answer with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update user answer", e);
        }
    }
}
