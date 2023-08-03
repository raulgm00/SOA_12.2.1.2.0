<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="EmpPartyInq" tipo="atomic">
        <operation>EmpPartyInq</operation>
        <service>T24PartyAppSvc</service>
        <schema>http://xmlns.banesco.com/appopt/T24PartyAppSvc/EmpPartyInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24Party/EmpPartyInq/EmpPartyInq_V1.0</route_schema>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/EmpPartyInq/xslt_in_EmpPartyInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>T24AppRS/resources/xslt/EmpPartyInq/xslt_out_EmpPartyInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="AcctTrnInq" tipo="atomic">
        <operation>AcctTrnInq</operation>
        <schema>http://xmlns.banesco.com/appopt/T24AcctAppSvc/AcctTrnInq_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24Acct/AcctTrnInq/AcctTrnInq_V1.0</route_schema>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/AcctTrnInq/xslt_in_AcctTrnInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>T24AppRS/resources/xslt/AcctTrnInq/xslt_out_AcctTrnInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctSvc/proxy/PX_AcctSvc_V1.0</component_target>
      </rule>
      <rule id="AcctStmtInq" tipo="atomic">
        <operation>AcctStmtInq</operation>
        <schema>http://xmlns.banesco.com/appopt/T24AcctAppSvc/AcctStmtInq_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24Acct/AcctStmtInq/AcctStmtInq_V1.0</route_schema>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/AcctStmtInq/xslt_in_AcctStmtInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>T24AppRS/resources/xslt/AcctStmtInq/xslt_out_AcctStmtInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_Acct_V1.0</component_target>
      </rule>
      <rule id="CardAcctRelInq" tipo="atomic">
        <operation>CardAcctRelInq</operation>
        <schema>http://xmlns.banesco.com/appopt/T24CardAppSvc/CardAcctRelInq_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24Card/CardAcctRelInq/CardAcctRelInq_V1.0</route_schema>
      </rule>
      <rule id="CardAcctTrnInq" tipo="atomic">
        <operation>CardAcctTrnInq</operation>
        <schema>http://xmlns.banesco.com/appopt/T24CardAppSvc/CardAcctTrnInq_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24Card/CardAcctTrnInq/CardAcctTrnInq_V1.0</route_schema>
      </rule>
      <rule id="CardStatusMod" tipo="atomic">
        <operation>CardStatusMod</operation>
        <schema>http://xmlns.banesco.com/appopt/T24CardAppSvc/CardStatusMod_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24Card/CardStatusMod/CardStatusMod_V1.0</route_schema>
      </rule>
      <rule id="PmtCardAdd" tipo="atomic">
        <operation>PmtCardAdd</operation>
        <service>T24PmtAppSvc</service>
        <schema>http://xmlns.banesco.com/appopt/T24PmtAppSvc/PmtCardAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24Pmt/PmtCardAdd/PmtCardAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/PmtCardAdd/xslt_in_PmtCardAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>T24AppRS/resources/xslt/PmtCardAdd/xslt_out_PmtCardAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PmtSvc/proxy/PXL_PmtSvc_V1.0</component_target>
      </rule>
      <rule id="PmtCardRev" tipo="atomic">
        <operation>PmtCardRev</operation>
        <schema>http://xmlns.banesco.com/appopt/T24PmtAppSvc/PmtCardRev_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24Pmt/PmtCardRev/PmtCardRev_V1.0</route_schema>
      </rule>
      <rule id="CardDbStatusHTMod" tipo="atomic">
        <operation>CardDbStatusHTMod</operation>
        <service>T24CardDbAppSvc</service>
        <schema>http://xmlns.banesco.com/appopt/T24CardDbAppSvc/CardDbStatusHTMod_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24CardDb/CardDbStatusHTMod/CardDbStatusHTMod_V1.0</route_schema>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/CardDbStatusHTMod/xslt_in_CardDbStatusHTMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>T24AppRS/resources/xslt/CardDbStatusHTMod/xslt_out_CardDbStatusHTMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>CardTS/proxy/PX_CardDb_V1.0</component_target>
      </rule>
        <rule id="CardCrFinancingAdd" tipo="atomic">
        <operation>CardCrFinancingAdd</operation>
        <service>T24CardCrAppSvc</service>
        <schema>http://xmlns.banesco.com/appopt/T24CardCrAppSvc/CardCrFinancingAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24CardCr/CardCrFinancingAdd/CardCrFinancingAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/CardCrFinancingAdd/xslt_in_CardCrFinancingAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>T24AppRS/resources/xslt/CardCrFinancingAdd/xslt_out_CardCrFinancingAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>CardTS/proxy/PX_CardCr_V1.0</component_target>
      </rule>   
      <rule id="CardCrCashAdd" tipo="atomic">
        <operation>CardCrCashAdd</operation>
        <service>T24CardTrnAppSvc</service>
        <schema>http://xmlns.banesco.com/appopt/T24CardTrnAppSvc/CardCrCashAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24CardTrn/CardCrCashAdd/CardCrCashAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/CardCrCashAdd/xslt_in_CardCrCashAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>T24AppRS/resources/xslt/CardCrCashAdd/xslt_out_CardCrCashAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>CardTS/proxy/PX_CardCr_V1.0</component_target>
      </rule>
      <rule id="CardCrInq" tipo="atomic">
        <operation>CardCrInq</operation>
        <service>T24CardCrAppSvc</service>
        <schema>http://xmlns.banesco.com/appopt/T24CardCrAppSvc/CardCrInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24CardCr/CardCrInq/CardCrInq_V1.0</route_schema>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/CardCrInq/xslt_in_CardCrInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>T24AppRS/resources/xslt/CardCrInq/xslt_out_CardCrInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>CardSvc/proxy/PX_CardCrSvc_V1.0</component_target>
      </rule>
      <rule id="CardCrInfoInq" tipo="atomic">
        <operation>CardCrInfoInq</operation>
        <service>T24CardCrAppSvc</service>
        <schema>http://xmlns.banesco.com/appopt/T24CardCrAppSvc/CardCrInfoInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/T24CardCr/CardCrInfoInq/CardCrInfoInq_V1.0</route_schema>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/CardCrInfoInq/xslt_in_CardCrInfoInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>T24AppRS/resources/xslt/CardCrInfoInq/xslt_out_CardCrInfoInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>CardSvc/proxy/PX_CardCrSvc_V1.0</component_target>
      </rule>
      <rule id="Acct1018Adv" tipo="atomic">
        <operation>Acct1018Adv</operation>
        <service>T24Acct1018AdvAppSvc</service>
        <auditRecord>true</auditRecord>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/Acct1018Adv/xslt_in_Acct1018Adv_reception_to_canonical</transformation_reception_to_canonical>
        <component_target>AcctSvc/proxy/PX_Acct1018Adv_V1.0</component_target>
      </rule>
      <rule id="AdminTrnACHAdv" tipo="atomic">
        <operation>AdminTrnACHAdv</operation>
        <service>T24AdminTrnACHAdvAppSvc</service>
        <auditRecord>true</auditRecord>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/AdminTrnACHAdv/xslt_in_AdminTrnACHAdv_reception_to_canonical</transformation_reception_to_canonical>
        <component_target>AcctSvc/proxy/PX_AdminTrnACHAdv_V1.0</component_target>
      </rule>
      <rule id="AcctTrnAddFTAdv" tipo="atomic">
        <operation>AcctTrnAddFTAdv</operation>
        <service>T24AcctTrnAddFTAdvAppSvc</service>
        <auditRecord>true</auditRecord>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/AcctTrnAddFTAdv/xslt_in_AcctTrnAddFTAdv_reception_to_canonical</transformation_reception_to_canonical>
        <component_target>AcctSvc/proxy/PX_AcctTrnAddAdv_V1.0</component_target>
      </rule>
      <rule id="AcctTrnAddTTAdv" tipo="atomic">
        <operation>AcctTrnAddTTAdv</operation>
        <service>T24AcctTrnAddTTAdvAppSvc</service>
        <auditRecord>true</auditRecord>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/AcctTrnAddTTAdv/xslt_in_AcctTrnAddTTAdv_reception_to_canonical</transformation_reception_to_canonical>
        <component_target>AcctSvc/proxy/PX_AcctTrnAddAdv_V1.0</component_target>
      </rule>
       <rule id="AcctTrnAddACRAdv" tipo="atomic">
        <operation>AcctTrnAddACRAdv</operation>
        <service>T24AcctTrnAddACRAdvAppSvc</service>
        <auditRecord>true</auditRecord>
        <transformation_reception_to_canonical>T24AppRS/resources/xslt/AcctTrnAddACRAdv/xslt_in_AcctTrnAddACRAdv_reception_to_canonical</transformation_reception_to_canonical>
        <component_target>AcctSvc/proxy/PX_AcctTrnAddAdv_V1.0</component_target>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>