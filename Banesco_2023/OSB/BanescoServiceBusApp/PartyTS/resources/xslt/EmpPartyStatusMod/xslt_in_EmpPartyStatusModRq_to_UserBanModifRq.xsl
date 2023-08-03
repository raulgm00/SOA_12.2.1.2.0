<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:ns0="http://xmlns.banesco.com/eopt/EmpPartyStatusMod_V1.0"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:tns="http://www.temenos.com/T24/ofs/Request"
                xmlns:ns1="http://xmlns.banesco.com/eo/Banking_V1.0"
                xmlns:eoPar="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:ns2="http://xmlns.banesco.com/eo/Common_V1.0"
                xmlns:head="http://xmlns.banesco.com/appopt/AOptCommon_V1.0"
                xmlns:reqcns="http://www.temenos.com/T24/ofs/RequestCommon"
                xmlns:ns3="http://www.temenos.com/T24/ofs/USERBANMODIFType"
                exclude-result-prefixes="oracle-xsl-mapper xsl ns0 tns ns1 eoPar eoStatus ns2 reqcns">
  <xsl:param name="varHeader"></xsl:param>
  <xsl:template match="/">
        <opaq:opaqueElement xmlns:opaq="http://xmlns.oracle.com/pcbpel/adapter/opaque/">
      <tns:USERBANMODIFRequest>
        <RequestCommon>
          <UserName>
            <xsl:value-of select="$varHeader/*:ClientApp/*:UserId"/>
          </UserName>
        </RequestCommon>
        <USERBANMODIF>
          <xsl:attribute name="id">
            <xsl:value-of select="/ns0:EmpPartyStatusModRq/ns0:LoginIdent/eoPar:LoginName"/>
          </xsl:attribute>
          <USERNAME>
            <xsl:value-of select="/ns0:EmpPartyStatusModRq/ns0:PersonData/eoPar:PersonName/eoPar:FullName"/>
          </USERNAME>          
          <!--SIGNONNAME>
            <xsl:value-of select="/ns0:EmpPartyStatusModRq/ns0:LoginIdent/eoPar:LoginName"/>
          </SIGNONNAME-->
          <DEPARTMENTCODE>
            <xsl:value-of select="/ns0:EmpPartyStatusModRq/ns0:EmployeeData/eoPar:Department/ns2:Value"/>
          </DEPARTMENTCODE>
          <DEACTIVATIONDATE>
            <xsl:value-of select="/ns0:EmpPartyStatusModRq/ns0:PartyStatus/eoPar:EffDt"/>
          </DEACTIVATIONDATE>
          <LTLOCKREASON>
            <xsl:value-of select="/ns0:EmpPartyStatusModRq/ns0:PartyStatus/eoPar:StatusDesc"/>
          </LTLOCKREASON>
          <LTUSERTYPE>
            <xsl:value-of select="/ns0:EmpPartyStatusModRq/ns0:Employment/eoPar:EmploymentType"/>
          </LTUSERTYPE>
        </USERBANMODIF>
      </tns:USERBANMODIFRequest>
    </opaq:opaqueElement>
    </xsl:template>
</xsl:stylesheet>
