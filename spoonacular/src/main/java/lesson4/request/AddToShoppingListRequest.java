package lesson4.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "item",
        "aisle",
        "parse"
})
@Data
public class AddToShoppingListRequest {

    @JsonProperty("item")
    private String item;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("parse")
    private Boolean parse;



    public AddToShoppingListRequest(String item, String aisle, Boolean parse) {
        this.item = item;
        this.aisle = aisle;
        this.parse = parse;
    }

    public String getItem() {
        return item;
    }

    public String getAisle() {
        return aisle;
    }

    public Boolean getParse() {
        return parse;
    }
}