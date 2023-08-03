xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CalcularCuotas_DB";
(:: import schema at "../XSD/CalcularCuotas_DB_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $CalcularCuotasRequest as element() (:: schema-element(ns1:CalcularCuotasRequest) ::) external;

declare function local:func($CalcularCuotasRequest as element() (:: schema-element(ns1:CalcularCuotasRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:VDFECHA_INICIO>{fn:data($CalcularCuotasRequest/ns1:Plazo/des:FechaInicio)}</ns2:VDFECHA_INICIO>
        {
            if ($CalcularCuotasRequest/ns1:Plazo/des:Frecuencia/com:Tipo/cat:codigoExterno)
            then <ns2:VVTIPO_FRECUENCIA>{fn:data($CalcularCuotasRequest/ns1:Plazo/des:Frecuencia/com:Tipo/cat:codigoExterno)}</ns2:VVTIPO_FRECUENCIA>
            else ()
        }
        <ns2:VNFRECUENCIA>{fn:data($CalcularCuotasRequest/ns1:Plazo/des:Frecuencia/com:Valor)}</ns2:VNFRECUENCIA>
        <ns2:PDFECHAVENCIMIENTO>{fn:data($CalcularCuotasRequest/ns1:FechaVencimiento)}</ns2:PDFECHAVENCIMIENTO>
        <ns2:PNFLAGS>1</ns2:PNFLAGS>
    </ns2:InputParameters>
};

local:func($CalcularCuotasRequest)