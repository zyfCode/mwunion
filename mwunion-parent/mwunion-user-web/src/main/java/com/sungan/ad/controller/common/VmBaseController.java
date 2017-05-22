package com.sungan.ad.controller.common;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangyf18255 on 2017/5/20.
 */
@Controller
@RequestMapping("/us")
public class VmBaseController {
    @RequestMapping("/topage/{viewpath:.+}")
    public String toBase(@PathVariable  String viewpath, Model model){
        model.addAttribute("screen_content","helloWorld");
        model.addAttribute("viewpath",viewpath);
        return  viewpath;
    }

}
