package org.sample.controller.pojos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.sample.model.Ad;

public class EnquiryForm {

	//TODO: validation, when login works
	
	private Long enquiryId;
	private Long senderId; 
    private Long receiverId; 
    private String messageText;
    private Ad ad;
    private long adId;

    
    public Long getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
	}
    
	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public long getAdId() {
		return adId;
	}

	public void setAdId(long adId) {
		this.adId = adId;
	}
	
	
   
}
