function(doc){
	if (doc.type == 'Attribute') {
		emit({ attribute: doc.attribute }, doc);
	}
}