package bet.infra.adapters.feign;

import feign.Response;
import feign.codec.ErrorDecoder;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

public class BetErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new BadRequestException(methodKey);
            case 404:
                return new NotFoundException(methodKey);
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
