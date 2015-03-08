function(doc){
	if (doc.type == 'Parameter') {
		emit(doc._id, doc.value);
	}
}