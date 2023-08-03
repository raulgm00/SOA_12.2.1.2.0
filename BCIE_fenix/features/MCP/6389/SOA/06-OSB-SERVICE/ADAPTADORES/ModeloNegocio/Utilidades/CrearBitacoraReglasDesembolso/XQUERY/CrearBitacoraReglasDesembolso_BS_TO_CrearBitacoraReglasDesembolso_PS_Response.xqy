xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraReglasDesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraReglasDesembolso/V1/Schema/CrearBitacoraReglasDesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearBitacoraReglasDesembolso";
(:: import schema at "../XSD/CrearBitacoraReglasDesembolso_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $TbiReglaDesembolsoCollection as element() (:: schema-element(ns1:TbiReglaDesembolsoCollection) ::) external;

declare function local:func($TbiReglaDesembolsoCollection as element() (:: schema-element(ns1:TbiReglaDesembolsoCollection) ::)) as element() (:: schema-element(ns2:CrearBitacoraReglasDesembolsoResponse) ::) {
    <ns2:CrearBitacoraReglasDesembolsoResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:concat("Id insertado ", fn:data($TbiReglaDesembolsoCollection/ns1:TbiReglaDesembolso/ns1:id))}</res:message>
        </ns2:Resultado>
    </ns2:CrearBitacoraReglasDesembolsoResponse>
};

local:func($TbiReglaDesembolsoCollection)
