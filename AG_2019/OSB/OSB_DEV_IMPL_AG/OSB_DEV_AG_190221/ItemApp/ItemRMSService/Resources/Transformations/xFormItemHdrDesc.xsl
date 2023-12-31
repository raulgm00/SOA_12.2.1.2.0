<xsl:stylesheet version="1.0" exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:tns="http://www.agarcia.mx/EnterpriseObjects/Item" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:ns0="http://www.oracle.com/retail/integration/base/bo/ItemHdrDesc/v1" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:CustFlexAttriVo="http://www.oracle.com/retail/integration/base/bo/CustFlexAttriVo/v1" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:ns1="http://www.agarcia.mx/EnterpriseObjects/Custom">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../../../../MetaData/Components/Custom/Schemas/RMS_XSD/ItemHdrDesc.xsd"/>
        <oracle-xsl-mapper:rootElement name="ItemHdrDesc" namespace="http://www.oracle.com/retail/integration/base/bo/ItemHdrDesc/v1"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/Item/ItemEBM.xsd"/>
        <oracle-xsl-mapper:rootElement name="ProcessItemEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/Item"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [MON OCT 12 16:43:35 CDT 2020].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="InstanceID"/>
  <xsl:param name="TrackingID"/>
  <xsl:param name="SourceID"/>
  <xsl:param name="Interface"/>
  <xsl:param name="Operation"/>
  <xsl:param name="TargetApplication"/>
  <xsl:template match="/">
    <tns:ProcessItemEBM>
      <tns:Header>
        <com:TrackingID>
          <xsl:value-of select="$TrackingID"/>
        </com:TrackingID>
        <com:InstanceID>
          <xsl:value-of select="$InstanceID"/>
        </com:InstanceID>
        <com:SourceID>
          <xsl:value-of select="$SourceID"/>
        </com:SourceID>
        <com:Target>
          <com:Applications>
            <com:Name>
              <xsl:value-of select="$TargetApplication"/>
            </com:Name>
          </com:Applications>
        </com:Target>
        <com:Operation>
          <xsl:value-of select="$Operation"/>
        </com:Operation>
        <com:Interface>
          <xsl:value-of select="$Interface"/>
        </com:Interface>
      </tns:Header>
      <tns:DataArea>
        <tns:ProcessItem>
          <com:Identification>
            <com:ID>
              <xsl:value-of select="/ns0:ItemHdrDesc/ns0:item"/>
            </com:ID>
          </com:Identification>
          <tns:Department>
            <com:ID>
              <xsl:value-of select="/ns0:ItemHdrDesc/ns0:dept"/>
            </com:ID>
          </tns:Department>
          <tns:Class>
            <com:ID>
              <xsl:value-of select="/ns0:ItemHdrDesc/ns0:item_level"/>
            </com:ID>
          </tns:Class>
          <tns:SubClass>
            <com:ID>
              <xsl:value-of select="/ns0:ItemHdrDesc/ns0:tran_level"/>
            </com:ID>
          </tns:SubClass>
          <tns:Detail>
            <tns:Identification>
              <com:ID>
                <xsl:value-of select="/ns0:ItemHdrDesc/ns0:item_parent"/>
              </com:ID>
            </tns:Identification>
          </tns:Detail>
          <tns:Status>
            <com:Code>
              <xsl:value-of select="/ns0:ItemHdrDesc/ns0:status"/>
            </com:Code>
          </tns:Status>
          <tns:Supplier>
            <com:Identification>
              <com:ID>
                <xsl:value-of select="/ns0:ItemHdrDesc/ns0:supplier"/>
              </com:ID>
            </com:Identification>
          </tns:Supplier>
          <tns:Description>
            <xsl:value-of select="/ns0:ItemHdrDesc/ns0:item_desc"/>
          </tns:Description>
        </tns:ProcessItem>
      </tns:DataArea>
    </tns:ProcessItemEBM>
  </xsl:template>
</xsl:stylesheet>