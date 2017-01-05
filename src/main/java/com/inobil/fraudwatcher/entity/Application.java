package com.inobil.fraudwatcher.entity;

import javax.persistence.*;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
 
    @Version
    private Integer version;
 
    private String applicationId;
    private String name;
    private Double fraudLimit;
    private Boolean isActive;
 
    public Integer getVersion() {
        return version;
    }
 
    public void setVersion(Integer version) {
        this.version = version;
    }
 
    /**
	 * @return the Id
	 */
    public Integer getId() {
        return id;
    }
 
	/**
	 * @param id the id to set
	 */
    public void setId(Integer id) {
        this.id = id;
    }
 
    /**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the fraudLimit
	 */
	public Double getFraudLimit() {
		return fraudLimit;
	}

	/**
	 * @param fraudLimit the fraudLimit to set
	 */
	public void setFraudLimit(Double fraudLimit) {
		this.fraudLimit = fraudLimit;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}