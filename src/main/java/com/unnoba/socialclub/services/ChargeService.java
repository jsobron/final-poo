/**
 * 
 */
package com.unnoba.socialclub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unnoba.socialclub.entities.Charge;
import com.unnoba.socialclub.repositories.ChargeRepository;
/**
 * @author lfalcioni
 *
 */

@Service
public class ChargeService {
	
	private ChargeRepository chargeRepository;

	@Autowired
	public ChargeService(ChargeRepository chargeRepository) {
		this.chargeRepository = chargeRepository;
	}

	public void saveCharge(Charge charge) {
		chargeRepository.save(charge);
	}
	
	public Charge findChargeById(int id) {
		return chargeRepository.findChargeById(id);
	}
	
    public void deleteCharge(int chargeId) {
        chargeRepository.deleteById(chargeId);
    }
	
}

