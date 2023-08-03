xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDetalleTransaccionDesembolso_db";
(:: import schema at "../XSD/ConsultarDetalleTransaccionByIdDesembolso.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarDetalleTransaccionDesembolso_dbOutputCollection as element() (:: schema-element(ns1:ConsultarDetalleTransaccionDesembolso_dbOutputCollection) ::) external;

declare function local:func($ConsultarDetalleTransaccionDesembolso_dbOutputCollection as element() (:: schema-element(ns1:ConsultarDetalleTransaccionDesembolso_dbOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarDetalleTransaccionResponse) ::) {
    <ns2:ConsultarDetalleTransaccionResponse>
    {
    if (count($ConsultarDetalleTransaccionDesembolso_dbOutputCollection/ns1:ConsultarDetalleTransaccionDesembolso_dbOutput)>0)
    then
        for $idTransaccion in distinct-values($ConsultarDetalleTransaccionDesembolso_dbOutputCollection/ns1:ConsultarDetalleTransaccionDesembolso_dbOutput/ns1:ID_TRANSACCION)
        let $transaccion := $ConsultarDetalleTransaccionDesembolso_dbOutputCollection/ns1:ConsultarDetalleTransaccionDesembolso_dbOutput[ns1:ID_TRANSACCION = $idTransaccion][1]
        return
        <ns2:TransaccionDesembolso>
            <des:id>{fn:data($idTransaccion)}</des:id>
            <des:idDesembolso>{fn:data($transaccion/ns1:ID_CONTRATO_DESEMBOLSO)}</des:idDesembolso>
            <des:plataforma>{fn:data($transaccion/ns1:PLATAFORMA)}</des:plataforma>
            <des:operacion>{fn:data($transaccion/ns1:TRANSACCION)}</des:operacion>
            <des:fechaRegistro>{fn:data($transaccion/ns1:FECHA_REGISTRO)}</des:fechaRegistro>
            <des:estatus>{fn:data($transaccion/ns1:BAN_ESTATUS)}</des:estatus>
            <des:detalleTransaccion>
              {for $i in $ConsultarDetalleTransaccionDesembolso_dbOutputCollection/ns1:ConsultarDetalleTransaccionDesembolso_dbOutput[ns1:ID_TRANSACCION = $idTransaccion]
                return
                <des:Parameters>
                    <com:name>{fn:data($i/ns1:PARAMETRO_NOMBRE)}</com:name>
                    <com:value>{fn:data($i/ns1:PARAMETRO_VALOR)}</com:value>
                    <com:id>{fn:data($i/ns1:ID_DETALLE_TRANSACCION)}</com:id>
                    <com:agrupador>{fn:data($i/ns1:AGRUPADOR)}</com:agrupador>
                </des:Parameters>
              }   
            </des:detalleTransaccion>
        </ns2:TransaccionDesembolso>
        else()
        }
        <ns2:Resultado>
              <res:result>OK</res:result>
              <res:message>{
              if (count($ConsultarDetalleTransaccionDesembolso_dbOutputCollection/ns1:ConsultarDetalleTransaccionDesembolso_dbOutput)>0)
              then ('Consulta Exitosa')
              else('Consulta sin resultados')
              }</res:message>
        </ns2:Resultado>
    </ns2:ConsultarDetalleTransaccionResponse>
};

local:func($ConsultarDetalleTransaccionDesembolso_dbOutputCollection)
