xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ActualizarLineasCreditoAprobacion";
(:: import schema at "../XSD/ActualizarLineasCreditoAprobacion_sp.xsd" ::)

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ActualizarLineaCreditoAprobacionRequest as element() (:: schema-element(ns1:ActualizarLineaCreditoAprobacionRequest) ::) external;

declare function local:func($ActualizarLineaCreditoAprobacionRequest as element() (:: schema-element(ns1:ActualizarLineaCreditoAprobacionRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_APROBACION>{fn:data($ActualizarLineaCreditoAprobacionRequest/ns1:Aprobacion/apr:idAprobacion)}</ns2:P_ID_APROBACION>
        {
            if ($ActualizarLineaCreditoAprobacionRequest/ns1:Aprobacion/apr:idContrato)
            then <ns2:P_ID_CONTRATO>{fn:data($ActualizarLineaCreditoAprobacionRequest/ns1:Aprobacion/apr:idContrato)}</ns2:P_ID_CONTRATO>
            else ()
        }
        <ns2:P_LINEAS_CREDITO>
        {
        for $LineaCredito in $ActualizarLineaCreditoAprobacionRequest/ns1:Aprobacion/apr:LineaCredito
        return
            <ns2:P_LINEAS_CREDITO_ITEM>
                {
                    if ($LineaCredito/lin:idLineaCredito)
                    then <ns2:ID>{fn:data($LineaCredito/lin:idLineaCredito)}</ns2:ID>
                    else ()
                }
                <ns2:NUMERO_LINEA_CREDITO>{fn:data($LineaCredito/lin:NumeroLineaCredito)}</ns2:NUMERO_LINEA_CREDITO>
                <ns2:DESCRIPCION_LINEA>{fn:data($LineaCredito/lin:Descripcion)}</ns2:DESCRIPCION_LINEA>
                <ns2:MONTO_LINEA>{fn:data($LineaCredito/lin:MontoLinea)}</ns2:MONTO_LINEA>
                <ns2:ESTATUS>{
                 if (string($LineaCredito/lin:idLineaCredito) != '')then
                  
                    if ($LineaCredito/lin:Estado = true())
                    then (1)
                    else (0)
                  
                  else
                  ()
                }</ns2:ESTATUS>
                
                 {
                    if ($LineaCredito/lin:IdTipoMonedaMontoLinea)
                    then <ns2:ID_TCA_TIPO_MONEDA>{fn:data($LineaCredito/lin:IdTipoMonedaMontoLinea)}</ns2:ID_TCA_TIPO_MONEDA>
                    else ()
                }
            </ns2:P_LINEAS_CREDITO_ITEM>
          }
        </ns2:P_LINEAS_CREDITO>
        
</ns2:InputParameters>
};

local:func($ActualizarLineaCreditoAprobacionRequest)
