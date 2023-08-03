<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="TrnPmtInq" tipo="atomic">
        <operation>TrnPmtInq</operation>
        <schema>http://xmlns.banesco.com/eopt/TrnPmtInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Utility/TrnPmtInq/TrnPmtInq_V1.0</route_schema>
      </rule>
      <rule id="PmtSessionInq" tipo="atomic">
        <operation>PmtSessionInq</operation>
        <schema>http://xmlns.banesco.com/eopt/PmtSessionInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Utility/PmtSessionInq/PmtSessionInq_V1.0</route_schema>
      </rule>
      <rule id="PartyOnlineBankInq" tipo="atomic">
        <operation>PartyOnlineBankInq</operation>
        <schema>http://xmlns.banesco.com/eopt/PartyOnlineBankInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Utility/PartyOnlineBankInq/PartyOnlineBankInq_V1.0</route_schema>
      </rule>
      <rule id="ChannelTrnAudHistAdd" tipo="atomic">
        <operation>ChannelTrnAudHistAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/ChannelTrnAudHistAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Utility/ChannelTrnAudHistAdd/ChannelTrnAudHistAdd_V1.0</route_schema>
      </rule>
      <rule id="OnlineBankTrnAddAdvise" tipo="atomic">
        <operation>OnlineBankTrnAddAdvise</operation>
        <schema>http://xmlns.banesco.com/eopt/OnlineBankTrnAddAdvise_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Utility/OnlineBankTrnAddAdvise/OnlineBankTrnAddAdvise_V1.0</route_schema>
      </rule>
      <rule id="AdminOnlineBankAddAdvise" tipo="atomic">
        <operation>AdminOnlineBankAddAdvise</operation>
        <schema>http://xmlns.banesco.com/eopt/AdminOnlineBankAddAdvise_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Utility/AdminOnlineBankAddAdvise/AdminOnlineBankAddAdvise_V1.0</route_schema>
      </rule>
      <rule id="ChannelTrnAudHistInq" tipo="atomic">
        <operation>ChannelTrnAudHistInq</operation>
        <schema>http://xmlns.banesco.com/eopt/ChannelTrnAudHistInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Utility/ChannelTrnAudHistInq/ChannelTrnAudHistInq_V1.0</route_schema>
      </rule>
      <rule id="AlertSmsTTextoAdv" tipo="atomic">
        <operation>AlertSmsTTextoAdv</operation>
        <schema>http://xmlns.banesco.com/eopt/AlertSmsTTextoAdv_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Utility/AlertSmsTTextoAdv/AlertSmsTTextoAdv_V1.0</route_schema>
      </rule>
      <rule id="AlertSmsQuanticAdv" tipo="atomic">
        <operation>AlertSmsQuanticAdv</operation>
        <schema>http://xmlns.banesco.com/eopt/AlertSmsQuanticAdv_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Utility/AlertSmsQuanticAdv/AlertSmsQuanticAdv_V1.0</route_schema>
      </rule>
      <rule id="AlertEmailAdv" tipo="atomic">
        <operation>AlertEmailAdv</operation>
        <schema>http://xmlns.banesco.com/eopt/AlertEmailAdv_V1.0</schema>
        <auditRecord>true</auditRecord>
        <requiresHeader>true</requiresHeader>
        <route_schema>Commons/xsd/EOpt/Utility/AlertEmailAdv/AlertEmailAdv_V1.0</route_schema>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>
