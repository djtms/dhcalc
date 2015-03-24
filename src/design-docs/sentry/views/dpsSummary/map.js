function(doc){
	if (doc.type == 'DPS') {
		var build = '';
		
		if (doc.build) {
			build += doc.build.sentryRune;
			build += '/';
			
			
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