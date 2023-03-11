package Utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;


@UtilityClass
public class RetrofitUtils {
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ConfigUtils.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @UtilityClass
    public class ConfigUtils {
        Properties prop = new Properties();
        private static InputStream configFile;

        static {
            try {
                configFile = new FileInputStream("src/test/resources/my.properties");
                configFile = new FileInputStream("src/main/resources/my.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        @SneakyThrows
        public String getBaseUrl() {
            prop.load(configFile);
            return prop.getProperty("url");
        }
    }

}

//   HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// LoggingInterceptor logging2 = new LoggingInterceptor();
// OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


