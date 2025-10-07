package bd.edu.seu.ecommerce.repository;

import bd.edu.seu.ecommerce.model.User;
import bd.edu.seu.ecommerce.model.UserType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
    @Modifying
    @Transactional
    void deleteUserByEmail(String email);
}
