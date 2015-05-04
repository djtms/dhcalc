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