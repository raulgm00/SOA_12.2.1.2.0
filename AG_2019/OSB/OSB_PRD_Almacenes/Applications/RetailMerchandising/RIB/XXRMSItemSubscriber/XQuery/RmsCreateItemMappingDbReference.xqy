xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://mx.agarcia.ea/Capabilities/Core/Items/CreateItemEntity";
(:: import schema at "../../../../../Capabilities/Core/Items/CreateItemEntitySvc/Schemas/CreateItemEntitySchema.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/RMSCreateItemDbReference";
(:: import schema at "../Resources/JCA/RMSCrearEstilo/RMSCreateItemDbReference_sp.xsd" ::)

declare namespace ns3 = "http://mx.agarcia.ea/Canonical/Commons/Items/v1";

declare namespace ns4 = "http://mx.agarcia.ea/Canonical/Commons/Suppliers/v1";

declare variable $RmsCreateItemReq as element() (:: schema-element(ns1:createItemRequest) ::) external;

declare function local:RmsCreateItemMappingDbReference($RmsCreateItemReq as element() (:: schema-element(ns1:createItemRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($RmsCreateItemReq/ns1:items/ns3:item/ns3:itemDetailInfo/ns3:styleID)
            then <ns2:I_STYLE>{fn:data($RmsCreateItemReq/ns1:items/ns3:item/ns3:itemDetailInfo/ns3:styleID)}</ns2:I_STYLE>
            else ()
        }
        {
            if ($RmsCreateItemReq/ns1:items/ns3:item/ns3:itemDetailInfo/ns3:supplier/ns4:supplierID/@type)
            then <ns2:I_SUPPLIER>{fn:data($RmsCreateItemReq/ns1:items/ns3:item/ns3:itemDetailInfo/ns3:supplier/ns4:supplierID/@type)}</ns2:I_SUPPLIER>
            else ()
        }
        {
            if ($RmsCreateItemReq/ns1:items/ns3:item/ns3:images/ns3:itemImage/ns3:url)
            then <ns2:I_IMAGEURL>{fn:data($RmsCreateItemReq/ns1:items/ns3:item/ns3:images/ns3:itemImage/ns3:url)}</ns2:I_IMAGEURL>
            else ()
        }
    </ns2:InputParameters>
};

local:RmsCreateItemMappingDbReference($RmsCreateItemReq)