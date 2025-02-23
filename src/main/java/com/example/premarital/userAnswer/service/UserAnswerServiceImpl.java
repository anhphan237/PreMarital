package com.example.premarital.userAnswer.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.userAnswer.dto.UserAnswerDTO;
import com.example.premarital.userAnswer.model.UserAnswer;
import com.example.premarital.userAnswer.repository.UserAnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;

    public UserAnswerServiceImpl(UserAnswerRepository userAnswerRepository) {
        this.userAnswerRepository = userAnswerRepository;
    }

    @Override
    public PagingResult<UserAnswerDTO> getUserAnswers(PaginationRequest request) {
        return null;
    }

    @Override
    public UserAnswerDTO createUserAnswer(UserAnswerDTO dto) {
        return null;
    }

    @Override
    public UserAnswer getUserAnswerById(Long id) {
        return null;
    }

    @Override
    public boolean deleteUserAnswerById(Long id) {
        return false;
    }

    @Override
    public boolean updateUserAnswer(Long id, UserAnswerDTO updatedUserAnswerDTO) {
        return false;
    }
}
