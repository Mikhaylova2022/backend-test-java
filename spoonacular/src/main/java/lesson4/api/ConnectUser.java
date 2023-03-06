package lesson4.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "username",
        "firstName",
        "lastName",
        "email",
})

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectUser {
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public ConnectUser(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
