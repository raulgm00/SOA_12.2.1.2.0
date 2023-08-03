xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ValidarInicioAutomaticoFormalizacion";
(:: import schema at "../XSD/ValidarInicioAutomaticoFormalizacion.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ValidarInicioAutomaticoFormalizacionOutput as element() (:: schema-element(ns1:ValidarInicioAutomaticoFormalizacionOutputCollection) ::) external;

declare function local:func($ValidarInicioAutomaticoFormalizacionOutput as element() (:: schema-element(ns1:ValidarInicioAutomaticoFormalizacionOutputCollection) ::)) as element() (:: schema-element(ns2:ValidarInicioFormalizacionResponse) ::) {
    <ns2:ValidarInicioFormalizacionResponse>
        {
            if ($ValidarInicioAutomaticoFormalizacionOutput/ns1:ValidarInicioAutomaticoFormalizacionOutput/ns1:INICIO_AUTOMATICO) then
                <ns2:InicioAutomatico>{fn:data($ValidarInicioAutomaticoFormalizacionOutput/ns1:ValidarInicioAutomaticoFormalizacionOutput/ns1:INICIO_AUTOMATICO)}</ns2:InicioAutomatico>
            else
                <ns2:InicioAutomatico>1</ns2:InicioAutomatico>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta exitosa</res:message>
        </ns2:Resultado>
    </ns2:ValidarInicioFormalizacionResponse>
};

local:func($ValidarInicioAutomaticoFormalizacionOutput)
