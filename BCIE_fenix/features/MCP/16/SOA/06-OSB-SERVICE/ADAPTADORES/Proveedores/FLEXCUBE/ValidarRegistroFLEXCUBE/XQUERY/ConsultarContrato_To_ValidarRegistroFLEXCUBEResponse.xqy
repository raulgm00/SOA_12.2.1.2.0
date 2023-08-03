xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace ns2="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

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

declare variable $consultarContratoResponse as element(m:consultarContratoResponse)(:: element(ns2:contrato_out) ::) external;
declare variable $ValidarRegistroFLEXCUBERequest as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBERequest) ::) external;

declare function local:func($consultarContratoResponse as element(m:consultarContratoResponse) (:: element(ns2:contrato_out) ::), 
                            $ValidarRegistroFLEXCUBERequest as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBERequest) ::)) 
                            as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBEResponse) ::) {
          let $fechaValorVal := if(fn:contains($ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:FechaValor/text(),'T')) then fn:substring-before($ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:FechaValor/text(),'T') else $ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:FechaValor/text()
          let $fechaValorCon := if(fn:contains($consultarContratoResponse/contrato_out/ns2:fechaValor/text(),'T')) then fn:substring-before($consultarContratoResponse/contrato_out/ns2:fechaValor/text(),'T') else $consultarContratoResponse/contrato_out/ns2:fechaValor/text()
          return
          if ($ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:Descripcion = 'COMMITMENT')
            then 
            
            if ($ValidarRegistroFLEXCUBERequest/ns1:codigoCliente = $consultarContratoResponse/contrato_out/ns2:codigoCliente and 
            $ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:MontoLinea = $consultarContratoResponse/contrato_out/ns2:montoAprobado and
             $fechaValorVal = $fechaValorCon)
            then 
             <ns2:ValidarRegistroFLEXCUBEResponse>
                 <ns1:LineaCredito>
                     <lin:idLineaCredito>{fn:data($ValidarRegistroFLEXCUBERequest/ns1:codigoCliente)}</lin:idLineaCredito>
                     <lin:idContrato>{fn:data($consultarContratoResponse/contrato_out/ns2:numeroContrato)}</lin:idContrato>
                      <lin:CodigoPantallaLimite>{fn:data($consultarContratoResponse/contrato_out/ns2:plantillaLimite)}</lin:CodigoPantallaLimite>
                      <lin:CodigoSerialLimite>{fn:data($consultarContratoResponse/contrato_out/ns2:serialLimite)}</lin:CodigoSerialLimite>
                 </ns1:LineaCredito>
                  <ns1:Resultado>
                     <res:result>OK</res:result>
                     <res:message>El registro se creo exitosamente</res:message>
                 </ns1:Resultado>
             </ns2:ValidarRegistroFLEXCUBEResponse>
          else
		  <ns2:ValidarRegistroFLEXCUBEResponse>
                  <ns1:Resultado>
                     <res:result>ERROR</res:result>
                     <res:message>No se encontro registro en FLEXCUBE para el contrato : {fn:data($consultarContratoResponse/contrato_out/ns2:numeroContrato)}</res:message>
                 </ns1:Resultado>
		   </ns2:ValidarRegistroFLEXCUBEResponse>
        else if ($ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:Descripcion = 'LOAN')
            then 
            if ($ValidarRegistroFLEXCUBERequest/ns1:codigoCliente = $consultarContratoResponse/contrato_out/ns2:codigoCliente and 
            $ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:MontoLinea = $consultarContratoResponse/contrato_out/ns2:montoAprobado and
             $fechaValorVal = $fechaValorCon)
            then 
             <ns2:ValidarRegistroFLEXCUBEResponse>
                 <ns1:LineaCredito>
                     <lin:idContrato>{fn:data($consultarContratoResponse/contrato_out/ns2:numeroContrato)}</lin:idContrato>
                 </ns1:LineaCredito>
                  <ns1:Resultado>
                     <res:result>OK</res:result>
                     <res:message>El registro se creo exitosamente</res:message>
                 </ns1:Resultado>
             </ns2:ValidarRegistroFLEXCUBEResponse>
          else
		  <ns2:ValidarRegistroFLEXCUBEResponse>
                  <ns1:Resultado>
                     <res:result>ERROR</res:result>
                     <res:message>No se encontro registro en FLEXCUBE para el contrato {fn:data($consultarContratoResponse/contrato_out/ns2:numeroContrato)}  o no coinciden los parámetros de comparación</res:message>
                 </ns1:Resultado>
		   </ns2:ValidarRegistroFLEXCUBEResponse>
        else()
};

local:func($consultarContratoResponse, $ValidarRegistroFLEXCUBERequest)