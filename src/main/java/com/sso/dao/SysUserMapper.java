package com.sso.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sso.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenhaowen
 * @since 2020-08-11
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询数据量
     * @return
     */
    Long countSize();

}
