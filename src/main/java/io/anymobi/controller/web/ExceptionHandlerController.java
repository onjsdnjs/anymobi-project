package io.anymobi.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@Controller
public class ExceptionHandlerController {

    @RequestMapping(value = "/handling")
    public String sampleTest(HttpServletRequest request, Model model) {
        String msg = (String) request.getAttribute("msg");
        model.addAttribute("msg", msg);
        return "/error";
    }

    @GetMapping("/exceptionOnHtmlRequst")
    public String exceptionOnHtmlRequst() throws NullPointerException {
        throw new NullPointerException();
    }

    @GetMapping("/sqlExceptionOnHtmlRequest")
    public String sqlExceptionOnHtmlRequest() throws SQLException {
        throw new SQLException("SQLExceptin", new SQLException("SQL_101"));
    }

}