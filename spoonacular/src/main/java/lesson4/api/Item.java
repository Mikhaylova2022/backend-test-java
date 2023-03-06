package lesson4.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "measures",
        "usages",
        "usageRecipeIds",
        "pantryItem",
        "aisle",
        "cost",
        "ingredientId"
})

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private Integer id;
    private String name;
    private Measures measures;
    private Boolean pantryItem;
    private String aisle;
    private Double cost;
    private Integer ingredientId;

}
