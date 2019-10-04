/**
   Copyright 2019 Notima System Integration AB

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package org.notima.tibber.api.entities;

import java.util.Date;

/**
 * Record that describes the current price.
 * 
 * @author Daniel Tamm
 *
 */
public class CurrentPrice {

	private Double total;
	private Double energy;
	private Double tax;
	private Date startsAt;
	
	/**
	 * 
	 * @return	Total price (energy + tax)
	 */
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	/**
	 * 
	 * @return	Energy part of the price.
	 */
	public Double getEnergy() {
		return energy;
	}
	public void setEnergy(Double energy) {
		this.energy = energy;
	}
	
	/**
	 * 
	 * @return	Tax part of the price
	 */
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	
	/**
	 * 
	 * @return	At what time this price starts. Normally it's valid for one hour
	 * 			ahead.
	 */
	public Date getStartsAt() {
		return startsAt;
	}
	public void setStartsAt(Date startsAt) {
		this.startsAt = startsAt;
	}
	
}
