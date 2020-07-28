package com.exerate.service;

import com.exerate.entitiy.IPLocation;
import com.exerate.entitiy.InternetProvider;
import com.exerate.exception.IPNotFoundException;
import com.exerate.repo.IPLocationRepo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IPLocationService {

    @Value("${app.ipUrl}")
    private String ipUrl;

    @Value("${app.ipToken}")
    private String token;

    @Autowired
    private IPLocationRepo ipLocationRepo;

    public IPLocation getIpInfo() {
        String apiUrl = uriBuilder(ipUrl, token);
        String jsonResponse = apiResponse(apiUrl);

        IPLocation ipLocation = new IPLocation();

        JSONObject mainJson = new JSONObject(jsonResponse);
        ipLocation.setIp(mainJson.getString("ip"));
        ipLocation.setCity(mainJson.getString("city"));
        //ipLocation.setHostname(mainJson.getString("hostname"));
        ipLocation.setCountry(mainJson.getString("country"));
        ipLocation.setRegion(mainJson.getString("region"));
        ipLocation.setPostal(mainJson.getString("timezone"));

        InternetProvider internetProvider = new InternetProvider();
        internetProvider.setCountry("UK");
        internetProvider.setName("Airtel");
        internetProvider.setAddress("Lusaka");
        internetProvider.setDomain("airtel.com");
        internetProvider.setEmail("info@airtel.com");
        internetProvider.setType("Limited Company");
        internetProvider.setPhone("+260974045128");

        ipLocation.setInternetProvider(internetProvider);


        return ipLocation;
    }

    public String uriBuilder(String ipUrl, String token) {

        return UriComponentsBuilder.fromHttpUrl(ipUrl).queryParam("token", token).build().toUriString();
    }

    private String apiResponse(String apiUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(apiUrl).get().build();
        Response response;
        String apiResponse = "";
        try {
            response = client.newCall(request).execute();
            apiResponse = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    public IPLocation saveIpInfo(boolean isSave) {
        IPLocation ipLocation = getIpInfo();
        if (isSave) {
            ipLocationRepo.save(ipLocation);
        }

        return ipLocation;
    }

    public List<IPLocation> getIpDetails() {
        Iterable<IPLocation> iterable = ipLocationRepo.findAll();
        List<IPLocation> list = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());

        return list;
    }


    public void deleteIpDetails(String ip) {
        ipLocationRepo.deleteByIp(ip);
    }

    public ResponseEntity updateIpInfo(IPLocation ipLocation, long id) {
        Optional<IPLocation> optIpLocation = ipLocationRepo.findById(id);
        if (optIpLocation != null) {
            IPLocation existingIpLocation = optIpLocation.get();
            existingIpLocation.setCountry(ipLocation.getCountry());
            existingIpLocation.setCity(ipLocation.getCity());
            ipLocationRepo.save(existingIpLocation);
        } else {
            throw new IPNotFoundException();
            //throw new NoSuchElementException("No such content in db");
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public Iterable<IPLocation> getAll() {
        return ipLocationRepo.findAll();
    }
}