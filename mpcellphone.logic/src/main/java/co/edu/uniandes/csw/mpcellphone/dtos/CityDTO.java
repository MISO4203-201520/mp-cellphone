/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpcellphone.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author m.amaya11
 */
@XmlRootElement
public class CityDTO {
    private Long id;
    private String name;
    private StateDTO state;
    private Double longitude;
    private Double latitude;

    /**
     * @return 
     * @generated
     */
    public Long getId() {
        return id;
    }
    
    /**
     * @param id
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return 
     * @generated
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 
     * @generated
     */
    public StateDTO getState() {
        return state;
    }

    /**
     * @param state
     * @generated
     */
    public void setState(StateDTO state) {
        this.state = state;
    }
    
    /**
     * @return 
     * @generated
     */
    public Double getLongitude() {
        return longitude;
    }
    
    /**
     * @param longitude
     * @generated
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    /**
     * @return 
     * @generated
     */
    public Double getLatitude() {
        return latitude;
    }
    
    /**
     * @param latitude
     * @generated
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}