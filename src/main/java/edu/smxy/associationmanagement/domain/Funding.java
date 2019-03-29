package edu.smxy.associationmanagement.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Funding {
	private Integer id;

	private Integer fundingAssid;

	private String fundingName;

	private Integer fundingType;

	private BigDecimal fundingCost;

	private String fundingInfo;

	private Date fundingTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFundingAssid() {
		return fundingAssid;
	}

	public void setFundingAssid(Integer fundingAssid) {
		this.fundingAssid = fundingAssid;
	}

	public String getFundingName() {
		return fundingName;
	}

	public void setFundingName(String fundingName) {
		this.fundingName = fundingName == null ? null : fundingName.trim();
	}

	public Integer getFundingType() {
		return fundingType;
	}

	public void setFundingType(Integer fundingType) {
		this.fundingType = fundingType;
	}

	public BigDecimal getFundingCost() {
		return fundingCost;
	}

	public void setFundingCost(BigDecimal fundingCost) {
		this.fundingCost = fundingCost;
	}

	public String getFundingInfo() {
		return fundingInfo;
	}

	public void setFundingInfo(String fundingInfo) {
		this.fundingInfo = fundingInfo == null ? null : fundingInfo.trim();
	}

	public Date getFundingTime() {
		return fundingTime;
	}

	public void setFundingTime(Date fundingTime) {
		this.fundingTime = fundingTime;
	}
}
