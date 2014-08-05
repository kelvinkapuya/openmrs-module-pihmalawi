<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables_jui.css"/>
<openmrs:htmlInclude file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />

<openmrs:hasPrivilege privilege="View Patients">

    <script type="text/javascript" charset="utf-8">

        var patSearchTable;

        jQuery(document).ready(function() {

            patSearchTable = jQuery("#openmrsSearchTable").dataTable( {
                "sPaginationType": "full_numbers",
                bFilter: false,
                bJQueryUI: true,
                sDom: 't<"fg-button ui-helper-clearfix"ip><"ui-helper-clearfix"l>',
                oLanguage: {
                    "sInfo": omsgs.sInfoLabel,
                    "oPaginate": {"sFirst": omsgs.first, "sPrevious": omsgs.previous, "sNext": omsgs.next, "sLast": omsgs.last},
                    "sZeroRecords": omsgs.noMatchesFound,
                    "sInfoEmpty": " ",
                    "sLengthMenu": omsgs.showNumberofEntries
                }
            });

            // This creates a hyperlink from each row in the result table to the associated patient dashboard
            jQuery('#openmrsSearchTable').delegate('tbody > tr', 'click', function(event) {
                var rowNum = patSearchTable.fnGetPosition(this);
                var pId = patSearchTable.fnGetData(rowNum)[0];
                document.location.href = "${pageContext.request.contextPath}/patientDashboard.form?patientId="+pId;
            });

            jQuery("#patientSearchPhrase").keydown(function(event) {
                if(event.which == 13) {  // Enter key pressed
                    performSearch();
                }
            });

            jQuery("#patientSearchButton").click(function(event) {
                performSearch();
            });

            clearPatientSearchResults();
        });

        function clearPatientSearchResults() {
            patSearchTable.fnClearTable();
            jQuery("#patientSearchResultsSection").hide();
        }

        function performSearch() {
            var url = "${pageContext.request.contextPath}/module/pihmalawi/findMatchingPatients.form";
            var queryParams = new Object();
            queryParams.phrase = jQuery("#patientSearchPhrase").val();
            queryParams.soundexEnabled = jQuery("#soundexEnabledCheckbox").is(':checked');

            clearPatientSearchResults();

            if (queryParams.phrase) {
                jQuery.ajax({
                    url: url,
                    data: queryParams,
                    dataType: "json",
                    success: function (data) {

                        var dataRows = [];
                        if (data) {
                            for (rowNum in data) {
                                var p = data[rowNum];
                                dataRows.push([p.patientId, p.identifier, p.givenName, p.familyName, p.familyName2, p.age, p.gender, p.birthdateDisplay]);
                            }
                        }
                        patSearchTable.fnAddData(dataRows);
                        patSearchTable.fnSetColumnVis(0, false);
                        patSearchTable.fnDraw();
                        jQuery("#patientSearchResultsSection").show();
                    }
                });
            }
        }

    </script>

    <style>
        #searchTableHeader th {
            color:black; border: none; background: none;
        }

    </style>

    <div>
        <b class="boxHeader"><openmrs:message code="Patient.find"/></b>
        <div class="box">
            <div class="searchWidgetContainer">
                <openmrs:message code="Patient.searchBox" />
                <input type="text" id="patientSearchPhrase" name="phrase" size="35" placeholder="<spring:message code="Patient.searchBox.placeholder"/>"/>
                <input type="button" id="patientSearchButton" value="<openmrs:message code="general.searchButton" />" />
                &nbsp;&nbsp;
                <input type="checkbox" id="soundexEnabledCheckbox" name="soundexEnabled" value="true"/> Enable Soundex Search
            </div>
            <div id="patientSearchResultsSection" class="openmrsSearchDiv" style="display:none;">
                <table id="openmrsSearchTable" style="width:100%;">
                    <thead id="searchTableHeader">
                        <th class="hidden">Patient ID</th>
                        <th>Identifier</th>
                        <th>Given Name</th>
                        <th>Family Name</th>
                        <th>Family Name 2</th>
                        <th>Age</th>
                        <th>Gender</th>
                        <th>Birthdate</th>
                    </thead>
                    <tbody></tbody>
                    <tfoot></tfoot>
                </table>
            </div>
        </div>
    </div>
    <c:if test="${empty model.hideAddNewPatient}">
        <openmrs:hasPrivilege privilege="Add Patients">
            <br/> &nbsp; <openmrs:message code="general.or"/><br/><br/>
            <openmrs:portlet id="addPersonForm" url="addPersonForm" parameters="personType=patient|postURL=admin/person/addPerson.htm|viewType=shortEdit" />
        </openmrs:hasPrivilege>
    </c:if>

</openmrs:hasPrivilege>
