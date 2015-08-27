<%@ page import="com.dawg6.web.dhcalc.shared.calculator.d3api.*"%>
<%@ page import="com.dawg6.web.dhcalc.server.DHCalcServiceImpl"%>
<%@ page import="com.dawg6.web.dhcalc.server.JsonServlet"%>
<%@ page import="com.dawg6.web.dhcalc.server.IO"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.logging.Logger"%>
<%@ page import="java.util.logging.Level"%>


<html>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link rel="shortcut icon" href="demonhunter_female.ico?v1" type="image/x-icon">
<link rel="icon" href="demonhunter_female.ico?v1" type="image/x-icon">
<link rel="stylesheet" type="text/css" media="all"
	href="http://us.battle.net/d3/static/local-common/css/common-game-site.min.css?v=58-85" />
<link rel="stylesheet" type="text/css" media="all"
	href="paperdoll.css?v=3" />
<link rel="stylesheet" type="text/css" media="all"
	href="http://us.battle.net/d3/static/css/tooltips.css?v=85" />
<link rel="stylesheet" type="text/css" media="all"
	href="http://us.battle.net/d3/static/css/profile/shared.css?v=58-85" />
<link rel="stylesheet" type="text/css" media="all"
	href="http://us.battle.net/d3/static/css/profile/hero.css?v=58-85" />
<link rel="stylesheet" type="text/css" media="all"
	href="http://us.battle.net/d3/static/css/tool/gear-calculator/hero-slots.css?v=58-85" />
<script type="text/javascript"
	src="http://us.battle.net/d3/static/local-common/js/third-party.js?v=58-85"></script>
<script type="text/javascript" src="http://us.battle.net/d3/static/local-common/js/common-game-site.min.js?v=58-85"></script>
<script src="tooltips.js?v=13"></script>
<script type="text/javascript" src="http://us.battle.net/d3/static/js/d3.js?v=85"></script>
<script type="text/javascript" src="http://us.battle.net/d3/static/js/profile/profile.js?v=58-85"></script>

<%
	try {
		DHCalcServiceImpl service = new DHCalcServiceImpl();

		String realmName = request.getParameter("realm");
		String profile = request.getParameter("profile");
		String tag = request.getParameter("tag");
		String id = request.getParameter("id");
		Realm realm = null;

		if (realmName != null)
	realm = Realm.valueOf(realmName);

		if ((realm == null) || (profile == null)
		|| (profile.trim().length() == 0) || (tag == null)
		|| (tag.trim().length() == 0) || (id == null)
		|| (id.trim().length() == 0))

		{
	throw new RuntimeException("Missing Field Data");
		} else {

	profile = URLDecoder.decode(profile, "UTF-8");
	String encodedProfile = URLEncoder.encode(profile, "UTF-8");

	Logger.getLogger("paperdoll.jsp").log(
			Level.INFO,
			"Profile: " + realm + "/" + profile + "-" + tag
					+ "/" + id);

	CareerProfile career = service.getProfile(realm, profile,
			Integer.valueOf(tag));
	HeroProfile hero = service.getHero(realm, profile,
			Integer.valueOf(tag), Integer.valueOf(id));
%>
<title><%=profile%>-<%=tag%> <%=hero.name%> : Level <%=hero.level%>
	(<%=hero.paragonLevel%>) <%=hero.hardcore ? "Hardcore " : ""%><%=hero.seasonal ? "Seasonal" : ""%></title>

<body>
	<h2 class="header-3">
		<a target="_blank"
			href="json?<%=JsonServlet.REALM%>=<%=realm.name()%>&<%=JsonServlet.PROFILE%>=<%=profile%>&<%=JsonServlet.TAG%>=<%=tag%>"><%=career.battleTag%></a>
	</h2>

	<h2 class="header-3 name">
		<a href="json?<%=JsonServlet.REALM%>=<%=realm.name()%>&<%=JsonServlet.PROFILE%>=<%=profile%>&<%=JsonServlet.TAG%>=<%=tag%>&<%=JsonServlet.ID%>=<%=id%>" target="_blank"><%=hero.name%></a>
	</h2>

	<h2 class="class">

		<strong><%=hero.level%><span class="paragon-level">(<%=hero.paragonLevel%>)
		</span></strong> Demon Hunter
		<%
			if (hero.hardcore) {
		%>
		- <strong class="d3-color-hardcore">Hardcore</strong>
		<%
			}

					if (hero.seasonal) {
		%>
		- <strong class="d3-color-seasonal">Seasonal</strong>
		<%
			}
		%>
	</h2>
	<br />
	<ul class="gear-slots">
		<%
			for (Map.Entry<String, ItemInformation> e : hero.items
							.entrySet()) {

						String loc = e.getKey();
						ItemInformation i = e.getValue();
		%>
		<li class="slot-<%=loc%>"><a class="slot-link"
			onClick="javascript:window.open('json?<%=JsonServlet.REALM%>=<%=realm.name()%>&<%=JsonServlet.ITEM%>=<%=URLEncoder.encode(i.tooltipParams, "UTF-8")%>','_blank'); return false;"
			href="http://us.battle.net/d3/en/itemData/<%=i.tooltipParams%>">
				<span class="d3-icon d3-icon-item d3-icon-item-<%=i.displayColor%>">
					<span class="icon-item-gradient"> <span
						class="icon-item-inner"></span>
				</span>
			</span> <span class="image"> <img
					src="http://media.blizzard.com/d3/icons/items/large/<%=i.icon%>.png"
					alt="" />
			</span> <%
 	if (i.gems != null) {
%>

 <span class="sockets-wrapper"> <span class="sockets-align">
 <% 		
 					for (ItemInformation.Gem g : i.gems) {
 %>
						<span class="socket"> <img class="gem"
							src="http://media.blizzard.com/d3/icons/items/small/<%=g.item.icon%>.png" />
					</span><br />
				
			<%
 			}
 %>
</span> </span> 

 <%
	}
 %>
		</a></li>
		<%
			}
		%>
	</ul>
	<span class="clear"><!-- --></span>
	
<div class="page-section kanai-cube">
			<div class="section-header ">

<h3 class="header-3" >Kanai's Cube Powers</h3>


			</div>

<table>
<col width="75px"/>
<col width="75px"/>
<col width="75px"/>
<col width="75px"/>
<col width="75px"/>
<col width="75px"/>
<tr>
<%
	if (hero.legendaryPowers != null) {
		for (HeroProfile.LegendaryPowers p : hero.legendaryPowers) {
			
			if (p != null) {
%>
<td align="center">
<a href="http://us.battle.net/d3/en/<%= p.tooltipParams %>">
<img src="http://media.blizzard.com/d3/icons/items/large/<%= p.icon %>.png"></img></a>
</td>
<td></td>
<%
			} else {
%>
<td align="center">
</td>
<td></td>
<%
			}
		}
%>
</tr>
</table>
</div>
<%
	}
%>

	<%
		}
		} catch (Exception e) {
			Logger.getLogger("paperdoll.jsp").log(Level.SEVERE,
					"Exception", e);
	%>
	Exception on server. Check log for details.
	<br />
	<input type="submit" value="Close Window"
		onClick="javascript:window.close();" />
	<%
		}
	%>
</body>
</html>