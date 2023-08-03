<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <dinamicRule>
            <rule id="AcctTrnInq" tipo="atomic">
                <service>TCIBAcctAppSvc</service>
                <operation>AcctTrnInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBAcctAppSvc/AcctTrnInq_V1.0</schema>
                <route_schema>Commons/xsd/AppOpt/TCIBAcct/AcctTrnInq/AcctTrnInq_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/AcctTrnInq/xslt_in_AcctTrnInq_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/AcctTrnInq/xslt_out_AcctTrnInq_canonical_to_reception</transformation_canonical_to_reception>
            </rule>
            <rule id="AcctStmtImgInq" tipo="atomic">
                <operation>AcctStmtImgInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBAcctAppSvc/AcctStmtImgInq_V1.0</schema>
                <route_schema>Commons/xsd/AppOpt/TCIBAcct/AcctStmtImgInq/AcctStmtImgInq_V1.0</route_schema>
            </rule>
            <rule id="ChkOrdImgInq" tipo="atomic">
                <operation>ChkOrdImgInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBBankingAppSvc/ChkOrdImgInq_V1.0</schema>
                <route_schema>Commons/xsd/AppOpt/TCIBBanking/ChkOrdImgInq/ChkOrdImgInq_V1.0</route_schema>
            </rule>
            <rule id="CardAcctRelInq" tipo="atomic">
                <operation>CardAcctRelInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBCardAppSvc/CardAcctRelInq_V1.0</schema>
                <route_schema>Commons/xsd/AppOpt/TCIBCard/CardAcctRelInq/CardAcctRelInq_V1.0</route_schema>
            </rule>
            <rule id="CardAcctTrnInq" tipo="atomic">
                <operation>CardAcctTrnInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBCardAppSvc/CardAcctTrnInq_V1.0</schema>
                <route_schema>Commons/xsd/AppOpt/TCIBCard/CardAcctTrnInq/CardAcctTrnInq_V1.0</route_schema>
            </rule>
            <rule id="CardStatusMod" tipo="atomic">
                <operation>CardStatusMod</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBCardAppSvc/CardStatusMod_V1.0</schema>
                <route_schema>Commons/xsd/AppOpt/TCIBCard/CardStatusMod/CardStatusMod_V1.0</route_schema>
            </rule>
            <rule id="PmtCardAdd" tipo="atomic">
                <operation>PmtCardAdd</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBPmtAppSvc/PmtCardAdd_V1.0</schema>
                <route_schema>Commons/xsd/AppOpt/TCIBPmt/PmtCardAdd/PmtCardAdd_V1.0</route_schema>
            </rule>
            <rule id="PmtCardRev" tipo="atomic">
                <operation>PmtCardRev</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBPmtAppSvc/PmtCardRev_V1.0</schema>
                <route_schema>Commons/xsd/AppOpt/TCIBPmt/PmtCardRev/PmtCardRev_V1.0</route_schema>
            </rule>
            <rule id="BillersInfoInq" tipo="atomic">
                <operation>BillersInfoInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBPmtAppSvc/BillersInfoInq_V1.0</schema>
                <auditRecord>true</auditRecord>
                <route_schema>Commons/xsd/AppOpt/TCIBPmt/BillersInfoInq/BillersInfoInq_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/BillersInfoInq/xslt_in_BillersInfoInq_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/BillersInfoInq/xslt_out_BillersInfoInq_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>PmtSvc/proxy/PXL_PmtSvc_V1.0</component_target>
            </rule>
            <rule id="CardCrStatusMod" tipo="atomic">
                <operation>CardCrStatusMod</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBCardCrAppSvc/CardCrStatusMod_V1.0</schema>
                <auditRecord>false</auditRecord>
                <route_schema>Commons/xsd/AppOpt/TCIBCardCr/CardCrStatusMod/CardCrStatusMod_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/CardCrStatusMod/xslt_in_CardCrStatusMod_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/CardCrStatusMod/xslt_out_CardCrStatusMod_canonical_to_reception</transformation_canonical_to_reception>
            </rule>
            <rule id="CardDbInq" tipo="atomic">
                <operation>CardDbInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBCardDbAppSvc/CardDbInq_V1.0</schema>
                <auditRecord>false</auditRecord>
                <route_schema>Commons/xsd/AppOpt/TCIBCardDb/CardDbInq/CardDbInq_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/CardDbInq/xslt_in_CardDbInq_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/CardDbInq/xslt_out_CardDbInq_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>CardTS/proxy/PX_CardDb_V1.0</component_target>
            </rule>
             <rule id="BillersRulesInq" tipo="atomic">
                <operation>BillersRulesInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBPmtAppSvc/BillersRulesInq_V1.0</schema>
                <auditRecord>false</auditRecord>
                <route_schema>Commons/xsd/AppOpt/TCIBPmt/BillersRulesInq/BillersRulesInq_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/BillersRulesInq/xslt_in_BillersRulesInq_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/BillersRulesInq/xslt_out_BillersRulesInq_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>PmtTS/proxy/PX_Pmt_V1.0</component_target>
            </rule>
             <rule id="ChkImageInq" tipo="atomic">
                <service>TCIBChkAppSvc</service>
                <operation>ChkImageInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBChkAppSvc/ChkImageInq_V1.0</schema>
                <auditRecord>false</auditRecord>
                <route_schema>Commons/xsd/AppOpt/TCIBChk/ChkImageInq/ChkImageInq_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/ChkImageInq/xslt_in_ChkImageInq_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/ChkImageInq/xslt_out_ChkImageInq_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>AcctTS/proxy/PX_Chk_V1.0</component_target>
            </rule>
            <rule id="PmtUtilAdd" tipo="atomic">
                <service>TCIBPmtAppSvc</service>
                <operation>PmtUtilAdd</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBPmtAppSvc/PmtUtilAdd_V1.0</schema>
                <auditRecord>false</auditRecord>
                <route_schema>Commons/xsd/AppOpt/TCIBPmt/PmtUtilAdd/PmtUtilAdd_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/PmtUtilAdd/xslt_in_PmtUtilAdd_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/PmtUtilAdd/xslt_out_PmtUtilAdd_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>PmtSvc/proxy/PXL_PmtSvc_V1.0</component_target>
            </rule>
            <rule id="BillInq" tipo="atomic">
                <service>TCIBPmtAppSvc</service>
                <operation>BillInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBPmtAppSvc/BillInq_V1.0</schema>
                <auditRecord>true</auditRecord>
                <route_schema>Commons/xsd/AppOpt/TCIBPmt/BillInq/BillInq_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/BillInq/xslt_in_BillInq_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/BillInq/xslt_out_BillInq_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>PmtSvc/proxy/PXL_PmtSvc_V1.0</component_target>
            </rule>
            <rule id="CardCrTrnInq" tipo="atomic">
                <service>TCIBTrnAppSvc</service>
                <operation>CardCrTrnInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBCardAppSvc/CardAcctTrnInq_V1.0</schema>
                <auditRecord>true</auditRecord>
                <route_schema>Commons/xsd/AppOpt/TCIBCardCr/CardCrTrnInq/CardCrTrnInq_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/CardCrTrnInq/xslt_in_CardCrTrnInq_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/CardCrTrnInq/xslt_out_CardCrTrnInq_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>CardSvc/proxy/PX_CardCrSvc_V1.0</component_target>
            </rule>
             <rule id="CardCrFinancingInq" tipo="atomic">
                <service>TCIBCardCrAppSvc</service>
                <operation>CardCrFinancingInq</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBCardCrAppSvc/CardCrFinancingInq_V1.0</schema>
                <auditRecord>true</auditRecord>
                <route_schema>Commons/xsd/AppOpt/TCIBCardCr/CardCrFinancingInq/CardCrFinancingInq_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/CardCrFinancingInq/xslt_in_CardCrFinancingInq_to_BS_BPEL_CardCrFinancingInq</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/CardCrFinancingInq/xslt_out_BS_BPEL_CardCrFinancingInq_to_CardCrFinancingInq</transformation_canonical_to_reception>
                <targetBsService>true</targetBsService>
                <component_target>Commons/business/Bpel/business/BS_BPEL_CardCrFinancingInq</component_target>
            </rule>
            <rule id="CardValidInq" tipo="atomic">
                <operation>CardValidInq</operation>
                <service>TCIBCardAppSvc</service>
                <schema>http://xmlns.banesco.com/appopt/TCIBCardAppSvc/CardValidInq_V1.0</schema>
                <auditRecord>true</auditRecord>
                <route_schema>Commons/xsd/AppOpt/TCIBCard/CardValidInq/CardValidInq_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/CardValidInq/xslt_in_CardValidInq_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/CardValidInq/xslt_out_CardValidInq_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>CardTS/proxy/PX_Card_V1.0</component_target>
            </rule>
            <rule id="CardCrTransferAdd" tipo="atomic">
                <service>TCIBCardCrAppSvc</service>
                <operation>CardCrTransferAdd</operation>
                <schema>http://xmlns.banesco.com/appopt/TCIBCardCrAppSvc/CardCrTransferAdd_V1.0</schema>
                <auditRecord>true</auditRecord>
                <route_schema>Commons/xsd/AppOpt/TCIBCardCr/CardCrTransferAdd/CardCrTransferAdd_V1.0</route_schema>
                <transformation_reception_to_canonical>TCIBAppRS/resources/xslt/CardCrTransferAdd/xslt_in_CardCrTransferAdd_reception_to_canonical</transformation_reception_to_canonical>
                <transformation_canonical_to_reception>TCIBAppRS/resources/xslt/CardCrTransferAdd/xslt_out_CardCrTransferAdd_canonical_to_reception</transformation_canonical_to_reception>
                <component_target>CardTS/proxy/PX_CardCr_V1.0</component_target>
            </rule>
        </dinamicRule>
    </xsl:template>
</xsl:stylesheet>