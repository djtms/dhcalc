<%@ page import="com.dawg6.web.sentry.shared.calculator.ActiveSkill"%>
<%@ page import="com.dawg6.web.sentry.shared.calculator.Rune"%>
<%@ page import="com.dawg6.web.sentry.shared.calculator.SkillType"%>

<html>
<script src="tooltips.js?v=12"></script>

<table>
<%
for (ActiveSkill s : ActiveSkill.values()) {
	if (s.getSkillType() != SkillType.NA) {
%>
<tr>
<td><a href="<%= s.getUrl() %>"><%= s.getLongName() %></a></td>
<%		
		for (Rune r : s.getRunes()) {
			
			if (r != Rune.None) {
				String url = s.getUrl() + "#" + r.getSlug() + "+";
%>
<td><a href="<%= url %>"><%= r.getLongName() %></a></td>
<% 			
			}			

		}
%>
</tr>
<%
	}
}
%>			
</table>
</html>