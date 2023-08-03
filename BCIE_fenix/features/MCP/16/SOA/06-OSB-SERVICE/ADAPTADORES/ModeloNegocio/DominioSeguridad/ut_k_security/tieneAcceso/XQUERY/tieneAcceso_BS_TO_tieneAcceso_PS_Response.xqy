xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SeguridadMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioSeguridad/Schema/SeguridadMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/tieneAcceso";
(:: import schema at "../XSD/tieneAcceso_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $tieneAccesoOutput as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($tieneAccesoOutput as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:TieneAccesoResponse) ::) {
    <ns2:tieneAccesoResponse>
        {
            if ($tieneAccesoOutput/ns1:TIENEACCESO)
            then <ns2:TIENEACCESO>{fn:data($tieneAccesoOutput/ns1:TIENEACCESO)}</ns2:TIENEACCESO>
            else ()
        }
 
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message></res:message>
        </ns2:Resultado>
    </ns2:tieneAccesoResponse>
};

local:func($tieneAccesoOutput)