xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/FENIX/SP_CALCULA_FECHA_VENCIMIENTO/";
(:: import schema at "../XSD/CalcularFechaVencimiento_sp.xsd" ::)

declare variable $CalcularFechaVencimientoRequest as element() (:: schema-element(ns1:CalcularFechaVencimientoRequest) ::) external;

declare function local:func($CalcularFechaVencimientoRequest as element() (:: schema-element(ns1:CalcularFechaVencimientoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_OPERACION>{fn:data($CalcularFechaVencimientoRequest/ns1:idOperacion)}</ns2:P_ID_OPERACION>
        <ns2:P_ID_ID_TCA_TERMINO>{fn:data($CalcularFechaVencimientoRequest/ns1:idTcaTermino)}</ns2:P_ID_ID_TCA_TERMINO>
        <ns2:V_FECHA_INICIO>{fn:data($CalcularFechaVencimientoRequest/ns1:FechaInicio)}</ns2:V_FECHA_INICIO>
        <ns2:P_LOGIN_MODIFICA>{fn:data($CalcularFechaVencimientoRequest/ns1:loginUsuario)}</ns2:P_LOGIN_MODIFICA>
        <ns2:P_NOMBRE_MODIFICA>{fn:data($CalcularFechaVencimientoRequest/ns1:NombreUsuario)}</ns2:P_NOMBRE_MODIFICA>
    </ns2:InputParameters>
};

local:func($CalcularFechaVencimientoRequest)
