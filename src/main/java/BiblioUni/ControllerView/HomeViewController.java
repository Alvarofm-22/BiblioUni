package BiblioUni.ControllerView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {


    @GetMapping("/home")
    public String login() {
        return "index";
    }


}
