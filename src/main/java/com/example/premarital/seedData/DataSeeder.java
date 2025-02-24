package com.example.premarital.seedData;

import com.example.premarital.repositories.QuestionRepository;
import com.example.premarital.repositories.ArticleRepository;
import com.example.premarital.repositories.ArticlePartRepository;
import com.example.premarital.repositories.BankAccountRepository;
import com.example.premarital.repositories.CategoryRepository;
import com.example.premarital.repositories.ConsultationBookingRepository;
import com.example.premarital.repositories.QuestionOptionRepository;
import com.example.premarital.repositories.QuizRepository;
import com.example.premarital.repositories.QuizCategoryRepository;
import com.example.premarital.repositories.QuizQuestionRepository;
import com.example.premarital.repositories.QuizUserAdviceRepository;
import com.example.premarital.models.Role;
import com.example.premarital.repositories.RoleRepository;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.repositories.TherapistMajorRepository;
import com.example.premarital.repositories.TherapistScheduleRepository;
import com.example.premarital.repositories.TransactionRepository;
import com.example.premarital.models.User;
import com.example.premarital.repositories.UserRepository;
import com.example.premarital.repositories.UserAnswerRepository;
import com.example.premarital.repositories.UserQuizHistoryRepository;
import com.example.premarital.repositories.WalletRepository;
import com.example.premarital.repositories.WithdrawRequestRepository;
import org.springframework.boot.CommandLineRunner;

public class DataSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuizCategoryRepository quizCategoryRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizUserAdviceRepository quizUserAdviceRepository;
    private final TherapistRepository therapistRepository;
    private final TherapistMajorRepository therapistMajorRepository;
    private final TherapistScheduleRepository therapistScheduleRepository;
    private final TransactionRepository transactionRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final UserQuizHistoryRepository userQuizHistoryRepository;
    private final WalletRepository walletRepository;
    private final WithdrawRequestRepository withdrawRequestRepository;
    private final ArticleRepository articleRepository;
    private final ArticlePartRepository articlePartRepository;
    private final BankAccountRepository bankAccountRepository;
    private final CategoryRepository categoryRepository;
    private final ConsultationBookingRepository consultationBookingRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;

    public DataSeeder(
            RoleRepository roleRepository,
            UserRepository userRepository,
            QuizRepository quizRepository,
            QuizCategoryRepository quizCategoryRepository,
            QuizQuestionRepository quizQuestionRepository,
            QuizUserAdviceRepository quizUserAdviceRepository,
            TherapistRepository therapistRepository,
            TherapistMajorRepository therapistMajorRepository,
            TherapistScheduleRepository therapistScheduleRepository,
            TransactionRepository transactionRepository,
            UserAnswerRepository userAnswerRepository,
            UserQuizHistoryRepository userQuizHistoryRepository,
            WalletRepository walletRepository,
            WithdrawRequestRepository withdrawRequestRepository,
            ArticleRepository articleRepository,
            ArticlePartRepository articlePartRepository,
            BankAccountRepository bankAccountRepository,
            CategoryRepository categoryRepository,
            ConsultationBookingRepository consultationBookingRepository,
            QuestionRepository questionRepository,
            QuestionOptionRepository questionOptionRepository
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.quizCategoryRepository = quizCategoryRepository;
        this.quizQuestionRepository = quizQuestionRepository;
        this.quizUserAdviceRepository = quizUserAdviceRepository;
        this.therapistRepository = therapistRepository;
        this.therapistMajorRepository = therapistMajorRepository;
        this.therapistScheduleRepository = therapistScheduleRepository;
        this.transactionRepository = transactionRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.userQuizHistoryRepository = userQuizHistoryRepository;
        this.walletRepository = walletRepository;
        this.withdrawRequestRepository = withdrawRequestRepository;
        this.articleRepository = articleRepository;
        this.articlePartRepository = articlePartRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.categoryRepository = categoryRepository;
        this.consultationBookingRepository = consultationBookingRepository;
        this.questionRepository = questionRepository;
        this.questionOptionRepository = questionOptionRepository;
    }

    @Override
    public void run(String... args) {
        seedRoles();
        seedUsers();
    }

    private void seedRoles() {
        if (roleRepository.count() == 0) { // Kiểm tra nếu chưa có dữ liệu
            Role adminRole = new Role(null, "ADMIN");
            Role userRole = new Role(null, "USER");
            Role therapistRole = new Role(null, "THERAPIST");

            roleRepository.save(adminRole);
            roleRepository.save(userRole);
            roleRepository.save(therapistRole);
            System.out.println("Seeded roles successfully!");
        } else {
            System.out.println("Roles already exist, skipping seeding.");
        }
    }

    private void seedUsers() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin123");
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setEmail("admin@example.com");
        admin.setRole(roleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Role not found.")));
        admin.setStreet("123 Main St");
        admin.setCity("New York");
        admin.setState("NY");
        admin.setPostalCode("10001");
        admin.setCountry("USA");

        userRepository.save(admin);
        System.out.println("Seeded admin user successfully!");
    }


}
