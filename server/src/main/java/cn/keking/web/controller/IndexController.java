package cn.keking.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *  页面跳转
 * @author yudian-it
 * @date 2017/12/27
 */
@Controller
public class IndexController {
    @Value("${url.base64:true}")
    private String base641;
    @Value("${local.preview.index:true}")
    private String index;
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String go2Index(){
        if(index.equalsIgnoreCase("true")){
            if(base641.equalsIgnoreCase("true")){
                return "index";
            }else {
                return "index1";
            }
        }else {
            return "null";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
            return "redirect:/index";
    }
}
