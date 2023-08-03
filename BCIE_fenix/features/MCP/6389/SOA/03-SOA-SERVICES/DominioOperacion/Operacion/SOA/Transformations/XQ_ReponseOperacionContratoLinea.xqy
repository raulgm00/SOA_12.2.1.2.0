xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace conMO="http://www.bcie.org/ContratoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ope="http://www.bcie.org/OperacionBO";
declare namespace conBO="http://www.bcie.org/ContratoBO";
declare namespace linCreBO="http://www.bcie.org/LineaCreditoBO";

declare variable $ContratoResponse as element() (:: schema-element(conMO:ConsultarContratoResponse) ::) external;

declare function local:func($ContratoResponse as element() (:: schema-element(conMO:ConsultarContratoResponse) ::)) {
   for $i in $ContratoResponse/conMO:clienteContrato
   return
   <ope:contrato>
   {$i/conBO:idContrato}
   {$i/conBO:fechaFirma}
   {$i/conBO:MontoEscriturado}
      <conBO:cuentaCliente>{$i/conBO:cuentaCliente/conBO:cuentaCliente}</conBO:cuentaCliente>
   {$i/conBO:instanciaProceso}
   {
   for $j in $i/conBO:LineaCredito
   return 
   <conBO:LineaCredito>{$j/*}</conBO:LineaCredito>
   }
   </ope:contrato>
};

local:func($ContratoResponse)
