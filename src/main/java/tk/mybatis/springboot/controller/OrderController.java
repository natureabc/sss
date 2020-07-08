package tk.mybatis.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.springboot.model.Bug;
import tk.mybatis.springboot.model.vo.BugVo;
import tk.mybatis.springboot.util.VoPoConverter;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {


	@RequestMapping("testListConver")
    public void testListConver (){


    }


}
