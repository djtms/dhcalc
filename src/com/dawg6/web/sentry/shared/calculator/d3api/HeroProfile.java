package com.dawg6.web.sentry.shared.calculator.d3api;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeroProfile extends D3Message implements Serializable {

	private static final long serialVersionUID = 1L;

	public long id;
	public String name;
	@JsonProperty("class")
	public String clazz;
	public int gender;
	public int level;
	public int paragonLevel;
	public boolean hardcore;

	public boolean seasonal;
	public int seasonCreated;

	public Skills skills;

	public static class Skills implements Serializable {

		private static final long serialVersionUID = 1L;

		public Active[] active;

		public static class Active implements Serializable {

			private static final long serialVersionUID = 1L;

			public Skill skill;

			public static class Skill implements Serializable {

				private static final long serialVersionUID = 1L;

				public String slug;
				public String name;
				public String icon;
				public int level;
				public String categorySlug;
				public String tooltipUrl;
				public String description;
				public String simpleDescription;
				public String skillCalcId;
			}

			public Rune rune;

			public static class Rune implements Serializable {

				private static final long serialVersionUID = 1L;


				public String slug;
				public String type;
				public String name;
				public int level;
				public String description;
				public String simpleDescription;
				public String tooltipParams;
				public String skillCalcId;
				public int order;
			}
		}

		public Passive[] passive;

		public static class Passive implements Serializable {

			private static final long serialVersionUID = 1L;


			public Skill skill;

			public static class Skill implements Serializable {

				private static final long serialVersionUID = 1L;


				public String slug;
				public String name;
				public String icon;
				public int level;
				public String tooltipUrl;
				public String description;
				public String flavor;
				public String skillCalcId;
			}
		}

	}


	/*
	 * @gwt.typeArgs<java.lang.String, com.dawg6.web.sentry.shared.calculator.d3api.ItemInformation>
	 */
	public Map<String, ItemInformation> items;

	public static class Items implements Serializable {

		private static final long serialVersionUID = 1L;

		public ItemInformation head;
		public ItemInformation torso;
		public ItemInformation feet;
		public ItemInformation hands;
		public ItemInformation shoulders;
		public ItemInformation legs;
		public ItemInformation bracers;
		public ItemInformation mainHand;
		public ItemInformation offHand;
		public ItemInformation waist;
		public ItemInformation rightFinger;
		public ItemInformation leftFinger;
		public ItemInformation neck;

		public ItemInformation[] allItems() {
			return new ItemInformation[] { head, torso, feet, hands, shoulders,
					legs, bracers, mainHand, offHand, waist, rightFinger,
					leftFinger, neck };
		}

	}

	public Followers followers;

	public static class Followers implements Serializable {

		private static final long serialVersionUID = 1L;

		public Follower templar;
		public Follower scoundrel;
		public Follower enchantress;

		public static class Follower implements Serializable {

			private static final long serialVersionUID = 1L;

			public String slug;
			public int level;
			public Items items;

			public static class Items implements Serializable {

				private static final long serialVersionUID = 1L;

				public ItemInformation special;
				public ItemInformation mainHand;
				public ItemInformation offHand;
				public ItemInformation rightFinger;
				public ItemInformation leftFinger;
				public ItemInformation neck;
			}

			public Stats stats;

			public static class Stats implements Serializable {

				private static final long serialVersionUID = 1L;

				public int goldFind;
				public int magicFind;
				public int experienceBonus;
			}

			public Skills[] skills;

			public static class Skills implements Serializable {

				private static final long serialVersionUID = 1L;



				public Skill skill;

				public static class Skill implements Serializable {

					private static final long serialVersionUID = 1L;



					public String slug;
					public String name;
					public String icon;
					public int level;
					public String tooltipUrl;
					public String description;
					public String simpleDescription;
					public String skillCalcId;

				}

			}

		}
	}

	public HeroStats stats;

	public Kill kills;

	public Progression progression;

	public static class Progression implements Serializable {

		private static final long serialVersionUID = 1L;

		public Act act1;
		public Act act2;
		public Act act3;
		public Act act4;
		public Act act5;

		public static class Act implements Serializable {

			private static final long serialVersionUID = 1L;

			public boolean completed;
			public CompletedQuest[] completedQuests;

			public static class CompletedQuest implements Serializable {

				private static final long serialVersionUID = 1L;

				public String slug;
				public String name;
			}
		}
	}

	public boolean dead;
	@JsonProperty("last-updated")
	public long lastUpdated;

}
