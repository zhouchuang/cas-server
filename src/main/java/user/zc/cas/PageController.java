package user.zc.cas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
