package user.zc.cas.dto;

import lombok.Data;
import user.zc.cas.entity.User;

@Data
public class RegisterDTO extends DTO<User> {
    private String username;
    private String password;
    private String name;
}
