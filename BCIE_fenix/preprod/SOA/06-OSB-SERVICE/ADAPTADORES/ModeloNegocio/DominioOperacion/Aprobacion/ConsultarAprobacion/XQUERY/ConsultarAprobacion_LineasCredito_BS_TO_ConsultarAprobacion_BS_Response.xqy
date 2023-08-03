xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAprobacion_LineasCredito";
(:: import schema at "../XSD/ConsultarAprobacion_LineasCredito.xsd" ::)

declare namespace cat1 = "http://www.bcie.org/CatalogoTerminoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarAprobacion_LineasCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarAprobacion_LineasCreditoOutputCollection) ::) external;

declare function local:func($ConsultarAprobacion_LineasCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarAprobacion_LineasCreditoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarAprobacionResponse) ::) {
    <ns2:ConsultarAprobacionResponse>
        <ns2:Aprobacion>
            <apr:idAprobacion>{fn:data($ConsultarAprobacion_LineasCreditoOutputCollection/ns1:ConsultarAprobacion_LineasCreditoOutput[1]/ns1:ID_APROBACION)}</apr:idAprobacion>
            <apr:fechaAprobacion>{fn:data($ConsultarAprobacion_LineasCreditoOutputCollection/ns1:ConsultarAprobacion_LineasCreditoOutput[1]/ns1:FECHA_APROBACION)}</apr:fechaAprobacion>
            <apr:numeroResolucion>{fn:data($ConsultarAprobacion_LineasCreditoOutputCollection/ns1:ConsultarAprobacion_LineasCreditoOutput[1]/ns1:NUMERO_RESOLUCION)}</apr:numeroResolucion>
            <apr:resumen>{fn:data($ConsultarAprobacion_LineasCreditoOutputCollection/ns1:ConsultarAprobacion_LineasCreditoOutput[1]/ns1:RESUMEN)}</apr:resumen>
            
            {
                  if (string-length(string($ConsultarAprobacion_LineasCreditoOutputCollection/ns1:ConsultarAprobacion_LineasCreditoOutput[1]/ns1:MONEDA_APROBACION)) > 0) then 
                    <apr:idTipoMonedaMontoAprobacion>{fn:data($ConsultarAprobacion_LineasCreditoOutputCollection/ns1:ConsultarAprobacion_LineasCreditoOutput[1]/ns1:MONEDA_APROBACION)}</apr:idTipoMonedaMontoAprobacion>
                  else ()
             }
                
                
            <apr:MontoAprobacion>{fn:data($ConsultarAprobacion_LineasCreditoOutputCollection/ns1:ConsultarAprobacion_LineasCreditoOutput[1]/ns1:MONTO)}</apr:MontoAprobacion>
            <apr:idContrato>{fn:data($ConsultarAprobacion_LineasCreditoOutputCollection/ns1:ConsultarAprobacion_LineasCreditoOutput[1]/ns1:ID_CONTRATO)}</apr:idContrato>
            {
            for $LineaCredito in $ConsultarAprobacion_LineasCreditoOutputCollection/ns1:ConsultarAprobacion_LineasCreditoOutput
            return
            if (string-length(string($LineaCredito/ns1:ID_LINEA)) > 0) then
            <apr:LineaCredito>
                <lin:idLineaCredito>{fn:data($LineaCredito/ns1:ID_LINEA)}</lin:idLineaCredito>
                <lin:NumeroLineaCredito>{fn:data($LineaCredito/ns1:NUMERO_LINEA_CREDITO)}</lin:NumeroLineaCredito>
                <lin:Descripcion>{fn:data($LineaCredito/ns1:DESCRIPCION_LINEA)}</lin:Descripcion>
                
                {
                  if (string-length(string($LineaCredito/ns1:ID_TCA_TIPO_MONEDA)) > 0) then 
                    <lin:IdTipoMonedaMontoLinea>{fn:data($LineaCredito/ns1:ID_TCA_TIPO_MONEDA)}</lin:IdTipoMonedaMontoLinea>
                  else ()
                }
                
                <lin:MontoLinea>{fn:data($LineaCredito/ns1:MONTO_LINEA)}</lin:MontoLinea>
            </apr:LineaCredito>
           else
            ()
            }
        </ns2:Aprobacion>

         <ns2:Resultado>
            <res:result>OK</res:result>
            {
            if (count($ConsultarAprobacion_LineasCreditoOutputCollection/ns1:ConsultarAprobacion_LineasCreditoOutput) > 0) then
            <res:message>Operacion Exitosa</res:message>
            else
             <res:message>No existen Registros</res:message>
             }
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ConsultarAprobacionResponse>
};

local:func($ConsultarAprobacion_LineasCreditoOutputCollection)
