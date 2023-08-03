xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearAvisoCobro";
(:: import schema at "../XSD/CrearAvisoCobro_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearAvisoCobroResponse) ::) {
    <ns2:CrearAvisoCobroResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Aviso Creado</res:message>
        </ns2:Resultado>
    </ns2:CrearAvisoCobroResponse>
};

local:func($OutputParameters)
