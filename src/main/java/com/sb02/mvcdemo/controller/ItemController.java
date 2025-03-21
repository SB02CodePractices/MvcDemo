package com.sb02.mvcdemo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {

    @PostMapping("/items/add")
    @ResponseBody
    public String addNewItem(@RequestBody @Valid ItemRequest itemRequest) {
        System.out.println(itemRequest);
        return "Item added: " + itemRequest.name() + " with price " + itemRequest.price();
    }

    @GetMapping("/items")
    public String items() {
        return "items";
    }

    @PostMapping("/items/register")
    public String registerItem(@ModelAttribute("form") ItemRequest itemRequest) {
        // 폼의 name, price 등 필드가 ItemRequest 객체에 자동 바인딩
        System.out.println(itemRequest);
        // "form" 이름으로 모델에 추가되어 뷰 템플릿에서 참조 가능
        return "item_register_success";
    }
}
