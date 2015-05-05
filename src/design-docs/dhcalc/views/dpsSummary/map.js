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
function(doc){
	if (doc.type == 'DPS') {
		var build = '';
		
		if (doc.build) {
			
			if (doc.build.sentry) {
				build += doc.build.sentryRune;
				build += '/';
			}
			
			if (doc.build.skills) {
				doc.build.skills.forEach(function(r){
					build += r.skill;
					build += '.';
					build += r.rune;
					build += '/';
				});
			}
		}
		
		var fields = [ "single_elite", "single", "multiple_elite", "multiple" ];
		
		var dps = {};
		dps.count = 1;
		dps.total = {};
		
		fields.forEach(function(f) {
			dps.total[f] = doc[f];
		});

		emit(build, dps);
	}
}