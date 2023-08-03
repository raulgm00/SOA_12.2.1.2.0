xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.com/Cuestionario/LeerExcel";
(:: import schema at "../XSD/LeerCuestionario.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ObtenerCuestionario";
(:: import schema at "../XSD/ObtenerCuestionario.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarPreguntas";
(:: import schema at "../XSD/ActualizarPreguntas_table.xsd" ::)

declare variable $outObtenerCuestionario as element() (:: schema-element(ns1:ObtenerCuestionarioOutputCollection) ::) external;
declare variable $outLeerCuestionario as element() (:: schema-element(ns2:LeerDatosResponse) ::) external; 

declare function local:func($outLeerCuestionario as element() (:: schema-element(ns2:LeerDatosResponse) ::)) 
                            as element() (:: schema-element(ns3:PreguntaCollection) ::) {
    <ns3:PreguntaCollection>
        {
        
            
            for $preguntasLst in $outLeerCuestionario/return/preguntasLst
            return 
            <ns3:Pregunta>
                <ns3:idPregunta>{fn:data($preguntasLst/idPregunta)}</ns3:idPregunta>
                {
                    if ($preguntasLst/respuesta)
                    then <ns3:respuesta>{fn:data($preguntasLst/respuesta)}</ns3:respuesta>
                    else ()
                }
                {
                    if ($preguntasLst/justificacion)
                    then <ns3:justificacion>{fn:data($preguntasLst/justificacion)}</ns3:justificacion>
                    else ()
                }</ns3:Pregunta>
        }
    </ns3:PreguntaCollection>
};

local:func($outLeerCuestionario)
