package xinhocbong.api.entites;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    public  boolean loginSuccess;
    public String username;

}

