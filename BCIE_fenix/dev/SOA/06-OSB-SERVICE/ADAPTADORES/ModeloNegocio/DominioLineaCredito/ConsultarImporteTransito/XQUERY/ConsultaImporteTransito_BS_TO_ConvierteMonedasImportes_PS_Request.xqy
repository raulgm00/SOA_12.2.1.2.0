xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/Consultar_Importe_Transito_db";
(:: import schema at "../XSD/Consultar_Importe_Transito_db_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/ConversorMonedasBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConvertirMonedasImportesRequest) ::) {
    <ns2:ConvertirMonedasImportesRequest>
     {
            for $montoTransito in $OutputParameters/ns1:RECORDSET/ns1:Row
            return
        <ns2:ConvierteMonedas>
            <con:monedaDe>
                <cat:codigoExterno>{fn:data($montoTransito/ns1:Column[@name='COD_EXTERNO'])}</cat:codigoExterno>
            </con:monedaDe>
            <con:monedaA>
                <cat:codigoExterno>USD</cat:codigoExterno>
            </con:monedaA>
            <con:monto>{fn:data($montoTransito/ns1:Column[@name='MONTO_DESEMBOLSAR'])}</con:monto>
        </ns2:ConvierteMonedas>
        }
    </ns2:ConvertirMonedasImportesRequest>
};

local:func($OutputParameters)
