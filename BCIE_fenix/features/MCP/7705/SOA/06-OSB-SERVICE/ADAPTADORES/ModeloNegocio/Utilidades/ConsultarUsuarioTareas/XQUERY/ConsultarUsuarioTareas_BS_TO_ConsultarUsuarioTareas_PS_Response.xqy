xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bicie.org/ConsultarUsuarioTareasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarUsuarioTareas/V1/Schema/ConsultarUsuarioTareasMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarUsuarioTareasDB";
(:: import schema at "../XSD/ConsultarUsuarioTareasDB.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarUsuarioTareasBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarUsuarioTareasRequest as element() (:: schema-element(ns1:ConsultarUsuarioTareasRequest) ::) external;
declare variable $ConsultarUsuarioTareasDBOutputCollection as element() (:: schema-element(ns2:ConsultarUsuarioTareasDBOutputCollection) ::) external;

declare function local:func($ConsultarUsuarioTareasRequest as element() (:: schema-element(ns1:ConsultarUsuarioTareasRequest) ::), 
                            $ConsultarUsuarioTareasDBOutputCollection as element() (:: schema-element(ns2:ConsultarUsuarioTareasDBOutputCollection) ::)) 
                            as element() (:: schema-element(ns1:ConsultarUsuarioTareasResponse) ::) {
    <ns1:ConsultarUsuarioTareasResponse>
    
    {
    let $filtro := if(count($ConsultarUsuarioTareasRequest/ns1:Tareas) > 0) 
        then 
          for $filtro in distinct-values($ConsultarUsuarioTareasRequest/ns1:Tareas/con:idTarea)
          return
            <filtro>{$filtro}</filtro>
        else 
          for $filtro in distinct-values($ConsultarUsuarioTareasDBOutputCollection/ns2:ConsultarUsuarioTareasDBOutput/ns2:ID_TAREA)
           return
            <filtro>{$filtro}</filtro>
    for $idtarea in $filtro
    let $tareas := $ConsultarUsuarioTareasDBOutputCollection/ns2:ConsultarUsuarioTareasDBOutput[$idtarea = ns2:ID_TAREA][1]
    return
        if(not(empty($tareas))) then 
          <ns1:UsuariosTareas>
              <con:idBitacoraClienteProceso>{fn:data($tareas/ns2:ID)}</con:idBitacoraClienteProceso>
              <con:usuario>{fn:data($tareas/ns2:USUARIO)}</con:usuario>
              <con:nombreUsuario>{fn:data($tareas/ns2:NOMBRE_USUARIO)}</con:nombreUsuario>
              <con:fechaRegistro>{fn:data($tareas/ns2:FECHA_REGISTRO)}</con:fechaRegistro>
              <con:idTarea>{fn:data($tareas/ns2:ID_TAREA)}</con:idTarea>
          </ns1:UsuariosTareas>
        else
        ()
     }
    <ns1:Resultado>
        <res:result>OK</res:result>
        <res:message>Operación Exitosa</res:message>
    </ns1:Resultado>
    </ns1:ConsultarUsuarioTareasResponse>
};

local:func($ConsultarUsuarioTareasRequest, $ConsultarUsuarioTareasDBOutputCollection)
