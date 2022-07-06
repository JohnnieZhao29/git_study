package com.zjydemo.mallstore.mapper;

import com.zjydemo.mallstore.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 地址持久层接口
 * @author zjy
 * @version 1.0
 */

public interface AddressMapper {

    /**
     * 插入一条新的地址
     * @param address
     * @return
     */
    Integer insert(Address address);

    /**
     * 根据用户uid查询有多少条地址
     * @param uid
     * @return
     */
    Integer count(Integer uid);


    /**
     * 根据用户uid查找用户所有的地址
     * @param uid
     * @return
     */
    List<Address> findByUid(Integer uid);

    Address findByAid(Integer aid);

    Integer clearDefault(Integer uid);

    Integer setDefault(Integer aid,
                       @Param("modifiedUser") String modifiedUser,
                       @Param("modifiedTime") Date modifiedTime);

    Integer deleteByAid(Integer aid);

    Address findLastModified(Integer uid);
}
