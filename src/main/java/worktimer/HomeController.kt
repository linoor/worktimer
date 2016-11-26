package worktimer

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Created by linoor on 9/11/16.
 */

@Controller
@CrossOrigin(origins = arrayOf("http://localhost:3000"))
class HomeController {

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun index(): String {
        return "index"
    }

    @RequestMapping(value = "/commutes/{id}", method = arrayOf(RequestMethod.GET))
    fun commutes(): String {
        return "index"
    }
}
