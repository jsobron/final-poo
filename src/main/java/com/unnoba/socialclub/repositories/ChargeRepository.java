/**
 * 
 */
package com.unnoba.socialclub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unnoba.socialclub.entities.Charge;

/**
 * @author lfalcioni
 *
 */
public interface ChargeRepository extends JpaRepository <Charge, Integer> {
	
	@Query ("SELECT c FROM Charge c WHERE c.id= ?1")
	public Charge findChargeById(int id);

}
