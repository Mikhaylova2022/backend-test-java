package lesson4.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
})

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient {
    private String name;

    public Ingredient(String name) {
        this.name = name;
    }
}

