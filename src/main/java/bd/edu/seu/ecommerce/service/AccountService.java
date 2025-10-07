package bd.edu.seu.ecommerce.service;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
