package com.exerate.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Setter
@Getter
@Entity(name = "IP_LOCATION_TB")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@ApiModel(value = "Response IP Details")
public class IPLocation {
    @Id
    @GeneratedValue
    //@JsonIgnore
    private long id;
    private String ip;
    private String hostname;
    private String city;
    private String region;
    private String country;
    private String postal;
    private String timezone;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private InternetProvider internetProvider;


}
