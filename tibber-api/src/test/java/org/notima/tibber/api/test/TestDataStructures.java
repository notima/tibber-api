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

package org.notima.tibber.api.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.notima.tibber.api.TibberClient;
import org.notima.tibber.api.TibberResponse;
import org.notima.tibber.api.TibberSettings;
import org.notima.tibber.api.TibberUtil;
import org.notima.tibber.api.entities.CurrentPrice;
import org.notima.tibber.api.entities.Data;
import org.notima.tibber.api.entities.Home;
import org.notima.tibber.api.entities.PriceInfo;
import org.notima.tibber.api.entities.Subscription;
import org.notima.tibber.api.entities.Viewer;

class TestDataStructures {

	private static TibberSettings settings;
	
	@BeforeAll
	static void setUp() {
		try {			
			settings = TibberSettings.readFromFile(TibberSettings.DEFAULT_FILE);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	@DisplayName("Test creating a json datastructure")
	@Test
	void testDataStructure() {
		TibberResponse r = new TibberResponse();
		Data data = new Data();
		r.setData(data);
		Viewer viewer = new Viewer();
		data.setViewer(viewer);
		List<Home> homes = new ArrayList<Home>();
		viewer.setHomes(homes);
		Home home = new Home();
		homes.add(home);
		Subscription s = new Subscription();
		home.setCurrentSubscription(s);
		PriceInfo pi = new PriceInfo();
		s.setPriceInfo(pi);
		CurrentPrice cp = new CurrentPrice();
		pi.setCurrentPrice(cp);
		cp.setEnergy(0.50);
		
		String result = TibberUtil.gson.toJson(r);
		assertEquals(
				"{\"data\":{\"viewer\":{\"homes\":[{\"currentSubscription\":{\"priceInfo\":{\"current\":{\"energy\":0.5}}}}]}}}",
				result);
		
	}
	
	@DisplayName("Test making a pricerequest call")
	@Test
	void testPriceRequest() {
		String accessToken = settings.getAccessToken();
		TibberClient cl = new TibberClient(accessToken);
		TibberResponse r;
		try {
			r = cl.requestCurrentPrice();
			CurrentPrice cp = r.getData().getViewer().getHomes().get(0).getCurrentSubscription().getPriceInfo().getCurrentPrice();
			
			assertNotNull(cp, "No current price record returned");
			assertNotNull(cp.getEnergy(), "No energy price returned");
			
			if (cp!=null) {
				System.out.println("Energy price: " + cp.getEnergy());
				System.out.println("Tax: " + cp.getTax());
				System.out.println("Total: " + cp.getTotal());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		

	@DisplayName("Test making a pricerequest call for today's prices")
	@Test
	void testPriceRequestToday() {
		String accessToken = settings.getAccessToken();
		TibberClient cl = new TibberClient(accessToken);
		TibberResponse r;
		try {
			r = cl.requestPrice(TibberClient.TimeIndicatorType.today);
			List<CurrentPrice> cps = r.getData().getViewer().getHomes().get(0).getCurrentSubscription().getPriceInfo().getTodaysPrice();
			
			assertNotNull(cps, "No todays price records returned");
			
			if (cps!=null) {
				for (CurrentPrice cp : cps) {
					System.out.println("Starts at: " + cp.getStartsAt());
					System.out.println("Energy price: " + cp.getEnergy());
					System.out.println("Tax: " + cp.getTax());
					System.out.println("Total: " + cp.getTotal());
					System.out.println("Currency: " + cp.getCurrency());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
