xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://mx.agarcia.ea/Applications/Prototipos/CreateSKU";
(:: import schema at "AGCreateSKU.xsd" ::)

declare variable $createSKURequest as element() (:: schema-element(ns1:createSKURequest) ::) external;

declare function local:skuPair ($cad as xs:string, $createSKURequest as element() (:: schema-element(ns1:createSKURequest) ::), $pos as xs:integer) as xs:string {

  
  if( $pos <= count($createSKURequest/ns1:skuList/ns1:skuData) )then
    local:skuPair (fn:concat($cad,'"',$createSKURequest/ns1:skuList/ns1:skuData[$pos]/ns1:size,'": " ',$createSKURequest/ns1:skuList/ns1:skuData[$pos]/ns1:sku, '", '),$createSKURequest, $pos + 1)
  else 
    fn:substring($cad,1, fn:string-length($cad)-2)
};

declare function local:getJson($createSKURequest as element() (:: schema-element(ns1:createSKURequest) ::)) as xs:string {
    fn:concat('{',
                '"estilo": "',$createSKURequest/ns1:estilo, '",',
                '"sku": {',
                 local:skuPair ('',$createSKURequest, 1),
                '}',
              '}')
};

declare function local:func($createSKURequest as element() (:: schema-element(ns1:createSKURequest) ::)) as text() {
    
    text{local:getJson($createSKURequest)}
};
local:func($createSKURequest)