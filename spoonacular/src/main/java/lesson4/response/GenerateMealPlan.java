package lesson4.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lesson4.api.Meal;
import lesson4.api.Nutrients;
import lombok.Data;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "meals",
        "nutrients",
})

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenerateMealPlan {
    public ArrayList<Meal> meals;
    public Nutrients nutrients;
}
