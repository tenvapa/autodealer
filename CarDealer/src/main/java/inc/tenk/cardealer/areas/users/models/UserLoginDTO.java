package inc.tenk.cardealer.areas.users.models;

import inc.tenk.cardealer.utils.HTMLEncoder;
import org.hibernate.validator.constraints.NotEmpty;

public class UserLoginDTO {
    private Long id;
    private static final String EMPTY_FIELD_MESSAGE = "Field must not be empty";
    @NotEmpty(message = EMPTY_FIELD_MESSAGE)
    private String username;
    @NotEmpty(message = EMPTY_FIELD_MESSAGE)
    private String password;

    public UserLoginDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = HTMLEncoder.escapeHTML(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = HTMLEncoder.escapeHTML(password);
    }


}
