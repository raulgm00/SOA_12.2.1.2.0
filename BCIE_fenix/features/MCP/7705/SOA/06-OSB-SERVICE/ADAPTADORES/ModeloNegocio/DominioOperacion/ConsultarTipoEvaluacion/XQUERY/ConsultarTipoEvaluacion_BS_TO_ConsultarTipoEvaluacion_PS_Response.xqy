xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTipoEvaluacion";
(:: import schema at "../XSD/ConsultarTipoEvaluacion.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarTipoEvaluacionOutputCollection as element() (:: schema-element(ns1:ConsultarTipoEvaluacionOutputCollection) ::) external;

declare function local:func($ConsultarTipoEvaluacionOutputCollection as element() (:: schema-element(ns1:ConsultarTipoEvaluacionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTipoEvaluacionResponse) ::) {
    <ns2:ConsultarTipoEvaluacionResponse>
      {for $tipoEval in $ConsultarTipoEvaluacionOutputCollection/ns1:ConsultarTipoEvaluacionOutput
      return
        <ns2:tipoEvaluacion>
            <cat:Id>{fn:data($tipoEval/ns1:ID)}</cat:Id>
            <cat:Descripcion>{fn:data($tipoEval/ns1:DESCRIPCION)}</cat:Descripcion>
            <cat:DescripcionCorta>{fn:data($tipoEval/ns1:DESCRIPCION_CORTA)}</cat:DescripcionCorta>
            <cat:estatus>{fn:data($tipoEval/ns1:BAN_ESTATUS)}</cat:estatus>
            <cat:codigoExterno>{fn:data($tipoEval/ns1:COD_EXTERNO)}</cat:codigoExterno>
        </ns2:tipoEvaluacion>
      }   
        <ns2:Resultado>
            <res:result>OK</res:result>
            {if(fn:count($ConsultarTipoEvaluacionOutputCollection/ns1:ConsultarTipoEvaluacionOutput) > 0)then
              <res:message>Consulta exitosa</res:message>
            else
              <res:message>Consulta sin resultados</res:message>
            }
        </ns2:Resultado>
    </ns2:ConsultarTipoEvaluacionResponse>
};

local:func($ConsultarTipoEvaluacionOutputCollection)
