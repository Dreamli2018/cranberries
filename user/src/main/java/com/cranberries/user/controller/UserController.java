package com.cranberries.user.controller;

import com.cranberries.user.model.User;
import com.cranberries.user.respnose.ResultVO;
import com.cranberries.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author dreamli
 * @version 1.0.0
 * @since 7-5-2020
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<ResultVO> userRegister(@Valid @ModelAttribute User user) {

        ResultVO<String> resultVO = new ResultVO<>();

        resultVO = userService.register(user);

        if (resultVO != null && !"0".equals(resultVO.getResultCode())) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultVO);
    }

    @GetMapping(value = "/getUserById")
    public ResponseEntity<ResultVO> getUserById(int userId) {

        ResultVO<User> resultVO = new ResultVO<>();

        resultVO = userService.getUserById(userId);

        if (resultVO != null && !"0".equals(resultVO.getResultCode())) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultVO);
    }

}
