<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:ns0="http://xmlns.banesco.com/eopt/PartyInfoInq_V1.0" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:tns="http://xmlns.banesco.com/eopt/OrgPartyInfoInq_V1.0" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns1="http://xmlns.banesco.com/eo/Banking_V1.0"
                xmlns:eoPar="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:ns2="http://xmlns.banesco.com/eo/Common_V1.0">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Party/PartyInfoInq/PartyInfoInq_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="PartyInfoInqRq" namespace="http://xmlns.banesco.com/eopt/PartyInfoInq_V1.0"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Party/OrgPartyInfoInq/OrgPartyInfoInq_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="OrgPartyInfoInqRq" namespace="http://xmlns.banesco.com/eopt/OrgPartyInfoInq_V1.0"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [TUE MAR 13 13:36:13 COT 2018].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:OrgPartyInfoInqRq>
            <tns:PartyData>
               <xsl:if test="/ns0:PartyInfoInqRq/PartyData/eoPar:Contact">
                  <eoPar:Contact>
                     <xsl:if test="/ns0:PartyInfoInqRq/PartyData/eoPar:Contact/ns2:ContactName/text()">
                        <ns2:ContactName>
                           <xsl:value-of select="/ns0:PartyInfoInqRq/PartyData/eoPar:Contact/ns2:ContactName"/>
                        </ns2:ContactName>
                     </xsl:if>
                     <xsl:if test="/ns0:PartyInfoInqRq/PartyData/eoPar:Contact/ns2:ContactType/text()">
                        <ns2:ContactType>
                           <xsl:value-of select="/ns0:PartyInfoInqRq/PartyData/eoPar:Contact/ns2:ContactType"/>
                        </ns2:ContactType>
                     </xsl:if>
                     <xsl:if test="/ns0:PartyInfoInqRq/PartyData/eoPar:Contact/ns2:Locator">
                        <ns2:Locator>
                           <xsl:for-each select="/ns0:PartyInfoInqRq/PartyData/eoPar:Contact/ns2:Locator/ns2:Email">
                              <ns2:Email>
                                 <xsl:if test="ns2:Type/text()">
                                    <ns2:Type>
                                       <xsl:value-of select="ns2:Type"/>
                                    </ns2:Type>
                                 </xsl:if>
                                 <xsl:if test="ns2:Value/text()">
                                    <ns2:Value>
                                       <xsl:value-of select="ns2:Value"/>
                                    </ns2:Value>
                                 </xsl:if>
                              </ns2:Email>
                           </xsl:for-each>
                           <xsl:if test="/ns0:PartyInfoInqRq/PartyData/eoPar:Contact/ns2:Locator/ns2:ResourceLocator/text()">
                              <ns2:ResourceLocator>
                                 <xsl:value-of select="/ns0:PartyInfoInqRq/PartyData/eoPar:Contact/ns2:Locator/ns2:ResourceLocator"/>
                              </ns2:ResourceLocator>
                           </xsl:if>
                           <xsl:for-each select="/ns0:PartyInfoInqRq/PartyData/eoPar:Contact/ns2:Locator/ns2:PostAddr">
                              <ns2:PostAddr>
                                 <xsl:if test="ns2:Addr1/text()">
                                    <ns2:Addr1>
                                       <xsl:value-of select="ns2:Addr1"/>
                                    </ns2:Addr1>
                                 </xsl:if>
                                 <xsl:if test="ns2:Addr2/text()">
                                    <ns2:Addr2>
                                       <xsl:value-of select="ns2:Addr2"/>
                                    </ns2:Addr2>
                                 </xsl:if>
                                 <xsl:if test="ns2:Addr3/text()">
                                    <ns2:Addr3>
                                       <xsl:value-of select="ns2:Addr3"/>
                                    </ns2:Addr3>
                                 </xsl:if>
                                 <xsl:if test="ns2:Addr4/text()">
                                    <ns2:Addr4>
                                       <xsl:value-of select="ns2:Addr4"/>
                                    </ns2:Addr4>
                                 </xsl:if>
                                 <xsl:if test="ns2:AddrDesc/text()">
                                    <ns2:AddrDesc>
                                       <xsl:value-of select="ns2:AddrDesc"/>
                                    </ns2:AddrDesc>
                                 </xsl:if>
                                 <xsl:if test="ns2:AddressIdent/text()">
                                    <ns2:AddressIdent>
                                       <xsl:value-of select="ns2:AddressIdent"/>
                                    </ns2:AddressIdent>
                                 </xsl:if>
                                 <xsl:if test="ns2:AddrType/text()">
                                    <ns2:AddrType>
                                       <xsl:value-of select="ns2:AddrType"/>
                                    </ns2:AddrType>
                                 </xsl:if>
                                 <xsl:if test="ns2:Building/text()">
                                    <ns2:Building>
                                       <xsl:if test="ns2:Building/ns2:Cod/text()">
                                          <ns2:Cod>
                                             <xsl:value-of select="ns2:Building/ns2:Cod"/>
                                          </ns2:Cod>
                                       </xsl:if>
                                       <xsl:if test="ns2:Building/ns2:Desc/text()">
                                          <ns2:Desc>
                                             <xsl:value-of select="ns2:Building/ns2:Desc"/>
                                          </ns2:Desc>
                                       </xsl:if>
                                    </ns2:Building>
                                 </xsl:if>
                                 <xsl:if test="ns2:City/text()">
                                    <ns2:City>
                                       <xsl:if test="ns2:City/ns2:Cod/text()">
                                          <ns2:Cod>
                                             <xsl:value-of select="ns2:City/ns2:Cod"/>
                                          </ns2:Cod>
                                       </xsl:if>
                                       <xsl:if test="ns2:City/ns2:Desc/text()">
                                          <ns2:Desc>
                                             <xsl:value-of select="ns2:City/ns2:Desc"/>
                                          </ns2:Desc>
                                       </xsl:if>
                                    </ns2:City>
                                 </xsl:if>
                                 <xsl:if test="ns2:CountyDistrict/text()">
                                    <ns2:CountyDistrict>
                                       <xsl:if test="ns2:CountyDistrict/ns2:Cod/text()">
                                          <ns2:Cod>
                                             <xsl:value-of select="ns2:CountyDistrict/ns2:Cod"/>
                                          </ns2:Cod>
                                       </xsl:if>
                                       <xsl:if test="ns2:CountyDistrict/ns2:Desc/text()">
                                          <ns2:Desc>
                                             <xsl:value-of select="ns2:CountyDistrict/ns2:Desc"/>
                                          </ns2:Desc>
                                       </xsl:if>
                                    </ns2:CountyDistrict>
                                 </xsl:if>
                                 <xsl:if test="ns2:HouseNumber/text()">
                                    <ns2:HouseNumber>
                                       <xsl:value-of select="ns2:HouseNumber"/>
                                    </ns2:HouseNumber>
                                 </xsl:if>
                                 <xsl:if test="ns2:Jurisdiction/text()">
                                    <ns2:Jurisdiction>
                                       <xsl:if test="ns2:Jurisdiction/ns2:Cod/text()">
                                          <ns2:Cod>
                                             <xsl:value-of select="ns2:Jurisdiction/ns2:Cod"/>
                                          </ns2:Cod>
                                       </xsl:if>
                                       <xsl:if test="ns2:Jurisdiction/ns2:Desc/text()">
                                          <ns2:Desc>
                                             <xsl:value-of select="ns2:Jurisdiction/ns2:Desc"/>
                                          </ns2:Desc>
                                       </xsl:if>
                                    </ns2:Jurisdiction>
                                 </xsl:if>
                                 <xsl:if test="ns2:POBox/text()">
                                    <ns2:POBox>
                                       <xsl:value-of select="ns2:POBox"/>
                                    </ns2:POBox>
                                 </xsl:if>
                                 <xsl:if test="ns2:PostalCode/text()">
                                    <ns2:PostalCode>
                                       <xsl:value-of select="ns2:PostalCode"/>
                                    </ns2:PostalCode>
                                 </xsl:if>
                                 <xsl:if test="ns2:Province/text()">
                                    <ns2:Province>
                                       <xsl:if test="ns2:Province/ns2:Cod/text()">
                                          <ns2:Cod>
                                             <xsl:value-of select="ns2:Province/ns2:Cod"/>
                                          </ns2:Cod>
                                       </xsl:if>
                                       <xsl:if test="ns2:Province/ns2:Desc/text()">
                                          <ns2:Desc>
                                             <xsl:value-of select="ns2:Province/ns2:Desc"/>
                                          </ns2:Desc>
                                       </xsl:if>
                                    </ns2:Province>
                                 </xsl:if>
                                 <xsl:if test="ns2:State/text()">
                                    <ns2:State>
                                       <xsl:if test="ns2:State/ns2:Cod/text()">
                                          <ns2:Cod>
                                             <xsl:value-of select="ns2:State/ns2:Cod"/>
                                          </ns2:Cod>
                                       </xsl:if>
                                       <xsl:if test="ns2:State/ns2:Desc/text()">
                                          <ns2:Desc>
                                             <xsl:value-of select="ns2:State/ns2:Desc"/>
                                          </ns2:Desc>
                                       </xsl:if>
                                    </ns2:State>
                                 </xsl:if>
                                 <xsl:if test="ns2:Street/text()">
                                    <ns2:Street>
                                       <xsl:value-of select="ns2:Street"/>
                                    </ns2:Street>
                                 </xsl:if>
                                 <xsl:if test="ns2:UpDt/text()">
                                    <ns2:UpDt>
                                       <xsl:value-of select="ns2:UpDt"/>
                                    </ns2:UpDt>
                                 </xsl:if>
                                 <xsl:if test="ns2:Country/text()">
                                    <ns2:Country>
                                       <xsl:if test="ns2:Country/ns2:CountryCode/text()">
                                          <ns2:CountryCode>
                                             <xsl:value-of select="ns2:Country/ns2:CountryCode"/>
                                          </ns2:CountryCode>
                                       </xsl:if>
                                       <xsl:if test="ns2:Country/ns2:CountryName/text()">
                                          <ns2:CountryName>
                                             <xsl:value-of select="ns2:Country/ns2:CountryName"/>
                                          </ns2:CountryName>
                                       </xsl:if>
                                    </ns2:Country>
                                 </xsl:if>
                              </ns2:PostAddr>
                           </xsl:for-each>
                           <xsl:for-each select="/ns0:PartyInfoInqRq/PartyData/eoPar:Contact/ns2:Locator/ns2:Phone">
                              <ns2:Phone>
                                 <xsl:if test="ns2:AreaCode/text()">
                                    <ns2:AreaCode>
                                       <xsl:value-of select="ns2:AreaCode"/>
                                    </ns2:AreaCode>
                                 </xsl:if>
                                 <xsl:if test="ns2:PhoneExt/text()">
                                    <ns2:PhoneExt>
                                       <xsl:value-of select="ns2:PhoneExt"/>
                                    </ns2:PhoneExt>
                                 </xsl:if>
                                 <xsl:if test="ns2:PhoneNum/text()">
                                    <ns2:PhoneNum>
                                       <xsl:value-of select="ns2:PhoneNum"/>
                                    </ns2:PhoneNum>
                                 </xsl:if>
                                 <xsl:if test="ns2:PhoneType/text()">
                                    <ns2:PhoneType>
                                       <xsl:value-of select="ns2:PhoneType"/>
                                    </ns2:PhoneType>
                                 </xsl:if>
                              </ns2:Phone>
                           </xsl:for-each>
                        </ns2:Locator>
                     </xsl:if>
                  </eoPar:Contact>
               </xsl:if>
               <xsl:for-each select="/ns0:PartyInfoInqRq/PartyData/eoPar:IssuedIdent">
                  <eoPar:IssuedIdent>
                     <xsl:if test="eoPar:Desc/text()">
                        <eoPar:Desc>
                           <xsl:value-of select="eoPar:Desc"/>
                        </eoPar:Desc>
                     </xsl:if>
                     <xsl:if test="eoPar:ExpDt/text()">
                        <eoPar:ExpDt>
                           <xsl:value-of select="eoPar:ExpDt"/>
                        </eoPar:ExpDt>
                     </xsl:if>
                     <xsl:if test="eoPar:Ident">
                        <eoPar:Ident>
                           <xsl:if test="eoPar:Ident/eoPar:FolioDigit/text()">
                              <eoPar:FolioDigit>
                                 <xsl:value-of select="eoPar:Ident/eoPar:FolioDigit"/>
                              </eoPar:FolioDigit>
                           </xsl:if>
                           <xsl:if test="eoPar:Ident/eoPar:IdentImgDigit/text()">
                              <eoPar:IdentImgDigit>
                                 <xsl:value-of select="eoPar:Ident/eoPar:IdentImgDigit"/>
                              </eoPar:IdentImgDigit>
                           </xsl:if>
                           <xsl:if test="eoPar:Ident/eoPar:IssuedIdentType/text()">
                              <eoPar:IssuedIdentType>
                                 <xsl:value-of select="eoPar:Ident/eoPar:IssuedIdentType"/>
                              </eoPar:IssuedIdentType>
                           </xsl:if>
                           <xsl:if test="eoPar:Ident/eoPar:IssuedIdentValue/text()">
                              <eoPar:IssuedIdentValue>
                                 <xsl:value-of select="eoPar:Ident/eoPar:IssuedIdentValue"/>
                              </eoPar:IssuedIdentValue>
                           </xsl:if>
                           <xsl:if test="eoPar:Ident/eoPar:ProvinceDigit/text()">
                              <eoPar:ProvinceDigit>
                                 <xsl:value-of select="eoPar:Ident/eoPar:ProvinceDigit"/>
                              </eoPar:ProvinceDigit>
                           </xsl:if>
                           <xsl:if test="eoPar:Ident/eoPar:SpecialIdentDigit/text()">
                              <eoPar:SpecialIdentDigit>
                                 <xsl:value-of select="eoPar:Ident/eoPar:SpecialIdentDigit"/>
                              </eoPar:SpecialIdentDigit>
                           </xsl:if>
                        </eoPar:Ident>
                     </xsl:if>
                     <xsl:if test="eoPar:IdentImg">
                        <eoPar:IdentImg>
                           <xsl:if test="eoPar:IdentImg/ns2:BinData/text()">
                              <ns2:BinData>
                                 <xsl:value-of select="eoPar:IdentImg/ns2:BinData"/>
                              </ns2:BinData>
                           </xsl:if>
                           <xsl:if test="eoPar:IdentImg/ns2:BinLength/text()">
                              <ns2:BinLength>
                                 <xsl:value-of select="eoPar:IdentImg/ns2:BinLength"/>
                              </ns2:BinLength>
                           </xsl:if>
                           <xsl:if test="eoPar:IdentImg/ns2:ContentType/text()">
                              <ns2:ContentType>
                                 <xsl:value-of select="eoPar:IdentImg/ns2:ContentType"/>
                              </ns2:ContentType>
                           </xsl:if>
                           <xsl:if test="eoPar:IdentImg/ns2:FileURL/text()">
                              <ns2:FileURL>
                                 <xsl:value-of select="eoPar:IdentImg/ns2:FileURL"/>
                              </ns2:FileURL>
                           </xsl:if>
                        </eoPar:IdentImg>
                     </xsl:if>
                     <xsl:if test="eoPar:IssuedLoc">
                        <eoPar:IssuedLoc>
                           <xsl:if test="eoPar:IssuedLoc/ns2:Type/text()">
                              <ns2:Type>
                                 <xsl:value-of select="eoPar:IssuedLoc/ns2:Type"/>
                              </ns2:Type>
                           </xsl:if>
                           <xsl:if test="eoPar:IssuedLoc/ns2:Value/text()">
                              <ns2:Value>
                                 <xsl:value-of select="eoPar:IssuedLoc/ns2:Value"/>
                              </ns2:Value>
                           </xsl:if>
                        </eoPar:IssuedLoc>
                     </xsl:if>
                     <xsl:if test="eoPar:IssueDt/text()">
                        <eoPar:IssueDt>
                           <xsl:value-of select="eoPar:IssueDt"/>
                        </eoPar:IssueDt>
                     </xsl:if>
                     <xsl:if test="eoPar:Issuer/text()">
                        <eoPar:Issuer>
                           <xsl:value-of select="eoPar:Issuer"/>
                        </eoPar:Issuer>
                     </xsl:if>
                  </eoPar:IssuedIdent>
               </xsl:for-each>
            </tns:PartyData>
            <tns:PartyKey>
               <xsl:if test="/ns0:PartyInfoInqRq/PartyKey/eoPar:FolioDigit/text()">
                  <eoPar:FolioDigit>
                     <xsl:value-of select="/ns0:PartyInfoInqRq/PartyKey/eoPar:FolioDigit"/>
                  </eoPar:FolioDigit>
               </xsl:if>
               <xsl:if test="/ns0:PartyInfoInqRq/PartyKey/eoPar:IdentImgDigit/text()">
                  <eoPar:IdentImgDigit>
                     <xsl:value-of select="/ns0:PartyInfoInqRq/PartyKey/eoPar:IdentImgDigit"/>
                  </eoPar:IdentImgDigit>
               </xsl:if>
               <xsl:if test="/ns0:PartyInfoInqRq/PartyKey/eoPar:IssuedIdentType/text()">
                  <eoPar:IssuedIdentType>
                     <xsl:value-of select="/ns0:PartyInfoInqRq/PartyKey/eoPar:IssuedIdentType"/>
                  </eoPar:IssuedIdentType>
               </xsl:if>
               <xsl:if test="/ns0:PartyInfoInqRq/PartyKey/eoPar:IssuedIdentValue/text()">
                  <eoPar:IssuedIdentValue>
                     <xsl:value-of select="/ns0:PartyInfoInqRq/PartyKey/eoPar:IssuedIdentValue"/>
                  </eoPar:IssuedIdentValue>
               </xsl:if>
               <xsl:if test="/ns0:PartyInfoInqRq/PartyKey/eoPar:ProvinceDigit/text()">
                  <eoPar:ProvinceDigit>
                     <xsl:value-of select="/ns0:PartyInfoInqRq/PartyKey/eoPar:ProvinceDigit"/>
                  </eoPar:ProvinceDigit>
               </xsl:if>
               <xsl:if test="/ns0:PartyInfoInqRq/PartyKey/eoPar:SpecialIdentDigit/text()">
                  <eoPar:SpecialIdentDigit>
                     <xsl:value-of select="/ns0:PartyInfoInqRq/PartyKey/eoPar:SpecialIdentDigit"/>
                  </eoPar:SpecialIdentDigit>
               </xsl:if>
               <xsl:if test="/ns0:PartyInfoInqRq/PartyKey/eoPar:PartyId/text()">
                  <eoPar:PartyId>
                     <xsl:value-of select="/ns0:PartyInfoInqRq/PartyKey/eoPar:PartyId"/>
                  </eoPar:PartyId>
               </xsl:if>
               <xsl:if test="/ns0:PartyInfoInqRq/PartyKey/eoPar:PartyType/text()">
                  <eoPar:PartyType>
                     <xsl:value-of select="/ns0:PartyInfoInqRq/PartyKey/eoPar:PartyType"/>
                  </eoPar:PartyType>
               </xsl:if>
               <xsl:if test="/ns0:PartyInfoInqRq/PartyKey/eoPar:LoginIdent">
                  <eoPar:LoginIdent>
                     <xsl:if test="/ns0:PartyInfoInqRq/PartyKey/eoPar:LoginIdent/eoPar:LoginAuthority/text()">
                        <eoPar:LoginAuthority>
                           <xsl:value-of select="/ns0:PartyInfoInqRq/PartyKey/eoPar:LoginIdent/eoPar:LoginAuthority"/>
                        </eoPar:LoginAuthority>
                     </xsl:if>
                     <xsl:if test="/ns0:PartyInfoInqRq/PartyKey/eoPar:LoginIdent/eoPar:LoginName/text()">
                        <eoPar:LoginName>
                           <xsl:value-of select="/ns0:PartyInfoInqRq/PartyKey/eoPar:LoginIdent/eoPar:LoginName"/>
                        </eoPar:LoginName>
                     </xsl:if>
                  </eoPar:LoginIdent>
               </xsl:if>
            </tns:PartyKey>
      </tns:OrgPartyInfoInqRq>
   </xsl:template>
</xsl:stylesheet>
