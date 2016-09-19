package org.springframework.samples.mvc.simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class IndexController {

	  @RequestMapping(method = RequestMethod.GET)
	    public String getIndexPage() {
	        return "UserManagement";
	    }

}