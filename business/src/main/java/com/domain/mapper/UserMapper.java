package com.domain.mapper;

import com.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author hellsino
 * @since 2026-06-10 13:33:41.654
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<LinkedHashMap> selectLinkedHashMap();

}
