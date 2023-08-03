xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare variable $ExceptuarSolicitudRequest as element() (:: schema-element(ns1:ExceptuarSolicitudRequest) ::) external;
declare variable $ConsultarExcepcionSolicitudResponse as element() (:: schema-element(ns1:ConsultarExcepcionSolicitudResponse) ::) external;

declare function local:func($ExceptuarSolicitudRequest as element() (:: schema-element(ns1:ExceptuarSolicitudRequest) ::), 
                            $ConsultarExcepcionSolicitudResponse as element() (:: schema-element(ns1:ConsultarExcepcionSolicitudResponse) ::)) 
                            as element() (:: schema-element(ns1:CrearActualizarExcepcionSolicitudRequest) ::) {
    <ns1:CrearActualizarExcepcionSolicitudRequest>
    {
    for $solicitud in $ConsultarExcepcionSolicitudResponse/ns1:SolicitudDesembolso
    where $solicitud/des:Excepcion/des:instanciaProceso = $ExceptuarSolicitudRequest/ns1:instanciaProceso
    return
        <ns1:Excepcion>
            <reg:Id>{fn:data($solicitud/des:Excepcion/reg:Id)}</reg:Id>
            <reg:Exceptuado>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus>{true()}</cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </reg:Exceptuado>
            <des:instanciaProceso>{fn:data($solicitud/des:Excepcion/des:instanciaProceso)}</des:instanciaProceso>
            <des:enProcesoExcepcion>{fn:data($solicitud/des:Excepcion/des:enProcesoExcepcion)}</des:enProcesoExcepcion>
        </ns1:Excepcion>
      }
    </ns1:CrearActualizarExcepcionSolicitudRequest>
};

local:func($ExceptuarSolicitudRequest, $ConsultarExcepcionSolicitudResponse)
