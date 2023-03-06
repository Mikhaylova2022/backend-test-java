package lesson4.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "status",
            "username",
            "spoonacularPassword",
            "hash",
    })

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class ConnectUserResponse {
        private String status;
        private String username;
        private String spoonacularPassword;
        private String hash;
}
