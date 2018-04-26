package com.chm.fj.service;

import com.chm.fj.entity.UserInfo;

public interface UserInfoService {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);
}