package lesson4.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ingredients",
})

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {
    private ArrayList<Ingredient> ingredients;

    public Value(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}

