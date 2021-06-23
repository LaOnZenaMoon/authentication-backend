package me.lozm.api.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

    @GetMapping("sign-in")
    public String signIn() {
        return "pages/sign/signIn";
    }

}
