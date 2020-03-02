package user.zc.cas.entity;

import lombok.Data;

@Data
public class User extends BaseEntity{
    private String username;
    private String password;
    private String name;
}
