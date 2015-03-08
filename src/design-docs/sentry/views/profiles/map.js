function(doc){
	if (doc.type == 'DPS') {
		emit({ realm: doc.realm, battletag: doc.battletag }, doc);
	}
}