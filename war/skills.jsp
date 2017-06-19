<%@ page import="com.dawg6.web.dhcalc.shared.calculator.*"%>
<%@ page import="com.dawg6.d3api.shared.*"%>
<%@ page import="com.dawg6.web.dhcalc.server.*"%>

<html>
<meta http-equiv="expires" content="0">
<link rel="shortcut icon" href="sentry-new.ico" type="image/x-icon">
<link rel="icon" href="sentry-new.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" media="all"
	href="http://us.battle.net/d3/static/local-common/css/common-game-site.min.css?v=58-80" />
<link rel="stylesheet" type="text/css" media="all"
	href="paperdoll.css?v=3" />
<link rel="stylesheet" type="text/css" media="all"
	href="http://us.battle.net/d3/static/css/tooltips.css?v=80" />
<link rel="stylesheet" type="text/css" media="all"
	href="http://us.battle.net/d3/static/css/profile/shared.css?v=58-80" />
<link rel="stylesheet" type="text/css" media="all"
	href="http://us.battle.net/d3/static/css/profile/hero.css?v=58-80" />
<link rel="stylesheet" type="text/css" media="all"
	href="http://us.battle.net/d3/static/css/tool/gear-calculator/hero-slots.css?v=58-80" />
<script type="text/javascript"
	src="http://us.battle.net/d3/static/local-common/js/third-party.js?v=58-80"></script>
<script src="tooltips.js?v=13"></script>

<%

	try {
		HeroData data = IO.getInstance().readHeroData(Realm.US, D3Class.DemonHunter);
	
		if (data == null) {
%>
Null HeroData
<%			
		} else {
			
%>
<table border="1">
<tr>
<th>Skill</th><th>Runes</th>
</tr>
<%	
			SkillDefinitions skills = data.skills;

			if (skills != null) {
				
				for (SkillDefinition skill : skills.active) {
				
					if (skill != null) {
%>
<tr><td><a target="_blank" href="http://us.battle.net/d3/en/class/demon-hunter/active/<%= skill.slug %>"><%= skill.name %></a> (<%= skill.categorySlug %>)</td>
<td>
<%
						com.dawg6.d3api.shared.Rune runes[] = skill.runes;

						if (runes != null) {
%>
<table>
<%
							for (com.dawg6.d3api.shared.Rune rune : runes) {
							
									if (rune != null) {
%>
<tr><td><a target="_blank" href="http://us.battle.net/d3/en/class/demon-hunter/active/<%= skill.slug %>#<%= rune.type %>+"><%= rune.name %></a> (<%= rune.type %>)</td>
<%										
									}
							}
%>
</table>
<%							
						}

%>
</td>
</tr>
<%						
					}
				}
				
				for (SkillDefinition skill : skills.passive) {
				
					if (skill != null) {
%>
<tr><td><a target="_blank" href="http://us.battle.net/d3/en/class/demon-hunter/passive/<%= skill.slug %>"><%= skill.name %></a></td>
<td>&nbsp;</td>
</tr>
<%						
					}
				}
			}
		}
%>
</table>
<%	
	} catch (Exception e) {
%>
Exception: <%= e.toString() %>
<%		
	}
%>
</body>
</html>