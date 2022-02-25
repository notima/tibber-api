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

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PriceInfo {

	@SerializedName("current")
	private CurrentPrice currentPrice;

	public CurrentPrice getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(CurrentPrice currentPrice) {
		this.currentPrice = currentPrice;
  }
  
  @SerializedName("today")
  private List<CurrentPrice> todaysPrice;

  public List<CurrentPrice> getTodaysPrice() {
    return todaysPrice;
  }

  public void setTodaysPrice(List<CurrentPrice> todaysPrice) {
    this.todaysPrice = todaysPrice;
  }
  
  
}
