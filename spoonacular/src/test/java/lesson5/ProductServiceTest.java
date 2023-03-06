package lesson5;

import com.github.javafaker.Faker;
import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.util.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductServiceTest {
    static ProductService productService;
    Product product;
    Product productWithNullId;
    Product modifedProduct;
    Faker faker = new Faker();
    int id;

    @BeforeAll
    static void beforeAll() {
        productService =
                RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));

        productWithNullId = new Product()
                .withId(0)
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));

        modifedProduct = new Product()
                .withId(1)
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice(product.getPrice());
    }

    @Test
    @SneakyThrows
    void createProductInFoodCategoryPositiveTest() {
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
    }


    @Test
    @SneakyThrows
    void modifyProductPositiveTest() {
        Response<Product> response = productService.modifyProduct(modifedProduct).execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getTitle(), equalTo(modifedProduct.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));

        Response<ResponseBody> delete = productService.deleteProduct(id).execute();
        assertThat(delete.isSuccessful(), CoreMatchers.is(true));
    }


    @Test
    @SneakyThrows
    void createProductInFoodCategoryNegativeTest() {
        Response<Product> response = productService.createProduct(productWithNullId).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(400));
    }

    @Test
    @SneakyThrows
    void returnProductsTest() {
        Response<ResponseBody> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }


}
