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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Utility class to hold GsonBuilder
 * 
 * @author Daniel Tamm
 *
 */
public class TibberUtil {

	public static Gson gson;
	public static String dfmtStr = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static DateFormat dfmt = new SimpleDateFormat(dfmtStr);
	
	static {
		GsonBuilder gb = new GsonBuilder();
		gb.setDateFormat(dfmtStr);
		gson = gb.create();
	}
	
}
