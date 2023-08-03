<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:cos="http://mx.agarcia.ea/Capabilities/Foundation/Party/CostChangeTask"
    xmlns:v1="http://mx.agarcia.ea/Canonical/Commons/ChangeCost/v1"
    xmlns:v11="http://mx.agarcia.ea/Canonical/Commons/RetailStore/v1"
    xmlns:v12="http://mx.agarcia.ea/Canonical/Commons/Items/v1"
    xmlns:v13="http://mx.agarcia.ea/Canonical/Commons/Suppliers/v1"
    xmlns:ns0="http://msoffice.utils.ea.agarcia.mx/" exclude-result-prefixes="xs ns0" version="2.0">
    <xsl:output indent="yes"/>
    <xsl:template match="/">
        <cos:costChangeExcelParsingResponse>
            <xsl:for-each select="//ns0:parseResponse/return/row">
                <xsl:variable name="dateIni" select="cell[@xPos='4']/data/text()"/>
                <xsl:variable name="dateExp" select="cell[@xPos='5']/data/text()"/>
                <xsl:variable name="ddIni" select="substring($dateIni, 1, 2)"/> 
                <xsl:variable name="mmIni" select="substring($dateIni, 4, 2)"/> 
                <xsl:variable name="yyIni" select="substring($dateIni, 5, 6)"/> 
                <xsl:variable name="ddExp" select="substring($dateExp, 1, 2)"/> 
                <xsl:variable name="mmExp" select="substring($dateExp, 4, 2)"/> 
                <xsl:variable name="yyExp" select="substring($dateExp, 5, 6)"/> 
                
                <cos:event>
                    <xsl:if test="exists(cell[@xPos='4'])">
                        
                        <v1:effectiveDate>
                            <xsl:value-of select="concat($ddIni,'-',$mmIni,'-',$yyIni)"/>
                        </v1:effectiveDate>
                        
                    </xsl:if>
                    <xsl:if test="exists(cell[@xPos='5'])">
                        <v1:expirationDate>
                            <xsl:value-of select="concat($ddExp,'-',$mmExp,'-',$yyExp)"/>
                        </v1:expirationDate>
                    </xsl:if>
                    <xsl:if test="exists(cell[@xPos='3'])">
                        <v1:scope>
                            <xsl:value-of select="cell[@xPos='3']/data/text()"/>
                        </v1:scope>
                    </xsl:if>
                    <xsl:if test="exists(cell[@xPos='6'])">
                        <v1:businessStores>
                            <v1:businessUnit TypeCode="RetailStore">
                                <xsl:value-of select="cell[@xPos='6']/data/text()"/>
                            </v1:businessUnit>
                        </v1:businessStores>
                    </xsl:if>
                    <v1:changes>
                        <v1:change>
                            <xsl:if test="exists(cell[@xPos='9'])">
                                <v1:cost>
                                    <xsl:value-of select="cell[@xPos='9']/data/text()"/>
                                </v1:cost>
                            </xsl:if>
                            <v1:item>
                                <xsl:if test="exists(cell[@xPos='8'])">
                                    <v12:id Type="STYLE">
                                        <xsl:value-of select="cell[@xPos='8']/data/text()"/>
                                    </v12:id>
                                </xsl:if>
                                <xsl:if test="exists(cell[@xPos='7'])">
                                    <v12:id Type="SKU">
                                        <xsl:value-of select="cell[@xPos='7']/data/text()"/>
                                    </v12:id>
                                </xsl:if>
                                <xsl:if test="exists(cell[@xPos='0'])">
                                    <v12:name>
                                        <xsl:value-of select="cell[@xPos='0']/data/text()"/>
                                    </v12:name>
                                </xsl:if>
                            </v1:item>
                        </v1:change>
                    </v1:changes>
                    <v1:supplierID>
                        <xsl:if test="exists(cell[@xPos='1'])">
                            <v13:supplierID>
                                <xsl:value-of select="cell[@xPos='1']/data/text()"/>
                            </v13:supplierID>
                        </xsl:if>
                    </v1:supplierID>
                </cos:event>
            </xsl:for-each>
        </cos:costChangeExcelParsingResponse>
    </xsl:template>
</xsl:stylesheet>