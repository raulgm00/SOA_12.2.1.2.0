xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDetalleAvisoCobro";
(:: import schema at "../XSD/ConsultarDetalleAvisoCobro.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarDetalleAvisoCobroOutputCollection as element() (:: schema-element(ns1:ConsultarDetalleAvisoCobroOutputCollection) ::) external;

declare function local:func($ConsultarDetalleAvisoCobroOutputCollection as element() (:: schema-element(ns1:ConsultarDetalleAvisoCobroOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarDetalleAvisoCobroResponse) ::) {
    <ns2:ConsultarDetalleAvisoCobroResponse>
    {
    for $detalle in $ConsultarDetalleAvisoCobroOutputCollection/ns1:ConsultarDetalleAvisoCobroOutput
    return
        <ns2:DetalleAviso>
            <ges:id>{fn:data($detalle/ns1:ID)}</ges:id>
            <ges:idLote>{fn:data($detalle/ns1:ID_LOTE)}</ges:idLote>
            <ges:idCliente>{fn:data($detalle/ns1:ID_CLIENTE)}</ges:idCliente>
            <ges:responsableCliente>{fn:data($detalle/ns1:RESPONSABLE)}</ges:responsableCliente>
            <ges:Estado>{fn:data($detalle/ns1:ESTADO_EJECUCION)}</ges:Estado>
            <ges:Mensaje_Error>
                <cat:Id>{fn:data($detalle/ns1:ID_TCA_MENSAJE_ERROR)}</cat:Id>
                <cat:Descripcion>{fn:data($detalle/ns1:DESCRIPCION)}</cat:Descripcion>
            </ges:Mensaje_Error>
        </ns2:DetalleAviso>
      }
    <ns2:Resultado>
        <res:result>OK</res:result>
        <res:message>Consulta realizada exitosamente</res:message>
    </ns2:Resultado>
    </ns2:ConsultarDetalleAvisoCobroResponse>
};

local:func($ConsultarDetalleAvisoCobroOutputCollection)
