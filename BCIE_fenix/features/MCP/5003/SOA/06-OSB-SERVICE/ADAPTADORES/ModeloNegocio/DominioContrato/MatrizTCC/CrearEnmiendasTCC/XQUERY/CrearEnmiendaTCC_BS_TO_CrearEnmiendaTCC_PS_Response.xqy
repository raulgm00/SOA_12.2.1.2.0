xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearEnmiendasTCC";
(:: import schema at "../XSD/CrearEnmiendasTCC_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $EnmiendaTccCollection as element() (:: schema-element(ns1:EnmiendaTccCollection) ::) external;

declare function local:func($EnmiendaTccCollection as element() (:: schema-element(ns1:EnmiendaTccCollection) ::)) as element() (:: schema-element(ns2:CrearEnmiendaTCCResponse) ::) {
    <ns2:CrearEnmiendaTCCResponse>
        <ns2:idEnmienda>{fn:data($EnmiendaTccCollection/ns1:EnmiendaTcc/ns1:id)}</ns2:idEnmienda>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La inserción se ha realizado correctamente.</res:message>
            
        </ns2:Resultado>
    </ns2:CrearEnmiendaTCCResponse>
};

local:func($EnmiendaTccCollection)