package lesson4.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "calories",
        "protein",
        "fat",
        "carbohydrates",
})

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
    public class Nutrients{
        private Double calories;
        private Double protein;
        private Double fat;
        private Double carbohydrates;
    }

