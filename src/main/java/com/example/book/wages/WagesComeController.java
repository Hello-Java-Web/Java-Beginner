package com.example.book.wages;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WagesComeController {
    @Autowired
    WagesService wagesService;

    @RequestMapping("/wagerCome")
    public WagesUserEntity userWagesCome(String username) {

        QueryWrapper<WagesUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return wagesService.getOne(wrapper);

    }


}
