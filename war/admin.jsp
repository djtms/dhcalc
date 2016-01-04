<%@ page import="com.dawg6.web.dhcalc.server.ClientBuffer"%>
<%@ page import="com.dawg6.web.dhcalc.server.IO"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.dawg6.d3api.server.*"%>
<%@ page import="com.dawg6.d3api.shared.*"%>
<%@ page import="com.dawg6.web.dhcalc.server.util.ServerUtils"%>
<%@ page import="com.dawg6.d3api.server.Cache"%>
<%@ page import="com.dawg6.web.dhcalc.server.db.couchdb.CouchDBDHCalcParameters"%>
<%@ page import="com.dawg6.web.dhcalc.server.db.couchdb.CouchDBDHCalcDatabase"%>
<%@ page import="com.dawg6.web.dhcalc.server.db.couchdb.ItemDocument"%>
<%@ page import="com.dawg6.web.dhcalc.server.db.couchdb.SetDocument"%>
<%@ page import="com.dawg6.web.dhcalc.server.db.couchdb.AccountDocument"%>
<%@ page import="com.dawg6.web.dhcalc.server.DHCalcServiceImpl"%>

<%
	Runtime runtime = Runtime.getRuntime();
	double Kb = 1024;
	double Mb = Kb * 1024;
	double Gb = Mb * 1024;
	
	String clear = request.getParameter("clear");
	String clearItemCache = request.getParameter("");

	if ((clear != null) && (clear.trim().length() > 0)) {

		if (clear.equals("all")) {
	ClientBuffer.getInstance().clear();
		} else if (clear.equals("itemCache")) {
	IO.getInstance().clearItemCache();
		} else if (clear.equals("parameterCache")) {
	CouchDBDHCalcParameters.getInstance().reloadCache();
		} else {
	ClientBuffer.getInstance().get(clear);
		}
%>
<script>
	location = location;
</script>
<%
	}
%>
<html>
<meta http-equiv="expires" content="-1">
<link rel="shortcut icon" href="demonhunter_female.ico?v1" type="image/x-icon">
<link rel="icon" href="demonhunter_female.ico?v1" type="image/x-icon">
<title>DH DPS Calculator Admin page</title>
<body>
	Production Mode: <%=ServerUtils.isProductionMode()%>
	<br/>
	<br/>
	Client Cache:
	<%
		if (ClientBuffer.getInstance().inspect().size() > 0) {
	%>
	<form action="admin.jsp" method="post">
		<input type="hidden" name="clear" value="all" /> <input type="submit"
			name="submit" value="Clear" />
	</form>
	<%
		}
	%>

	<br />
	<br />
	<table border="0">
		<tr>
			<td>JVM Max Memory:</td>
			<td><%=DHCalcServiceImpl.DECIMAL_FORMAT.format((double) runtime
					.maxMemory() / Mb)%>Mb</td>
		</tr>
		<tr>
			<td>JVM Used Memory:</td>
			<td><%=DHCalcServiceImpl.DECIMAL_FORMAT.format((double) runtime
					.totalMemory() / Mb)%>Mb</td>
		</tr>
		<tr>
			<td>JVM Free Memory:</td>
			<td><%=DHCalcServiceImpl.DECIMAL_FORMAT.format((double) runtime
					.freeMemory() / Mb)%>Mb</td>
		</tr>
	</table>
	<br />
	<br />
	<table border="1">
		<tr>
			<th>Key</th>
			<th>Type</th>
			<th>Action</th>
		</tr>
		<%
			for (Map.Entry<String, Object> e : ClientBuffer.getInstance()
					.inspect().entrySet()) {
				String key = e.getKey();
				Object value = e.getValue();
				Class<?> type = (value != null) ? value.getClass() : null;
		%>
		<tr>
			<td><%=key%></td>
			<td><%=type%></td>
			<td>
				<form action="admin.jsp" method="post">
					<input type="hidden" name="clear" value="<%=key%>" /> <input
						type="submit" name="submit" value="Clear" />
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<br />
	<br />
	<form action="admin.jsp" method="post">
		<input type="hidden" name="clear" value="itemCache" /> Item Cache <input
			type="submit" name="submit" value="Clear" />
	</form>
	<table border="1">
		<%
			long hits = IO.getInstance().getCacheHits();
			long misses = IO.getInstance().getCacheMisses();
			long requests = IO.getInstance().getNumRequests();
			int accounts = CouchDBDHCalcDatabase.getInstance().findAll(AccountDocument.class).size(); 
			int items = CouchDBDHCalcDatabase.getInstance().findAll(ItemDocument.class).size(); 
			int setItems = CouchDBDHCalcDatabase.getInstance().findAll(SetDocument.class).size(); 
			long total = hits + misses;
			long errors = IO.getInstance().getErrors();
			long retryAttempts = IO.getInstance().getRetryAttempts();
			double hitPercent = 0.0;
			double missPercent = 0.0;

			if (total > 0) {
				hitPercent = (double) hits / (double) total;
				missPercent = 1.0 - hitPercent;
			}
		%>
		<col width="100px" />
		<col width="100px" />
		<col width="100px" />
		<tr>
			<td>Max Size:</td>
			<td colspan="2"><%=IO.getInstance().getItemCache().getMaxSize()%></td>
		</tr>
		<tr>
			<td>Current Size:</td>
			<td colspan="2"><%=IO.getInstance().getItemCache().size()%></td>
		</tr>
		<tr>
			<td>Hits:</td>
			<td><%=DHCalcServiceImpl.DECIMAL_FORMAT.format(hits)%></td>
			<td><%=DHCalcServiceImpl.DECIMAL_FORMAT.format(Math
					.round(hitPercent * 10000.0) / 100.0)%>%</td>
		</tr>
		<tr>
			<td>Misses:</td>
			<td><%=DHCalcServiceImpl.DECIMAL_FORMAT.format(misses)%></td>
			<td><%=DHCalcServiceImpl.DECIMAL_FORMAT.format(Math
					.round(missPercent * 10000.0) / 100.0)%>%</td>
		</tr>
		<tr>
			<td># Requests:</td>
			<td colspan="2"><%=requests%></td>
		</tr>
	</table>
	<br />
	<br />
	Statistical Data
	<table border="1">
		<col width="200px" />
		<col width="100px" />
		<tr>
			<td># Errors:</td>
			<td><%=errors%></td>
		</tr>
		<tr>
			<td># Retry Attempts:</td>
			<td><%=retryAttempts%></td>
		</tr>
		<tr>
			<td># Accounts:</td>
			<td><%=accounts%></td>
		</tr>
		<tr>
			<td># Item Types:</td>
			<td><%=items%></td>
		</tr>
		<tr>
			<td># Set Types:</td>
			<td><%=setItems%></td>
		</tr>
	</table>
	<br />
	<br />
	<form action="admin.jsp" method="post">
		<input type="hidden" name="clear" value="parameterCache" />
		Parametric Data <input type="submit" name="submit" value="Reload" />
	</form>
	<table border="1">
		<col width="150px" />
		<col width="150px" />
		<tr>
			<th>Key</th>
			<th>Value</th>
		</tr>
		<%
			Map<String, String> map = CouchDBDHCalcParameters.getInstance().copy();

			for (Map.Entry<String, String> e : map.entrySet()) {
		%>
		<tr>
			<td><%=e.getKey()%></td>
			<td><%=e.getValue()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>