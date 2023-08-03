xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarContrato";
(:: import schema at "../../ConsultarFLEXCUBE/XSD/ConsultarContrato_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ValidarRegistroFLEXCUBERequest as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBERequest) ::) external;
declare variable $consultarContratoResponse as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($ValidarRegistroFLEXCUBERequest as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBERequest) ::), 
                            $consultarContratoResponse as element() (:: schema-element(ns2:OutputParameters) ::)) 
                            as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBEResponse) ::) {
    let $fechaValorVal := if(fn:contains($ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:FechaValor/text(),'T')) then fn:substring-before($ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:FechaValor/text(),'T') else $ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:FechaValor/text()
          let $fechaValorCon := if(fn:contains($consultarContratoResponse/ns2:CONTRATO/ns2:FECHA_VALOR/text(),'T')) then fn:substring-before($consultarContratoResponse/ns2:CONTRATO/ns2:FECHA_VALOR/text(),'T') else $consultarContratoResponse/ns2:CONTRATO/ns2:FECHA_VALOR/text()
          return
          if ($ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:Descripcion = 'COMMITMENT')
            then 
            
            if ($ValidarRegistroFLEXCUBERequest/ns1:codigoCliente = $consultarContratoResponse/ns2:CONTRATO/ns2:CODIGO_CLIENTE and 
            $ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:MontoLinea = $consultarContratoResponse/ns2:CONTRATO/ns2:MONTO_APROBADO and
             $fechaValorVal = $fechaValorCon)
            then 
             <ns1:ValidarRegistroFLEXCUBEResponse>
                 <ns1:LineaCredito>
                     <lin:idLineaCredito>{fn:data($ValidarRegistroFLEXCUBERequest/ns1:codigoCliente)}</lin:idLineaCredito>
                     <lin:idContrato>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:NUMERO_CONTRATO)}</lin:idContrato>
                      <lin:CodigoPantallaLimite>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:PLANTILLA_LIMITE)}</lin:CodigoPantallaLimite>
                      <lin:CodigoSerialLimite>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:SERIAL_LIMITE)}</lin:CodigoSerialLimite>
                 </ns1:LineaCredito>
                  <ns1:Resultado>
                     <res:result>OK</res:result>
                     <res:message>El registro se creo exitosamente</res:message>
                 </ns1:Resultado>
             </ns1:ValidarRegistroFLEXCUBEResponse>
          else
            <ns1:ValidarRegistroFLEXCUBEResponse>
                  <ns1:Resultado>
                     <res:result>ERROR</res:result>
                     <res:message>No se encontro registro en FLEXCUBE para el contrato : {fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:NUMERO_CONTRATO)}</res:message>
                 </ns1:Resultado>
            </ns1:ValidarRegistroFLEXCUBEResponse>
        else if ($ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:Descripcion = 'LOAN')
            then 
            if ($ValidarRegistroFLEXCUBERequest/ns1:codigoCliente = $consultarContratoResponse/ns2:CONTRATO/ns2:CODIGO_CLIENTE and 
            $ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:MontoLinea = $consultarContratoResponse/ns2:CONTRATO/ns2:MONTO_APROBADO and
             $fechaValorVal = $fechaValorCon)
            then 
             <ns1:ValidarRegistroFLEXCUBEResponse>
                 <ns1:LineaCredito>
                     <lin:idContrato>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:NUMERO_CONTRATO)}</lin:idContrato>
                 </ns1:LineaCredito>
                  <ns1:Resultado>
                     <res:result>OK</res:result>
                     <res:message>El registro se creo exitosamente</res:message>
                 </ns1:Resultado>
             </ns1:ValidarRegistroFLEXCUBEResponse>
          else
		  <ns1:ValidarRegistroFLEXCUBEResponse>
                  <ns1:Resultado>
                     <res:result>ERROR</res:result>
                     <res:message>No se encontro registro en FLEXCUBE para el contrato {fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:NUMERO_CONTRATO)}  o no coinciden los parámetros de comparación</res:message>
                 </ns1:Resultado>
                </ns1:ValidarRegistroFLEXCUBEResponse>
        else()
};

local:func($ValidarRegistroFLEXCUBERequest, $consultarContratoResponse)