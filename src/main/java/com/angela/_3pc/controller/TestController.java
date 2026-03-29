package com.angela._3pc.controller;

import com.angela._3pc.ordin._3PCOrdinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private _3PCOrdinator ordinator;

    @PostMapping("/3pc/test")
    public String test() {
        ordinator.execute("tx1001",1L);
        return "ok";
    }
}
