xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarDetalleTransaccion_DB";
(:: import schema at "../XSD/CrearActualizarDetalleTransaccion_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearActualizarDetalleTransaccionResponse) ::) {
    <ns2:CrearActualizarDetalleTransaccionResponse>
        <ns2:TransaccionDesembolso>
            <des:id>{fn:data($OutputParameters/ns1:P_DETALLETR_OUT/ns1:P_DETALLETR_ITEM[1]/ns1:ID_TRANSACCION)}
          
            </des:id>
            <des:idDesembolso></des:idDesembolso>
            <des:plataforma></des:plataforma>
            <des:operacion></des:operacion>
            <des:fechaRegistro></des:fechaRegistro>
            <des:estatus></des:estatus>
            <des:detalleTransaccion>
                {
                    for $P_DETALLETR_ITEM in $OutputParameters/ns1:P_DETALLETR_OUT/ns1:P_DETALLETR_ITEM
                    return 
                    <des:Parameters>
                    
                    <com:id>{fn:data($P_DETALLETR_ITEM/ns1:ID)}</com:id>
                    </des:Parameters>
                }</des:detalleTransaccion>
        </ns2:TransaccionDesembolso>
         <ns2:Resultado>
            {
        if(fn:data($OutputParameters/ns1:P_CODIGO_RES) = 1) then
            <res:result>OK</res:result>   
        else
             <res:result>ERROR</res:result>
            }
            <res:message>{fn:data($OutputParameters/ns1:P_MENSAJE)}</res:message>
        </ns2:Resultado>
    </ns2:CrearActualizarDetalleTransaccionResponse>
};

local:func($OutputParameters)
