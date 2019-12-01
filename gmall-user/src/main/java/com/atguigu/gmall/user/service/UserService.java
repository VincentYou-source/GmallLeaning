package com.atguigu.gmall.user.service;

import com.atguigu.gmall.user.bean.UmsMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UmsMember> getAllUser();
}
