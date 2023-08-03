xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.com/Cuestionario/ObtenerCuestionario";
(:: import schema at "../XSD/CuestionarioExcel.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ObtenerCuestionario";
(:: import schema at "../XSD/ObtenerCuestionario.xsd" ::)

declare variable $CuestionarioResponse as element() (:: schema-element(ns1:ObtenerCuestionarioOutputCollection) ::) external;
declare variable $idOperacion as element() external;

declare function local:func($idOperacion as element(),$CuestionarioResponse as element() (:: schema-element(ns1:ObtenerCuestionarioOutputCollection) ::)) as element() (:: schema-element(ns2:crearExcel) ::) {
    <ns2:crearExcel>
        {
            for $ObtenerCuestionarioOutput in $CuestionarioResponse/ns1:ObtenerCuestionarioOutput
            return 
            <cuestionario>
                <evidencia>{fn:data($ObtenerCuestionarioOutput/ns1:ID_DOCUMENTO)}</evidencia>
                <fechaRegistro>{fn:data($ObtenerCuestionarioOutput/ns1:FECHA_REGISTRO)}</fechaRegistro>
                <filename>{fn:data($ObtenerCuestionarioOutput/ns1:NOMBRE_DOCUMENTO)}</filename>
                <idPregunta>{fn:data($ObtenerCuestionarioOutput/ns1:ID_PREGUNTA[1])}</idPregunta>
                <justificacion>{fn:data($ObtenerCuestionarioOutput/ns1:JUSTIFICACION)}</justificacion>
                <opciones>{fn:data($ObtenerCuestionarioOutput/ns1:OPCION)}</opciones>                
                <pregunta>{fn:data($ObtenerCuestionarioOutput/ns1:PREGUNTA[1])}</pregunta>
                <respuesta>{fn:data($ObtenerCuestionarioOutput/ns1:RESPUESTA)}</respuesta>                
                <seccion>{fn:data($ObtenerCuestionarioOutput/ns1:ID_PRINCIPIO[1])}</seccion>
                <subSeccion>{fn:data($ObtenerCuestionarioOutput/ns1:ID_CRITERIO[1])}</subSeccion>
                <tipoPregunta>{fn:data($ObtenerCuestionarioOutput/ns1:TIPO_PREGUNTA)}</tipoPregunta>
                <usuario>{fn:data($ObtenerCuestionarioOutput/ns1:RESPONSABLE)}</usuario>
                
                </cuestionario>
                
        }
        <idOperacion>{fn:data($idOperacion)}</idOperacion>
    </ns2:crearExcel>
};

local:func($idOperacion ,$CuestionarioResponse)