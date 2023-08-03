xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearFT05_DB";
(:: import schema at "../XSD/CrearFT05_DB_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $CrearActualizarTransferenciaFT05Request as element() (:: schema-element(ns1:CrearActualizarTransferenciaFT05Request) ::) external;

declare function local:func($CrearActualizarTransferenciaFT05Request as element() (:: schema-element(ns1:CrearActualizarTransferenciaFT05Request) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_DESEMBOLSO>{fn:data($CrearActualizarTransferenciaFT05Request/ns1:TransferenciaFT05/des:idDesembolso)}</ns2:P_ID_DESEMBOLSO>
        <ns2:P_ID_TRANSFERENCIA_FT05>{fn:data($CrearActualizarTransferenciaFT05Request/ns1:TransferenciaFT05/des:idTransferenciaFT05)}</ns2:P_ID_TRANSFERENCIA_FT05>
        {
            if ($CrearActualizarTransferenciaFT05Request/ns1:TransferenciaFT05/des:idFacturador)
            then <ns2:P_BHQ_TRANSFERENCIA>{fn:data($CrearActualizarTransferenciaFT05Request/ns1:TransferenciaFT05/des:idFacturador)}</ns2:P_BHQ_TRANSFERENCIA>
            else ()
        }
    </ns2:InputParameters>
};

local:func($CrearActualizarTransferenciaFT05Request)
