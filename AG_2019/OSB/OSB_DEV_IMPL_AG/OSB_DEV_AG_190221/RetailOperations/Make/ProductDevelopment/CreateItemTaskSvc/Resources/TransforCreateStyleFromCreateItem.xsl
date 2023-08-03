<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:v1="http://mx.agarcia.ea/Canonical/Commons/Items/v1"
    xmlns:cre="http://mx.agarcia.ea/Capabilities/Core/Items/CreateItemEntity"
    xmlns:v11="http://mx.agarcia.ea/Canonical/Commons/Suppliers/v1"
     exclude-result-prefixes="xs v1 cre v11" version="1.0">
    <xsl:output indent="yes"/>
    <xsl:template match="/">
<cre:createItemRequest xmlns:cre="http://mx.agarcia.ea/Capabilities/Core/Items/CreateItemEntity">
    <cre:items xmlns:v1="http://mx.agarcia.ea/Canonical/Commons/Items/v1" xmlns:v11="http://mx.agarcia.ea/Canonical/Commons/Suppliers/v1">
        <!--Zero or more repetitions:-->
        <v1:item>
            <!--Optional:-->
            <v1:id Name="Prototipos" Type="Style"><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:id"/></v1:id>
             <v1:name><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:name"/></v1:name>
            <v1:description><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:description"/></v1:description>
            <v1:shortDescription><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:shortDescription"/></v1:shortDescription>
            <v1:itemHierarchyInfo>
                <xsl:for-each select="//cre:createItemRequest/cre:items/v1:item/v1:itemHierarchyInfo/v1:hierarchy">
                <!--Zero or more repetitions:-->
                    <v1:hierarchy Type="{v1:code}">
                        <v1:id><xsl:value-of select="v1:id"/></v1:id>
                </v1:hierarchy>
                </xsl:for-each>
            </v1:itemHierarchyInfo>
           
            <v1:owner><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:owner"/></v1:owner>

            <v1:price><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:price"/></v1:price>

            <v1:cost><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:cost"/></v1:cost>

            <v1:itemDetailInfo>
                
                <v1:colorGroup>
                    <!--Optional:-->
                    <v1:id><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:itemDetailInfo/v1:colorGroup/v1:id"/></v1:id>
                    
                </v1:colorGroup>
                <!--Optional:-->
                <v1:sizeGroup>
                    <!--Optional:-->
                    <v1:id><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:itemDetailInfo/v1:sizeGroup/v1:code"/></v1:id>
                    
                </v1:sizeGroup>
                <!--Optional:-->
                <v1:colors>
                    <!--Zero or more repetitions:-->
                    <xsl:for-each select="//cre:createItemRequest/cre:items/v1:item/v1:itemDetailInfo/v1:colors/v1:color">
                    <v1:color>
                        <!--Optional:-->
                        <v1:id><xsl:value-of select="v1:id"/></v1:id>                      
                    </v1:color>
                    </xsl:for-each>
                </v1:colors>
                <!--Optional:-->
                <v1:sizes>
                    <xsl:for-each select="//cre:createItemRequest/cre:items/v1:item/v1:itemDetailInfo/v1:sizes/v1:size">
                    <!--Zero or more repetitions:-->
                    <v1:size>
                        <!--Optional:-->
                        <v1:id><xsl:value-of select="v1:id"/></v1:id>         
                    </v1:size>
                    </xsl:for-each>
                </v1:sizes>
                <!--Optional:-->
                <v1:supplier>
                    <!--Optional:-->
                    <v11:supplierID><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:itemDetailInfo/v1:supplier/v11:supplierID"/></v11:supplierID>
                </v1:supplier>
               
            </v1:itemDetailInfo>
            <!--Optional:-->
            <v1:catalogs>
                <!--Zero or more repetitions:-->
                
                <xsl:for-each select="//cre:createItemRequest/cre:items/v1:item/v1:catalogs/v1:catalog">
                    <v1:catalog Type="{v1:code}">
                    <!--Optional:-->
                    <v1:id><xsl:value-of select="v1:id"/></v1:id>
                  
                </v1:catalog>
                </xsl:for-each>
            </v1:catalogs>
            
            <v1:images>
             
                <v1:itemImage>
                   
                    <v1:url><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:images/v1:itemImage/v1:url"/></v1:url>
                </v1:itemImage>
            </v1:images>
        </v1:item>
    </cre:items>
</cre:createItemRequest>
    </xsl:template>
</xsl:stylesheet>