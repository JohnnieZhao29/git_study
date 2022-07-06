package com.zjydemo.mallstore.controller;

import com.zjydemo.mallstore.entity.Address;
import com.zjydemo.mallstore.service.IAddressService;
import com.zjydemo.mallstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import sun.reflect.generics.tree.VoidDescriptor;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 */

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController{

    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addAddress(Address address, HttpSession session){
        Integer uid = getuidFromSession(session);
        String name = getUsernameFromSession(session);
        addressService.addAddress(address,name,uid);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getAddressByUid(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<Address> data = addressService.getAddressByUid(uid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session){
        addressService.setDefault(aid,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(HttpSession session, @PathVariable("aid") Integer aid){
        addressService.delete(
                aid,
                getuidFromSession(session),
                getUsernameFromSession(session)
        );
        return new JsonResult<>(OK);
    }



}
