xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearMiembroReunion_DB";
(:: import schema at "../XSD/CrearMiembroReunion_DB_table.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare variable $CrearMiembroReunionRequest as element() (:: schema-element(ns1:CrearMiembroReunionRequest) ::) external;

declare function local:func($CrearMiembroReunionRequest as element() (:: schema-element(ns1:CrearMiembroReunionRequest) ::)) as element() (:: schema-element(ns2:TcaMiembroReunionCollection) ::) {
    <ns2:TcaMiembroReunionCollection>
        <ns2:TcaMiembroReunion>
            <ns2:idTreComiteRol>{fn:data($CrearMiembroReunionRequest/ns1:listadoEmitirVoto/sol:MiembroReunion/sol:rol/cat:Id)}</ns2:idTreComiteRol>
            <ns2:loginUsuario>{fn:data($CrearMiembroReunionRequest/ns1:listadoEmitirVoto/sol:MiembroReunion/sol:listadoUsuarios/sol:usuario/sol:usuario)}</ns2:loginUsuario>
            <ns2:nombreUsuario>{fn:data($CrearMiembroReunionRequest/ns1:listadoEmitirVoto/sol:MiembroReunion/sol:listadoUsuarios/sol:usuario/sol:nombreUsuario)}</ns2:nombreUsuario>
            <ns2:dependencia>{fn:data($CrearMiembroReunionRequest/ns1:listadoEmitirVoto/sol:MiembroReunion/sol:listadoUsuarios/sol:usuario/sol:dependecia)}</ns2:dependencia>
            <ns2:esPropietario>{
              if (fn:data($CrearMiembroReunionRequest/ns1:listadoEmitirVoto/sol:MiembroReunion/sol:listadoUsuarios/sol:usuario/sol:propietario) = true())
                then(1)
                else (0)                  
            }
            </ns2:esPropietario>
        </ns2:TcaMiembroReunion>
    </ns2:TcaMiembroReunionCollection>
};

local:func($CrearMiembroReunionRequest)
