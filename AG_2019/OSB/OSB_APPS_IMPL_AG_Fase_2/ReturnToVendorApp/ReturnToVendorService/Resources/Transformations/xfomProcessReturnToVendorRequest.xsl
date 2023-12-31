<xsl:stylesheet version="1.0" exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl ns0 UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:ns0="http://www.agarcia.mx/EnterpriseObjects/FulfillmentOrder" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:ns1="http://www.agarcia.mx/EnterpriseObjects/Custom">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/FulfillmentOrder/FulfillmentOrderEBM.xsd"/>
        <oracle-xsl-mapper:rootElement name="ProcessFulfillmentOrderEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/FulfillmentOrder"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/FulfillmentOrder/FulfillmentOrderEBM.xsd"/>
        <oracle-xsl-mapper:rootElement name="ProcessFulfillmentOrderEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/FulfillmentOrder"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [TUE SEP 08 20:27:55 CDT 2020].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <ns0:ProcessFulfillmentOrderEBM>
      <ns0:Header>
        <com:TrackingID>
          <xsl:value-of select="/ns0:ProcessFulfillmentOrderEBM/ns0:Header/com:TrackingID"/>
        </com:TrackingID>
        <com:Target>
          <com:Applications>
            <com:Name>RIB</com:Name>
          </com:Applications>
        </com:Target>
        <com:Operation>ProcessReturnToVendor</com:Operation>
        <com:Interface>ReturnToVendorService</com:Interface>
        <com:RequestDateTime>
          <xsl:value-of select="xp20:format-dateTime (xp20:current-dateTime(), &quot;[Y001]-[M01]-[D01]T[h01]:[m01]:[s01]&quot; )"/>
        </com:RequestDateTime>
      </ns0:Header>
      <ns0:DataArea>
        <ns0:ProcessFulfillmentOrder>
          <ns0:Identification>
            <com:ID>
              <xsl:value-of select="/ns0:ProcessFulfillmentOrderEBM/ns0:DataArea/ns0:ProcessFulfillmentOrder/ns0:Identification/com:ID"/>
            </com:ID>
          </ns0:Identification>
          <ns0:ProcessID>
            <xsl:value-of select="/ns0:ProcessFulfillmentOrderEBM/ns0:DataArea/ns0:ProcessFulfillmentOrder/ns0:ProcessID"/>
          </ns0:ProcessID>
          <ns0:LocationId>
            <xsl:value-of select="/ns0:ProcessFulfillmentOrderEBM/ns0:DataArea/ns0:ProcessFulfillmentOrder/ns0:LocationId"/>
          </ns0:LocationId>
          <ns0:Supplier>
            <com:Identification>
              <com:ID>
                <xsl:value-of select="/ns0:ProcessFulfillmentOrderEBM/ns0:DataArea/ns0:ProcessFulfillmentOrder/ns0:Supplier/com:Identification/com:ID"/>
              </com:ID>
            </com:Identification>
          </ns0:Supplier>
          <xsl:for-each select="/ns0:ProcessFulfillmentOrderEBM/ns0:DataArea/ns0:ProcessFulfillmentOrder/ns0:FulfillmentOrderLine">
            <ns0:FulfillmentOrderLine>
              <ns0:SKU>
                <xsl:value-of select="ns0:SKU"/>
              </ns0:SKU>
              <ns0:Quantity>
                <xsl:value-of select="ns0:Quantity"/>
              </ns0:Quantity>
            </ns0:FulfillmentOrderLine>
          </xsl:for-each>
          <xsl:for-each select="/ns0:ProcessFulfillmentOrderEBM/ns0:DataArea/ns0:ProcessFulfillmentOrder/ns0:Comments">
            <ns0:Comments>
              <xsl:value-of select="."/>
            </ns0:Comments>
          </xsl:for-each>
        </ns0:ProcessFulfillmentOrder>
        <ns0:FulfillmentOrderRecordHistory>
          <com:CreationDate>
            <xsl:value-of select="xp20:format-dateTime (xp20:current-dateTime(), &quot;[Y001]-[M01]-[D01]T[h01]:[m01]:[s01]&quot; )"/>
          </com:CreationDate>
          <com:CreatedBy>JDA</com:CreatedBy>
          <com:LastUpdateDate>
            <xsl:value-of select="xp20:format-dateTime (xp20:current-dateTime(), &quot;[Y001]-[M01]-[D01]T[h01]:[m01]:[s01]&quot; )"/>
          </com:LastUpdateDate>
          <com:LastUpdatedBy>JDA</com:LastUpdatedBy>
        </ns0:FulfillmentOrderRecordHistory>
      </ns0:DataArea>
    </ns0:ProcessFulfillmentOrderEBM>
  </xsl:template>
</xsl:stylesheet>