xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarEnmiendasTCCByIdOperacion";
(:: import schema at "../XSD/ConsultarEnmiendasTCCByIdOperacion_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare variable $EnmiendaTccCollection as element() (:: schema-element(ns1:EnmiendaTccCollection) ::) external;

declare function local:func($EnmiendaTccCollection as element() (:: schema-element(ns1:EnmiendaTccCollection) ::)) as element() (:: schema-element(ns2:ConsultarEnmiendasTCCByIdOperacionResponse) ::) {
    <ns2:ConsultarEnmiendasTCCByIdOperacionResponse>
        <ns2:Enmiendas>
        {
        for $Enmienda in $EnmiendaTccCollection/ns1:EnmiendaTcc
        return
            <mat:EnmiendaTCC>
                <mat:idEnmienda>{fn:data($Enmienda/ns1:id)}</mat:idEnmienda>
                <mat:idOperacion>{fn:data($Enmienda/ns1:idOperacion)}</mat:idOperacion>
                <mat:instanciaProceso>{fn:data($Enmienda/ns1:instanciaProceso)}</mat:instanciaProceso>
                <mat:requiereOGC>{fn:data($Enmienda/ns1:requiereOgc)}</mat:requiereOGC>
                <mat:requiereASJUR>{fn:data($Enmienda/ns1:requiereAsjur)}</mat:requiereASJUR>
                <mat:fechaRegistro>{fn:data($Enmienda/ns1:fechaRegistro)}</mat:fechaRegistro>
                <mat:estado>{fn:data($Enmienda/ns1:banEstatus)}</mat:estado>
                <mat:estado_formalizacion>{fn:data($Enmienda/ns1:banderaFormalizacion)}</mat:estado_formalizacion>
                <mat:requiereCOF>{fn:data($Enmienda/ns1:requiereCOF)}</mat:requiereCOF>
                 <mat:esDesobligacion>{fn:data($Enmienda/ns1:esDesobligacion)}</mat:esDesobligacion>
                <mat:esCambioPlazo>{fn:data($Enmienda/ns1:esCambioPlazo)}</mat:esCambioPlazo>
            </mat:EnmiendaTCC>
          }
        </ns2:Enmiendas>
        <ns2:Resultado>
            <res:result>OK</res:result>
            {
            if (count($EnmiendaTccCollection/ns1:EnmiendaTcc/ns1:idOperacion)>0) then
            <res:message>La consulta se ha realizado exitosamente</res:message>
            else 
            <res:message>No existen registros</res:message>
            }
        </ns2:Resultado>
    </ns2:ConsultarEnmiendasTCCByIdOperacionResponse>
};

local:func($EnmiendaTccCollection)