package com.cranberries.data.service;

import java.util.List;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/09/06 12:16
 * @description ：同步用户数据服务
 */
public interface SyncUserDataService {
    void syncHandleUserData(List<Integer> idList);
}
