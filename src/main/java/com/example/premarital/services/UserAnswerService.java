package com.example.premarital.services;

import com.example.premarital.dtos.UserAnswerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAnswerService {
    Page<UserAnswerDTO> getUserAnswers(Pageable pageable);
    void createUserAnswer(UserAnswerDTO dto);
    UserAnswerDTO getUserAnswerById(Long id);
    boolean deleteUserAnswerById(Long id);
    boolean updateUserAnswer(Long id, UserAnswerDTO updatedUserAnswerDTO);
}
