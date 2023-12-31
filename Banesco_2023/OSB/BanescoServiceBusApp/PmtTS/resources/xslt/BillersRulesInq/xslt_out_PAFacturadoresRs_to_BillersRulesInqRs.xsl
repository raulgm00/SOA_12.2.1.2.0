<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:tns="http://xmlns.banesco.com/eopt/BillersRulesInq_V1.0" xmlns:ns0="http://xmlns.banesco.com/backend/PA_Facturadores" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns1="http://xmlns.banesco.com/eo/Banking_V1.0" xmlns:ns2="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:eoBill="http://xmlns.banesco.com/eo/Bill_V1.0"
                xmlns:eoCom="http://xmlns.banesco.com/eo/Common_V1.0">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/backends/ICS/resources/xsd/PA_Facturadores_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="PAFacturadoresRs" namespace="http://xmlns.banesco.com/backend/PA_Facturadores"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Payment/BillersRulesInq/BillersRulesInq_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="BillersRulesInqRs" namespace="http://xmlns.banesco.com/eopt/BillersRulesInq_V1.0"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [MON APR 30 16:12:01 COT 2018].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:BillersRulesInqRs>
         <xsl:choose>
            <xsl:when test="/ns0:PAFacturadoresRs/ns0:P_RECORDSET/ns0:Row">
               <tns:Bill>
                  <tns:Billers>
                     <eoBill:BillerId>
                        <xsl:value-of select="/ns0:PAFacturadoresRs/ns0:P_RECORDSET/ns0:Row/ns0:Column[@name='BILLERID']"/>
                     </eoBill:BillerId>              
                  </tns:Billers>
               </tns:Bill>
               <xsl:for-each select="/ns0:PAFacturadoresRs/ns0:P_RECORDSET/ns0:Row">
                  <tns:Field>
                     <xsl:if test="ns0:Column[@name='FIELDCALC']/text()">
                        <eoCom:FieldCalc>
                           <xsl:value-of select="ns0:Column[@name='FIELDCALC']"/>
                        </eoCom:FieldCalc>
                     </xsl:if>
                     <xsl:if test="ns0:Column[@name='FIELDID']/text()">
                        <eoCom:FieldId>
                           <xsl:value-of select="ns0:Column[@name='FIELDID']"/>
                        </eoCom:FieldId>
                     </xsl:if>                     
                     <xsl:if test="ns0:Column[@name='FIELDINQ']/text()">
                        <!--Sera varchar y solo se ingresaran 0,1 02-05-2018--> 
                        <!--Sera manejara Y y N en base de datos, 16-05-2018-->
                        <eoCom:FieldInq>                           
                           <xsl:value-of select="DVMFunctions:lookupValue ('Commons/dvm/ServiceProperties', 'CODE', ns0:Column[@name='FIELDINQ'], 'VALUE', '2')"/>
                           <!--xsl:value-of select="ns0:Column[@name='FIELDINQ']"/-->
                        </eoCom:FieldInq>
                     </xsl:if> 
                     <xsl:if test="ns0:Column[@name='FIELDLABEL']/text()">
                        <eoCom:FieldLabel>
                           <xsl:value-of select="ns0:Column[@name='FIELDLABEL']"/>
                        </eoCom:FieldLabel>
                     </xsl:if>  
                     <xsl:if test="ns0:Column[@name='FIELDTYPE']/text()">
                        <eoCom:FieldType>
                           <xsl:value-of select="ns0:Column[@name='FIELDTYPE']"/>
                        </eoCom:FieldType>
                     </xsl:if>  
                     <xsl:if test="ns0:Column[@name='FIELDXMLPROV']/text()">
                        <eoCom:FieldXmlProvider>
                           <xsl:value-of select="ns0:Column[@name='FIELDXMLPROV']"/>
                        </eoCom:FieldXmlProvider>
                     </xsl:if>  
                     <xsl:if test="ns0:Column[@name='LISTVALUES']/text()">
                        <eoCom:ListValues>
                           <xsl:value-of select="ns0:Column[@name='LISTVALUES']"/>
                        </eoCom:ListValues>
                     </xsl:if>                  
                     <xsl:if test="ns0:Column[@name='SERVICEFIELD']/text()">
                        <eoCom:ServiceField>
                           <xsl:value-of select="ns0:Column[@name='SERVICEFIELD']"/>
                        </eoCom:ServiceField>
                     </xsl:if>  
                     <xsl:if test="ns0:Column[@name='SERVICEOPER']/text()">
                        <eoCom:ServiceOper>
                           <xsl:value-of select="ns0:Column[@name='SERVICEOPER']"/>
                        </eoCom:ServiceOper>
                     </xsl:if>     
                     <eoCom:Restriction>                         
                        <xsl:if test="ns0:Column[@name='ISREADONLY']/text()">
                           <!--Sera varchar y solo se ingresaran 0,1 02-05-2018-->
                           <!--Sera manejara Y y N en base de datos, 16-05-2018-->
                           <eoCom:IsReadOnly>
                              <xsl:value-of select="DVMFunctions:lookupValue ('Commons/dvm/ServiceProperties', 'CODE', ns0:Column[@name='ISREADONLY'], 'VALUE', '2')"/>
                              <!--xsl:value-of select="ns0:Column[@name='ISREADONLY']"/!-->
                           </eoCom:IsReadOnly>
                        </xsl:if>                     
                        <xsl:if test="ns0:Column[@name='MAXLENGTH']/text()">
                           <eoCom:MaxLenght>
                              <xsl:value-of select="ns0:Column[@name='MAXLENGTH']"/>
                           </eoCom:MaxLenght>
                        </xsl:if>                    
                        <xsl:if test="ns0:Column[@name='MAXVAL']/text()">
                           <eoCom:MaxValue>
                              <xsl:value-of select="ns0:Column[@name='MAXVAL']"/>
                           </eoCom:MaxValue>
                        </xsl:if>                     
                        <xsl:if test="ns0:Column[@name='MINLENGTH']/text()">
                           <eoCom:MinLenght>
                              <xsl:value-of select="ns0:Column[@name='MINLENGTH']"/>
                           </eoCom:MinLenght>
                        </xsl:if>                    
                        <xsl:if test="ns0:Column[@name='MINVAL']/text()">
                           <eoCom:MinValue>
                              <xsl:value-of select="ns0:Column[@name='MINVAL']"/>
                           </eoCom:MinValue>
                        </xsl:if>                     
                     </eoCom:Restriction>
                  </tns:Field>
               </xsl:for-each>               
               <tns:Status>
                  <eoStatus:StatusCode>M0000</eoStatus:StatusCode>
                  <eoStatus:StatusDesc>SUCCESS</eoStatus:StatusDesc>                  
               </tns:Status>
            </xsl:when>
            <xsl:otherwise>
               <tns:Status>
                  <eoStatus:StatusCode>M0002</eoStatus:StatusCode>
                  <eoStatus:StatusDesc>SIN DATA ASOCIADA A LA CONSULTA</eoStatus:StatusDesc>                  
               </tns:Status>
            </xsl:otherwise>
         </xsl:choose>
      </tns:BillersRulesInqRs>
   </xsl:template>
</xsl:stylesheet>
