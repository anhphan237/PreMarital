package com.example.premarital.services.impl;

import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.mappers.ConsultationBookingMapper;
import com.example.premarital.models.*;
import com.example.premarital.repositories.*;
import com.example.premarital.services.ConsultationBookingService;
import com.example.premarital.dtos.ConsultationBookingDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsultationBookingServiceImpl implements ConsultationBookingService {
    private final ConsultationBookingMapper consultationBookingMapper;
    private final ConsultationBookingRepository consultationBookingRepository;
    private final TherapistScheduleRepository therapistScheduleRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final WalletRepository walletRepository;
    private static final Logger logger = LoggerFactory.getLogger(ConsultationBookingServiceImpl.class);

    @Override
    public Page<ConsultationBookingDTO> getConsultationBookings(Pageable pageable) {
        Page<ConsultationBooking> bookings = consultationBookingRepository.findBookingsByIsActiveTrue(pageable);
        if (bookings.isEmpty()) {
            logger.warn("No bookings actively found in the system");
        }
        return bookings.map(consultationBookingMapper::toDTO);
    }

    @Override
    @Transactional
    public void createConsultationBooking(ConsultationBookingDTO dto) {
        if (dto == null) {
            throw new InvalidDataException("Consultation booking data cannot be null");
        }

        if (!therapistScheduleRepository.existsById(dto.getTherapistScheduleId())) {
            throw new InvalidDataException("Invalid therapistScheduleId: " + dto.getTherapistScheduleId());
        }

        if (!userRepository.existsById(dto.getUserId())) {
            throw new InvalidDataException("Invalid userId: " + dto.getUserId());
        }

        if (!categoryRepository.existsById(dto.getCategoryId())) {
            throw new InvalidDataException("Invalid categoryId: " + dto.getCategoryId());
        }

        try {
            ConsultationBooking consultationBooking = consultationBookingMapper.toEntity(dto);
            consultationBookingRepository.save(consultationBooking);
            logger.info("Consultation booking created successfully with ID: {}", consultationBooking.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while creating consultation booking: {}", e.getMessage(), e);
            throw new InvalidDataException("Invalid consultation booking data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating consultation booking: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create consultation booking", e);
        }
    }

    @Override
    public ConsultationBookingDTO getConsultationBookingById(Long id) {
        return consultationBookingRepository.findById(id)
                .map(consultationBookingMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Booking with ID " + id + " not found"));
    }

    @Override
    @Transactional
    public boolean deleteConsultationBookingById(Long id) {
        ConsultationBooking consultationBooking = consultationBookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ConsultationBooking with ID " + id + " not found"));

        if (!consultationBooking.getIsActive().equals(true)) {
            logger.warn("ConsultationBooking with ID {} is already inactive", id);
            return false;
        }

        try {
            consultationBooking.setIsActive(false);
            consultationBookingRepository.save(consultationBooking);
            logger.info("ConsultationBooking with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating ConsultationBooking with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate consultation booking", e);
        }
    }

    @Override
    @Transactional
    public boolean updateConsultationBooking(Long id, ConsultationBookingDTO updatedConsultationBookingDTO) {
        if (!consultationBookingRepository.existsById(id)) {
            throw new EntityNotFoundException("ConsultationBooking with ID " + id + " not found");
        }

        if (!therapistScheduleRepository.existsById(updatedConsultationBookingDTO.getTherapistScheduleId())) {
            throw new EntityNotFoundException("Therapist schedule with ID " + updatedConsultationBookingDTO.getTherapistScheduleId() + " not found");
        }

        if (!userRepository.existsById(updatedConsultationBookingDTO.getUserId())) {
            throw new EntityNotFoundException("User with ID " + updatedConsultationBookingDTO.getUserId() + " not found");
        }

        if (!categoryRepository.existsById(updatedConsultationBookingDTO.getCategoryId())) {
            throw new EntityNotFoundException("Category with ID " + updatedConsultationBookingDTO.getCategoryId() + " not found");
        }

        try {
            ConsultationBooking updatedBooking = consultationBookingMapper.toEntityWithId(id, updatedConsultationBookingDTO);
            consultationBookingRepository.save(updatedBooking);
            logger.info("ConsultationBooking with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating ConsultationBooking with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating ConsultationBooking with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update ConsultationBooking", e);
        }
    }

    @Override
    @Transactional
    public boolean processBookingPayment(Long customerId, Long therapistId, Long bookingId, Long amount) {
        try {
            // Lấy ví của khách hàng
            Wallet customerWallet = walletRepository.getWalletByUserId(customerId);

            if (customerWallet == null) {
                throw new EntityNotFoundException("Customer wallet not found");
            }

            // Kiểm tra số dư có đủ không
            if (customerWallet.getBalance() < amount) {
                throw new RuntimeException("Not enough balance to complete the transaction.");
            }

            // Lấy ví của therapist
            Wallet therapistWallet = walletRepository.getWalletByUserId(therapistId);

            if (therapistWallet == null) {
                throw new EntityNotFoundException("Customer wallet not found");
            }

            // Cập nhật số dư
            Long customerOldBalance = customerWallet.getBalance();
            Long therapistOldBalance = therapistWallet.getBalance();
            customerWallet.setBalance(customerOldBalance - amount);
            therapistWallet.setBalance(therapistOldBalance + amount);

            // Tạo giao dịch cho khách hàng (trừ tiền)
            Transaction customerTransaction = new Transaction();
            customerTransaction.setAmount(amount);
            customerTransaction.setBalanceBefore(customerOldBalance);
            customerTransaction.setTotalAmount(customerWallet.getBalance());
            customerTransaction.setTransactionType("PAYMENT");
            customerTransaction.setTransactionStatus("SUCCESSFUL");
            customerTransaction.setWallet(customerWallet);
            customerTransaction.setTransactionTime(LocalDateTime.now());
            customerTransaction.setConsultationBooking(consultationBookingRepository.getReferenceById(bookingId));

            // Tạo giao dịch cho therapist (nhận tiền)
            Transaction therapistTransaction = new Transaction();
            therapistTransaction.setAmount(amount);
            therapistTransaction.setBalanceBefore(therapistOldBalance);
            therapistTransaction.setTotalAmount(therapistWallet.getBalance());
            therapistTransaction.setTransactionType("RECEIVE_PAYMENT");
            therapistTransaction.setTransactionStatus("SUCCESSFUL");
            therapistTransaction.setWallet(therapistWallet);
            therapistTransaction.setTransactionTime(LocalDateTime.now());
            therapistTransaction.setConsultationBooking(consultationBookingRepository.getReferenceById(bookingId));

            // Lưu vào DB
            transactionRepository.save(customerTransaction);
            transactionRepository.save(therapistTransaction);
            walletRepository.save(customerWallet);
            walletRepository.save(therapistWallet);

            return true;
        } catch (EntityNotFoundException e) {
            logger.error("Wallet not found: {}", e.getMessage());
            throw e;
        } catch (DataAccessException e) {
            logger.error("Database error: {}", e.getMessage());
            throw new RuntimeException("Database error occurred.");
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            throw new RuntimeException("Unexpected error occurred.");
        }
    }
}
