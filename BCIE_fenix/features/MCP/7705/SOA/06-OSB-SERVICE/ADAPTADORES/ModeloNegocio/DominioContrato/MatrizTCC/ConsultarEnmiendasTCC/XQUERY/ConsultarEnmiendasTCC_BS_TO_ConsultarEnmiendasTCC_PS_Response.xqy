xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEnmiendas";
(:: import schema at "../XSD/ConsultarEnmiendas.xsd" ::)

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarEnmiendasOutputCollection as element() (:: schema-element(ns1:ConsultarEnmiendasOutputCollection) ::) external;

declare function local:func($ConsultarEnmiendasOutputCollection as element() (:: schema-element(ns1:ConsultarEnmiendasOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarEnmiendasTCCResponse) ::) {
    <ns2:ConsultarEnmiendasTCCResponse>
        <ns2:enmiendaTCC>
            <mat:idEnmienda>{fn:data($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput[1]/ns1:ID)}</mat:idEnmienda>
            <mat:idOperacion>{fn:data($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput[1]/ns1:ID_OPERACION)}</mat:idOperacion>
            <mat:instanciaProceso>{fn:data($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput[1]/ns1:INSTANCIA_PROCESO)}</mat:instanciaProceso>
            <mat:requiereOGC>{fn:data($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput[1]/ns1:REQUIERE_OGC)}</mat:requiereOGC>
            <mat:requiereASJUR>{fn:data($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput[1]/ns1:REQUIERE_ASJUR)}</mat:requiereASJUR>
            <mat:fechaRegistro>{fn:data($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput[1]/ns1:FECHA_REGISTRO)}</mat:fechaRegistro>
            <mat:estado></mat:estado>
            <mat:estado_formalizacion>{fn:data($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput[1]/ns1:BANDERA_FORMALIZACION)}</mat:estado_formalizacion>
            <mat:requiereCOF>{fn:data($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput[1]/ns1:REQUIERE_COF)}</mat:requiereCOF>
            <mat:esDesobligacion>{fn:data($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput[1]/ns1:ES_DESOBLIGACION)}</mat:esDesobligacion>
            <mat:esCambioPlazo>{fn:data($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput[1]/ns1:ES_CAMBIO_PLAZO)}</mat:esCambioPlazo>
                    {
                for $ConsultarEnmiendasOutput in $ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput
                return 
                <mat:detalleEnmiendaTCC>
                    <mat:idEnmiendaTCC>{fn:data($ConsultarEnmiendasOutput/ns1:ID_ENMIENDA_TCC)}</mat:idEnmiendaTCC>
                    <mat:tipoTCC>{fn:data($ConsultarEnmiendasOutput/ns1:TIPO_TCC)}</mat:tipoTCC>
                    <mat:idTCC>{fn:data($ConsultarEnmiendasOutput/ns1:ID_TCC)}</mat:idTCC>
                    <mat:accion>{fn:data($ConsultarEnmiendasOutput/ns1:ACCION)}</mat:accion>
                    <mat:estado>{fn:data($ConsultarEnmiendasOutput/ns1:BAN_ESTATUS_DET)}</mat:estado></mat:detalleEnmiendaTCC>
            }</ns2:enmiendaTCC>
        <ns2:Resultado>
        {
            if (count($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput/ns1:ID)>0) then
            <res:result>OK</res:result>
            else
            <res:result>SIN DATOS</res:result>
            }
            {
            if (count($ConsultarEnmiendasOutputCollection/ns1:ConsultarEnmiendasOutput/ns1:ID)>0) then
            <res:message>La consulta se ha realizado exitosamente</res:message>
            else 
            <res:message>No existen registros</res:message>
            }
      
        </ns2:Resultado>
    </ns2:ConsultarEnmiendasTCCResponse>
};

local:func($ConsultarEnmiendasOutputCollection)