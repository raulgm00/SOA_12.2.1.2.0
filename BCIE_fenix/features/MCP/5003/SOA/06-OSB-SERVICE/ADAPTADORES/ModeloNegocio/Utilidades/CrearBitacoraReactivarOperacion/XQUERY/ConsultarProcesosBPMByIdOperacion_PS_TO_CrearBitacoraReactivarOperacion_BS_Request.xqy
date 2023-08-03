xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarBitacoraProcesoByIdIOperacion_DB";
(:: import schema at "../../CrearBitacoraProceso/XSD/ConsultarBitacoraProcesoByIdIOperacion_DB.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarProcesosBPMByIdOperacion_DB";
(:: import schema at "../XSD/ConsultarProcesosBPMByIdOperacion_DB.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearBitacoraReactivarOperacion_DB";
(:: import schema at "../XSD/CrearBitacoraReactivarOperacion_DB_table.xsd" ::)

declare namespace ns4="http://www.bcie.org/CrearBitacoraReactivarOperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraReactivarOperacion/V1/Schema/CrearBitacoraReactivarOperacionMO.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraReactivarOperacionBO";

declare variable $ConsultarProcesosBPMByIdOperacion_DBOutputCollection as element() (:: schema-element(ns1:ConsultarProcesosBPMByIdOperacion_DBOutputCollection) ::) external;
declare variable $ConsultarBitacoraProcesoByIdIOperacion_DBOutputCollection as element() (:: schema-element(ns2:ConsultarBitacoraProcesoByIdIOperacion_DBOutputCollection) ::) external;
declare variable $requestCrearBitacoraReactivarOperacionMessage as element() (:: schema-element(ns4:requestCrearBitacoraReactivarOperacionMessage) ::) external;
declare variable $index as xs:integer external;

declare function local:func($ConsultarProcesosBPMByIdOperacion_DBOutputCollection as element() (:: schema-element(ns1:ConsultarProcesosBPMByIdOperacion_DBOutputCollection) ::), 
                            $ConsultarBitacoraProcesoByIdIOperacion_DBOutputCollection as element() (:: schema-element(ns2:ConsultarBitacoraProcesoByIdIOperacion_DBOutputCollection) ::), 
                            $requestCrearBitacoraReactivarOperacionMessage as element() (:: schema-element(ns4:requestCrearBitacoraReactivarOperacionMessage) ::),
                            $index as xs:integer) 
                            as element() (:: schema-element(ns3:TbiReactivaOperacionCollection) ::) {
    <ns3:TbiReactivaOperacionCollection>
        <ns3:TbiReactivaOperacion>
            <ns3:id></ns3:id>
            <ns3:idTreOperacionProceso>{fn:data($ConsultarProcesosBPMByIdOperacion_DBOutputCollection/ns1:ConsultarProcesosBPMByIdOperacion_DBOutput[$index]/ns1:ID)}</ns3:idTreOperacionProceso>
            <ns3:idTbiProcesoOperacion>{fn:data($ConsultarBitacoraProcesoByIdIOperacion_DBOutputCollection/ns2:ConsultarBitacoraProcesoByIdIOperacion_DBOutput[1]/ns2:ID)}</ns3:idTbiProcesoOperacion>
            <ns3:responsableOperacion>{fn:data($requestCrearBitacoraReactivarOperacionMessage/ns4:Bitacora/cre:responsableOperacion)}</ns3:responsableOperacion>
            <ns3:banReactivado>0</ns3:banReactivado>
            <ns3:fechaRegistro>{fn:current-dateTime()}</ns3:fechaRegistro>
            <ns3:fechaReactivado></ns3:fechaReactivado>
        </ns3:TbiReactivaOperacion>
    </ns3:TbiReactivaOperacionCollection>
};

local:func($ConsultarProcesosBPMByIdOperacion_DBOutputCollection, $ConsultarBitacoraProcesoByIdIOperacion_DBOutputCollection, $requestCrearBitacoraReactivarOperacionMessage,$index)
