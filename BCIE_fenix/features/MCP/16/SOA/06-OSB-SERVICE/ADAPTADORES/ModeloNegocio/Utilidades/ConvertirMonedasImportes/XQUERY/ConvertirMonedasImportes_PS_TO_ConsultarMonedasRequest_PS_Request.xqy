xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ConversorMonedasBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";
declare variable $counter as xs:int external;

declare variable $ConvertirMonedasImportesRequest as element() (:: schema-element(ns1:ConvertirMonedasImportesRequest) ::) external;

declare function local:func($ConvertirMonedasImportesRequest as element() (:: schema-element(ns1:ConvertirMonedasImportesRequest) ::), $counter as xs:int) as element() (:: schema-element(ns1:ConversorMonedasRequest) ::) {
    
    <ns1:ConversorMonedasRequest>
        <ns1:ConvierteMonedas>
            <con:monedaDe>
                <cat:Id> </cat:Id>
                 <cat:Descripcion> </cat:Descripcion>
                <cat:DescripcionCorta> </cat:DescripcionCorta>
                   <cat:estatus> </cat:estatus>
               <cat:codigoExterno>{fn:data($ConvertirMonedasImportesRequest/ns1:ConvierteMonedas[$counter]/con:monedaDe/cat:codigoExterno)}</cat:codigoExterno>
               
            </con:monedaDe>
            <con:monedaA>
                <cat:Id> </cat:Id>
                   <cat:Descripcion> </cat:Descripcion>
               <cat:DescripcionCorta> </cat:DescripcionCorta>
                <cat:estatus> </cat:estatus>
              <cat:codigoExterno>{fn:data($ConvertirMonedasImportesRequest/ns1:ConvierteMonedas[$counter]/con:monedaA/cat:codigoExterno)}</cat:codigoExterno>
               
            </con:monedaA>
            <con:monto>{fn:data($ConvertirMonedasImportesRequest/ns1:ConvierteMonedas[$counter]/con:monto)}</con:monto>
        </ns1:ConvierteMonedas>
    </ns1:ConversorMonedasRequest>
};

local:func($ConvertirMonedasImportesRequest,$counter)
