xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $crearCommitmentResponse as element(m:crearCommitmentResponse) external;

declare function local:func($crearCommitmentResponse as element(m:crearCommitmentResponse)) as element() (:: schema-element(lin:CrearCommitmentFLEXCUBEResponse) ::) {
    <lin:CrearCommitmentFLEXCUBEResponse>
        <lin:codigoContrato>{data($crearCommitmentResponse/codigoContrato_out)}</lin:codigoContrato>
        <lin:plantillaLimite>{data($crearCommitmentResponse/plantillaLimite_out)}</lin:plantillaLimite>
        <lin:serialLimite>{data($crearCommitmentResponse/serialLimite_out)}</lin:serialLimite>
        <lin:Resultado>
        {
          if (data($crearCommitmentResponse/codigoResultado_out) = 0 ) then 
          <res:result>OK</res:result>
          else
          <res:result>ERROR</res:result>
        }
            <res:message>{data($crearCommitmentResponse/mensaje_out)}</res:message>
            {
            if (data($crearCommitmentResponse/codigoResultado_out) != 0) then 
            <res:error>
                <err:errorCode>{data($crearCommitmentResponse/codigoResultado_out)}</err:errorCode>
                <err:errorDescription>{data($crearCommitmentResponse/tipoResultado_out)}</err:errorDescription>
                <err:errorType>NEG</err:errorType>
            </res:error>
            else ()
            }
        </lin:Resultado>
    </lin:CrearCommitmentFLEXCUBEResponse>
};

local:func($crearCommitmentResponse)
