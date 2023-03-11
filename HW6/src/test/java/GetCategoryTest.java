import Utils.RetrofitUtils;
import api.CategoryService;
import dto.GetCategoryResponse;
import dto.Product;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetCategoryTest {


    static CategoryService categoryService;

    @BeforeAll
    static void beforeAll() {
        categoryService =
                RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows



    @Test
    void getCategoryByIdPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();




        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1));
        assertThat(response.body().getTitle(), equalTo("Food"));
        response.body().getProducts().forEach(new Consumer<Product>() {
            @Override
            public void accept(Product product) {
                assertThat(product.getCategoryTitle(), equalTo("Food"));
            }
        });

    }

    @Test
    void test(){

        MuClass muClass = new MuClass();
        System.out.println("1 " + muClass.toString());
        test1(muClass);

    }

    public void test1(MuClass muClass){
        System.out.println("2 " + muClass.toString());

    }

    public class MuClass{

        public String str="123";
        public Integer my = 123;

    }


}