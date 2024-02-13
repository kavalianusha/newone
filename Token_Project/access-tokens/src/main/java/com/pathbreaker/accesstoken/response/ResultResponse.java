package  com.pathbreaker.accesstoken.response;

import lombok.Data;

@Data
public class ResultResponse {

    private String result;
    private String accessToken;
    private String refreshToken;
}
