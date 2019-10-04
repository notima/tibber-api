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

package org.notima.tibber.api;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple java client to call Tibber API. 
 * 
 * @author Daniel Tamm
 *
 */
public class TibberClient {

	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

	public static String defaultTibberUrl = "https://api.tibber.com/v1-beta/gql";

	private String tibberUrl;
	
	private String accessToken;

	// GraphQL query to request price
	private String requestPrice = "{\n" + 
			"  viewer {\n" + 
			"    homes {\n" + 
			"      currentSubscription{\n" + 
			"        priceInfo{\n" + 
			"          current{\n" + 
			"            total\n" + 
			"            energy\n" + 
			"            tax\n" + 
			"            startsAt\n" + 
			"          }\n" + 
			"        }\n" + 
			"      }\n" + 
			"    }\n" + 
			"  }\n" + 
			"}\n";

	private OkHttpClient client = new OkHttpClient();

	/**
	 * Creates a new TibberClient using default TibberURL.
	 * 
	 * @param accessToken
	 */
	public TibberClient(String accessToken) {
		tibberUrl = defaultTibberUrl;
		this.accessToken = accessToken;
	}

	/**
	 * Creates new TibberClient with URL specified.
	 * 
	 * @param tibberUrl
	 * @param accessToken
	 */
	public TibberClient(String tibberUrl, String accessToken) {
		this.tibberUrl = tibberUrl;
		this.accessToken = accessToken;
	}
	
	/**
	 * Requests current price.
	 * 
	 * @return	A TibberResponse.
	 * @throws IOException
	 */
	public TibberResponse requestPrice() throws IOException {

		String response = post(tibberUrl, requestPrice);
		TibberResponse result = TibberUtil.gson.fromJson(response, TibberResponse.class);

		return result;
	}

	/**
	 * Generic method to call Tibber-API
	 * 
	 * @param url
	 * @param query
	 * @return
	 * @throws IOException
	 */
	private String post(String url, String query) throws IOException {
		TibberQuery q = new TibberQuery();
		q.setQuery(query);
		String json = TibberUtil.gson.toJson(q);
		RequestBody body = RequestBody.create(json, JSON);

		Request request = new Request.
				Builder().
				url(url).
				header("Authorization", "Bearer " + accessToken).
				post(body)
				.build();
		
		try (
				Response response = client.newCall(request).execute()) {
				return response.body().string();
		}
	}

}
