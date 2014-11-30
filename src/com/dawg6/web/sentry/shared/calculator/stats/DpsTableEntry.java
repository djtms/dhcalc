package com.dawg6.web.sentry.shared.calculator.stats;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.dawg6.server.common.aws.dao.EnumMarshaller;
import com.dawg6.web.sentry.server.db.aws.BuildMarshaller;
import com.dawg6.web.sentry.shared.calculator.Build;
import com.dawg6.web.sentry.shared.calculator.d3api.Realm;

@DynamoDBTable(tableName = "undefined")
public class DpsTableEntry implements Serializable {

	private static final long serialVersionUID = -7605233453931536272L;

	protected String battletag;
	protected Realm realm;
	protected Long when;
	protected Double single;
	protected Double single_elite;
	protected Double multiple;
	protected Double multiple_elite;
	protected Integer paragon;
	protected Integer paragon_ias;
	protected Integer paragon_cdr;
	protected Integer paragon_cc;
	protected Integer paragon_chd;
	protected Build build;
	protected Integer bp;
	protected Double sentryDps;
	protected Double playerAps;
	protected String profile;
	protected Integer tag;
	protected Integer heroId;
	protected String heroName;
	protected Boolean seasonal;
	protected Boolean hardcore;
	protected Boolean dead;
	protected Double sheetDps;
	protected Integer level;

	@DynamoDBHashKey(attributeName = "battletag")
	public String getBattletag() {
		return battletag;
	}

	public void setBattletag(String battletag) {
		this.battletag = battletag;
	}

	@DynamoDBRangeKey(attributeName = "realm")
	@DynamoDBMarshalling(marshallerClass = EnumMarshaller.class)
	public Realm getRealm() {
		return realm;
	}

	public void setRealm(Realm realm) {
		this.realm = realm;
	}

	@DynamoDBAttribute(attributeName = "when")
	public Long getWhen() {
		return when;
	}

	public void setWhen(Long when) {
		this.when = when;
	}

	@DynamoDBAttribute(attributeName = "single")
	public Double getSingle() {
		return single;
	}

	public void setSingle(Double single) {
		this.single = single;
	}

	@DynamoDBAttribute(attributeName = "single_elite")
	public Double getSingle_elite() {
		return single_elite;
	}

	public void setSingle_elite(Double single_elite) {
		this.single_elite = single_elite;
	}

	@DynamoDBAttribute(attributeName = "multiple")
	public Double getMultiple() {
		return multiple;
	}

	public void setMultiple(Double multiple) {
		this.multiple = multiple;
	}

	@DynamoDBAttribute(attributeName = "multiple_elite")
	public Double getMultiple_elite() {
		return multiple_elite;
	}

	public void setMultiple_elite(Double multiple_elite) {
		this.multiple_elite = multiple_elite;
	}

	@DynamoDBAttribute(attributeName = "paragon")
	public Integer getParagon() {
		return paragon;
	}

	public void setParagon(Integer paragon) {
		this.paragon = paragon;
	}

	public Integer getParagon_ias() {
		return paragon_ias;
	}

	@DynamoDBAttribute(attributeName = "paragon_ias")
	public void setParagon_ias(Integer paragon_ias) {
		this.paragon_ias = paragon_ias;
	}

	public Integer getParagon_cdr() {
		return paragon_cdr;
	}

	@DynamoDBAttribute(attributeName = "paragon_cdr")
	public void setParagon_cdr(Integer paragon_cdr) {
		this.paragon_cdr = paragon_cdr;
	}

	public Integer getParagon_cc() {
		return paragon_cc;
	}

	@DynamoDBAttribute(attributeName = "paragon_cc")
	public void setParagon_cc(Integer paragon_cc) {
		this.paragon_cc = paragon_cc;
	}

	public Integer getParagon_chd() {
		return paragon_chd;
	}

	@DynamoDBAttribute(attributeName = "paragon_chd")
	public void setParagon_chd(Integer paragon_chd) {
		this.paragon_chd = paragon_chd;
	}

	@DynamoDBAttribute(attributeName = "build")
	@DynamoDBMarshalling(marshallerClass = BuildMarshaller.class)
	public Build getBuild() {
		return build;
	}

	public void setBuild(Build build) {
		this.build = build;
	}

	@DynamoDBAttribute(attributeName = "bp")
	public Integer getBp() {
		return bp;
	}

	public void setBp(Integer bp) {
		this.bp = bp;
	}

	@DynamoDBAttribute(attributeName = "sentryDps")
	public Double getSentryDps() {
		return sentryDps;
	}

	public void setSentryDps(Double sentryDps) {
		this.sentryDps = sentryDps;
	}

	@DynamoDBAttribute(attributeName = "playerAps")
	public Double getPlayerAps() {
		return playerAps;
	}

	public void setPlayerAps(Double playerAps) {
		this.playerAps = playerAps;
	}

	@DynamoDBAttribute(attributeName = "profile")
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@DynamoDBAttribute(attributeName = "tag")
	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	@DynamoDBAttribute(attributeName = "heroId")
	public Integer getHeroId() {
		return heroId;
	}

	public void setHeroId(Integer heroId) {
		this.heroId = heroId;
	}

	@DynamoDBAttribute(attributeName = "heroName")
	public String getHeroName() {
		return heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	@DynamoDBAttribute(attributeName = "seasonal")
	public Boolean getSeasonal() {
		return seasonal;
	}

	public void setSeasonal(Boolean seasonal) {
		this.seasonal = seasonal;
	}

	@DynamoDBAttribute(attributeName = "hardcore")
	public Boolean getHardcore() {
		return hardcore;
	}

	public void setHardcore(Boolean hardcore) {
		this.hardcore = hardcore;
	}

	@DynamoDBAttribute(attributeName = "dead")
	public Boolean getDead() {
		return dead;
	}

	public void setDead(Boolean dead) {
		this.dead = dead;
	}

	@DynamoDBAttribute(attributeName = "sheetDps")
	public Double getSheetDps() {
		return sheetDps;
	}

	public void setSheetDps(Double sheetDps) {
		this.sheetDps = sheetDps;
	}

	@DynamoDBAttribute(attributeName = "level")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
