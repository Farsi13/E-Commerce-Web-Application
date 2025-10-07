package bd.edu.seu.ecommerce.dto;

public class LoginDto {
    private String email;
    private String password;
//    private boolean rememberMe;
    private boolean registrationSuccess;

    public LoginDto() {
    }

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
//        this.rememberMe = rememberMe;
    }

    public boolean isRegistrationSuccess() {
        return registrationSuccess;
    }

    public void setRegistrationSuccess(boolean registrationSuccess) {
        this.registrationSuccess = registrationSuccess;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public boolean isRememberMe() {
//        return rememberMe;
//    }
//
//    public void setRememberMe(boolean rememberMe) {
//        this.rememberMe = rememberMe;
//    }
}
