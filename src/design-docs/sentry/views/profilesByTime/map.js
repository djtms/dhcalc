function(doc){
	if (doc.type == 'DPS') {
		emit(doc.when, doc);
	}
}