<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="TrnPmtInq" tipo="atomic">
        <operation>TrnPmtInq</operation>
        <schema>http://xmlns.banesco.com/appopt/UtilityAppSvc/TrnPmtInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/Utility/TrnPmtInq/TrnPmtInq_V1.0</route_schema>
        <transformation_reception_to_canonical>UtilityRS/resources/xslt/TrnPmtInq/xslt_in_TrnPmtInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>UtilityRS/resources/xslt/TrnPmtInq/xslt_out_TrnPmtInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>UtilityTS/proxy/PX_Utility_V1.0</component_target>
      </rule>
      <rule id="PmtSessionInq" tipo="atomic">
        <operation>PmtSessionInq</operation>
        <schema>http://xmlns.banesco.com/appopt/UtilityAppSvc/PmtSessionInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/Utility/PmtSessionInq/PmtSessionInq_V1.0</route_schema>
        <transformation_reception_to_canonical>UtilityRS/resources/xslt/PmtSessionInq/xslt_in_PmtSessionInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>UtilityRS/resources/xslt/PmtSessionInq/xslt_out_PmtSessionInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>UtilityTS/proxy/PX_Utility_V1.0</component_target>
      </rule>
       <rule id="PartyOnlineBankInq" tipo="atomic">
        <operation>PartyOnlineBankInq</operation>
        <service>UtilityAppSvc</service>
        <schema>http://xmlns.banesco.com/appopt/UtilityAppSvc/PartyOnlineBankInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/Utility/PartyOnlineBankInq/PartyOnlineBankInq_V1.0</route_schema>
        <transformation_reception_to_canonical>UtilityRS/resources/xslt/PartyOnlineBankInq/xslt_in_PartyOnlineBankInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>UtilityRS/resources/xslt/PartyOnlineBankInq/xslt_out_PartyOnlineBankInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>UtilityTS/proxy/PX_Utility_V1.0</component_target>
      </rule>
      <rule id="OnlineBankTrnAdv" tipo="atomic">
        <operation>OnlineBankTrnAdv</operation>
        <schema>http://xmlns.banesco.com/appopt/UtilityAppSvc/OnlineBankTrnAdv_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/Utility/OnlineBankTrnAdv/OnlineBankTrnAdv_V1.0</route_schema>
        <transformation_reception_to_canonical>UtilityRS/resources/xslt/OnlineBankTrnAdv/xslt_in_OnlineBankTrnAdv_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>UtilityRS/resources/xslt/OnlineBankTrnAdv/xslt_out_OnlineBankTrnAdv_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>UtilitySvc/proxy/PX_UtilitySvc_V1.0</component_target>
      </rule>
      <rule id="AlertAdv" tipo="atomic">
        <operation>AlertAdv</operation>
        <service>UtilityAppSvc</service>
        <schema>http://xmlns.banesco.com/appopt/UtilityAppSvc/AlertAdv_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/Utility/AlertAdv/AlertAdv_V1.0</route_schema>
        <transformation_reception_to_canonical>UtilityRS/resources/xslt/AlertAdv/xslt_in_AlertAdv_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>UtilityRS/resources/xslt/AlertAdv/xslt_out_AlertAdv_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>UtilitySvc/proxy/PXL_UtilitySvc_V1.0</component_target>
      </rule>
      <rule id="TrnAuditInq" tipo="atomic">
        <operation>TrnAuditInq</operation>
        <service>UtilityAppSvc</service>
        <schema>http://xmlns.banesco.com/appopt/UtilityAppSvc/TrnAuditInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/Utility/TrnAuditInq/TrnAuditInq_V1.0</route_schema>
        <transformation_reception_to_canonical>UtilityRS/resources/xslt/TrnAuditInq/xslt_in_TrnAuditInqRq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>UtilityRS/resources/xslt/TrnAuditInq/xslt_out_TrnAuditInqRq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>UtilityTS/proxy/PX_Utility_V1.0</component_target>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>
