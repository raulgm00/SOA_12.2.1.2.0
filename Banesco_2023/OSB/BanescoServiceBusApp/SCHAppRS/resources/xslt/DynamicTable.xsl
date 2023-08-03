<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <dinamicRule>
            <rule id="ChkEnableAdd" tipo="atomic">
                <service>SCHAppSvc</service>
                <operation>ChkEnableAdd</operation>
                <schema>http://xmlns.banesco.com/appopt/SCHChkAppSvc/ChkEnableAdd_V1.0</schema>
                <auditRecord>true</auditRecord>
                <route_schema>Commons/xsd/AppOpt/SCHChk/ChkEnableAdd/ChkEnableAdd_V1.0</route_schema>
                <transformation_reception_to_canonical>SCHAppRS/resources/xslt/ChkEnableAdd/xslt_in_ChkEnableAdd_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>SCHAppRS/resources/xslt/ChkEnableAdd/xslt_out_ChkEnableAdd_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>AcctTS/proxy/PX_Chk_V1.0</component_target>
            </rule>
            <rule id="ChkChargeAdd" tipo="atomic">
                <service>SCHAppSvc</service>
                <operation>ChkChargeAdd</operation>
                <schema>http://xmlns.banesco.com/appopt/SCHChkAppSvc/ChkChargeAdd_V1.0</schema>
                <auditRecord>true</auditRecord>
                <route_schema>Commons/xsd/AppOpt/SCHChk/ChkChargeAdd/ChkChargeAdd_V1.0</route_schema>
                <transformation_reception_to_canonical>SCHAppRS/resources/xslt/ChkChargeAdd/xslt_in_ChkChargeAdd_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>SCHAppRS/resources/xslt/ChkChargeAdd/xslt_out_ChkChargeAdd_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>AcctTS/proxy/PX_Chk_V1.0</component_target>
            </rule> 
            <rule id="ChkAdd" tipo="atomic">
                <service>SCHChkAppSvc</service>
                <operation>ChkAdd</operation>
                <schema>http://xmlns.banesco.com/appopt/SCHChkAppSvc/ChkAdd_V1.0</schema>
                <auditRecord>true</auditRecord>
                <route_schema>Commons/xsd/AppOpt/SCHChk/ChkAdd/ChkAdd_V1.0</route_schema>
                <transformation_reception_to_canonical>SCHAppRS/resources/xslt/ChkAdd/xslt_in_ChkAddRq_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>SCHAppRS/resources/xslt/ChkAdd/xslt_out_ChkAddRq_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>AcctTS/proxy/PX_Chk_V1.0</component_target>
            </rule>  
        </dinamicRule>
    </xsl:template>
</xsl:stylesheet>