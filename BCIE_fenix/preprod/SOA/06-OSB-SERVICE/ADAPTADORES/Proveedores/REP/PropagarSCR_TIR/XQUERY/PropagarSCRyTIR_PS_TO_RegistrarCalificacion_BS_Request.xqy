xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/registrarCalificacion";
(:: import schema at "../../RegistrarSCRREP/XSD/registrarCalificacion_sp.xsd" ::)

declare variable $bhq as xs:string external;
declare variable $scr as xs:string external;
declare variable $estadoscr as xs:string external;
declare variable $observacion as xs:string external;
declare variable $fecha as xs:string external;
declare variable $usuariomodifico as xs:string external;
declare variable $usuariovalido as xs:string external;

declare function local:func($bhq as xs:string, 
                            $scr as xs:string, 
                            $estadoscr as xs:string, 
                            $observacion as xs:string, 
                            $fecha as xs:string, 
                            $usuariomodifico as xs:string, 
                            $usuariovalido as xs:string) 
                            as element() (:: schema-element(ns1:InputParameters) ::) {
    <ns1:InputParameters>
        <ns1:BHQ>{fn:data($bhq)}</ns1:BHQ>
        <ns1:SCR>{fn:data($scr)}</ns1:SCR>
        <ns1:ESTADOSCR>{fn:data($estadoscr)}</ns1:ESTADOSCR>
        <ns1:OBSERVACION>{fn:data($observacion)}</ns1:OBSERVACION>
        <ns1:FECHA>{fn:data($fecha)}</ns1:FECHA>
        <ns1:USUARIOMODIFICO>{fn:data($usuariomodifico)}</ns1:USUARIOMODIFICO>
        <ns1:USUARIOVALIDO>{fn:data($usuariovalido)}</ns1:USUARIOVALIDO>
    </ns1:InputParameters>
};

local:func($bhq, $scr, $estadoscr, $observacion, $fecha, $usuariomodifico, $usuariovalido)