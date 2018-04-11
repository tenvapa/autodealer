package inc.tenk.cardealer.areas.users.models;

import inc.tenk.cardealer.utils.HTMLEncoder;
import org.hibernate.validator.constraints.NotEmpty;
import inc.tenk.cardealer.areas.users.entities.Role;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterDTO {
    private static final int USERNAME_MIN_LENGTH = 6;
    private static final int USERNAME_MAX_LENGTH = 15;
    private static final String USERNAME_LENGTH_ERROR_MESSAGE = "Username must be between 6 and 15 symbols";
    private static final String EMPTY_FIELD_MESSAGE = "Field must not be empty";
    private static final String USERNAME_PATTERN_MISMATCH_ERROR = "Username must contains: letter,digit,'-' or '_' and ends with digit or letter";
    private static final String NAME_PATTERN_MISMATCH_ERROR = "Name must contains only letters";
    private static final String PASSWORD_LENGTH_ERROR_MESSAGE = "Password length must be between 7 and 20";
    private static final int PASSWORD_MIN_LENGTH = 7;
    private static final int PASSWORD_MAX_LENGTH = 20;
    private static final String INVALID_PHONE_NUMBER = "Invalid phone number: must start with 08 or 09 and have 10 symbols ";
    public static final String INVALID_POSTAL_CODE = "postal code is not a number or length is not 4";


    @NotEmpty(message = EMPTY_FIELD_MESSAGE)
    @Pattern(regexp = "^[a-zA-Z]+$", message = NAME_PATTERN_MISMATCH_ERROR)
    private String firstName;

    @NotEmpty(message = EMPTY_FIELD_MESSAGE)
    @Pattern(regexp = "^[a-zA-Z]+$", message = NAME_PATTERN_MISMATCH_ERROR)
    private String lastName;

    @NotEmpty(message = EMPTY_FIELD_MESSAGE)
    @Size(min = USERNAME_MIN_LENGTH, max = USERNAME_MAX_LENGTH, message = USERNAME_LENGTH_ERROR_MESSAGE)
    @Pattern(regexp = "^([a-zA-Z][a-zA-Z0-9_-]+)[a-zA-Z0-9]$", message = USERNAME_PATTERN_MISMATCH_ERROR)
    private String username;

    @NotEmpty(message = EMPTY_FIELD_MESSAGE)
    @Size(min = PASSWORD_MIN_LENGTH,max = PASSWORD_MAX_LENGTH, message = PASSWORD_LENGTH_ERROR_MESSAGE)
    private String password;

    @NotEmpty(message = EMPTY_FIELD_MESSAGE)
    @Size(min = PASSWORD_MIN_LENGTH,max = PASSWORD_MAX_LENGTH, message = PASSWORD_LENGTH_ERROR_MESSAGE)
    private String confirmPassword;
    private Role role;

    @NotEmpty(message = EMPTY_FIELD_MESSAGE)
    @Pattern(regexp = "^[0-9]{10}$", message = INVALID_PHONE_NUMBER)
    private String phoneNumber;
    private String address;
    @Pattern(regexp = "^[0-9]{4}$", message = INVALID_POSTAL_CODE)
    private String postCode;

    public UserRegisterDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = HTMLEncoder.escapeHTML(password);
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = HTMLEncoder.escapeHTML(confirmPassword);
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = HTMLEncoder.escapeHTML(address);
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = HTMLEncoder.escapeHTML(postCode);
    }
}
