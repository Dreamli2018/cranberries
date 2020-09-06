package com.cranberries.data.service.impl;

import com.cranberries.data.mapper.SyncUserDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/09/06 12:17
 * @description ：同步用户数据服务
 */

@Service
@Slf4j
public class SyncUserDataServiceImpl {

    @Autowired
    private SyncUserDataMapper syncUserDataMapper;


}
