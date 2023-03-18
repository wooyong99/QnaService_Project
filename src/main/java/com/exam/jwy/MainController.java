package com.exam.jwy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
  @RequestMapping("/jwy")
  @ResponseBody
  public String index(){
    return "안녕하세요";
  }
}
