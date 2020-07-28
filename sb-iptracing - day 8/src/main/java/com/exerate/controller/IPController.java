package com.exerate.controller;

import com.exerate.entitiy.IPLocation;
import com.exerate.service.IPLocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Api(value = "IP Details Controller")
@Transactional
public class IPController {
    @Autowired
    private IPLocationService ipLocationService;



    @GetMapping("/ipinfo")
    public IPLocation getIpInfo() {
        return ipLocationService.getIpInfo();
    }

    @GetMapping("/ipinfos")
    public Iterable<IPLocation> getAllRecords() {
        return ipLocationService.getAll();
    }


    @ApiOperation(value = "Get Ip Info")
    @PostMapping("/ipinfo")
    public IPLocation saveIpInfo(@RequestParam("isSave") boolean isSave) {
        return ipLocationService.saveIpInfo(isSave);
    }

    @GetMapping("/ipdetails")
    public List<IPLocation> getIpDetails() {
        return ipLocationService.getIpDetails();
    }

    @DeleteMapping("/ipinfo/{ip}")
    public void deleteIpInfo(@PathVariable("ip") String ip) {
        ipLocationService.deleteIpDetails(ip);
    }

    @PutMapping("/ipinfo/{id}")
    public void updateIpInfo(@RequestBody IPLocation ipLocation, @PathVariable("id") long id){
        ipLocationService.updateIpInfo(ipLocation,id);
    }

}
