package com.example.premarital.services.impl;

import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.mappers.WalletMapper;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.models.Transaction;
import com.example.premarital.models.Wallet;
import com.example.premarital.repositories.TransactionRepository;
import com.example.premarital.repositories.WalletRepository;
import com.example.premarital.services.WalletService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final TransactionRepository transactionRepository;

    @Override
    public Page<WalletDTO> getWallets(Pageable pageable) {
        Page<Wallet> entities = walletRepository.findAll(pageable);
        Page<WalletDTO> dtoPage = entities.map(new Function<Wallet, WalletDTO>() {

            @Override
            public WalletDTO apply(Wallet wallet) {
                WalletDTO dto = walletMapper.toDTO(wallet);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createWallet(WalletDTO dto) {
        walletRepository.save(walletMapper.toEntity(dto));
    }

    @Override
    public WalletDTO getWalletById(Long id) {
        return walletMapper.toDTO(walletRepository.findById(id).orElse(null));
    }

    @Override
    public WalletDTO getWalletByUserId(Long userId) {
        return walletMapper.toDTO(walletRepository.getWalletByUserId(userId));
    }

    @Override
    public boolean deleteWalletById(Long id) {
        return walletRepository.findById(id).map(wallet -> {
            wallet.setIsActive(false);
            walletRepository.save(wallet);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateWallet(Long id, WalletDTO updatedWalletDTO) {
        return walletRepository.findById(id).map(wallet -> {
            Wallet updatedWallet = walletMapper.toEntityWithId(id, updatedWalletDTO);
            walletRepository.save(updatedWallet);
            return true;
        }).orElse(false);
    }

    @Override
    @Transactional
    public boolean updateWalletBalance(Long id, Long balance) {
        try {
            // Lấy thông tin ví từ database
            Wallet wallet = walletRepository.getReferenceById(id);

            if (wallet == null) {
                throw new EntityNotFoundException("Wallet with ID " + id + " not found.");
            }

            // Cập nhật số dư ví
            Long oldBalance = wallet.getBalance();
            wallet.setBalance(oldBalance + balance);

            // Tạo giao dịch
            Transaction transaction = new Transaction();
            transaction.setAmount(balance);
            transaction.setIsActive(true);
            transaction.setBalanceBefore(oldBalance);
            transaction.setTransactionFee(0L); // Phí nạp tiền có thể bằng 0
            transaction.setWallet(wallet);
            transaction.setConsultationBooking(null);
            transaction.setTransactionTime(LocalDateTime.now());
            transaction.setTotalAmount(wallet.getBalance()); // Số dư mới sau giao dịch
            transaction.setTransactionType("DEPOSIT");
            transaction.setTransactionStatus("SUCCESSFUL");
            transaction.setWithdrawRequest(null);

            // Lưu vào database
            transactionRepository.save(transaction);
            walletRepository.save(wallet);

            return true;
        } catch (EntityNotFoundException e) {
            throw e; // Ném lại exception để transaction rollback
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update wallet balance due to database error.");
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while updating wallet balance.");
        }
    }

}
