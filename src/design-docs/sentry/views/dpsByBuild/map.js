function(doc){
	if (doc.type == 'DPS') {
		var build = {};
		
		if (doc.build) {
			if (doc.build.sentryRune)	
				build.Sentry = doc.build.sentryRune;
			
			if (doc.build.skills) {
				doc.build.skills.forEach(function(r){
					
					if (r.skill)
						build[r.skill] = true;
					
					if (r.rune && (r.rune != 'None'))
						build[r.rune] = true;
				});
			}
		}
		
		emit(build, doc);
	}
}