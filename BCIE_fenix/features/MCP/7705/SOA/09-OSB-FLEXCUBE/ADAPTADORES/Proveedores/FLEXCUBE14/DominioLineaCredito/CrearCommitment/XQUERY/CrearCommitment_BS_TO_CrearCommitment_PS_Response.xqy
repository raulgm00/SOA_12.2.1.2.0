xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/FLEXCUBE14/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Flexcube14/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/crearCommitment_DB";
(:: import schema at "../XSD/crearCommitment_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/FLEXCUBE14/ResultBO";

declare namespace err = "http://www.bcie.org/FLEXCUBE14/ErrorBO";

declare variable $CommitmentResponse as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($CommitmentResponse as element() (:: schema-element(ns2:OutputParameters) ::)) as element() (:: schema-element(ns1:CrearCommitmentResponse) ::) {
    <ns1:CrearCommitmentResponse>
    {
      if($CommitmentResponse/ns2:CODIGO_RESULTADO/text() = "0")
        then
        <ns1:Response>
            <ns1:Codigo_Contrato>{fn:data($CommitmentResponse/ns2:CODIGO_CONTRATO)}</ns1:Codigo_Contrato>
            <ns1:Plantilla_Limite>{fn:data($CommitmentResponse/ns2:PLANTILLA_LIMITE)}</ns1:Plantilla_Limite>
            <ns1:Serial_Limite>{fn:data($CommitmentResponse/ns2:SERIAL_LIMITE)}</ns1:Serial_Limite>
            <ns1:Plantilla_Limite_CC>{fn:data($CommitmentResponse/ns2:PLANTILLA_LIMITE_CC)}</ns1:Plantilla_Limite_CC>
            <ns1:Serial_Limite_CC>{fn:data($CommitmentResponse/ns2:SERIAL_LIMITE_CC)}</ns1:Serial_Limite_CC>
          <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:concat('La inserción se ha realizado correctamente, Codigo_Contrato:', xs:string($CommitmentResponse/ns2:CODIGO_CONTRATO))}</res:message>
          </ns1:Resultado>
        </ns1:Response>
        else if($CommitmentResponse/ns2:CODIGO_RESULTADO/text() eq "1" or  $CommitmentResponse/ns2:CODIGO_RESULTADO/text() eq "2")
        then
        <ns1:Response>
          <ns1:Codigo_Contrato/>
          <ns1:Plantilla_Limite/>
          <ns1:Serial_Limite/>
          <ns1:Plantilla_Limite_CC/>
          <ns1:Serial_Limite_CC/>
          <ns1:Resultado>
            <res:result>ERROR</res:result>
                <res:message>{fn:data($CommitmentResponse/ns2:MENSAJE)}</res:message>
                <res:error>
                    <err:errorCode>{fn:data($CommitmentResponse/ns2:CODIGO_RESULTADO)}</err:errorCode>
                    <err:errorDescription>{fn:data($CommitmentResponse/ns2:MENSAJE)}</err:errorDescription>
                    <err:errorType>{fn:data($CommitmentResponse/ns2:TIPO_RESULTADO)}</err:errorType>
                </res:error>
          </ns1:Resultado>
        </ns1:Response>
        else()
      }
    </ns1:CrearCommitmentResponse>
};

local:func($CommitmentResponse)