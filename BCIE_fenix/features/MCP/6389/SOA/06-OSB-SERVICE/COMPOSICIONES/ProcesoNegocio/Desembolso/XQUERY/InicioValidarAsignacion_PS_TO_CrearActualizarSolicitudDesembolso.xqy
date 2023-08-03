xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $inicioValidacionAsignacion as element() (:: schema-element(ns1:InicioValidacionAsignacion) ::) external;

declare function local:func($inicioValidacionAsignacion as element() (:: schema-element(ns1:InicioValidacionAsignacion) ::)) as element() (:: schema-element(ns2:CrearActualizarSolicitudDesembolsoRequest) ::) {
    <ns2:CrearActualizarSolicitudDesembolsoRequest>
        <ns2:SolicitudDesembolso>
            <des:idDesembolso>{fn:data($inicioValidacionAsignacion/ns1:Header/ns3:ParameterType[ns3:parameterName='ID_SOLICITUD']/ns3:parameterValue)}</des:idDesembolso>
            <des:estado>
                <cat:Id>6</cat:Id>
            </des:estado>
        </ns2:SolicitudDesembolso>
    </ns2:CrearActualizarSolicitudDesembolsoRequest>
};

local:func($inicioValidacionAsignacion)
