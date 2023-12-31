<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:ns0="http://xmlns.banesco.com/eopt/AlertAdv_V1.0" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:tns="http://xmlns.banesco.com/eopt/AlertEmailAdv_V1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns3="http://xmlns.banesco.com/eo/Product_V1.0" xmlns:ns4="http://xmlns.banesco.com/eo/Acct_V1.0"
                xmlns:ns5="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:ns9="http://xmlns.banesco.com/eo/Chk_V1.0" xmlns:ns1="http://xmlns.banesco.com/eo/Banking_V1.0"
                xmlns:eoAlert="http://xmlns.banesco.com/eo/Alert_V1.0" xmlns:ns2="http://xmlns.banesco.com/eo/Fee_V1.0"
                xmlns:ns6="http://xmlns.banesco.com/eo/Card_V1.0" xmlns:ns7="http://xmlns.banesco.com/eo/Payee_V1.0"
                xmlns:ns8="http://xmlns.banesco.com/eo/Common_V1.0">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Utility/AlertAdv/AlertAdv_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="AlertAdvRq" namespace="http://xmlns.banesco.com/eopt/AlertAdv_V1.0"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Utility/AlertEmailAdv/AlertEmailAdv_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="AlertEmailAdvRq" namespace="http://xmlns.banesco.com/eopt/AlertEmailAdv_V1.0"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.3.0(XSLT Build 170820.1700.2557) AT [THU MAY 31 17:44:09 COT 2018].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:AlertEmailAdvRq>
         <tns:Alert>
            <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AcctKey">
               <eoAlert:AcctKey>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AcctKey/ns4:AcctId/text()">
                     <ns4:AcctId>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AcctKey/ns4:AcctId"/>
                     </ns4:AcctId>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AcctKey/ns4:AcctReference/text()">
                     <ns4:AcctReference>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AcctKey/ns4:AcctReference"/>
                     </ns4:AcctReference>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AcctKey/ns4:BBAN/text()">
                     <ns4:BBAN>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AcctKey/ns4:BBAN"/>
                     </ns4:BBAN>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AcctKey/ns4:IBAN/text()">
                     <ns4:IBAN>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AcctKey/ns4:IBAN"/>
                     </ns4:IBAN>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AcctKey/ns4:UPIC/text()">
                     <ns4:UPIC>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AcctKey/ns4:UPIC"/>
                     </ns4:UPIC>
                  </xsl:if>
               </eoAlert:AcctKey>
            </xsl:if>
            <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AlertDesc/text()">
               <eoAlert:AlertDesc>
                  <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AlertDesc"/>
               </eoAlert:AlertDesc>
            </xsl:if>
            <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AlertIdent/text()">
               <eoAlert:AlertIdent>
                  <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:AlertIdent"/>
               </eoAlert:AlertIdent>
            </xsl:if>
            <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:CardKey">
               <eoAlert:CardKey>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:CardKey/ns6:CardNum/text()">
                     <ns6:CardNum>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:CardKey/ns6:CardNum"/>
                     </ns6:CardNum>
                  </xsl:if>
               </eoAlert:CardKey>
            </xsl:if>
            <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:LastUpdateDt/text()">
               <eoAlert:LastUpdateDt>
                  <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:LastUpdateDt"/>
               </eoAlert:LastUpdateDt>
            </xsl:if>
            <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey">
               <eoAlert:PartyKey>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:File/text()">
                     <ns5:File>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:File"/>
                     </ns5:File>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:FolioDigit/text()">
                     <ns5:FolioDigit>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:FolioDigit"/>
                     </ns5:FolioDigit>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:HolderName/text()">
                     <ns5:HolderName>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:HolderName"/>
                     </ns5:HolderName>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:IdentImgDigit/text()">
                     <ns5:IdentImgDigit>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:IdentImgDigit"/>
                     </ns5:IdentImgDigit>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:IssuedIdentType/text()">
                     <ns5:IssuedIdentType>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:IssuedIdentType"/>
                     </ns5:IssuedIdentType>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:IssuedIdentValue/text()">
                     <ns5:IssuedIdentValue>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:IssuedIdentValue"/>
                     </ns5:IssuedIdentValue>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:ProvinceDigit/text()">
                     <ns5:ProvinceDigit>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:ProvinceDigit"/>
                     </ns5:ProvinceDigit>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:RollDoc/text()">
                     <ns5:RollDoc>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:RollDoc"/>
                     </ns5:RollDoc>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:SpecialIdentDigit/text()">
                     <ns5:SpecialIdentDigit>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:SpecialIdentDigit"/>
                     </ns5:SpecialIdentDigit>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:VerifyDigit/text()">
                     <ns5:VerifyDigit>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:VerifyDigit"/>
                     </ns5:VerifyDigit>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:PartyId/text()">
                     <ns5:PartyId>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:PartyId"/>
                     </ns5:PartyId>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:PartyType/text()">
                     <ns5:PartyType>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:PartyType"/>
                     </ns5:PartyType>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:LoginIdent">
                     <ns5:LoginIdent>
                        <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:LoginIdent/ns5:LoginAuthority/text()">
                           <ns5:LoginAuthority>
                              <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:LoginIdent/ns5:LoginAuthority"/>
                           </ns5:LoginAuthority>
                        </xsl:if>
                        <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:LoginIdent/ns5:LoginName/text()">
                           <ns5:LoginName>
                              <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:PartyKey/ns5:LoginIdent/ns5:LoginName"/>
                           </ns5:LoginName>
                        </xsl:if>
                     </ns5:LoginIdent>
                  </xsl:if>
               </eoAlert:PartyKey>
            </xsl:if>
            <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:Prioriry/text()">
               <eoAlert:Prioriry>
                  <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:Prioriry"/>
               </eoAlert:Prioriry>
            </xsl:if>
            <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction">
               <eoAlert:DeliveryInstruction>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Carrier/text()">
                     <eoAlert:Carrier>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Carrier"/>
                     </eoAlert:Carrier>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Contact">
                     <eoAlert:Contact>
                        <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Contact/ns8:ContactName/text()">
                           <ns8:ContactName>
                              <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Contact/ns8:ContactName"/>
                           </ns8:ContactName>
                        </xsl:if>
                        <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Contact/ns8:ContactType/text()">
                           <ns8:ContactType>
                              <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Contact/ns8:ContactType"/>
                           </ns8:ContactType>
                        </xsl:if>
                        <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Contact/ns8:Locator">
                           <ns8:Locator>
                              <xsl:for-each select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Contact/ns8:Locator/ns8:Email">
                                 <ns8:Email>
                                    <xsl:if test="ns8:Type/text()">
                                       <ns8:Type>
                                          <xsl:value-of select="ns8:Type"/>
                                       </ns8:Type>
                                    </xsl:if>
                                    <xsl:if test="ns8:Value/text()">
                                       <ns8:Value>
                                          <xsl:value-of select="ns8:Value"/>
                                       </ns8:Value>
                                    </xsl:if>
                                 </ns8:Email>
                              </xsl:for-each>
                              <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Contact/ns8:Locator/ns8:ResourceLocator/text()">
                                 <ns8:ResourceLocator>
                                    <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Contact/ns8:Locator/ns8:ResourceLocator"/>
                                 </ns8:ResourceLocator>
                              </xsl:if>
                              <xsl:for-each select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Contact/ns8:Locator/ns8:PostAddr">
                                 <ns8:PostAddr>
                                    <xsl:if test="ns8:Addr1/text()">
                                       <ns8:Addr1>
                                          <xsl:value-of select="ns8:Addr1"/>
                                       </ns8:Addr1>
                                    </xsl:if>
                                    <xsl:if test="ns8:Addr2/text()">
                                       <ns8:Addr2>
                                          <xsl:value-of select="ns8:Addr2"/>
                                       </ns8:Addr2>
                                    </xsl:if>
                                    <xsl:if test="ns8:Addr3/text()">
                                       <ns8:Addr3>
                                          <xsl:value-of select="ns8:Addr3"/>
                                       </ns8:Addr3>
                                    </xsl:if>
                                    <xsl:if test="ns8:Addr4/text()">
                                       <ns8:Addr4>
                                          <xsl:value-of select="ns8:Addr4"/>
                                       </ns8:Addr4>
                                    </xsl:if>
                                    <xsl:if test="ns8:AddrDesc/text()">
                                       <ns8:AddrDesc>
                                          <xsl:value-of select="ns8:AddrDesc"/>
                                       </ns8:AddrDesc>
                                    </xsl:if>
                                    <xsl:if test="ns8:AddressIdent/text()">
                                       <ns8:AddressIdent>
                                          <xsl:value-of select="ns8:AddressIdent"/>
                                       </ns8:AddressIdent>
                                    </xsl:if>
                                    <xsl:if test="ns8:AddrType/text()">
                                       <ns8:AddrType>
                                          <xsl:value-of select="ns8:AddrType"/>
                                       </ns8:AddrType>
                                    </xsl:if>
                                    <xsl:if test="ns8:Building">
                                       <ns8:Building>
                                          <xsl:if test="ns8:Building/ns8:Cod/text()">
                                             <ns8:Cod>
                                                <xsl:value-of select="ns8:Building/ns8:Cod"/>
                                             </ns8:Cod>
                                          </xsl:if>
                                          <xsl:if test="ns8:Building/ns8:Desc/text()">
                                             <ns8:Desc>
                                                <xsl:value-of select="ns8:Building/ns8:Desc"/>
                                             </ns8:Desc>
                                          </xsl:if>
                                       </ns8:Building>
                                    </xsl:if>
                                    <xsl:if test="ns8:City">
                                       <ns8:City>
                                          <xsl:if test="ns8:City/ns8:Cod/text()">
                                             <ns8:Cod>
                                                <xsl:value-of select="ns8:City/ns8:Cod"/>
                                             </ns8:Cod>
                                          </xsl:if>
                                          <xsl:if test="ns8:City/ns8:Desc/text()">
                                             <ns8:Desc>
                                                <xsl:value-of select="ns8:City/ns8:Desc"/>
                                             </ns8:Desc>
                                          </xsl:if>
                                       </ns8:City>
                                    </xsl:if>
                                    <xsl:if test="ns8:CountyDistrict">
                                       <ns8:CountyDistrict>
                                          <xsl:if test="ns8:CountyDistrict/ns8:Cod/text()">
                                             <ns8:Cod>
                                                <xsl:value-of select="ns8:CountyDistrict/ns8:Cod"/>
                                             </ns8:Cod>
                                          </xsl:if>
                                          <xsl:if test="ns8:CountyDistrict/ns8:Desc/text()">
                                             <ns8:Desc>
                                                <xsl:value-of select="ns8:CountyDistrict/ns8:Desc"/>
                                             </ns8:Desc>
                                          </xsl:if>
                                       </ns8:CountyDistrict>
                                    </xsl:if>
                                    <xsl:if test="ns8:HouseNumber/text()">
                                       <ns8:HouseNumber>
                                          <xsl:value-of select="ns8:HouseNumber"/>
                                       </ns8:HouseNumber>
                                    </xsl:if>
                                    <xsl:if test="ns8:Jurisdiction">
                                       <ns8:Jurisdiction>
                                          <xsl:if test="ns8:Jurisdiction/ns8:Cod/text()">
                                             <ns8:Cod>
                                                <xsl:value-of select="ns8:Jurisdiction/ns8:Cod"/>
                                             </ns8:Cod>
                                          </xsl:if>
                                          <xsl:if test="ns8:Jurisdiction/ns8:Desc/text()">
                                             <ns8:Desc>
                                                <xsl:value-of select="ns8:Jurisdiction/ns8:Desc"/>
                                             </ns8:Desc>
                                          </xsl:if>
                                       </ns8:Jurisdiction>
                                    </xsl:if>
                                    <xsl:if test="ns8:POBox/text()">
                                       <ns8:POBox>
                                          <xsl:value-of select="ns8:POBox"/>
                                       </ns8:POBox>
                                    </xsl:if>
                                    <xsl:if test="ns8:PostalCode/text()">
                                       <ns8:PostalCode>
                                          <xsl:value-of select="ns8:PostalCode"/>
                                       </ns8:PostalCode>
                                    </xsl:if>
                                    <xsl:if test="ns8:Province">
                                       <ns8:Province>
                                          <xsl:if test="ns8:Province/ns8:Cod/text()">
                                             <ns8:Cod>
                                                <xsl:value-of select="ns8:Province/ns8:Cod"/>
                                             </ns8:Cod>
                                          </xsl:if>
                                          <xsl:if test="ns8:Province/ns8:Desc/text()">
                                             <ns8:Desc>
                                                <xsl:value-of select="ns8:Province/ns8:Desc"/>
                                             </ns8:Desc>
                                          </xsl:if>
                                       </ns8:Province>
                                    </xsl:if>
                                    <xsl:if test="ns8:State">
                                       <ns8:State>
                                          <xsl:if test="ns8:State/ns8:Cod/text()">
                                             <ns8:Cod>
                                                <xsl:value-of select="ns8:State/ns8:Cod"/>
                                             </ns8:Cod>
                                          </xsl:if>
                                          <xsl:if test="ns8:State/ns8:Desc/text()">
                                             <ns8:Desc>
                                                <xsl:value-of select="ns8:State/ns8:Desc"/>
                                             </ns8:Desc>
                                          </xsl:if>
                                       </ns8:State>
                                    </xsl:if>
                                    <xsl:if test="ns8:Street/text()">
                                       <ns8:Street>
                                          <xsl:value-of select="ns8:Street"/>
                                       </ns8:Street>
                                    </xsl:if>
                                    <xsl:if test="ns8:UpDt/text()">
                                       <ns8:UpDt>
                                          <xsl:value-of select="ns8:UpDt"/>
                                       </ns8:UpDt>
                                    </xsl:if>
                                    <xsl:if test="ns8:Country">
                                       <ns8:Country>
                                          <xsl:if test="ns8:Country/ns8:CountryCode/text()">
                                             <ns8:CountryCode>
                                                <xsl:value-of select="ns8:Country/ns8:CountryCode"/>
                                             </ns8:CountryCode>
                                          </xsl:if>
                                          <xsl:if test="ns8:Country/ns8:CountryName/text()">
                                             <ns8:CountryName>
                                                <xsl:value-of select="ns8:Country/ns8:CountryName"/>
                                             </ns8:CountryName>
                                          </xsl:if>
                                       </ns8:Country>
                                    </xsl:if>
                                 </ns8:PostAddr>
                              </xsl:for-each>
                              <xsl:for-each select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:Contact/ns8:Locator/ns8:Phone">
                                 <ns8:Phone>
                                    <xsl:if test="ns8:AreaCode/text()">
                                       <ns8:AreaCode>
                                          <xsl:value-of select="ns8:AreaCode"/>
                                       </ns8:AreaCode>
                                    </xsl:if>
                                    <xsl:if test="ns8:PhoneExt/text()">
                                       <ns8:PhoneExt>
                                          <xsl:value-of select="ns8:PhoneExt"/>
                                       </ns8:PhoneExt>
                                    </xsl:if>
                                    <xsl:if test="ns8:PhoneNum/text()">
                                       <ns8:PhoneNum>
                                          <xsl:value-of select="ns8:PhoneNum"/>
                                       </ns8:PhoneNum>
                                    </xsl:if>
                                    <xsl:if test="ns8:PhoneType/text()">
                                       <ns8:PhoneType>
                                          <xsl:value-of select="ns8:PhoneType"/>
                                       </ns8:PhoneType>
                                    </xsl:if>
                                 </ns8:Phone>
                              </xsl:for-each>
                           </ns8:Locator>
                        </xsl:if>
                     </eoAlert:Contact>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:DeliveryMethod/text()">
                     <eoAlert:DeliveryMethod>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:DeliveryInstruction/eoAlert:DeliveryMethod"/>
                     </eoAlert:DeliveryMethod>
                  </xsl:if>
               </eoAlert:DeliveryInstruction>
            </xsl:if>
            <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent">
               <eoAlert:MessageContent>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:Copy/text()">
                     <eoAlert:Copy>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:Copy"/>
                     </eoAlert:Copy>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:From/text()">
                     <eoAlert:From>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:From"/>
                     </eoAlert:From>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:Subject/text()">
                     <eoAlert:Subject>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:Subject"/>
                     </eoAlert:Subject>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:Tags/text()">
                     <eoAlert:Tags>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:Tags"/>
                     </eoAlert:Tags>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:TemplateIdent/text()">
                     <eoAlert:TemplateIdent>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:TemplateIdent"/>
                     </eoAlert:TemplateIdent>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:Text/text()">
                     <eoAlert:Text>
                        <xsl:value-of select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:Text"/>
                     </eoAlert:Text>
                  </xsl:if>
                  <xsl:if test="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:TemplateParameters">
                     <eoAlert:TemplateParameters>
                        <xsl:for-each select="/ns0:AlertAdvRq/ns0:Alert/eoAlert:MessageContent/eoAlert:TemplateParameters/eoAlert:Parameter">
                           <eoAlert:Parameter>
                              <xsl:if test="eoAlert:Key/text()">
                                 <eoAlert:Key>
                                    <xsl:value-of select="eoAlert:Key"/>
                                 </eoAlert:Key>
                              </xsl:if>
                              <xsl:if test="eoAlert:Value/text()">
                                 <eoAlert:Value>
                                    <xsl:value-of select="eoAlert:Value"/>
                                 </eoAlert:Value>
                              </xsl:if>
                           </eoAlert:Parameter>
                        </xsl:for-each>
                     </eoAlert:TemplateParameters>
                  </xsl:if>
               </eoAlert:MessageContent>
            </xsl:if>
         </tns:Alert>
      </tns:AlertEmailAdvRq>
   </xsl:template>
</xsl:stylesheet>
