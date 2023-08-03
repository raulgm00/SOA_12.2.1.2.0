xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/UpSertGestionCobro";
(:: import schema at "../XSD/UpSertGestionCobro_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $LoteGestionCobroCollection as element() (:: schema-element(ns1:LoteGestionCobroCollection) ::) external;

declare function local:func($LoteGestionCobroCollection as element() (:: schema-element(ns1:LoteGestionCobroCollection) ::)) as element() (:: schema-element(ns2:UpSertGestionCobroResponse) ::) {
    <ns2:UpSertGestionCobroResponse>
        <ns2:idLote>{fn:data($LoteGestionCobroCollection/ns1:LoteGestionCobro/ns1:id)}</ns2:idLote>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Operacion exitosa</res:message>
        </ns2:Resultado>
    </ns2:UpSertGestionCobroResponse>
};

local:func($LoteGestionCobroCollection)
