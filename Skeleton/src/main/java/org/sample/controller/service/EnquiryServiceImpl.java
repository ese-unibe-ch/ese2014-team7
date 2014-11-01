package org.sample.controller.service;

import java.util.LinkedList;

import org.sample.controller.exceptions.InvalidAdException;
import org.sample.controller.pojos.EnquiryForm;
import org.sample.model.Enquiry;
import org.sample.model.dao.AdDao;
import org.sample.model.dao.EnquiryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired EnquiryDao enquiryDao;
	@Autowired LoginService loginService;
	@Autowired AdDao adDao;

	@Transactional //TODO: throw exception for null Ads to load 404 in Enquiry controller
	public EnquiryForm submit(EnquiryForm enquiryForm) {
		Enquiry enquiry = new Enquiry();
		
		/* fetch ad from DB, according to submitted Ad-ID. Throw exception if no ad found */
		enquiryForm.setAd(adDao.findOne(enquiryForm.getAdId()));
		if(enquiryForm.getAd() == null)
			throw new InvalidAdException("This ad doesn't exist");
			
		/* Update enquiry FORM: set sender to logged in user, receiver to ad placer ID */
		enquiryForm.setSenderId(loginService.getLoggedInUser().getId());
		enquiryForm.setReceiverId(enquiryForm.getAd().getPlacerId());
		
		/* Fill in enquiry from completed enquiry form */
		enquiry.setSenderId(enquiryForm.getSenderId());
		enquiry.setReceiverId(enquiryForm.getReceiverId());
		enquiry.setMessageText(enquiryForm.getMessageText());
		
		enquiryDao.save(enquiry);
		
		return enquiryForm;
	}

	@Transactional
	public Iterable<Enquiry> findSentEnquiries() {
		Iterable<Enquiry> allEnquiries = enquiryDao.findAll();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if(e.getSenderId() == loginService.getLoggedInUser().getId())
				results.add(e);
		}
		
		return (Iterable<Enquiry>)results;
	}

	@Transactional
	public Iterable<Enquiry> findReceivedEnquiries() {
		Iterable<Enquiry> allEnquiries = enquiryDao.findAll();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if(e.getReceiverId() == loginService.getLoggedInUser().getId())
				results.add(e);
		}
		
		return (Iterable<Enquiry>)results;
	}
    
    

}
