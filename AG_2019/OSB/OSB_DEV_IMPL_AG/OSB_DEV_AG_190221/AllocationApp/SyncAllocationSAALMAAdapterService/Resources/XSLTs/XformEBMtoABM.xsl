<xsl:stylesheet version="1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:tns="http://www.agarcia.mx/nxsd/SyncAllocationSaalmaReqMsg" xmlns:ns0="http://www.agarcia.mx/EnterpriseObjects/Allocation" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:ns1="http://www.agarcia.mx/EnterpriseObjects/PurchaseOrder" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:ns2="http://www.agarcia.mx/EnterpriseObjects/Custom" xmlns:nxsd="http://xmlns.oracle.com/pcbpel/nxsd">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/Allocation/AllocationEBM.xsd"/>
            <oracle-xsl-mapper:rootElement name="SyncAllocationEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/Allocation"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../NXSDs/nxsd_SyncAllocationSaalmaRequest.xsd"/>
            <oracle-xsl-mapper:rootElement name="Allocation" namespace="http://www.agarcia.mx/nxsd/SyncAllocationSaalmaReqMsg"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [TUE SEP 29 17:06:27 CDT 2020].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:Allocation>
         <tns:allocationId>
            <xsl:value-of select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:Identification/com:ID"/>
         </tns:allocationId>
         <tns:claveOS>
            <xsl:value-of select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:SupplierOrder/com:ID"/>
         </tns:claveOS>
         <tns:localOrigen>
            <xsl:value-of select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:Shipping/com:ShipFromPartyReference/com:LocationId"/>
         </tns:localOrigen>
         <xsl:for-each select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:Shipping/com:ShipToPartyReference">
         <tns:detalle>
            <tns:departamento>
               <xsl:value-of select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:Department/com:Identification/com:ID"/>
            </tns:departamento>
            <tns:dptoNombre>
               <xsl:value-of select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:Department/com:Name"/>
            </tns:dptoNombre>
            <tns:proveedor>
               <xsl:value-of select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:Supplier/com:Identification/com:ID"/>
            </tns:proveedor>
            <tns:provNombre>
               <xsl:value-of select="substring (/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:Supplier/com:Name, 0, 28 )"/>
            </tns:provNombre>
            <tns:estilo>
               <xsl:value-of select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:PurchaseOrderLine/ns1:Style"/>
            </tns:estilo>
            <tns:estiloDesc>
               <xsl:value-of select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:PurchaseOrderLine/ns1:ItemReference/com:ApplicationObjectReference/com:ApplicationName"/>
            </tns:estiloDesc>
            <tns:tienda>
               <xsl:value-of select="com:LocationId"/>
            </tns:tienda>
            <tns:tiendaNombre>
               <xsl:value-of select="com:Name"/>
            </tns:tiendaNombre>
            <tns:sku>
               <xsl:value-of select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:PurchaseOrderLine/ns1:SKU"/>
            </tns:sku>
            <tns:skuDesc>
               <xsl:value-of select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:PurchaseOrderLine/ns1:ItemDescription"/>
            </tns:skuDesc>
            <tns:cantidad>
               <xsl:value-of select="com:Description"/>
            </tns:cantidad>
            <tns:ubicacion/>
            <tns:ordenInventario/>
            <tns:prepack>
               <xsl:value-of select="/ns0:SyncAllocationEBM/ns0:DataArea/ns0:SyncAllocation/ns0:POAllocationEBO/ns1:Shipping/com:ShipmentItem/com:Prepack/com:Identification/com:ID"/>
            </tns:prepack>
         </tns:detalle>
         </xsl:for-each>
      </tns:Allocation>
   </xsl:template>
</xsl:stylesheet>