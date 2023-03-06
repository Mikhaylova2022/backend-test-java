package lesson4.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lesson4.api.Result;
import lombok.Data;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "offset",
        "number",
        "results",
        "totalResults"
})

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchRecipes {
    private Integer offset;
    private Integer number;
    private ArrayList <Result> results;
    private Integer totalResults;

}


