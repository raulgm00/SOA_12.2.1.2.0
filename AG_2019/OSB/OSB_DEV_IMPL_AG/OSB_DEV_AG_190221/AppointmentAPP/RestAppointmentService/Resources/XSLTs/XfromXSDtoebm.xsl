<xsl:stylesheet version="1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:tns="http://www.agarcia.mx/EnterpriseObjects/Appointment" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:ns0="http://www.oracle.com/retail/integration/base/bo/AppointDesc/v1" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:ns1="http://www.agarcia.mx/EnterpriseObjects/Custom">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/Custom/Schemas/AppointDesc.xsd"/>
            <oracle-xsl-mapper:rootElement name="AppointDesc" namespace="http://www.oracle.com/retail/integration/base/bo/AppointDesc/v1"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/Appointment/AppointmentEBM.xsd"/>
            <oracle-xsl-mapper:rootElement name="ProccesAppointmentEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/Appointment"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [MON SEP 07 19:19:12 CDT 2020].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:ProccesAppointmentEBM>
         <tns:header>
            <com:TrackingID/>
            <com:InstanceID/>
            <com:User/>
            <com:SourceID/>
            <com:Target>
               <com:Applications>
                  <com:Name/>
                  <com:Code/>
                  <com:User/>
                  <com:Version/>
               </com:Applications>
            </com:Target>
            <com:Operation/>
            <com:Interface/>
            <com:BusinessUnit>
               <com:Identification>
                  <com:ID/>
               </com:Identification>
               <com:Name/>
               <com:Code/>
               <com:CountryName/>
               <com:CountryCode/>
            </com:BusinessUnit>
            <com:RequestDateTime/>
            <com:DateTimes/>
         </tns:header>
         <tns:dataArea>
            <tns:AppointmentEBO>
               <tns:from_location>
                  <com:LocationId>
                     <xsl:value-of select="/ns0:AppointDesc/ns0:from_location"/>
                  </com:LocationId>
                  <com:Name/>
                  <com:Description/>
                  <com:TypeCode/>
                  <com:UseCode/>
                  <com:Directions/>
                  <com:Address>
                     <com:Identification>
                        <com:ID/>
                     </com:Identification>
                     <com:LineOne/>
                     <com:LineTwo/>
                     <com:LineThree/>
                     <com:LineFour/>
                     <com:Building/>
                     <com:Floor/>
                     <com:StreetNumber/>
                     <com:Apartment/>
                     <com:StreetName/>
                     <com:BetweenStreets/>
                     <com:Area/>
                     <com:CityName/>
                     <com:StateName/>
                     <com:ProvinceName/>
                     <com:RuralRoute/>
                     <com:CountyName/>
                     <com:CountryName/>
                     <com:CountryCode/>
                     <com:CountrySubDivisionID/>
                     <com:CitySubDivisionName/>
                     <com:ProcessingCode/>
                     <com:PostalCode/>
                     <com:LongPostalCode/>
                     <com:GEOCodeID/>
                     <com:InCityLimitIndicator/>
                     <com:TimeZoneCode/>
                     <com:TypeCode/>
                     <com:SubTypeCode/>
                  </com:Address>
                  <com:GEOCodeID>
                     <com:ID/>
                  </com:GEOCodeID>
                  <com:TimeZoneIdentifier>
                     <com:ID/>
                  </com:TimeZoneIdentifier>
                  <com:GeographicalCoordinate>
                     <com:AltitudeMeasure/>
                     <com:LatitudeMeasure/>
                     <com:LongitudeMeasure/>
                     <com:LatitudeDirectionCode/>
                     <com:LongitudeDirectionCode/>
                     <com:SystemID/>
                  </com:GeographicalCoordinate>
               </tns:from_location>
               <tns:to_location>
                  <com:LocationId>
                     <xsl:value-of select="/ns0:AppointDesc/ns0:to_location"/>
                  </com:LocationId>
               </tns:to_location>
               <tns:appt_nbr>
                  <com:ID>
                     <xsl:value-of select="/ns0:AppointDesc/ns0:appt_nbr"/>
                  </com:ID>
               </tns:appt_nbr>
               <tns:appt_start_ts>
                  <xsl:value-of select="/ns0:AppointDesc/ns0:appt_start_ts"/>
               </tns:appt_start_ts>
               <tns:appt_action_status>
                  <xsl:value-of select="/ns0:AppointDesc/ns0:appt_action_status"/>
               </tns:appt_action_status>
               <tns:Desc>
                  <tns:item_id>
                     <com:ID>
                        <xsl:value-of select="/ns0:AppointDesc/ns0:AppointDtl/ns0:item_id"/>
                     </com:ID>
                  </tns:item_id>
                  <tns:unit_qty>
                     <xsl:value-of select="/ns0:AppointDesc/ns0:AppointDtl/ns0:unit_qty"/>
                  </tns:unit_qty>
                  <tns:po_nbr>
                     <com:ID>
                        <xsl:value-of select="/ns0:AppointDesc/ns0:AppointDtl/ns0:po_nbr"/>
                     </com:ID>
                  </tns:po_nbr>
                  <tns:document_type>
                     <xsl:value-of select="/ns0:AppointDesc/ns0:AppointDtl/ns0:document_type"/>
                  </tns:document_type>
                  <tns:asn_nbr>
                     <com:ID>
                        <xsl:value-of select="/ns0:AppointDesc/ns0:AppointDtl/ns0:asn_nbr"/>
                     </com:ID>
                  </tns:asn_nbr>
               </tns:Desc>
            </tns:AppointmentEBO>
            <tns:AppointmentRecordHistory>
               <com:CreationDate/>
               <com:CreatedBy/>
               <com:LastUpdateDate/>
               <com:LastUpdatedBy/>
            </tns:AppointmentRecordHistory>
         </tns:dataArea>
      </tns:ProccesAppointmentEBM>
   </xsl:template>
</xsl:stylesheet>