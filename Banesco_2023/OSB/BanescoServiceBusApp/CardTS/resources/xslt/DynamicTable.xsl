<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
        <dinamicRule>
      <rule id="CardAcctRelInq" tipo="atomic">
        <operation>CardAcctRelInq</operation>
        <schema>http://xmlns.banesco.com/eopt/CardAcctRelInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Card/CardAcctRelInq/CardAcctRelInq_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardAcctRelInq/xslt_in_CardAcctRelInqRq_to_tdcCardAcctRelInqRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardAcctRelInq/xslt_out_tdcCardAcctRelInqRs_to_CardAcctRelInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/WAY4/business/BS_WAY4_V1.0</bs_backend>
        <backend_operation>tdcCardAcctRelInq</backend_operation>
      </rule>
      <rule id="CardActAdd" tipo="atomic">
        <operation>CardActAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/CardActAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Card/CardActAdd/CardActAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardActAdd/xslt_in_CardActAddRq_to_CardActivation</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardActAdd/xslt_out_CardActivationResponse_to_CardActAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_V1.0</bs_backend>
        <backend_operation>CardActivation</backend_operation>
      </rule>
      <rule id="CardAcctTrnInq" tipo="atomic">
        <operation>CardAcctTrnInq</operation>
        <schema>http://xmlns.banesco.com/eopt/CardAcctTrnInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Card/CardAcctTrnInq/CardAcctTrnInq_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardAcctTrnInq/xslt_in_CardAcctTrnInqRq_to_tdcCardAcctTrnInqRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardAcctTrnInq/xslt_out_tdcCardAcctTrnInqRs_to_CardAcctTrnInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/WAY4/business/BS_WAY4_V1.0</bs_backend>
        <backend_operation>tdcCardAcctTrnInq</backend_operation>
      </rule>
      <rule id="CardStatusMod" tipo="atomic">
        <operation>CardStatusMod</operation>
        <schema>http://xmlns.banesco.com/eopt/CardStatusMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Card/CardStatusMod/CardStatusMod_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardStatusMod/xslt_in_CardStatusModRq_to_CambiarEstatusTDDRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardStatusMod/xslt_out_CambiarEstatusTDDRs_to_CardStatusModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/WAY4/business/BS_WAY4_V1.0</bs_backend>
        <backend_operation>CardStatusMod</backend_operation>
      </rule>
      <rule id="CardCrStatusMod" tipo="atomic">
        <operation>CardCrStatusMod</operation>
        <schema>http://xmlns.banesco.com/eopt/CardCrStatusMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrStatusMod/CardCrStatusMod_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardCrStatusMod/xslt_in_CardCrStatusModRq_to_UpdateCardStatusRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardCrStatusMod/xslt_out_UpdateCardStatusRs_to_CardCrStatusModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/WAY4/business/BS_Way4_CardOutAPP_V1.0</bs_backend>
        <backend_operation>updateCardStatus</backend_operation>
      </rule>
      <rule id="CardDbHTAdd" tipo="atomic">
        <operation>CardDbHTAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/CardDbHTAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/CardDb/CardDbHTAdd/CardDbHTAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardDbHTAdd/xslt_in_CardDbHTAddRq_to_generateCardRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardDbHTAdd/xslt_out_generateCardRs_to_CardDbHTAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/HITECH/business/BS_HT_DebitCardCreationHandlerImplServices_V1.0</bs_backend>
        <backend>HiTech</backend>
        <backend_operation>generateCard</backend_operation>
      </rule>
      <rule id="CardDbT24Add" tipo="atomic">
        <operation>CardDbT24Add</operation>
        <schema>http://xmlns.banesco.com/eopt/CardDbT24Add_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/CardDb/CardDbT24Add/CardDbT24Add_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardDbT24Add/xslt_in_CardDbT24AddRq_to_GestionTDDRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardDbT24Add/xslt_out_GestionTDDRs_to_CardDbT24AddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_GestionTDD_V1.0</bs_backend>
        <backend>T24</backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="CardDbInq" tipo="atomic">
        <operation>CardDbInq</operation>
        <schema>http://xmlns.banesco.com/eopt/CardDbInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/CardDb/CardDbInq/CardDbInq_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardDbInq/xslt_in_CardDbInqRq_to_cardsQuerydRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardDbInq/xslt_out_cardsQueryRs_to_CardDbInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/HITECH/business/BS_HT_DebitCardQueryHandlerImpServices_V1.0</bs_backend>
        <backend>HiTech</backend>
        <backend_operation>cardsQuery</backend_operation>
      </rule>
      <rule id="CardDbStatusT24Mod" tipo="atomic">
        <operation>CardDbStatusT24Mod</operation>
        <schema>http://xmlns.banesco.com/eopt/CardDbStatusT24Mod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/CardDb/CardDbStatusT24Mod/CardDbStatusT24Mod_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardDbStatusT24Mod/xslt_in_CardDbStatusT24ModRq_to_GestionTDDRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardDbStatusT24Mod/xslt_out_GestionTDDRs_to_CardDbStatusT24ModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_GestionTDD_V1.0</bs_backend>
        <backend>T24</backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="CardDbStatusHTMod" tipo="atomic">
        <operation>CardDbStatusHTMod</operation>
        <schema>http://xmlns.banesco.com/eopt/CardDbStatusHTMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/CardDb/CardDbStatusHTMod/CardDbStatusHTMod_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardDbStatusHTMod/xslt_in_CardDbStatusHTModRq_to_ChangeStatusRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardDbStatusHTMod/xslt_out_ChangeStatusRs_to_CardDbStatusHTModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/HITECH/business/BS_HT_DebitCardStatusHandlerImplService_V1.0</bs_backend>
        <backend>HiTech</backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="CardCrFinancingAdd" tipo="atomic">
        <operation>CardCrFinancingAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/CardCrFinancingAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <requiresHeader>true</requiresHeader>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrFinancingAdd/CardCrFinancingAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardCrFinancingAdd/xslt_in_CardCrFinancingAddRq_to_mfl_EVERTEC1000Rq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardCrFinancingAdd/xslt_out_mfl_EVERTEC1010Rs_to_CardCrFinancingAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/EVERTEC/business/BS_EVERTECSOA_MQ_V1.0</bs_backend>
        <backend>EVERTEC</backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="CardCrTrnInq" tipo="atomic">
        <operation>CardCrTrnInq</operation>
        <schema>http://xmlns.banesco.com/eopt/CardCrTrnInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrTrnInq/CardCrTrnInq_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardCrTrnInq/xslt_in_CardCrTrnInqRq_to_ConsultaMovimientosRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardCrTrnInq/xslt_out_CardCrTrnInqRs_to_ConsultaMovimientosRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/EVERTEC/business/BS_EVERTEC_WSEVERTEC_V1.0</bs_backend>
        <backend>EVERTEC</backend>
        <backend_operation>consultaMovimientos</backend_operation>
      </rule>
      <rule id="CardCrTrnTransitInq" tipo="atomic">
        <operation>CardCrTrnTransitInq</operation>
        <schema>http://xmlns.banesco.com/eopt/CardCrTrnInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrTrnTransitInq/CardCrTrnTransitInq_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardCrTrnTransitInq/xslt_in_CardCrTrnTransitInqRq_to_consultaAutorizacionesTransitoRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardCrTrnTransitInq/xslt_out_consultaAutorizacionesTransitoRs_to_CardCrTrnTransitInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/EVERTEC/business/BS_EVERTEC_WSEVERTEC_V1.0</bs_backend>
        <backend>EVERTEC</backend>
        <backend_operation>consultaAutorizacionesTransito</backend_operation>
      </rule>
      <rule id="CardCrFinancingInq" tipo="atomic">
        <operation>CardCrFinancingInq</operation>
        <schema>http://xmlns.banesco.com/eopt/CardCrFinancingInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <requiresHeader>true</requiresHeader>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrFinancingInq/CardCrFinancingInq_V1.0</route_schema>
      </rule>
      <rule id="CardCrCashAdd" tipo="atomic">
        <operation>CardCrCashAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/CardCrCashAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <requiresHeader>true</requiresHeader>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrCashAdd/CardCrCashAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardCrCashAdd/xslt_in_CardCrCashAddRq_to_EVERTEC0400</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardCrCashAdd/xslt_out_EVERTEC0400_to_CardCrCashAddRs.xsl</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/EVERTEC/business/BS_EVERTECSOA_MQ_V1.0</bs_backend>
        <backend>EVERTEC</backend>
        <backend_operation>adelantoEfectivo</backend_operation>
      </rule>
      <rule id="CardCrPledgeAdd" tipo="atomic">
        <operation>CardCrPledgeAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/CardCrPledgeAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrPledgeAdd/CardCrPledgeAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardCrPledgeAdd/xslt_in_CardCrPledgeAddRq_To_BpaPignoracionTCRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardCrPledgeAdd/xslt_out_BpaPignoracionTCRs_To_CardCrPledgeAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaPignoracionTC_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctCardRelMod" tipo="atomic">
        <operation>AcctCardRelMod</operation>
        <schema>http://xmlns.banesco.com/eopt/AcctCardRelMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/CardDb/AcctCardRelMod/AcctCardRelMod_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/AcctCardRelMod/xslt_in_AcctCardRelModRq_to_GestionTDDRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/AcctCardRelMod/xslt_out_GestionTDDRs_to_AcctCardRelModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_GestionTDD_V1.0</bs_backend>
        <backend>T24</backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="CardCrTransferAdd" tipo="atomic">
        <operation>CardCrTransferAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/CardCrTransferAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrTransferAdd/CardCrTransferAdd_V1.0</route_schema>
        <requiresHeader>true</requiresHeader>
      </rule>
      <rule id="CardValidInq" tipo="atomic">
        <operation>CardValidInq</operation>
        <schema>http://xmlns.banesco.com/eopt/CardValidInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Card/CardValidInq/CardValidInq_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardValidInq/xslt_in_CardValidInqRq_to_SP_SeguridadEnAfiliacionRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardValidInq/xslt_out_SP_SeguridadEnAfiliacionRs_to_CardValidInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/SISCARDBD/business/BS_Siscardbd_V1.0</bs_backend>
        <backend_operation>Siscardbd</backend_operation>
      </rule>
      <rule id="CardCrPledgeMod" tipo="atomic">
        <operation>CardCrPledgeMod</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/CardCrPledgeMod_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrPledgeMod/CardCrPledgeMod_V1.0</route_schema>
        <transformation_canonical_to_backend>CardTS/resources/xslt/CardCrPledgeMod/xslt_in_CardCrPledgeModRq_To_BpaMaintainPignoracionTCRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardCrPledgeMod/xslt_out_BpaMaintainPignoracionTCRs_To_CardCrPledgeModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaMaintainPignoracionTC_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="CardCrInq" tipo="atomic">
        <operation>CardCrInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/CardCrInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrInq/CardCrInq_V1.0</route_schema>
        <requiresHeader>true</requiresHeader>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardCrInq/xslt_out_ConsultarCuentasTarjetaRs_To_CardCrInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/EVERTEC/business/BS_EVERTEC_SISCARD_V1.0</bs_backend>
        <backend_operation>consultaInformacionSiscard</backend_operation>
      </rule>
      <rule id="CardCrInfoInq" tipo="atomic">
        <operation>CardCrInfoInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/CardCrInfoInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrInfoInq/CardCrInfoInq_V1.0</route_schema>
        <transformation_backend_to_canonical>CardTS/resources/xslt/CardCrInfoInq/xslt_out_ConsultarCuentasTarjetaRs_To_CardCrInfoInqRs</transformation_backend_to_canonical>
        <requiresHeader>true</requiresHeader>
        <bs_backend>Commons/backends/EVERTEC/business/BS_EVERTEC_SISCARD_V1.0</bs_backend>
        <backend_operation>consultaInformacionSiscard</backend_operation>
      </rule>  
    </dinamicRule>
    </xsl:template>
</xsl:stylesheet>