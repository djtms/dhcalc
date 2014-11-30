package com.dawg6.web.sentry.shared.calculator.stats;

public enum StatCategory {
	Single("Single Target Non-Elite DPS", new Evaluator() {

		@Override
		public double getValue(DpsTableEntry e) {
			return e.getSingle();
		}
	}),

	Multiple("Multiple Targets Non-Elite DPS", new Evaluator() {

		@Override
		public double getValue(DpsTableEntry e) {
			return e.getMultiple();
		}
	}), SingleElite("Single Target Elite DPS", new Evaluator() {

		@Override
		public double getValue(DpsTableEntry e) {
			return e.getSingle_elite();
		}
	}), MultipleElite("Multiple Targets Elite DPS", new Evaluator() {

		@Override
		public double getValue(DpsTableEntry e) {
			return e.getMultiple_elite();
		}
	});

	private StatCategory(String description, Evaluator eval) {
		this.eval = eval;
		this.description = description;
	}

	private String description;
	private Evaluator eval;

	public String getDescription() {
		return this.description;
	}

	public double getValue(DpsTableEntry e) {
		return eval.getValue(e);
	}

	public interface Evaluator {
		double getValue(DpsTableEntry e);
	}
}