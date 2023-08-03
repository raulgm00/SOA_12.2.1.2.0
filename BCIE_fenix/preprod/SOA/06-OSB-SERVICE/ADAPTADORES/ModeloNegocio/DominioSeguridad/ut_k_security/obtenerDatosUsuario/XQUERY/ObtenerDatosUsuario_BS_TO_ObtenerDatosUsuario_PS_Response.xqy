xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SeguridadMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioSeguridad/Schema/SeguridadMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/obtenerDatosUsuario";
(:: import schema at "../XSD/obtenerDatosUsuario_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ObtenerDatosUsuarioRepsonse as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($ObtenerDatosUsuarioRepsonse as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ObtenerDatosUsuarioResponse) ::) {
    <ns2:ObtenerDatosUsuarioResponse>
          <ns2:Usuario>
                <ns2:NombreUsuario>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:NOMBRE_USUARIO)}</ns2:NombreUsuario>
                <ns2:NombreEmpleado>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:NOMBRE_EMPLEADO)}</ns2:NombreEmpleado>
                <ns2:CodigoEmpleado>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:CODIGO_EMPLEADO)}</ns2:CodigoEmpleado>
                <ns2:Compania>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:COMPANIA)}</ns2:Compania>
                <ns2:CodigoPais>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:CODIGO_PAIS)}</ns2:CodigoPais>
                <ns2:PaisSede>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:PAIS_SEDE)}</ns2:PaisSede>
                <ns2:CodigoPuesto>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:CODIGO_PUESTO)}</ns2:CodigoPuesto>
                <ns2:NombrePuesto>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:NOMBRE_PUESTO)}</ns2:NombrePuesto>
                <ns2:Email>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS//ns1:EMAIL)}</ns2:Email>
              <ns2:NombresEmpleado>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:NOMBRES_EMPLEADO)}</ns2:NombresEmpleado>
              <ns2:PrimerApellido>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:PRIMER_APELLIDO)}</ns2:PrimerApellido>
              <ns2:SegundoApellido>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:SEGUNDO_APELLIDO)}</ns2:SegundoApellido>
              <ns2:NumeroIdentificacion>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:NUMERO_IDENTIFICACION)}</ns2:NumeroIdentificacion>
              <ns2:Profesion>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:PROFESION)}</ns2:Profesion>
              <ns2:CodigoJefe>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:CODIGO_JEFE)}</ns2:CodigoJefe>
              <ns2:CodigoNacionalidad>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:CODIGO_NACIONALIDAD)}</ns2:CodigoNacionalidad>
              <ns2:CodigoCategoriaRRHH>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:CODIGO_CATEGORIA_RRHH)}</ns2:CodigoCategoriaRRHH>
              <ns2:NombreCategoriaRRHH>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:NOMBRE_CATEGORIA_RRHH)}</ns2:NombreCategoriaRRHH>
              <ns2:EsJefe>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:ES_JEFE)}</ns2:EsJefe>
              <ns2:CodigoNodoOrganigrama>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:CODIGO_NODO_ORGANIGRAMA)}</ns2:CodigoNodoOrganigrama>
              <ns2:NombreNodoOrganigrama>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:NOMBRE_NODO_ORGANIGRAMA)}</ns2:NombreNodoOrganigrama>
              <ns2:CodigoPlaza>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:CODIGO_PLAZA)}</ns2:CodigoPlaza>
              <ns2:FechaIngresoBCIE>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POTDATOS/ns1:FECHA_INGRESO_BCIE)}</ns2:FechaIngresoBCIE>
            </ns2:Usuario>
        {
            if ($ObtenerDatosUsuarioRepsonse/ns1:PONCODIGORESULTADO)
            then <ns2:CodigoResultado>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:PONCODIGORESULTADO)}</ns2:CodigoResultado>
            else ()
        }
        {
            if ($ObtenerDatosUsuarioRepsonse/ns1:PONTIPORESULTADO)
            then <ns2:TipoResultado>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:PONTIPORESULTADO)}</ns2:TipoResultado>
            else ()
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:data($ObtenerDatosUsuarioRepsonse/ns1:POVMENSAJE)}</res:message>
        </ns2:Resultado>
    </ns2:ObtenerDatosUsuarioResponse>
};

local:func($ObtenerDatosUsuarioRepsonse)