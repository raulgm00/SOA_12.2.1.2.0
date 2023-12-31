<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction"
                xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction"
                xmlns:tns="http://xmlns.banesco.com/eopt/AdminTrnAdvInq_V1.0"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions"
                xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction"
                xmlns:ns0="http://www.temenos.com/T24/ofs/Response"
                exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns1="http://www.temenos.com/T24/ofs/BANENQ318ACHAMLType"
                xmlns:rescns="http://www.temenos.com/T24/ofs/ResponseCommon"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/backends/T24/resources/xsd/WSBANENQ318ACH/CONSULTATrama318ACHResponse.xsd"/>
        <oracle-xsl-mapper:rootElement name="BANENQ318ACHAMLResponse"
                                       namespace="http://www.temenos.com/T24/ofs/Response"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Account/AdminTrnAdvInq/AdminTrnAdvInq_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="AdminTrnAdvACHInqRs"
                                       namespace="http://xmlns.banesco.com/eopt/AdminTrnAdvInq_V1.0"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [THU SEP 06 15:42:09 COT 2018].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:variable name="statusCode"
        select='DVMFunctions:lookupValue ("Commons/dvm/StatusMappingBcknd", "T24", /ns0:BANENQ318ACHAMLResponse/ResponseCommon/Status, "CNCODE", "M0006" )'/>
  <xsl:template match="/">
    <tns:AdminTrnAdvACHInqRs>
      <tns:Status>
        <eoStatus:StatusCode>
              <xsl:value-of select="$statusCode"/>
            </eoStatus:StatusCode>
               <eoStatus:StatusDesc>
                   <xsl:value-of select='DVMFunctions:lookupValue ("Commons/dvm/StatusMappingBcknd", "T24", /ns0:BANENQ318ACHAMLResponse/ResponseCommon/Status, "CNDESC", "FALLO EN LA OPERACION" )'/>
               </eoStatus:StatusDesc>
            <eoStatus:AdditionalStatus>
               <eoStatus:StatusCode>
                  <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/ResponseCommon/Status"/>
               </eoStatus:StatusCode>
               <eoStatus:StatusDesc>
                  <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/ResponseCommon/Message"/>
               </eoStatus:StatusDesc>
            </eoStatus:AdditionalStatus>
      </tns:Status>
      <xsl:for-each select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType">
        <tns:Records>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/EstatusCtaoProducto/text()">
            <tns:AcctStatus>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/EstatusCtaoProducto"/>
            </tns:AcctStatus>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/SaldoDisponible/text()">
            <tns:AvailAmt>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/SaldoDisponible"/>
            </tns:AvailAmt>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/SignoValor/text()">
            <tns:AvailAmtSign>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/SignoValor"/>
            </tns:AvailAmtSign>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/TipodeSucursal/text()">
            <tns:BranchType>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/TipodeSucursal"/>
            </tns:BranchType>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/CanaloMedio/text()">
            <tns:Channel>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/CanaloMedio"/>
            </tns:Channel>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/TipodeChequera/text()">
            <tns:ChkBookType>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/TipodeChequera"/>
            </tns:ChkBookType>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/CodClienteRIMOrigen/text()">
            <tns:CustomerId>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/CodClienteRIMOrigen"/>
            </tns:CustomerId>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/NombreClienteTitularCtaOrigen/text()">
            <tns:CustomerName>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/NombreClienteTitularCtaOrigen"/>
            </tns:CustomerName>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/FlagEmpleadoCtaOrigen/text()">
            <tns:EmployeeAcct>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/FlagEmpleadoCtaOrigen"/>
            </tns:EmployeeAcct>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/TipodeEmpleado/text()">
            <tns:EmployeeType>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/TipodeEmpleado"/>
            </tns:EmployeeType>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/NoChequeFinal/text()">
            <tns:FinalChk>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/NoChequeFinal"/>
            </tns:FinalChk>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/NoChequeInicial/text()">
            <tns:InitChk>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/NoChequeInicial"/>
            </tns:InitChk>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/TipodeNovedad/text()">
            <tns:NewTypeCode>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/TipodeNovedad"/>
            </tns:NewTypeCode>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/FechaAperturaCuenta/text()">
            <tns:OpenAcctDate>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/FechaAperturaCuenta"/>
            </tns:OpenAcctDate>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/SucursalAperturaCuentaOrigen/text()">
            <tns:OpenBranch>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/SucursalAperturaCuentaOrigen"/>
            </tns:OpenBranch>
          </xsl:if>
          <xsl:if test="IDdeCliente/text()">
            <tns:PersonalId>
              <xsl:value-of select="IDdeCliente"/>
            </tns:PersonalId>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/ValorPosterioralCambio/text()">
            <tns:PostValChange>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/ValorPosterioralCambio"/>
            </tns:PostValChange>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/ValorAnterioralCambio/text()">
            <tns:PrevValChange>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/ValorAnterioralCambio"/>
            </tns:PrevValChange>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/CodigodeProducto/text()">
            <tns:ProductCode>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/CodigodeProducto"/>
            </tns:ProductCode>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/CodigodeSubproducto/text()">
            <tns:SubProdCode>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/CodigodeSubproducto"/>
            </tns:SubProdCode>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/NumerodeCuentaOrigen/text()">
            <tns:TxnAccount>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/NumerodeCuentaOrigen"/>
            </tns:TxnAccount>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/FechaTrx/text()">
            <tns:TxnDate>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/FechaTrx"/>
            </tns:TxnDate>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/Id/text()">
            <tns:TxnRef>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/Id"/>
            </tns:TxnRef>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/HoraTrx/text()">
            <tns:TxnTime>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/HoraTrx"/>
            </tns:TxnTime>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/CodigodeTransaccion/text()">
            <tns:TxnTypeCode>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/CodigodeTransaccion"/>
            </tns:TxnTypeCode>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/NombredeUsuario/text()">
            <tns:UserName>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/NombredeUsuario"/>
            </tns:UserName>
          </xsl:if>
          <xsl:if test="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/TipodeUsuario/text()">
            <tns:UserType>
              <xsl:value-of select="/ns0:BANENQ318ACHAMLResponse/BANENQ318ACHAML/gBANENQ318ACHAMLDetailType/mBANENQ318ACHAMLDetailType/TipodeUsuario"/>
            </tns:UserType>
          </xsl:if>
        </tns:Records>
      </xsl:for-each>
    </tns:AdminTrnAdvACHInqRs>
  </xsl:template>
</xsl:stylesheet>
