package lesson4.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "measures",
        "image",
        "imageType",
})

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private Integer id;
    private String title;
    private String image;
    private String imageType;
}

