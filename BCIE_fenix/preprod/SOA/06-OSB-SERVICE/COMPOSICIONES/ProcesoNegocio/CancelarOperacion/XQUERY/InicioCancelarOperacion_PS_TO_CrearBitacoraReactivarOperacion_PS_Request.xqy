xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraReactivarOperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraReactivarOperacion/V1/Schema/CrearBitacoraReactivarOperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CancelarOperacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA05/CancelarOperacionProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraReactivarOperacionBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioCancelarOperacion as element() (:: schema-element(ns1:InicioCancelarOperacion) ::) external;

declare function local:func($InicioCancelarOperacion as element() (:: schema-element(ns1:InicioCancelarOperacion) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraReactivarOperacionMessage) ::) {
    <ns2:requestCrearBitacoraReactivarOperacionMessage>
        <ns2:Bitacora>
            <cre:id> </cre:id>
            <cre:idOperacionProceso></cre:idOperacionProceso>
            <cre:idOperacion>{fn:data($InicioCancelarOperacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($InicioCancelarOperacion/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso></cre:idProceso>
            <cre:responsableOperacion>{fn:data($InicioCancelarOperacion/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:responsableOperacion>
            <cre:ban_reactivado></cre:ban_reactivado>
            <cre:fecha_registro></cre:fecha_registro>
            <cre:fecha_reactivacion></cre:fecha_reactivacion>
        </ns2:Bitacora>
    </ns2:requestCrearBitacoraReactivarOperacionMessage>
};

local:func($InicioCancelarOperacion)
