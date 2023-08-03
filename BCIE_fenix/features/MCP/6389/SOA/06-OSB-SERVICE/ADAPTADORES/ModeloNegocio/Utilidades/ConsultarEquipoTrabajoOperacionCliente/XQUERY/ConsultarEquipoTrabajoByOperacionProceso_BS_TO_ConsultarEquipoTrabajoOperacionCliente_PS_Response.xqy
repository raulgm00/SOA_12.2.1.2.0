xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarEquipoTrabajoOperacionClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarEquipoTrabajoOperacionCliente/V1/Schema/ConsultarEquipoTrabajoOperacionClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarEquipoTrabajoByOperacionProceso";
(:: import schema at "../../ConsultarEquipoTrabajoByOperacionProceso/XSD/ConsultarEquipoTrabajoByOperacionProceso_table.xsd" ::)

declare namespace ns3 = "http://www.bcie.org/EqipoTrabajo/V1";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $EquipoTrabajoCollection as element() (:: schema-element(ns1:EquipoTrabajoCollection) ::) external;

declare function local:func($EquipoTrabajoCollection as element() (:: schema-element(ns1:EquipoTrabajoCollection) ::)) as element() (:: schema-element(ns2:ConsultarEquipoTrabajoOperacionClienteResponse) ::) {
    <ns2:ConsultarEquipoTrabajoOperacionClienteResponse>
        <ns2:listadoEquipoTrabajo>
            {
                for $EquipoTrabajo in $EquipoTrabajoCollection/ns1:EquipoTrabajo
                return 
                <ns3:equipoTrabajo>
                    <ns3:IdRol>{fn:data($EquipoTrabajo/ns1:idTcaRolBpm)}</ns3:IdRol>
                    <ns3:idProceso>{fn:data($EquipoTrabajo/ns1:idTcaProcesoBpm)}</ns3:idProceso>
                    <ns3:usuario>{fn:data($EquipoTrabajo/ns1:usuario)}</ns3:usuario>
                </ns3:equipoTrabajo>
            }
        </ns2:listadoEquipoTrabajo>
        <ns2:Resultado>
            <res:result>OK</res:result>
        </ns2:Resultado>
    </ns2:ConsultarEquipoTrabajoOperacionClienteResponse>
};

local:func($EquipoTrabajoCollection)
