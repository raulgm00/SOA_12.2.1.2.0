xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarEquipoTrabajoOperacionClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarEquipoTrabajoOperacionCliente/V1/Schema/ConsultarEquipoTrabajoOperacionClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEquipoTrabajoByClienteProceso";
(:: import schema at "../../ConsultarEquipoTrabajoByClienteProceso/XSD/ConsultarEquipoTrabajoByClienteProceso.xsd" ::)

declare namespace ns3 = "http://www.bcie.org/EqipoTrabajo/V1";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarEquipoTrabajoByClienteProcesoOutputCollection as element() (:: schema-element(ns1:ConsultarEquipoTrabajoByClienteProcesoOutputCollection) ::) external;

declare function local:func($ConsultarEquipoTrabajoByClienteProcesoOutputCollection as element() (:: schema-element(ns1:ConsultarEquipoTrabajoByClienteProcesoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarEquipoTrabajoOperacionClienteResponse) ::) {
    <ns2:ConsultarEquipoTrabajoOperacionClienteResponse>
        <ns2:listadoEquipoTrabajo>
            {
                for $ConsultarEquipoTrabajoByClienteProcesoOutput in $ConsultarEquipoTrabajoByClienteProcesoOutputCollection/ns1:ConsultarEquipoTrabajoByClienteProcesoOutput
                return 
                <ns3:equipoTrabajo>
                    <ns3:IdRol>{fn:data($ConsultarEquipoTrabajoByClienteProcesoOutput/ns1:ID_TCA_ROL_BPM)}</ns3:IdRol>
                    <ns3:idProceso>{fn:data($ConsultarEquipoTrabajoByClienteProcesoOutput/ns1:ID_TCA_PROCESO_BPM)}</ns3:idProceso>
                    <ns3:usuario>{fn:data($ConsultarEquipoTrabajoByClienteProcesoOutput/ns1:USUARIO)}</ns3:usuario>
                    </ns3:equipoTrabajo>
            }
        </ns2:listadoEquipoTrabajo>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns2:Resultado>
    </ns2:ConsultarEquipoTrabajoOperacionClienteResponse>
};

local:func($ConsultarEquipoTrabajoByClienteProcesoOutputCollection)
