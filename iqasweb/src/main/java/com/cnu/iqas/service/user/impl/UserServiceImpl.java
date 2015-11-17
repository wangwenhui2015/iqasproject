package com.cnu.iqas.service.user.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.user.User;
import com.cnu.iqas.dao.base.DaoSupport;
import com.cnu.iqas.service.user.UserService;
@Service("userService")
public class UserServiceImpl extends DaoSupport<User>implements UserService {


}
