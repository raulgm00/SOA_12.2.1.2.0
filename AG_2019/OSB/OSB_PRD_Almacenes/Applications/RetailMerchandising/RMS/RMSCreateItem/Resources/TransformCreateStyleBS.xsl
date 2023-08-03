<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:v1="http://mx.agarcia.ea/Canonical/Commons/Items/v1"
    xmlns:cre="http://mx.agarcia.ea/Capabilities/Core/Items/CreateItemEntity"
    xmlns:v11="http://mx.agarcia.ea/Canonical/Commons/Suppliers/v1"
    exclude-result-prefixes="xs v1 cre v11" version="1.0">
    <xsl:output indent="yes"/>
    <xsl:template match="/">
        
        <rms:InputParameters xmlns:rms="http://xmlns.oracle.com/pcbpel/adapter/db/sp/RMSCreateStyleDBBS">
            
            <rms:I_STYLE><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:id"/></rms:I_STYLE>
            
            <rms:I_DESCRIPTION><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:description"/></rms:I_DESCRIPTION>
            
            <rms:I_SUPPLIER><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:itemDetailInfo/v1:supplier/v11:supplierID"/></rms:I_SUPPLIER>
            
            <rms:I_DEPARTMENT><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:itemHierarchyInfo/v1:hierarchy[@Type='Department']/v1:id"/></rms:I_DEPARTMENT>
            
            <rms:I_CLASS><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:itemHierarchyInfo/v1:hierarchy[@Type='Class']/v1:id"/></rms:I_CLASS>
            
            <rms:I_SUBCLASS><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:itemHierarchyInfo/v1:hierarchy[@Type='Type']/v1:id"/></rms:I_SUBCLASS>
            
            <rms:I_IMAGEURL><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:images/v1:itemImage/v1:url"/></rms:I_IMAGEURL>
            
            <rms:I_COST><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:cost"/></rms:I_COST>
            
            <rms:I_RETAIL><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:price"/></rms:I_RETAIL>
            
            <rms:I_UDA>
                <rms:I_UDA_ITEM><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:catalogs/v1:catalog[@Type='Consumer']/v1:id"/></rms:I_UDA_ITEM>
                <rms:I_UDA_ITEM><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:catalogs/v1:catalog[@Type='Furnishing']/v1:id"/></rms:I_UDA_ITEM>
                <rms:I_UDA_ITEM><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:catalogs/v1:catalog[@Type='Expiration']/v1:id"/></rms:I_UDA_ITEM>
                <rms:I_UDA_ITEM><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:catalogs/v1:catalog[@Type='WeekFloorSale']/v1:id"/></rms:I_UDA_ITEM>
                <rms:I_UDA_ITEM><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:catalogs/v1:catalog[@Type='Classification']/v1:id"/></rms:I_UDA_ITEM>
                <rms:I_UDA_ITEM><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:catalogs/v1:catalog[@Type='Family']/v1:id"/></rms:I_UDA_ITEM>
                <rms:I_UDA_ITEM><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:catalogs/v1:catalog[@Type='Priority']/v1:id"/></rms:I_UDA_ITEM>
                <rms:I_UDA_ITEM><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:catalogs/v1:catalog[@Type='Sticker']/v1:id"/></rms:I_UDA_ITEM>
            </rms:I_UDA>
            
            <rms:I_WHSE>11</rms:I_WHSE>
            
            <rms:I_DIFFGROUP1><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:itemDetailInfo/v1:colorGroup/v1:id"/></rms:I_DIFFGROUP1>
            
            <rms:I_DIFFGROUP2><xsl:value-of select="//cre:createItemRequest/cre:items/v1:item/v1:itemDetailInfo/v1:sizeGroup/v1:id"/></rms:I_DIFFGROUP2>
            
            <rms:I_COLORS>
                <xsl:for-each select="//cre:createItemRequest/cre:items/v1:item/v1:itemDetailInfo/v1:colors/v1:color">
                <rms:I_COLORS_ITEM><xsl:value-of select="v1:id"/></rms:I_COLORS_ITEM>
            </xsl:for-each>
            </rms:I_COLORS>
            
            <rms:I_SIZES>
                <xsl:for-each select="//cre:createItemRequest/cre:items/v1:item/v1:itemDetailInfo/v1:sizes/v1:size">
                    <rms:I_SIZES_ITEM><xsl:value-of select="v1:id"/></rms:I_SIZES_ITEM>
                </xsl:for-each>
            </rms:I_SIZES>
        </rms:InputParameters>
        
    </xsl:template>
    
</xsl:stylesheet>