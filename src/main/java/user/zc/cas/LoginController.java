package user.zc.cas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import user.zc.cas.dto.RegisterDTO;
import user.zc.cas.entity.User;
import user.zc.cas.service.UserService;
import user.zc.cas.util.APIResponse;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/toRegister")
    public APIResponse toRegister(@RequestBody  RegisterDTO registerDTO){
        User user = registerDTO.trans(User.class);
        userService.insertUser(user);
        return APIResponse.success(Boolean.TRUE);
    }
}
