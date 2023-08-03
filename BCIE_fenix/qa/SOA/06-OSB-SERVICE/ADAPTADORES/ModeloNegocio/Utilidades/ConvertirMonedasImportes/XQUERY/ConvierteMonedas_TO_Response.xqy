xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ConversorMonedasBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConversorMonedasRequest as element() (:: schema-element(ns1:ConversorMonedasRequest) ::) external;
declare variable $counter as xs:int external;

declare function local:func($ConversorMonedasRequest as element() (:: schema-element(ns1:ConversorMonedasRequest) ::), 
                            $counter as xs:int) 
                            as element() (:: schema-element(ns1:ConvertirMonedasImportesResponse) ::) {
    <ns1:ConvertirMonedasImportesResponse>
        <ns1:ConvierteMonedasImporte>
            <con:monedaDe>{$ConversorMonedasRequest/ns1:ConvierteMonedas[$counter]/con:monedaDe/*}</con:monedaDe>
            <con:monedaA>{$ConversorMonedasRequest/ns1:ConvierteMonedas[$counter]/con:monedaA/*}</con:monedaA>
            <con:monto>{fn:data($ConversorMonedasRequest/ns1:ConvierteMonedas[$counter]/con:monto)}</con:monto>
            <con:montoConvertido>{fn:data($ConversorMonedasRequest/ns1:ConvierteMonedas[$counter]/con:monto)}</con:montoConvertido>
        </ns1:ConvierteMonedasImporte>
    </ns1:ConvertirMonedasImportesResponse>
};

local:func($ConversorMonedasRequest,$counter)
