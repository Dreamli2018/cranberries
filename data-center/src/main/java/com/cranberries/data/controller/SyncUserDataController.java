package com.cranberries.data.controller;

import com.cranberries.data.service.SyncUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/09/06 12:12
 * @description ：同步更新用户中心数据到mongoDB
 */
@RestController
@RequestMapping("/sync/user")
public class SyncUserDataController {

    @Autowired
    private SyncUserDataService syncUserDataService;

    /**
     * 同步处理用户数据到mongoDB
     *
     * @param idList
     */
    @PostMapping("syncHandleUserData")
    public void syncHandleUserData(@RequestParam(value = "idList", required = true) List<Integer> idList){

        this.syncUserDataService.syncHandleUserData(idList);
    }
}
