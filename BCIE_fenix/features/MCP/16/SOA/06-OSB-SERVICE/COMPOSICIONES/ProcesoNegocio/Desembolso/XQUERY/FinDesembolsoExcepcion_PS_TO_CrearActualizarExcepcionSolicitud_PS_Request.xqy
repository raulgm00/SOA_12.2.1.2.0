xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinDesembolsoExcepcion as element() (:: schema-element(ns1:FinDesembolsoExcepcion) ::) external;
declare variable $ConsultarExcepcionSolicitudResponse as element() (:: schema-element(ns2:ConsultarExcepcionSolicitudResponse) ::) external;

declare function local:func($FinDesembolsoExcepcion as element() (:: schema-element(ns1:FinDesembolsoExcepcion) ::), 
                            $ConsultarExcepcionSolicitudResponse as element() (:: schema-element(ns2:ConsultarExcepcionSolicitudResponse) ::)) 
                            as element() (:: schema-element(ns2:CrearActualizarExcepcionSolicitudRequest) ::) {
    <ns2:CrearActualizarExcepcionSolicitudRequest>
    {
    for $solicitud in $ConsultarExcepcionSolicitudResponse/ns2:SolicitudDesembolso
    where string($solicitud/des:Excepcion/des:instanciaProceso) = $FinDesembolsoExcepcion/ns1:Header/ns3:Proceso/ns4:InstanciaProceso
    return
        <ns2:Excepcion>
            <reg:Id>{fn:data($solicitud/des:Excepcion/reg:Id)}</reg:Id>
            <reg:Exceptuado>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus>{true()}</cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </reg:Exceptuado>
            <des:instanciaProceso>{fn:data($solicitud/des:Excepcion/des:instanciaProceso)}</des:instanciaProceso>
            <des:enProcesoExcepcion>{false()}</des:enProcesoExcepcion>
        </ns2:Excepcion>
        }
    </ns2:CrearActualizarExcepcionSolicitudRequest>
};

local:func($FinDesembolsoExcepcion, $ConsultarExcepcionSolicitudResponse)	