package com.exerate.controller;

import com.exerate.entitiy.IPLocation;
import com.exerate.service.IPLocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "IP Details Controller")
public class IPController {
    @Autowired
    private IPLocationService ipLocationService;

    @GetMapping("/ipinfo")
    public IPLocation getIpInfo(@RequestParam("ip") String ip){
        return ipLocationService.getIpInfo(ip);
    }

    @ApiOperation(value = "Get Ip Info")
    @PostMapping("/ipinfo")
    public IPLocation saveIpInfo(@RequestParam("ip") String ip, @RequestParam("isSave") boolean isSave){
        return ipLocationService.saveIpInfo(ip,isSave);
    }

    @GetMapping("/ipdetails")
    public List<IPLocation> getIpDetails(){
        return ipLocationService.getIpDetails();
    }

}
