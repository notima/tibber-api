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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * Class to read settings from JSON.
 * 
 * @author Daniel Tamm
 *
 */
public class TibberSettings {

	public static final String DEFAULT_FILE = "settings.json";
	public static final String EXAMPLE_FILE = "settings.example.json";
	
	/**
	 * Read TibberSettings from file.
	 * 
	 * If no settings file is found, we try the settings.example.json file which 
	 * contains a demo token.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException if file is not found or other reading error occurs.
	 */
	public static TibberSettings readFromFile(String fileName) throws IOException {
		// First see if it resolves to a file
		File f = new File(fileName);
		if (!f.exists()) {
			// Try to find the file as a resource
			URL url = ClassLoader.getSystemResource(fileName);
			if (url==null) {
				// Try to find the settings.example.json
				url = ClassLoader.getSystemResource(EXAMPLE_FILE);
				if (url==null)
					throw new FileNotFoundException(fileName);
				else 
					fileName = url.getFile();
			}
			else
				fileName = url.getFile();
		}
		
		FileReader fr = new FileReader(fileName);
		TibberSettings s = TibberUtil.gson.fromJson(fr, TibberSettings.class);
		fr.close();
		return s;
		
	}
	
	private String	accessToken;
	private String	tibberUrl;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTibberUrl() {
		return tibberUrl;
	}
	public void setTibberUrl(String tibberUrl) {
		this.tibberUrl = tibberUrl;
	}
	
}
