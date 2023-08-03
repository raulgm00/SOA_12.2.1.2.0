<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="AcctTrnInq" tipo="atomic">
        <operation>AcctTrnInq</operation>
        <schema>http://xmlns.banesco.com/eopt/AcctTrnInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctTrnInq/AcctTrnInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctTrnInq/xslt_in_AcctTrnInqRq_to_ConsultaMovHisRepoRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctTrnInq/xslt_out_ConsultaMovHisRepoRs_to_AcctTrnInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/ICS/business/BS_ICSDB_V1.0</bs_backend>
        <backend_operation>PAPJConsultaMovHis</backend_operation>
      </rule>
      <rule id="AcctStmtHisInq" tipo="atomic">
        <operation>AcctStmtHisInq</operation>
        <schema>http://xmlns.banesco.com/eopt/AcctStmtHisInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctStmtHisInq/AcctStmtHisInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctStmtHisInq/xslt_in_AcctStmtHisInqRq_to_ConsultarSaldoInicialRepoRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctStmtHisInq/xslt_out_ConsultarSaldoInicialRepoRs_to_AcctStmtHisInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/REPO/business/BS_Repo_V1.0</bs_backend>
        <backend_operation>ConsultarSaldoInicialRepo</backend_operation>
      </rule>
      <rule id="AcctStmtImgInq" tipo="atomic">
        <operation>AcctStmtImgInq</operation>
        <schema>http://xmlns.banesco.com/eopt/AcctStmtImgInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctStmtImgInq/AcctStmtImgInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctStmtImgInq/xslt_in_AcctStmtImgInqRq_to_sConsultarEstadoCuenta</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctStmtImgInq/xslt_out_sConsultarEstadoCuentaResponse_to_AcctStmtImgInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/ESCTA/business/BS_Escta_V1.0</bs_backend>
        <backend_operation>sConsultarEstadoCuenta</backend_operation>
      </rule>
      <rule id="AcctChargeAdd" tipo="atomic">
        <operation>AcctChargeAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctChargeAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctChargeAdd/AcctChargeAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctChargeAdd/xslt_in_AcctChargeAddRq_to_GenerateDebitNoteRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctChargeAdd/xslt_out_GenerateDebitNoteRs_to_AcctChargeAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_GenerateDebitNote_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanCalcAdd" tipo="atomic">
        <operation>AcctLoanCalcAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanCalcAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanCalcAdd/AcctLoanCalcAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanCalcAdd/xslt_in_AcctLoanCalcAddRq_to_BpaAASimulationRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanCalcAdd/xslt_out_BpaAASimulationRs_AcctLoanCalcAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaAASimulation_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PersonAcctInfoInq" tipo="atomic">
        <operation>PersonAcctInfoInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/PersonAcctInfoInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/PersonAcctInfoInq/PersonAcctInfoInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/PersonAcctInfoInq/xslt_in_PersonAcctInfoInqRq_to_BpaEnqAccountPNRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/PersonAcctInfoInq/xslt_out_BpaEnqAccountPNRs_to_PersonAcctInfoInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BPAENQACCOUNTPN_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="OrgAcctInfoInq" tipo="atomic">
        <operation>OrgAcctInfoInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/OrgAcctInfoInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/OrgAcctInfoInq/OrgAcctInfoInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/OrgAcctInfoInq/xslt_in_OrgAcctInfoInqRq_to_BpaEnqAccountPJRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/OrgAcctInfoInq/xslt_out_BpaEnqAccountPJRs_to_OrgAcctInfoInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BPAENQACCOUNTPJ_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanCalcInq" tipo="atomic">
        <operation>AcctLoanCalcInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanCalcInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanCalcInq/AcctLoanCalcInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanCalcInq/xslt_in_AcctLoanCalcInqRq_to_BpaAASimulationOutputRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanCalcInq/xslt_out_BpaAASimulationOutputRs_AcctLoanCalcInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaAASimulationOutput_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctSignatureGrpAdd" tipo="atomic">
        <operation>AcctSignatureGrpAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctSignatureGrpAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctSignatureGrpAdd/AcctSignatureGrpAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctSignatureGrpAdd/xslt_in_AcctSignatureGrpAddRq_to_WfSignGroupRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctSignatureGrpAdd/xslt_out_WfSignGroupRs_to_AcctSignatureGrpAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WfSignGroup_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctCondMobilAdd" tipo="atomic">
        <operation>AcctCondMobilAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctCondMobilAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctCondMobilAdd/AcctCondMobilAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctCondMobilAdd/xslt_in_AcctCondMobilAddRq_to_WSMANDATERq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctCondMobilAdd/xslt_out_WSMANDATERs_to_AcctCondMobilAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WSMANDATE_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctAdd" tipo="atomic">
        <operation>AcctAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctAdd/AcctAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctAdd/xslt_in_AcctAddRq_To_AAArrangementActivityRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctAdd/xslt_out_AAArrangementActivityRs_To_AcctAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_AAArrangementActivity_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctInfoAdd" tipo="atomic">
        <operation>AcctInfoAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctInfoAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctInfoAdd/AcctInfoAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctInfoAdd/xslt_in_AcctInfoAddRq_To_BanAAAdditionalInfoRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctInfoAdd/xslt_out_BanAAAdditionalInfoRs_To_AcctInfoAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BanAAAdditionalInfo_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="OrgAcctFixDepInq" tipo="atomic">
        <operation>OrgAcctFixDepInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctFixDepInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountDep/AcctFixDepInq/AcctFixDepInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/OrgAcctFixDepInq/xslt_in_OrgAcctFixDepInqRq_to_BpaNofEnqAADepositsPJRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/OrgAcctFixDepInq/xslt_out_BpaNofEnqAADepositsPJRs_To_OrgAcctFixDepInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaNofEnqAADepositsPJ_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PersonAcctFixDepInq" tipo="atomic">
        <operation>PersonAcctFixDepInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctFixDepInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountDep/AcctFixDepInq/AcctFixDepInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/PersonAcctFixDepInq/xslt_in_PersonAcctFixDepInqRq_to_BpaNofEnqAADepositsPNRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/PersonAcctFixDepInq/xslt_out_BpaNofEnqAADepositsPNRs_To_PersonAcctFixDepInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaNofEnqAADepositsPN_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctFixDepAdd" tipo="atomic">
        <operation>AcctFixDepAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctFixDepAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountDep/AcctFixDepAdd/AcctFixDepAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctFixDepAdd/xslt_in_AcctFixDepAddRq_To_AAArrangementActivityRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctFixDepAdd/xslt_out_AAArrangementActivityRs_To_AcctFixDepAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_AAArrangementActivity_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanLedgerAdd" tipo="atomic">
        <operation>AcctLoanLedgerAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanLedgerAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanLedgerAdd/AcctLoanLedgerAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanLedgerAdd/xslt_in_AcctLoanLedgerAddRq_To_WSBPACREATEFTACCOUNTINGRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanLedgerAdd/xslt_out_WSBPACREATEFTACCOUNTINGRs_To_AcctLoanLedgerAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WSBPACREATEFTACCOUNTING_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanStatusMod" tipo="atomic">
        <operation>AcctLoanStatusMod</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanStatusMod_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanStatusMod/AcctLoanStatusMod_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanStatusMod/xslt_in_AcctLoanStatusModRq_To_AAArrangementActivityAuthRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanStatusMod/xslt_out_AAArrangementActivityAuthRs_To_AcctLoanStatusModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_AAArrangementActivityAuth_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanPartyRelAdd" tipo="atomic">
        <operation>AcctLoanPartyRelAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanPartyRelAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanPartyRelAdd/AcctLoanPartyRelAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanPartyRelAdd/xslt_in_AcctLoanPartyRelAddRq_To_BpaParticipantsAssocClientRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanPartyRelAdd/xslt_out_BpaParticipantsAssocClientRs_To_AcctLoanPartyRelAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaParticipantsAssocClient_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="ChkOrdAdd" tipo="atomic">
        <operation>ChkOrdAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/ChkOrdAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Check/ChkOrdAdd/ChkOrdAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/ChkOrdAdd/xslt_in_ChkOrdAddRq_to_BpaCreateChqGerenciaRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/ChkOrdAdd/xslt_out_BpaCreateChqGerenciaRs_to_ChkOrdAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaCreateChqGerencia_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanAgreeInq" tipo="atomic">
        <operation>AcctLoanAgreeInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanAgreeInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanAgreeInq/AcctLoanAgreeInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanAgreeInq/xslt_in_AcctLoanAgreeInqRq_To_BpaAgreementConsultRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanAgreeInq/xslt_out_BpaAgreementConsultRs_To_AcctLoanAgreeInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaAgreementConsult_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="ChkOrdMod" tipo="atomic">
        <operation>ChkOrdMod</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/ChkOrdMod_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Check/ChkOrdMod/ChkOrdMod_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/ChkOrdMod/xslt_in_ChkOrdModRq_to_BpaMaintainChqGerenciaRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/ChkOrdMod/xslt_out_BpaMaintainChqGerenciaRs_to_ChkOrdModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaMaintainChqGerencia_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanAgreeAdd" tipo="atomic">
        <operation>AcctLoanAgreeAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanAgreeAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanAgreeAdd/AcctLoanAgreeAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanAgreeAdd/xslt_in_AcctLoanAgreeAddRq_To_BpaWSAcuerdoRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanAgreeAdd/xslt_out_BpaWSAcuerdoRs_To_AcctLoanAgreeAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaWSAcuerdos_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanAgreeMod" tipo="atomic">
        <operation>AcctLoanAgreeMod</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanAgreeMod_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanAgreeMod/AcctLoanAgreeMod_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanAgreeMod/xslt_in_AcctLoanAgreeModRq_To_BpaWSAcuerdoRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanAgreeMod/xslt_out_BpaWSAcuerdoRs_To_AcctLoanAgreeModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaWSAcuerdos_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanCollateralAdd" tipo="atomic">
        <operation>AcctLoanCollateralAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanCollateralAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanCollateralAdd/AcctLoanCollateralAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanCollateralAdd/xslt_in_AcctLoanCollateralAddRq_To_WSBPACREATEGARANTIARq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanCollateralAdd/xslt_out_WSBPACREATEGARANTIARs_To_AcctLoanCollateralAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WSBPACREATEGARANTIA_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanCollateralMod" tipo="atomic">
        <operation>AcctLoanCollateralMod</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanCollateralMod_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanCollateralMod/AcctLoanCollateralMod_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanCollateralMod/xslt_in_AcctLoanCollateralModRq_To_WSBPAMAINTAINGARANTIARq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanCollateralMod/xslt_out_WSBPAMAINTAINGARANTIARs_To_AcctLoanCollateralModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WSBPAMAINTAINGARANTIA_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="ChkImageInq" tipo="atomic">
        <operation>ChkImageInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/ChkImageInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Check/ChkImageInq/ChkImageInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/ChkImageInq/xslt_in_ChkImageInqRq_to_ConsultarCheque</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/ChkImageInq/xslt_out_ConsultarChequeResponse_ChkImageInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/ONBASE/business/BS_OnBase_EstadosCuenta_V1.0</bs_backend>
        <backend_operation>ConsultarCheque</backend_operation>
      </rule>
      <rule id="AcctStmtInq" tipo="atomic">
        <operation>AcctStmtInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctStmtInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctStmtInq/AcctStmtInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctStmtInq/xslt_in_AcctStmtInqRq_to_EstadoCuentasWsdlRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctStmtInq/xslt_out_EstadoCuentasWsdlRs_to_AcctStmtInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/ONBASE/business/BS_OnBase_EstadosCuenta_V1.0</bs_backend>
        <backend_operation>ConsultarEstadoCuenta</backend_operation>
      </rule>
      <rule id="AcctInq" tipo="atomic">
        <operation>AcctInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctInq/AcctInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctInq/xslt_in_AcctInqRq_to_BpaEnqAccCustomerRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctInq/xslt_out_BpaEnqAccCustomerRs_to_AcctInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BPAENQACCCUSTOMER_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="ChkEnableAdd" tipo="atomic">
        <operation>ChkEnableAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/ChkEnableAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Check/ChkEnableAdd/ChkEnableAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/ChkEnableAdd/xslt_in_ChkEnableAddRq_to_Bapa3mChqActiveRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/ChkEnableAdd/xslt_out_Bapa3mChqActiveRs_to_ChkEnableAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_Bapa3mChqActive_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanPartyRelMod" tipo="atomic">
        <operation>AcctLoanPartyRelMod</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanPartyRelMod_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanPartyRelMod/AcctLoanPartyRelMod_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanPartyRelMod/xslt_in_AcctLoanPartyRelModRq_To_BpaParticipantsAssocClientRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanPartyRelMod/xslt_out_BpaParticipantsAssocClientRs_To_AcctLoanPartyRelModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaParticipantsAssocClient_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanPartyInq" tipo="atomic">
        <operation>AcctLoanPartyInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanPartyInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanPartyInq/AcctLoanPartyInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanPartyInq/xslt_in_AcctLoanPartyInqRq_To_WsBpaCustLoanConsultRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanPartyInq/xslt_out_WsBpaCustLoanConsultRs_To_AcctLoanPartyInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WSBPACUSTLOANCONSULT_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanInq" tipo="atomic">
        <operation>AcctLoanInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanInq/AcctLoanInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanInq/xslt_in_AcctLoanInqRq_to_BpaWsLoanConsultByCustRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanInq/xslt_out_BpaWsLoanConsultByCustRs_To_AcctLoanInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaWsLoanConsultByCust_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="ChkChargeAdd" tipo="atomic">
        <operation>ChkChargeAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/ChkChargeAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Check/ChkChargeAdd/ChkChargeAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/ChkChargeAdd/xslt_in_ChkChargeAddRq_to_Bapa3MacChargeReqWsRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/ChkChargeAdd/xslt_out_Bapa3MacChargeReqWsRs_to_ChkChargeAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_Bapa3MacChargeReqWs_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanPrLetterAdd" tipo="atomic">
        <operation>AcctLoanPrLetterAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanPrLetterAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanPrLetterAdd/AcctLoanPrLetterAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanPrLetterAdd/xslt_in_AcctLoanPrLetterAddRq_To_MdDealBcmCartasPromesasWsRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanPrLetterAdd/xslt_out_MdDealBcmCartasPromesasWsRs_To_AcctLoanPrLetterAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_MdDealBcmCartasPromesas_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanPrLetterMod" tipo="atomic">
        <operation>AcctLoanPrLetterMod</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanPrLetterMod_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanPrLetterMod/AcctLoanPrLetterMod_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanPrLetterMod/xslt_in_AcctLoanPrLetterModRq_To_MdDealBcmCartasPromesasWsRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanPrLetterMod/xslt_out_MdDealBcmCartasPromesasWsRs_To_AcctLoanPrLetterModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_MdDealBcmCartasPromesas_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanInfoInq" tipo="atomic">
        <operation>AcctLoanInfoInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanInfoInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanInfoInq/AcctLoanInfoInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanInfoInq/xslt_in_AcctLoanInfoInqRq_To_BpaWSLoanConsultationRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanInfoInq/xslt_out_BpaWSLoanConsultationRs_To_AcctLoanInfoRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaWsLoanConsultation_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanInfoAdd" tipo="atomic">
        <operation>AcctLoanInfoAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanInfoAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanInfoAdd/AcctLoanInfoAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanInfoAdd/xslt_in_AcctLoanInfoAddRq_To_BanAAAdditionalInfoLoanRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanInfoAdd/xslt_out_BanAAAdditionalInfoLoanRs_To_AcctLoanInfoAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BanAAAdditionalInfoLoan_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanAdd" tipo="atomic">
        <operation>AcctLoanAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanAdd/AcctLoanAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanAdd/xslt_in_AcctLoanAddRq_to_BpaLoanCreateRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanAdd/xslt_out_BpaLoanCreateRs_To_AcctLoanAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaLoanCreate_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
       <rule id="ChkAdd" tipo="atomic">
        <operation>ChkAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/ChkAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Check/ChkAdd/ChkAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/ChkAdd/xslt_in_ChkAddRq_to_Bapa3mStockEntryInpWsRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/ChkAdd/xslt_out_Bapa3mStockEntryInpWsRs_to_ChkAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_Bapa3mStockEntryInpWs_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanAutoAdd" tipo="atomic">
        <operation>AcctLoanAutoAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanAutoAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanAutoAdd/AcctLoanAutoAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanAutoAdd/xslt_in_AcctLoanAutoAddRq_to_BpaLoanCreateRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanAutoAdd/xslt_out_BpaLoanCreateRs_to_AcctLoanAutoAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaLoanCreate_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctAddAdvInq" tipo="atomic">
        <operation>AcctAddAdvInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctAddAdvInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctAddAdvInq/AcctAddAdvInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctAddAdvInq/xslt_in_AcctAddAdvInqRq_to_WsBanEnq310AmlRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctAddAdvInq/xslt_out_WsBanEnq310AmlRs_To_AcctAddAdvInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WSBANENQ310AML_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
       <rule id="AcctAddAdv" tipo="atomic">
        <operation>AcctAddAdv</operation>
        <schema>http://xmlns.banesco.com/eopt/AcctAddAdv_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Account/AcctAddAdv/AcctAddAdv_V1.0</route_schema>
      </rule>
         <rule id="AdminTrnAdv" tipo="atomic">
        <operation>AdminTrnAdv</operation>
        <schema>http://xmlns.banesco.com/eopt/AdminTrnAdv_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Account/AdminTrnAdv/AdminTrnAdv_V1.0</route_schema>
      </rule>
       <rule id="AdminTrnAdvACHInq" tipo="atomic">
        <operation>AdminTrnAdvACHInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AdminTrnAdvInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AdminTrnAdvInq/AdminTrnAdvInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AdminTrnAdvACHInq/xslt_in_AdminTrnAdvACHInqRq_to_BanEnq318ACHAmlRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AdminTrnAdvACHInq/xslt_out_BanEnq318ACHAmlRs_to_AdminTrnAdvACHInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WSBANENQ318ACH_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AdminTrnAdvAAInq" tipo="atomic">
        <operation>AdminTrnAdvAAInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AdminTrnAdvInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AdminTrnAdvInq/AdminTrnAdvInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AdminTrnAdvAAInq/xslt_in_AdminTrnAdvAAInqRq_to_BanEnq318ArrActRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AdminTrnAdvAAInq/xslt_out_BanEnq318ArrActRs_to_AdminTrnAdvAAInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WSBANENQ318AAA_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctLoanMortAdd" tipo="atomic">
        <operation>AcctLoanMortAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctLoanMortAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/AccountLoan/AcctLoanMortAdd/AcctLoanMortAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctLoanMortAdd/xslt_in_AcctLoanMortAddRq_to_BpaLoanCreateRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctLoanMortAdd/xslt_out_BpaLoanCreateRs_to_AcctLoanMortAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaLoanCreate_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctTrnAddAdv" tipo="atomic">
        <operation>AcctTrnAddAdv</operation>
        <schema>http://xmlns.banesco.com/eopt/AcctTrnAddAdv_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Account/AcctTrnAddAdv/AcctTrnAddAdv_V1.0</route_schema>
      </rule>
      <rule id="AcctTrnAddAdvFTInq" tipo="atomic">
        <operation>AcctTrnAddAdvFTInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctTrnAddAdvInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctTrnAddAdvInq/AcctTrnAddAdvInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctTrnAddAdvFTInq/xslt_in_AcctTrnAddAdvFTInqRq_to_BanEnq345FtAmlRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctTrnAddAdvFTInq/xslt_out_BanEnq345FtAmlRs_to_AcctTrnAddAdvFTInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WSBANENQ345FTAML_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctTrnAddAdvTTInq" tipo="atomic">
        <operation>AcctTrnAddAdvTTInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctTrnAddAdvInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctTrnAddAdvInq/AcctTrnAddAdvInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctTrnAddAdvTTInq/xslt_in_AcctTrnAddAdvTTInqRq_to_BanEnq345TtAmlRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctTrnAddAdvTTInq/xslt_out_BanEnq345TtAmlRs_to_AcctTrnAddAdvTTInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WSBANENQ345TTAML_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="AcctTrnAddAdvACRInq" tipo="atomic">
        <operation>AcctTrnAddAdvACRInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/AcctTrnAddAdvInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Account/AcctTrnAddAdvInq/AcctTrnAddAdvInq_V1.0</route_schema>
        <transformation_canonical_to_backend>AcctTS/resources/xslt/AcctTrnAddAdvACRInq/xslt_in_AcctTrnAddAdvACRInqRq_to_BanEnq345AcrAmlRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>AcctTS/resources/xslt/AcctTrnAddAdvACRInq/xslt_out_BanEnq345AcrAmlRs_to_AcctTrnAddAdvACRInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_WSBANENQ345ACRAML_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>