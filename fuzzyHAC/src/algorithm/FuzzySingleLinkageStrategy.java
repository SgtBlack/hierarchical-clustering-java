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

import static java.lang.Math.sqrt;
import java.util.Collection;

public class FuzzySingleLinkageStrategy implements LinkageStrategy {

	@Override
	public Distance calculateDistance(Collection<Distance> distances) {
		double min = Double.NaN;
                double sum = 0;
                double weight = 0;

		for (Distance dist : distances) {
		    if (Double.isNaN(min) || dist.getDistance() < min){
                        min = dist.getDistance();
                    }
                    sum += dist.getDistance();
		        
		}
                
                weight = 1/(1+sqrt(min/sum));
		return new Distance( min, weight );
	}
}
