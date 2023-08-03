xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarTareasActivasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarTareasActivas/V1/Schema/ConsultarTareasActivasMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTareasActivas_DB";
(:: import schema at "../XSD/ConsultarTareasActivas_DB.xsd" ::)

declare variable $ConsultarTareasActivas_DBOutputCollection as element() (:: schema-element(ns1:ConsultarTareasActivas_DBOutputCollection) ::) external;

declare function local:func($ConsultarTareasActivas_DBOutputCollection as element() (:: schema-element(ns1:ConsultarTareasActivas_DBOutputCollection) ::)) as element() (:: schema-element(ns2:responseConsultarTareasActivas) ::) {
    <ns2:responseConsultarTareasActivas>

      {
      for $ListaTareasActivas in $ConsultarTareasActivas_DBOutputCollection/ns1:ConsultarTareasActivas_DBOutput
      return
        <ns2:ListaTareasActivas>
            <ns2:id>{fn:data($ListaTareasActivas/ns1:ID)}</ns2:id>
            <ns2:idOperacion>{fn:data($ListaTareasActivas/ns1:ID_OPERACION)}</ns2:idOperacion>
            <ns2:idProceso>{fn:data($ListaTareasActivas/ns1:ID_PROCESO)}</ns2:idProceso>
            <ns2:nombreProceso>{fn:data($ListaTareasActivas/ns1:NOMBRE_PROCESO)}</ns2:nombreProceso>
            <ns2:idTarea>{fn:data($ListaTareasActivas/ns1:ID_TAREA)}</ns2:idTarea>
            <ns2:nombreTarea>{fn:data($ListaTareasActivas/ns1:NOMBRE_TAREA)}</ns2:nombreTarea>
            <ns2:usuario>{fn:data($ListaTareasActivas/ns1:USUARIO)}</ns2:usuario>
            <ns2:nombreUsuario>{fn:data($ListaTareasActivas/ns1:NOMBRE_USUARIO)}</ns2:nombreUsuario>
            <ns2:fechaRegistro>{fn:data($ListaTareasActivas/ns1:FECHA_REGISTRO)}</ns2:fechaRegistro>
        </ns2:ListaTareasActivas>
        }
    </ns2:responseConsultarTareasActivas>
};

local:func($ConsultarTareasActivas_DBOutputCollection)
