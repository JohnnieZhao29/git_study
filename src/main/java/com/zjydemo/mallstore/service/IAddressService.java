package com.zjydemo.mallstore.service;

import com.zjydemo.mallstore.entity.Address;

import java.util.List;

/**
 * 地址管理业务层接口
 * @author zjy
 * @version 1.0
 */

public interface IAddressService {

    /**
     * 根据收货地址数据的id，查询收货地址详情
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @return 匹配的收货地址详情
     */
    Address getByAid(Integer aid, Integer uid);

    public void addAddress(Address address, String name, Integer uid);

    public List<Address> getAddressByUid(Integer uid);

    public void setDefault(Integer aid, Integer uid, String username);

    public void delete(Integer aid, Integer uid, String username);
}
