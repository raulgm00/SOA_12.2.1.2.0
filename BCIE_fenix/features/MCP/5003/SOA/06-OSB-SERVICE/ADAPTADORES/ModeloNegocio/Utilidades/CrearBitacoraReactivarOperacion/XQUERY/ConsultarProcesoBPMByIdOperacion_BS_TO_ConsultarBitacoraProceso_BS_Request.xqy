xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraReactivarOperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraReactivarOperacion/V1/Schema/CrearBitacoraReactivarOperacionMO.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarBitacoraProcesoByIdIOperacion_DB";
(:: import schema at "../../CrearBitacoraProceso/XSD/ConsultarBitacoraProcesoByIdIOperacion_DB.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarProcesosBPMByIdOperacion_DB";
(:: import schema at "../XSD/ConsultarProcesosBPMByIdOperacion_DB.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraReactivarOperacionBO";

declare variable $requestCrearBitacoraReactivarOperacionMessage as element() (:: schema-element(ns1:requestCrearBitacoraReactivarOperacionMessage) ::) external;
declare variable $ConsultarProcesosBPMByIdOperacion_DBOutputCollection as element() (:: schema-element(ns2:ConsultarProcesosBPMByIdOperacion_DBOutputCollection) ::) external;
declare variable $index as xs:integer external;

declare function local:func(
                            $ConsultarProcesosBPMByIdOperacion_DBOutputCollection as element() (:: schema-element(ns2:ConsultarProcesosBPMByIdOperacion_DBOutputCollection) ::), 
                            $index as xs:integer) 
                            as element() (:: schema-element(ns3:ConsultarBitacoraProcesoByIdIOperacion_DBInput) ::) {
    <ns3:ConsultarBitacoraProcesoByIdIOperacion_DBInput>
        <ns3:IDOPERACION>{fn:data($ConsultarProcesosBPMByIdOperacion_DBOutputCollection/ns2:ConsultarProcesosBPMByIdOperacion_DBOutput[$index]/ns2:ID_OPERACION)}</ns3:IDOPERACION>
        <ns3:IDPROCESO>{fn:data($ConsultarProcesosBPMByIdOperacion_DBOutputCollection/ns2:ConsultarProcesosBPMByIdOperacion_DBOutput[$index]/ns2:ID_PROCESO)}</ns3:IDPROCESO>
    </ns3:ConsultarBitacoraProcesoByIdIOperacion_DBInput>
};

local:func($ConsultarProcesosBPMByIdOperacion_DBOutputCollection, $index)
