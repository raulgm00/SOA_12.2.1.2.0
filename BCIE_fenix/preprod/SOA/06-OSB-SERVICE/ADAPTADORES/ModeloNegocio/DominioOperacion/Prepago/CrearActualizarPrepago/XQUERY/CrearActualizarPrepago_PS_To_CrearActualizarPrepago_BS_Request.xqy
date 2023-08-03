xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarPrepago";
(:: import schema at "../XSD/CrearActualizarPrepago_sp.xsd" ::)

declare variable $CalculoInteresPrepago as element() (:: schema-element(ns1:CrearActualizarPrepagoRequest) ::) external;

declare function local:func($CalculoInteresPrepago as element() (:: schema-element(ns1:CrearActualizarPrepagoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_PREPAGO>{fn:data($CalculoInteresPrepago/ns1:P_ID_PREPAGO)}</ns2:P_ID_PREPAGO>
        <ns2:P_FECHA_SOLICITUD>{fn:data($CalculoInteresPrepago/ns1:P_FECHA_SOLICITUD)}</ns2:P_FECHA_SOLICITUD>
        <ns2:P_FECHA_PREPAGO>{fn:data($CalculoInteresPrepago/ns1:P_FECHA_PREPAGO)}</ns2:P_FECHA_PREPAGO>
        <ns2:P_FECHA_RENOVACION>{fn:data($CalculoInteresPrepago/ns1:P_FECHA_RENOVACION)}</ns2:P_FECHA_RENOVACION>
        <ns2:P_MONTO_PREPAGO>{fn:data($CalculoInteresPrepago/ns1:P_MONTO_PREPAGO)}</ns2:P_MONTO_PREPAGO>
        <ns2:P_ID_TCA_TIPO_MONEDA>{fn:data($CalculoInteresPrepago/ns1:P_ID_TCA_TIPO_MONEDA)}</ns2:P_ID_TCA_TIPO_MONEDA>
        <ns2:P_ID_TCA_TIPO_PREPAGO>{fn:data($CalculoInteresPrepago/ns1:P_ID_TCA_TIPO_PREPAGO)}</ns2:P_ID_TCA_TIPO_PREPAGO>
        <ns2:P_ID_TCA_TIPO_RESOLUCION>{fn:data($CalculoInteresPrepago/ns1:P_ID_TCA_TIPO_RESOLUCION)}</ns2:P_ID_TCA_TIPO_RESOLUCION>
        <ns2:P_ID_TCA_TIPO_CAPTURA>{fn:data($CalculoInteresPrepago/ns1:P_ID_TCA_TIPO_CAPTURA)}</ns2:P_ID_TCA_TIPO_CAPTURA>
        <ns2:P_BAN_ESTATUS>{fn:data($CalculoInteresPrepago/ns1:P_BAN_ESTATUS)}</ns2:P_BAN_ESTATUS> 
        <ns2:P_OBSERVACION>{fn:data($CalculoInteresPrepago/ns1:P_OBSERVACION)}</ns2:P_OBSERVACION>
        <ns2:P_LOGIN_USUARIO>{fn:data($CalculoInteresPrepago/ns1:P_LOGIN_USUARIO)}</ns2:P_LOGIN_USUARIO> 
    </ns2:InputParameters>
};

local:func($CalculoInteresPrepago)