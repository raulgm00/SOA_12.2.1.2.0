xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearTreCategoriaCondicion";
(:: import schema at "../XSD/CrearTreCategoriaCondicion_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $TreCategoriaCondicionCollection as element() (:: schema-element(ns1:TreCategoriaCondicionCollection) ::) external;

declare function local:func($TreCategoriaCondicionCollection as element() (:: schema-element(ns1:TreCategoriaCondicionCollection) ::)) as element() (:: element(*, ns2:CrearTreCategoriaCondicionResponseType) ::) {
    <ns2:CrearTreCategoriaCondicionResponseType>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La inserción se ha realizado correctamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:CrearTreCategoriaCondicionResponseType>
};

local:func($TreCategoriaCondicionCollection)
