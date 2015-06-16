/*******************************************************************************
 * Copyright 2013 Lars Behnke
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AverageLinkageStrategy implements LinkageStrategy {

	@Override
	public Distance calculateDistance(Collection<Distance> distances) {
		double sum = 0;
		double result;

		for (Distance dist : distances) {
			sum += dist.getDistance();
		}
                
		if (distances.size() > 0) {
			result = sum / distances.size();
		} else {
			result = 0.0;
		}
                
		return  new Distance(result);
	}
        
        @Override
	public Distance calculateDistanceK(Collection<Distance> distances, int k) {
		double sum = 0;
		double result;
                List<Double> dist_list = new ArrayList<Double>();
                
                for (Distance dist : distances) {
                    dist_list.add(dist.getDistance());
                }
                
                if( k > dist_list.size() ){
                    k = dist_list.size();
                }
                
                int randomNum;
                int randomMin = 0;
                
                //Collections.sort(dist_list);
                
                for( int i = 0; i < k; i++ ){
                    
                    randomNum = randomMin + (int)(Math.random() * ( dist_list.size() - 1 )); 
                    
                    sum += dist_list.get(randomNum);
                    
                    dist_list.remove(randomNum);
                }
                
                if (distances.size() > 0) {
                    result = sum / k;
		} else {
                    result = 0.0;
		}
            
		return  new Distance(result);
	}
}
