xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ActualizarCommitmentRequest as element() (:: schema-element(ns1:ActualizarCommitmentRequest) ::) external;

declare function local:func($ActualizarCommitmentRequest as element() (:: schema-element(ns1:ActualizarCommitmentRequest) ::)) as element() (:: element(flex:actualizarContrato) ::) {
    <flex:actualizarContrato>
      <numeroLineaCredito>{fn:data($ActualizarCommitmentRequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</numeroLineaCredito>
      <fechaValor>{fn:data($ActualizarCommitmentRequest/ns1:LineaCredito/lin:FechaValor)}</fechaValor>
      <moneda>USD</moneda>
      <montoDelta>{fn:data($ActualizarCommitmentRequest/ns1:LineaCredito/lin:MontoLinea)}</montoDelta>
      <fechaMadurez></fechaMadurez>
    </flex:actualizarContrato>
};

local:func($ActualizarCommitmentRequest)