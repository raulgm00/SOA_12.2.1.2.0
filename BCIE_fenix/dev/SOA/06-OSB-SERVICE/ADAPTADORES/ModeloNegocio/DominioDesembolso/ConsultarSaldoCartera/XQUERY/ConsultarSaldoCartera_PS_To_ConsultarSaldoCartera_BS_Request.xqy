xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarSaldoCartera_DB";
(:: import schema at "../XSD/ConsultarSaldoCartera_DB_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ConsultarSaldoCarteraRequest as element() (:: schema-element(ns1:ConsultarSaldoCarteraRequest) ::) external;

declare function local:func($ConsultarSaldoCarteraRequest as element() (:: schema-element(ns1:ConsultarSaldoCarteraRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($ConsultarSaldoCarteraRequest/ns1:SaldoCartera/lin:idLineaCredito)
            then <ns2:P_LINEA_CREDITO>{fn:data($ConsultarSaldoCarteraRequest/ns1:SaldoCartera/lin:idLineaCredito)}</ns2:P_LINEA_CREDITO>
            else ()
        }
        <ns2:P_LINEA_FINANCIERA>
            <ns2:P_LINEA_FINANCIERA_ITEM>{fn:data($ConsultarSaldoCarteraRequest/ns1:SaldoCartera/des:ListaLineaFinanciera)}</ns2:P_LINEA_FINANCIERA_ITEM>
        </ns2:P_LINEA_FINANCIERA>
        <ns2:P_DESTINO>
            <ns2:P_LINEA_FINANCIERA_ITEM>{fn:data($ConsultarSaldoCarteraRequest/ns1:SaldoCartera/des:ListaDestino)}</ns2:P_LINEA_FINANCIERA_ITEM>
        </ns2:P_DESTINO>
        <ns2:P_MONEDA>
            <ns2:P_LINEA_FINANCIERA_ITEM>{fn:data($ConsultarSaldoCarteraRequest/ns1:SaldoCartera/des:ListaMoneda)}</ns2:P_LINEA_FINANCIERA_ITEM>
        </ns2:P_MONEDA>
        <ns2:P_PAIS>
            <ns2:P_LINEA_FINANCIERA_ITEM>{fn:data($ConsultarSaldoCarteraRequest/ns1:SaldoCartera/des:ListaPaises)}</ns2:P_LINEA_FINANCIERA_ITEM>
        </ns2:P_PAIS>
    </ns2:InputParameters>
};

local:func($ConsultarSaldoCarteraRequest)
