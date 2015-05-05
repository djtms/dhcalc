/*******************************************************************************
 * Copyright (c) 2014, 2015 Scott Clarke (scott@dawg6.com).
 *
 * This file is part of Dawg6's Demon Hunter DPS Calculator.
 *
 * Dawg6's Demon Hunter DPS Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dawg6's Demon Hunter DPS Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
function (key, values, rereduce) {
	
	var fields = [ "single_elite", "single", "multiple_elite", "multiple" ];
	var result = {};
	result.count = 0;
	result.total = {};
	result.min = {};
	result.max = {};

	fields.forEach(function(f){
		result.total[f] = 0;
	});
	
	values.forEach(function(r) {
		result.count += r.count;
		
		fields.forEach(function(f){
			result.total[f] += r.total[f];
			
			if (r.count == 1) {
				if (!result.min[f] || (r.total[f] < result.min[f])) {
					result.min[f] = r.total[f];
				}
				if (!result.max[f] || (r.total[f] > result.max[f])) {
					result.max[f] = r.total[f];
				}
			} else {
				if (!result.min[f] || (r.min[f] < result.min[f])) {
					result.min[f] = r.min[f];
				}
				if (!result.max[f] || (r.max[f] > result.max[f])) {
					result.max[f] = r.max[f];
				}
			}
		});
	});
	
	if (result.count > 0) {
		result.average = {};

		fields.forEach(function(f){
			result.average[f] = result.total[f] / result.count;
		});
		
	}

	return result;
}