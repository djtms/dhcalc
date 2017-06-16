<%@ page import="com.dawg6.web.dhcalc.shared.calculator.*"%>


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

<a href="http://us.battle.net/d3/en/class/monk/active/crippling-wave#a+">Skill A</a><br/>
<a href="http://us.battle.net/d3/en/class/monk/active/crippling-wave#b+">Skill B</a><br/>
<a href="http://us.battle.net/d3/en/class/monk/active/crippling-wave#c+">Skill C</a><br/>
<a href="http://us.battle.net/d3/en/class/monk/active/crippling-wave#d+">Skill D</a><br/>
<a href="http://us.battle.net/d3/en/class/monk/active/crippling-wave#e+">Skill E</a><br/>
<table border="1">
<tr>
<th>Skill</th><th>Runes</th>
</tr>
<%
for (ActiveSkill skill : ActiveSkill.values()) {
%>
<tr>
<td><a href="<%= skill.getUrl() %>" target="_blank"><%= skill.getLongName() %></a></td>
<td>
<table border="1">
<%
	for (Rune rune : skill.getRunes()) {
		String url = skill.getUrl();
		
		if (rune != Rune.None)
			url += ("#" + rune.getSlug() + "+");
%>
<tr><td><a href="<%= url %>" target="_blank"><%= rune.getLongName() %></a></td></tr>
<%		
	}
%>
</table>
</td>
</tr>
<%	
}
%>
</table>
</body>
</html>