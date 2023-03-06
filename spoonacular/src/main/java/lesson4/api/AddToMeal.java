package lesson4.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "slot",
        "position",
        "type",
        "value",
})


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddToMeal {
    private Integer date;
    private Integer slot;
    private Integer position;
    private String type;
    private Value value;

    public AddToMeal(Integer date, Integer slot, Integer position, String type, Value value) {
        this.date = date;
        this.slot = slot;
        this.position = position;
        this.type = type;
        this.value = value;
    }
}
