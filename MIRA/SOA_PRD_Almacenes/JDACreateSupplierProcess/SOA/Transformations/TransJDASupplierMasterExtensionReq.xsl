<?xml version = '1.0' encoding = 'UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:tns="http://mx.agarcia.ea/Applications/JDA/Supplier/CreateSupplier" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://mx.agarcia.ea/Capabilities/Foundation/Party/SupplierEntitySvc" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns mhdr oraext xp20 xref socket dvm oraxsl" xmlns:ns4="http://mx.agarcia.ea/Canonical/Commons/Languages/v1" xmlns:ns5="http://mx.agarcia.ea/Canonical/Commons/Types/v1" xmlns:ns1="http://mx.agarcia.ea/Capabilities/Core/Suppliers/JDACreateSupplierProcess" xmlns:ns8="http://mx.agarcia.ea/Canonical/Commons/Currencies/v1" xmlns:ns9="http://mx.agarcia.ea/Canonical/Commons/Countries/v1" xmlns:AGSH="http://mx.agarcia.ea/Commons/Schemas/AGStandardHeader" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:ns2="http://mx.agarcia.ea/Canonical/Commons/Enums/v1" xmlns:ns3="http://mx.agarcia.ea/Canonical/Commons/LocationsCommon/v1" xmlns:ns6="http://mx.agarcia.ea/Canonical/Commons/Suppliers/v1" xmlns:ns7="http://mx.agarcia.ea/Canonical/Commons/Items/v1" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:AGSM="http://mx.agarcia.ea/Commons/Schemas/AGStandardMessage" xmlns:plnk="http://docs.oasis-open.org/wsbpel/2.0/plnktype" xmlns:client="http://mx.agarcia.ea/Applications/JDA/Suppliers/CreateSupplier/v1.0">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="../WSDLs/JDACreateSupplierProcess.wsdl"/>
            <oracle-xsl-mapper:rootElement name="createSupplierRequest" namespace="http://mx.agarcia.ea/Capabilities/Foundation/Party/SupplierEntitySvc"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="WSDL">
            <oracle-xsl-mapper:schema location="http://192.1.50.179:7003/Applications/JDA/JDASupplier/PS/JDACreateSupplierAddressPXY/v1.0?wsdl"/>
            <oracle-xsl-mapper:rootElement name="createSupplierRequest" namespace="http://mx.agarcia.ea/Applications/JDA/Supplier/CreateSupplier"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [SAT MAY 25 22:47:41 CDT 2019].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:createSupplierRequest>
         <tns:supplier>
            <ns6:supplierID>
               <xsl:value-of select="/ns0:createSupplierRequest/ns0:suppliers/ns6:supplier/ns6:supplierID"/>
            </ns6:supplierID>
            <ns6:numVendor/>
            <ns6:nameVendor/>
            <ns6:website>
               <xsl:value-of select="/ns0:createSupplierRequest/ns0:suppliers/ns6:supplier/ns6:website"/>
            </ns6:website>
         </tns:supplier>
      </tns:createSupplierRequest>
   </xsl:template>
</xsl:stylesheet>