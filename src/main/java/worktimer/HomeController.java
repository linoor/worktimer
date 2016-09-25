package worktimer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by linoor on 9/11/16.
 */

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}
