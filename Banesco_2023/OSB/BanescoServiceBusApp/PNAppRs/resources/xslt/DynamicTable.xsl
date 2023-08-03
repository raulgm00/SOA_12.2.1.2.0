<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:template match="/">
    <dinamicRule>      
      <rule id="PartyStaffInq" tipo="atomic">
        <operation>PartyStaffInq</operation>
        <schema>http://xmlns.banesco.com/appopt/PNPartyAppSvc/PartyStaffInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/PNParty/PartyStaffInq/PartyStaffInq_V1.0</route_schema>
        <transformation_reception_to_canonical>PNAppRs/resources/xslt/PartyStaffInq/xslt_in_PartyStaffInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>PNAppRs/resources/xslt/PartyStaffInq/xslt_out_PartyStaffInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="PartyInfoInq" tipo="atomic">
        <operation>PartyInfoInq</operation>
        <schema>http://xmlns.banesco.com/appopt/PNPartyAppSvc/PartyInfoInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/PNParty/PartyInfoInq/PartyInfoInq_V1.0</route_schema>
        <transformation_reception_to_canonical>PNAppRs/resources/xslt/PartyInfoInq/xslt_in_PartyInfoInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>PNAppRs/resources/xslt/PartyInfoInq/xslt_out_PartyInfoInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="PartyRefInq" tipo="atomic">
        <operation>PartyRefInq</operation>
        <schema>http://xmlns.banesco.com/appopt/PNPartyAppSvc/PartyRefInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/PNParty/PartyRefInq/PartyRefInq_V1.0</route_schema>
        <transformation_reception_to_canonical>PNAppRs/resources/xslt/PartyRefInq/xslt_in_PartyRefInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>PNAppRs/resources/xslt/PartyRefInq/xslt_out_PartyRefInq_canonical_to_reception</transformation_canonical_to_reception>
      </rule>
      <rule id="PartyRefAdd" tipo="atomic">
        <operation>PartyRefAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/PNPartyAppSvc/PartyRefAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/PNParty/PartyRefAdd/PartyRefAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>PNAppRs/resources/xslt/PartyRefAdd/xslt_in_PartyRefAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>PNAppRs/resources/xslt/PartyRefAdd/xslt_out_PartyRefAdd_canonical_to_reception</transformation_canonical_to_reception>
      </rule>
      <rule id="PartyRefMod" tipo="atomic">
        <operation>PartyRefMod</operation>
        <schema>http://xmlns.banesco.com/appopt/PNPartyAppSvc/PartyRefMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/PNParty/PartyRefMod/PartyRefMod_V1.0</route_schema>
        <transformation_reception_to_canonical>PNAppRs/resources/xslt/PartyRefMod/xslt_in_PartyRefMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>PNAppRs/resources/xslt/PartyRefMod/xslt_out_PartyRefMod_canonical_to_reception</transformation_canonical_to_reception>
      </rule>
      <rule id="AcctCondMobilAdd" tipo="atomic">
        <operation>AcctCondMobilAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/PNAcctAppSvc/AcctCondMobilAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/PNAcct/AcctCondMobilAdd/AcctCondMobilAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>PNAppRs/resources/xslt/AcctCondMobilAdd/xslt_in_AcctCondMobilAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>PNAppRs/resources/xslt/AcctCondMobilAdd/xslt_out_AcctCondMobilAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_Acct_V1.0</component_target>
      </rule>
      <rule id="AcctAdd" tipo="atomic">
        <operation>AcctAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/PNAcctAppSvc/AcctAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/PNAcct/AcctAdd/AcctAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>PNAppRs/resources/xslt/AcctAdd/xslt_in_AcctAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>PNAppRs/resources/xslt/AcctAdd/xslt_out_AcctAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_Acct_V1.0</component_target>
      </rule>
      <rule id="PartyAdd" tipo="atomic">
        <operation>PartyAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/PNPartyAppSvc/PartyAdd_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/PNParty/PartyAdd/PartyAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>PNAppRs/resources/xslt/PartyAdd/xslt_in_PartyAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>PNAppRs/resources/xslt/PartyAdd/xslt_out_PartyAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
    </dinamicRule>    
  </xsl:template>
</xsl:stylesheet>
