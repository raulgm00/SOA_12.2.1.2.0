xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarDetalleTransaccion_DB";
(:: import schema at "../XSD/CrearActualizarDetalleTransaccion_DB_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $CrearActualizarDetalleTransaccionRequest as element() (:: schema-element(ns1:CrearActualizarDetalleTransaccionRequest) ::) external;

declare function local:func($CrearActualizarDetalleTransaccionRequest as element() (:: schema-element(ns1:CrearActualizarDetalleTransaccionRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_TRANSACCIONC>{fn:data($CrearActualizarDetalleTransaccionRequest/ns1:TransaccionDesembolso/des:id)}</ns2:P_ID_TRANSACCIONC>
        <ns2:P_ID_CONT_DES>{fn:data($CrearActualizarDetalleTransaccionRequest/ns1:TransaccionDesembolso/des:idDesembolso)}</ns2:P_ID_CONT_DES>
        <ns2:P_PLATAFORMA>{fn:data($CrearActualizarDetalleTransaccionRequest/ns1:TransaccionDesembolso/des:plataforma)}</ns2:P_PLATAFORMA>
        <ns2:P_TRANSACCION>{fn:data($CrearActualizarDetalleTransaccionRequest/ns1:TransaccionDesembolso/des:operacion)}</ns2:P_TRANSACCION>
        
         {
            if ($CrearActualizarDetalleTransaccionRequest/ns1:TransaccionDesembolso/des:estatus)then
        <ns2:P_BAN_ESTATUS>{
        if (string ($CrearActualizarDetalleTransaccionRequest/ns1:TransaccionDesembolso/des:estatus)= 'true')
            then (1) 
            else(0)} </ns2:P_BAN_ESTATUS>
              else()}
        <ns2:P_DETALLETR>
            {
                for $Parameters in $CrearActualizarDetalleTransaccionRequest/ns1:TransaccionDesembolso/des:detalleTransaccion/des:Parameters
                return 
                <ns2:P_DETALLETR_ITEM>
                    <ns2:ID>{fn:data($Parameters/com:id)}</ns2:ID>
                    <ns2:ID_TRANSACCION>{fn:data($CrearActualizarDetalleTransaccionRequest/ns1:TransaccionDesembolso/des:id)}</ns2:ID_TRANSACCION>
                    <ns2:AGRUPADOR>{fn:data($Parameters/com:agrupador)}</ns2:AGRUPADOR>
                    <ns2:PARAMETRO_NOMBRE>{fn:data($Parameters/com:name)}</ns2:PARAMETRO_NOMBRE>
                    <ns2:PARAMETRO_VALOR>{fn:data($Parameters/com:value)}</ns2:PARAMETRO_VALOR>
                </ns2:P_DETALLETR_ITEM>
            }</ns2:P_DETALLETR>
    </ns2:InputParameters>
};

local:func($CrearActualizarDetalleTransaccionRequest)
