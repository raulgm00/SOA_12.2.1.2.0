<xsl:stylesheet version="1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:ns0="http://www.agarcia.mx/EnterpriseObjects/MerchandiseHierarchy" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:ns1="http://www.agarcia.mx/EnterpriseObjects/Custom" xmlns:nxsd="http://xmlns.oracle.com/pcbpel/nxsd">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/MerchandiseHierarchy/MerchandiseHierarchyEBM.xsd"/>
            <oracle-xsl-mapper:rootElement name="SyncMerchandiseHierarchyEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/MerchandiseHierarchy"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/MerchandiseHierarchy/XSDtoJson.xsd"/>
            <oracle-xsl-mapper:rootElement name="Root-Element" namespace="http://www.agarcia.mx/EnterpriseObjects/MerchandiseHierarchy"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [SAT OCT 17 08:38:59 CDT 2020].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <ns0:Root-Element>
      <xsl:if test="/ns0:SyncMerchandiseHierarchyEBM/ns0:Header/com:Operation!='execute'">
            <ns0:replace>1</ns0:replace>
         </xsl:if>
         <xsl:for-each select="/ns0:SyncMerchandiseHierarchyEBM/ns0:DataArea/ns0:SyncMerchandiseHierarchy">
            <ns0:jerarquias_array>
               <ns0:marca_id>
                  <xsl:value-of select="ns0:Brand/com:ID"/>
               </ns0:marca_id>
               <ns0:marca>
                  <xsl:value-of select="ns0:Brand/com:ApplicationObjectReference/com:ApplicationName"/>
               </ns0:marca>
               <ns0:division_id>
                  <xsl:value-of select="ns0:Division/com:ID"/>
               </ns0:division_id>
               <ns0:division>
                  <xsl:value-of select="ns0:Division/com:ApplicationObjectReference/com:ApplicationName"/>
               </ns0:division>
               <ns0:departamento_id>
                  <xsl:value-of select="ns0:Department/com:ID"/>
               </ns0:departamento_id>
               <ns0:departamento>
                  <xsl:value-of select="ns0:Department/com:ApplicationObjectReference/com:ApplicationName"/>
               </ns0:departamento>
               <ns0:clase_id>
                  <xsl:value-of select="ns0:Class/com:ID"/>
               </ns0:clase_id>
               <ns0:clase>
                  <xsl:value-of select="ns0:Class/com:ApplicationObjectReference/com:ApplicationName"/>
               </ns0:clase>
               <ns0:tipo_id>
                  <xsl:value-of select="ns0:Type/com:ID"/>
               </ns0:tipo_id>
               <ns0:tipo>
                  <xsl:value-of select="ns0:Type/com:ApplicationObjectReference/com:ApplicationName"/>
               </ns0:tipo>
            </ns0:jerarquias_array>
         </xsl:for-each>
      </ns0:Root-Element>
   </xsl:template>
</xsl:stylesheet>