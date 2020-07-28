package com.exerate.repo;

import com.exerate.entitiy.IPLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPLocationRepo extends CrudRepository<IPLocation, Long> {

    void deleteByIp(String ip);
   // Optional<IPLocation> findByIp(String ip);

    /*
        @Query(value = "Select from {h-schema }IP_LOCATION_TB where ip=:ip", nativeQuery = true)
        void deleteIpDetails(@RequestParam("ip") String ip);
    */

}
