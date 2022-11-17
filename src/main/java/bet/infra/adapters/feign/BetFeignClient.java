package bet.infra.adapters.feign;

import bet.configuration.MapperConfiguration;
import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;

public class BetFeignClient<T> {

    private final Feign.Builder FeignBuilder;

    public BetFeignClient() {
        MapperConfiguration mapperConfiguration = new MapperConfiguration();
        //FeignBuilder = SleuthFeignBuilder.builder(beanFactory)
                //.client(new OkHttpClient())
        FeignBuilder = Feign.builder()
                .client(new OkHttpClient())
                .errorDecoder(new BetErrorDecoder())
                .encoder(mapperConfiguration.getEncoder())
                .decoder(mapperConfiguration.getDecoder())
                .logLevel(Logger.Level.FULL);
    }

    public T target(Class<T> apiInterface, String url) {
        return FeignBuilder.target(apiInterface, url);
    }

}
