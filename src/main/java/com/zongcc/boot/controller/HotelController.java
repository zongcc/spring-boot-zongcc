package com.zongcc.boot.controller;

import com.zongcc.boot.entity.Hotel;
import com.zongcc.boot.repository.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chunchengzong on 2017-01-06.
 */
//@EnableAutoConfiguration
@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelMapper hotelMapper;

    @RequestMapping("/{cityId}")
    public Hotel selectCityById(@PathVariable Integer cityId){
        Hotel hotel = hotelMapper.selectByCityId(cityId);
        return hotel;
    }
}