<xsl:stylesheet version="1.0" exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:tns="http://www.agarcia.mx/SAALMA/Ajustepiezasrecibidas" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns0="http://www.agarcia.mx/EnterpriseObjects/InventoryTransaction" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:ns1="http://www.agarcia.mx/EnterpriseObjects/Custom" xmlns:nxsd="http://xmlns.oracle.com/pcbpel/nxsd">
    <oracle-xsl-mapper:schema>
        <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
        <oracle-xsl-mapper:mapSources>
            <oracle-xsl-mapper:source type="XSD">
                <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/InventoryTransaction/InventoryTransactionEBM.xsd"/>
                <oracle-xsl-mapper:rootElement name="SyncInventoryTransactionEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/InventoryTransaction"/>
            </oracle-xsl-mapper:source>
        </oracle-xsl-mapper:mapSources>
        <oracle-xsl-mapper:mapTargets>
            <oracle-xsl-mapper:target type="XSD">
                <oracle-xsl-mapper:schema location="../Schemas/DeliveryQuantityAdjustmentRequest.xsd"/>
                <oracle-xsl-mapper:rootElement name="DeliveryQuantityAdjustmentRequest" namespace="http://www.agarcia.mx/SAALMA/Ajustepiezasrecibidas"/>
            </oracle-xsl-mapper:target>
        </oracle-xsl-mapper:mapTargets>
        <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [FRI NOV 06 18:00:27 CST 2020].-->
    </oracle-xsl-mapper:schema>
    <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
    <xsl:template match="/">
        <tns:DeliveryQuantityAdjustmentRequest>
            <xsl:for-each select="/ns0:SyncInventoryTransactionEBM/ns0:DataArea/ns0:SyncInventoryTransaction">
                <tns:AjustePiezasRecibidas>
                    <tns:local>
                        <xsl:value-of select="ns0:ShipFromPartyReference/com:LocationId"/>
                    </tns:local>
                    <tns:fechamovimiento>
                        <xsl:value-of select="/ns0:SyncInventoryTransactionEBM/ns0:Header/com:RequestDateTime"/>
                    </tns:fechamovimiento>
                    <xsl:for-each select="ns0:InventoryTransactionLine">
                        <tns:detalle>
                            <tns:sku>
                                <xsl:value-of select="ns0:Item"/>
                            </tns:sku>
                            <tns:transaccionqty>
                                <xsl:value-of select="ns0:TransferQuantity"/>
                            </tns:transaccionqty>
                            <tns:fechamoviemintod>
                                <xsl:value-of select="/ns0:SyncInventoryTransactionEBM/ns0:Header/com:RequestDateTime"/>
                            </tns:fechamoviemintod>
                        </tns:detalle>
                    </xsl:for-each>
                </tns:AjustePiezasRecibidas>
            </xsl:for-each>
        </tns:DeliveryQuantityAdjustmentRequest>
    </xsl:template>
</xsl:stylesheet>