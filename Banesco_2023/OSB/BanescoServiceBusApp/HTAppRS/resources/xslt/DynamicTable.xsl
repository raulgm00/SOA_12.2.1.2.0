<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="AcctChargeAdd" tipo="atomic">
        <operation>AcctChargeAdd</operation>
        <service>HTAcctAppSvc</service>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/appopt/ETAlertAppSvc/AcctChargeAdd_V1.0</schema>
        <route_schema>Commons/xsd/AppOpt/HTAcct/AcctChargeAdd/AcctChargeAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>HTAppRS/resources/xslt/AcctChargeAdd/xslt_in_AcctChargeAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>HTAppRS/resources/xslt/AcctChargeAdd/xslt_out_AcctChargeAdd_reception_to_canonical</transformation_canonical_to_reception>
        <component_target>AcctSvc/proxy/PX_AcctSvc_V1.0</component_target>
      </rule>
      <rule id="PartyInfoInq" tipo="atomic">
        <service>HTPartyAppSvc</service>
        <operation>PartyInfoInq</operation>
        <schema>http://xmlns.banesco.com/appopt/HTPartyAppSvc/PartyInfoInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/HTParty/PartyInfoInq/PartyInfoInq_V1.0</route_schema>
        <transformation_reception_to_canonical>HTAppRS/resources/xslt/PartyInfoInq/xslt_in_PartyInfoInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>HTAppRS/resources/xslt/PartyInfoInq/xslt_out_PartyInfoInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="CardDbStatusT24Mod" tipo="atomic">
        <service>HTCardDbAppSvc</service>
        <operation>CardDbStatusT24Mod</operation>
        <schema>http://xmlns.banesco.com/appopt/HTCardDbAppSvc/CardDbStatusT24Mod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/HTCardDb/CardDbStatusT24Mod/CardDbStatusT24Mod_V1.0</route_schema>
        <transformation_reception_to_canonical>HTAppRS/resources/xslt/CardDbStatusT24Mod/xslt_in_CardDbStatusT24Mod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>HTAppRS/resources/xslt/CardDbStatusT24Mod/xslt_out_CardDbStatusT24Mod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>CardTS/proxy/PX_CardDb_V1.0</component_target>
      </rule>
      <rule id="AcctInfoInq" tipo="atomic">
        <operation>AcctInfoInq</operation>
        <service>HTAcctAppSvc</service>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/appopt/HTAcctAppSvc/AcctInfoInq_V1.0</schema>
        <route_schema>Commons/xsd/AppOpt/HTAcct/AcctInfoInq/AcctInfoInq_V1.0</route_schema>
        <transformation_reception_to_canonical>HTAppRS/resources/xslt/AcctInfoInq/xslt_in_AcctInfoInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>HTAppRS/resources/xslt/AcctInfoInq/xslt_out_AcctInfoInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctSvc/proxy/PX_AcctSvc_V1.0</component_target>
      </rule>
      <rule id="AcctCardRelMod" tipo="atomic">
        <operation>AcctCardRelMod</operation>
        <service>HTCardDbAppSvc</service>
        <schema>http://xmlns.banesco.com/appopt/HTCardDbAppSvc/AcctCardRelMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/HTCardDb/AcctCardRelMod/AcctCardRelMod_V1.0</route_schema>
        <transformation_reception_to_canonical>HTAppRS/resources/xslt/AcctCardRelMod/xslt_in_AcctCardRelMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>HTAppRS/resources/xslt/AcctCardRelMod/xslt_out_AcctCardRelMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>CardTS/proxy/PX_CardDb_V1.0</component_target>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>
