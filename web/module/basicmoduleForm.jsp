<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<h2>PIH Malawi Customizations</h2>

<br/>
<a href="${pageContext.request.contextPath}/module/pihmalawi/register_artweeklyvisit.form">(Re) register ART Weekly Visit Report</a>
<a href="${pageContext.request.contextPath}/module/pihmalawi/remove_artweeklyvisit.form">Remove ART Weekly Visit Report</a>

<br/>
<a href="${pageContext.request.contextPath}/module/pihmalawi/register_artmissedappointment.form">(Re) register ART Missed Appointment Report</a>
<a href="${pageContext.request.contextPath}/module/pihmalawi/remove_artmissedappointment.form">Remove ART Missed Appointment Report</a>

<br/>
<a href="${pageContext.request.contextPath}/module/pihmalawi/register_partmissedappointment.form">(Re) register Pre-ART Missed Appointment Report</a>
<a href="${pageContext.request.contextPath}/module/pihmalawi/remove_partmissedappointment.form">Remove Pre-ART Missed Appointment Report</a>

<br/>
<a href="${pageContext.request.contextPath}/module/pihmalawi/register_hivweeklyoutcome.form">(Re) register HIV Weekly Outcome Report</a>
<a href="${pageContext.request.contextPath}/module/pihmalawi/remove_hivweeklyoutcome.form">Remove HIV Weekly Outcome Report</a>

<%@ include file="/WEB-INF/template/footer.jsp"%>
