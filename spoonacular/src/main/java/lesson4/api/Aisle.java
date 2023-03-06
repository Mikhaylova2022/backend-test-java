package lesson4.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisle",
        "items"
})

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Aisle {
    private String aisle;
    private ArrayList<Item> items;

}

