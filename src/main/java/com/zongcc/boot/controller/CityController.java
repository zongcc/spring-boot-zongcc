package com.zongcc.boot.controller;

import com.zongcc.boot.entity.City;
import com.zongcc.boot.repository.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chunchengzong on 2017-01-06.
 */
//@EnableAutoConfiguration
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityMapper cityMapper;

    @RequestMapping("/{id}")
    public City selectCityById(@PathVariable Long id){
        City city = cityMapper.selectCityById(id);
        return city;
    }
}