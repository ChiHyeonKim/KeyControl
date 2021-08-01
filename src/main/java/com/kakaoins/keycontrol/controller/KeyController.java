package com.kakaoins.keycontrol.controller;

import com.kakaoins.keycontrol.domain.Key;
import com.kakaoins.keycontrol.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/key")
public class KeyController {

    KeyService keyService = new KeyService();

    /**
     * KEY 정보를 등록하는 API
     */
    @PostMapping("/register")
    public void createKeyinfo(@RequestBody Key key){ //@RequestBody가 있어야 JSON ->객체로 변환

        keyService.CreateKeyInfo(key);
    }

    /**
     * 각 KEY 별로 새로운 KEY를 하나 발급 받는 API
     */
    @GetMapping("/{url}")
    public String getKey(@PathVariable("url") String str){

        return keyService.GenerateKey(str);
    }


}
