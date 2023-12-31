<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction"
                xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions"
                xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:ns0="http://xmlns.banesco.com/eopt/PmtCardCrPrepAdd_V1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction"
                exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl ns0 UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns2="http://xmlns.banesco.com/eo/Product_V1.0" xmlns:ns3="http://xmlns.banesco.com/eo/Acct_V1.0"
                xmlns:ns4="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:eoPmt="http://xmlns.banesco.com/eo/Pmt_V1.0" xmlns:ns5="http://xmlns.banesco.com/eo/Trn_V1.0"
                xmlns:eoChk="http://xmlns.banesco.com/eo/Chk_V1.0"
                xmlns:eoBank="http://xmlns.banesco.com/eo/Banking_V1.0" xmlns:ns1="http://xmlns.banesco.com/eo/Fee_V1.0"
                xmlns:eoCard="http://xmlns.banesco.com/eo/Card_V1.0" xmlns:ns6="http://xmlns.banesco.com/eo/Payee_V1.0"
                xmlns:ns7="http://xmlns.banesco.com/eo/Bill_V1.0" xmlns:ns8="http://xmlns.banesco.com/eo/Common_V1.0"
                xmlns:web="http://www.athservices.net/servicios/WebServiceSitioWeb">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Payment/PmtCardCrPrepAdd/PmtCardCrPrepAdd_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="PmtCardCrPrepAddRq"
                                       namespace="http://xmlns.banesco.com/eopt/PmtCardCrPrepAdd_V1.0"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets/>
    <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [MON JUL 02 16:23:26 GMT-05:00 2018].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <web:dispersionSaldos>
      <numeroTarjeta>
        <xsl:value-of select="/ns0:PmtCardCrPrepAddRq/ns0:Card/eoCard:CardKey/eoCard:CardNum"/>
      </numeroTarjeta>
      <localidad>
        <xsl:value-of select='DVMFunctions:lookupValue ("Commons/dvm/EvertecMapping", "T24", /ns0:PmtCardCrPrepAddRq/ns0:FIData/eoBank:Agency/eoBank:AgencyIdent, "AGENCIA","")'/>
      </localidad>
      <moneda>
        <xsl:value-of select='DVMFunctions:lookupValue ("Commons/dvm/EvertecMapping", "T24", /ns0:PmtCardCrPrepAddRq/ns0:Pmt/eoPmt:CurAmt/ns8:CurCode, "GENERAL", "840" )'/>
      </moneda>
      <codigoMovimiento>
      <xsl:value-of select='DVMFunctions:lookupValue ("Commons/dvm/EvertecMapping", "CODIGO", "CodigoMovimiento.Prepago", "GENERAL", "T5" )'/>
      </codigoMovimiento>
      <monto>
        <xsl:value-of select="/ns0:PmtCardCrPrepAddRq/ns0:Pmt/eoPmt:CurAmt/ns8:Amt"/>
      </monto>
      <numeroReferencia>0</numeroReferencia>
      <usuarioSiscard>
        <xsl:value-of select='DVMFunctions:lookupValue ("Commons/dvm/ServiceProperties", "CODE", "Evertec.User", "VALUE", "BAN00018")'/>
      </usuarioSiscard>
    </web:dispersionSaldos>
  </xsl:template>
</xsl:stylesheet>
