<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"   
   xmlns:tns="http://xmlns.banesco.com/eopt/PmtUtilAdd_V1.0"
   xmlns:ns0="http://xmlns.banesco.com/eopt/BillersRulesInq_V1.0"  
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
   exclude-result-prefixes="xsl ns0 tns ns0 eoStatus eoCom"
   xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"   
   xmlns:eoCom="http://xmlns.banesco.com/eo/Common_V1.0" >
   <xsl:template match="/">
      <tns:Fields>
         <xsl:for-each
            select="/ns0:BillersRulesInqRs/ns0:Field[./eoCom:ServiceOper='PmtUtilAddRq' or ./eoCom:ServiceOper='PmtUtilAddRs']">
            <tns:Field>
               <xsl:copy-of select="./*"/>
            </tns:Field>
         </xsl:for-each>
      </tns:Fields>
   </xsl:template>
</xsl:stylesheet>
