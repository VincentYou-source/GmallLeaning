package com.atguigu.gmall.user.mapper;

import com.atguigu.gmall.bean.UmsMember;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public interface UserMapper extends Mapper<UmsMember> {

    List<UmsMember> selectAllUser();
}
