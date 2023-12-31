<xsl:stylesheet version="1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:ns0="http://www.agarcia.mx/EnterpriseObjects/SupplierParty" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tns="http://TargetNamespace.com/SyncSupplierSAALMARestReference_Sync_request" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:cust="http://www.agarcia.mx/EnterpriseObjects/Custom" xmlns:nxsd="http://xmlns.oracle.com/pcbpel/nxsd">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/SupplierParty/SupplierPartyEBM.xsd"/>
            <oracle-xsl-mapper:rootElement name="SyncSupplierPartyEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/SupplierParty"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../XSD/inputSchema.xsd"/>
            <oracle-xsl-mapper:rootElement name="SupplierInput" namespace="http://TargetNamespace.com/SyncSupplierSAALMARestReference_Sync_request"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [WED DEC 02 14:31:05 CST 2020].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:SupplierInput>
         <xsl:if test="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:Identification/com:ID">
            <tns:code>
               <xsl:value-of select="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:Identification/com:ID"/>
            </tns:code>
         </xsl:if>
         <xsl:if test="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:OrganizationName">
            <tns:nombre>
               <xsl:value-of select="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:OrganizationName"/>
            </tns:nombre>
         </xsl:if>
         <xsl:if test="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactAddressCommunication/com:LineOne">
            <tns:direccion>
               <xsl:value-of select="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactAddressCommunication/com:LineOne"/>
            </tns:direccion>
         </xsl:if>
         <xsl:if test="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactAddressCommunication/com:CityName">
            <tns:ciudad>
               <xsl:value-of select="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactAddressCommunication/com:CityName"/>
            </tns:ciudad>
         </xsl:if>
         <xsl:if test="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactAddressCommunication/com:StateName">
            <tns:estado>
               <xsl:value-of select="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactAddressCommunication/com:StateName"/>
            </tns:estado>
         </xsl:if>
         <xsl:if test="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactAddressCommunication/com:PostalCode">
            <tns:cp>
               <xsl:value-of select="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactAddressCommunication/com:PostalCode"/>
            </tns:cp>
         </xsl:if>
         <xsl:if test="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactPhoneCommunication/com:DialNumber">
            <tns:telefono>
               <xsl:value-of select="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactPhoneCommunication/com:DialNumber"/>
            </tns:telefono>
         </xsl:if>
         <xsl:if test="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactFaxCommunication">
            <tns:fax>
               <xsl:value-of select="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactFaxCommunication"/>
            </tns:fax>
         </xsl:if>
         <xsl:if test="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactEmailCommunication">
            <tns:email>
               <xsl:value-of select="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:PartyContact/com:ContactEmailCommunication"/>
            </tns:email>
         </xsl:if>
         <xsl:if test="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:Status/com:Code">
            <tns:status>
               <xsl:value-of select="/ns0:SyncSupplierPartyEBM/ns0:DataArea/ns0:SyncSupplierParty/ns0:Status/com:Code"/>
            </tns:status>
         </xsl:if>
      </tns:SupplierInput>
   </xsl:template>
</xsl:stylesheet>