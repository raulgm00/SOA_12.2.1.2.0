xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarTransferenciaBancariaDB";
(:: import schema at "../XSD/ConsultarTransferenciaBancariaDB_sp.xsd" ::)

declare variable $ConsultarTransferenciasBancariasByIdRequest as element() (:: schema-element(ns1:ConsultarTransferenciasBancariasByIdRequest) ::) external;

declare function local:func($ConsultarTransferenciasBancariasByIdRequest as element() (:: schema-element(ns1:ConsultarTransferenciasBancariasByIdRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($ConsultarTransferenciasBancariasByIdRequest/ns1:idDesembolso)
            then <ns2:P_ID_DESEMBOLSO>{fn:data($ConsultarTransferenciasBancariasByIdRequest/ns1:idDesembolso)}</ns2:P_ID_DESEMBOLSO>
            else ()
        }
        {
            if ($ConsultarTransferenciasBancariasByIdRequest/ns1:idTransferencia)
            then <ns2:P_ID_TRANSFERENCIA>{fn:data($ConsultarTransferenciasBancariasByIdRequest/ns1:idTransferencia)}</ns2:P_ID_TRANSFERENCIA>
            else ()
        }
        {
            if ($ConsultarTransferenciasBancariasByIdRequest/ns1:idConsolidacionPadre)
            then <ns2:P_ID_CONSOLIDACION_PADRE>{fn:data($ConsultarTransferenciasBancariasByIdRequest/ns1:idConsolidacionPadre)}</ns2:P_ID_CONSOLIDACION_PADRE>
            else ()
        }
        
    </ns2:InputParameters>
};

local:func($ConsultarTransferenciasBancariasByIdRequest)
