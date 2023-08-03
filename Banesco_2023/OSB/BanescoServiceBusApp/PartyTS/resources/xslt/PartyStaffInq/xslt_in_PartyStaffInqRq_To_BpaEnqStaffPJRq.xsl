<xsl:stylesheet version="1.0"
    exclude-result-prefixes="xsl ns0 tns ns1 eoPar eoStatus ns2 ns3"      
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"   
    xmlns:tns="http://www.temenos.com/T24/ofs/Request"
    xmlns:ns0="http://xmlns.banesco.com/eopt/PartyStaffInq_V1.0"
    xmlns:ns1="http://xmlns.banesco.com/eo/Banking_V1.0"
    xmlns:eoPar="http://xmlns.banesco.com/eo/Party_V1.0"
    xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
    xmlns:ns2="http://xmlns.banesco.com/eo/Common_V1.0"
    xmlns:enqins="http://www.temenos.com/T24/ofs/EnquiryInput"
    xmlns:reqcns="http://www.temenos.com/T24/ofs/RequestCommon"
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
    xmlns:head="http://xmlns.banesco.com/appopt/AOptCommon_V1.0"
    xmlns:ns3="http://xmlns.banesco.com/appopt/AOptCommon_V1.0">  
    <xsl:param name="varHeader"></xsl:param>
    <xsl:template match="/">
        <opaq:opaqueElement xmlns:opaq="http://xmlns.oracle.com/pcbpel/adapter/opaque/">
            <tns:BPAENQSTAFFPJRequest>
                <RequestCommon>
                    <UserName>
                        <xsl:value-of select="$varHeader/head:ClientApp/head:UserId"/>
                    </UserName>
                </RequestCommon>
                <EnquiryInput>
                    <EnquiryCriteriaCollection>
                        <xsl:if test="/ns0:PartyStaffInqRq/ns0:Party/ns0:PartyKey/eoPar:PartyId/text()">
                            <EnquiryCriteria>
                                <Field>@ID</Field>
                                <Operator>EQ</Operator>
                                <Value>
                                    <xsl:value-of select="/ns0:PartyStaffInqRq/ns0:Party/ns0:PartyKey/eoPar:PartyId"/>
                                </Value>
                            </EnquiryCriteria>
                        </xsl:if>
                        <xsl:if test="/ns0:PartyStaffInqRq/ns0:Party/ns0:OrgPartyInfo/ns0:OrgData/eoPar:IssuedIdent/eoPar:Ident/eoPar:IssuedIdentValue/text()">
                            <EnquiryCriteria>
                                <Field>LEGAL.ID</Field>
                                <Operator>EQ</Operator>
                                <Value>
                                    <xsl:value-of select="/ns0:PartyStaffInqRq/ns0:Party/ns0:OrgPartyInfo/ns0:OrgData/eoPar:IssuedIdent/eoPar:Ident/eoPar:IssuedIdentValue"/>
                                </Value>
                            </EnquiryCriteria>
                        </xsl:if>
                        <xsl:if test="/ns0:PartyStaffInqRq/ns0:Party/ns0:OrgPartyInfo/ns0:OrgData/eoPar:IssuedIdent/eoPar:IssuedLoc/ns2:Value/text()">
                            <EnquiryCriteria>
                                <Field>LEGAL.ISS.AUTH</Field>
                                <Operator>EQ</Operator>
                                <Value>
                                    <xsl:value-of select="/ns0:PartyStaffInqRq/ns0:Party/ns0:OrgPartyInfo/ns0:OrgData/eoPar:IssuedIdent/eoPar:IssuedLoc/ns2:Value"/>
                                </Value>
                            </EnquiryCriteria>
                        </xsl:if>
                        <xsl:if test="/ns0:PartyStaffInqRq/ns0:Party/ns0:OrgPartyInfo/ns0:OrgData/eoPar:IssuedIdent/eoPar:Ident/eoPar:IssuedIdentType/text()">
                            <EnquiryCriteria>
                                <Field>LEGAL.DOC.NAME</Field>
                                <Operator>EQ</Operator>
                                <Value>
                                    <xsl:value-of select="/ns0:PartyStaffInqRq/ns0:Party/ns0:OrgPartyInfo/ns0:OrgData/eoPar:IssuedIdent/eoPar:Ident/eoPar:IssuedIdentType"/>
                                </Value>
                            </EnquiryCriteria>
                        </xsl:if>
                    </EnquiryCriteriaCollection>
                </EnquiryInput>
            </tns:BPAENQSTAFFPJRequest>
        </opaq:opaqueElement>
    </xsl:template>
</xsl:stylesheet>
