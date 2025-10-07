package bd.edu.seu.ecommerce.service;

import bd.edu.seu.ecommerce.model.Checkout;
import bd.edu.seu.ecommerce.repository.CheckoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService {
    private final CheckoutRepository checkoutRepository;

    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public Checkout insertCheckout(Checkout checkout) {
        return checkoutRepository.save(checkout);
    }

    public List<Checkout> getAllCheckouts() {
        return checkoutRepository.findAll();
    }

    public List<Checkout> getAllCheckoutsByUserEmail(String email) {
        return checkoutRepository.findCheckoutByEmail(email);
    }
}
