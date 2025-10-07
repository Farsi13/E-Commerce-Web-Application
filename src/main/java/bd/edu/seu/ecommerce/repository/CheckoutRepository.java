package bd.edu.seu.ecommerce.repository;

import bd.edu.seu.ecommerce.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {
    List<Checkout> findCheckoutByEmail(String email);
}
