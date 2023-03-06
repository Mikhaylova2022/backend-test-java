package lesson4.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "imageType",
        "title",
        "readyInMinutes",
        "servings",
        "sourceUrl",
})

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meal {
    private Integer id;
    private String imageType;
    private String title;
    private Integer readyInMinutes;
    private Integer servings;
    private String sourceUrl;
}
