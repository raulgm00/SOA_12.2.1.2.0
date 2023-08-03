xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/Comun/Header/V1/HeaderOPN.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $InicioReasignar as element() (:: element(*, ns1:Header) ::) external;

declare function local:func($InicioReasignar as element() (:: element(*, ns1:Header) ::)) as element() (:: schema-element(ns2:ActualizarOperacionRequest) ::) {
    <ns2:ActualizarOperacionRequest>
        <ns2:Operacion>
            <ope:idOperacion>{fn:data($InicioReasignar/ns1:Operacion/ns4:CodigoOperacion)}</ope:idOperacion>
            {
                if ($InicioReasignar/ns1:Operacion/ns4:ResponsableOperacion)
                then <ope:responsable>{fn:data($InicioReasignar/ns1:Operacion/ns4:ResponsableOperacion)}</ope:responsable>
                else ()
            }
            </ns2:Operacion>
        <ns2:reasignar>true</ns2:reasignar>
    </ns2:ActualizarOperacionRequest>
};

local:func($InicioReasignar)
