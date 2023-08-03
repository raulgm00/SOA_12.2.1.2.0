xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SeguridadMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioSeguridad/Schema/SeguridadMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/verificarEstadoUsuario";
(:: import schema at "../XSD/verificarEstadoUsuario_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $VerificarEstadoUsuarioOutput as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($VerificarEstadoUsuarioOutput as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:VerificarEstadoUsuarioResponse) ::) {
    <ns2:VerificarEstadoUsuarioResponse>
        {
            if ($VerificarEstadoUsuarioOutput/ns1:PONCODIGORESULTADO)
            then <ns2:CodigoResultado>{fn:data($VerificarEstadoUsuarioOutput/ns1:PONCODIGORESULTADO)}</ns2:CodigoResultado>
            else ()
        }
        {
            if ($VerificarEstadoUsuarioOutput/ns1:PONTIPORESULTADO)
            then <ns2:TipoResultado>{fn:data($VerificarEstadoUsuarioOutput/ns1:PONTIPORESULTADO)}</ns2:TipoResultado>
            else ()
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:data($VerificarEstadoUsuarioOutput/ns1:POVMENSAJE)}</res:message>
        </ns2:Resultado>
    </ns2:VerificarEstadoUsuarioResponse>
};

local:func($VerificarEstadoUsuarioOutput)