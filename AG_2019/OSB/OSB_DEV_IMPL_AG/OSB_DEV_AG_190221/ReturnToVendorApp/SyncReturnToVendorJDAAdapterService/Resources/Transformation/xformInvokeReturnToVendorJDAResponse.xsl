<xsl:stylesheet version="1.0" exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:ns0="http://www.agarcia.mx/JDA/DevolucionAProveedor" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:tns="http://www.agarcia.mx/EnterpriseObjects/FulfillmentOrder" xmlns:nxsd="http://xmlns.oracle.com/pcbpel/nxsd" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:ns1="http://www.agarcia.mx/EnterpriseObjects/Custom">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../Schemas/ReturnToVendorJDAResponse.xsd"/>
        <oracle-xsl-mapper:rootElement name="DevolucionAProveedorResponse" namespace="http://www.agarcia.mx/JDA/DevolucionAProveedor"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/FulfillmentOrder/FulfillmentOrderEBM.xsd"/>
        <oracle-xsl-mapper:rootElement name="SyncFulfillmentOrderResponseEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/FulfillmentOrder"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [TUE NOV 24 09:44:40 CST 2020].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="Code"/>
  <xsl:param name="Detail"/>
  <xsl:param name="InstanceID"/>
  <xsl:param name="TrackingID"/>
  <xsl:param name="Operation"/>
  <xsl:template match="/">
    <tns:SyncFulfillmentOrderResponseEBM>
      <tns:Response>
        <com:Code>
          <xsl:value-of select="$Code"/>
        </com:Code>
        <com:Message>
          <xsl:value-of select="/ns0:DevolucionAProveedorResponse/ns0:Mensaje"/>
        </com:Message>
        <com:Detail>
          <xsl:value-of select="$Detail"/>
        </com:Detail>
        <com:InstanceID>
          <xsl:value-of select="$InstanceID"/>
        </com:InstanceID>
        <com:TrackingID>
          <xsl:value-of select="$TrackingID"/>
        </com:TrackingID>
        <com:Operation>
          <xsl:value-of select="$Operation"/>
        </com:Operation>
        <com:Interface>
          <xsl:value-of select="DVMFunctions:lookupValue (&quot;MetaData/dvm/RETURNTOVENDOR_CONFIGURATION_PROPERTIES.dvm&quot;, &quot;COMMON&quot;, &quot;ADAPTER_JDA&quot;, &quot;VALUE&quot;, &quot;NOT FOUND&quot; )"/>
        </com:Interface>
        <com:FaultMessage>
          <xsl:if test="string-length (/ns0:DevolucionAProveedorResponse/ns0:Code ) > 0">
            <com:Code>
              <xsl:value-of select="/ns0:DevolucionAProveedorResponse/ns0:Code"/>
            </com:Code>
          </xsl:if>
        </com:FaultMessage>
      </tns:Response>
      <tns:FulfillmentOrderRecordHistory>
        <com:CreationDate>
          <xsl:value-of select="/ns0:DevolucionAProveedorResponse/ns0:Fecha"/>
        </com:CreationDate>
        <com:CreatedBy>
          <xsl:value-of select="DVMFunctions:lookupValue (&quot;MetaData/dvm/RETURNTOVENDOR_CONFIGURATION_PROPERTIES.dvm&quot;, &quot;COMMON&quot;, &quot;SOURCE_S&quot;, &quot;VALUE&quot;, &quot;NOT FOUND&quot; )"/>
        </com:CreatedBy>
        <com:LastUpdateDate>
          <xsl:value-of select="/ns0:DevolucionAProveedorResponse/ns0:Fecha"/>
        </com:LastUpdateDate>
        <com:LastUpdatedBy>
          <xsl:value-of select="DVMFunctions:lookupValue (&quot;MetaData/dvm/RETURNTOVENDOR_CONFIGURATION_PROPERTIES.dvm&quot;, &quot;COMMON&quot;, &quot;SOURCE_S&quot;, &quot;VALUE&quot;, &quot;NOT FOUND&quot; )"/>
        </com:LastUpdatedBy>
      </tns:FulfillmentOrderRecordHistory>
    </tns:SyncFulfillmentOrderResponseEBM>
  </xsl:template>
</xsl:stylesheet>