xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ConversorMonedasBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $ConsultarSaldoResponse as element() (:: schema-element(ns1:ConsultarSaldoResponse) ::) external;
declare variable $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:func($ConsultarSaldoResponse as element() (:: schema-element(ns1:ConsultarSaldoResponse) ::), 
                            $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::)) 
                            as element() (:: schema-element(ns2:ConvertirMonedasImportesRequest) ::) {
    <ns2:ConvertirMonedasImportesRequest>
        <ns2:ConvierteMonedas>
            <con:monedaDe>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta = 'DESEMBOLSO']/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
            </con:monedaDe>
            <con:monedaA>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno>USD</cat:codigoExterno>
            </con:monedaA>
            <con:monto>{fn:data($ConsultarSaldoResponse/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta = 'SALDO']/com:importe)}</con:monto>
        </ns2:ConvierteMonedas>
    </ns2:ConvertirMonedasImportesRequest>
};

local:func($ConsultarSaldoResponse, $ConsultarDesembolsosByIdResponse)
