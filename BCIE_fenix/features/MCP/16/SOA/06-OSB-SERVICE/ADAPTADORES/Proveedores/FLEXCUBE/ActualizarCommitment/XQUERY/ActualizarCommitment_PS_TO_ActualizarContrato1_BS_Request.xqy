xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ActualizarContrato1";
(:: import schema at "../../ActualizarPrestamoFLEXCUBE/XSD/ActualizarContrato1_sp.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ActualizarCommitmentRequest as element() (:: schema-element(ns1:ActualizarCommitmentRequest) ::) external;

declare function local:func($ActualizarCommitmentRequest as element() (:: schema-element(ns1:ActualizarCommitmentRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:NUMERO_LINEA_CREDITO>{fn:data($ActualizarCommitmentRequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</ns2:NUMERO_LINEA_CREDITO>
        <ns2:FECHA_VALOR>{fn:data($ActualizarCommitmentRequest/ns1:LineaCredito/lin:FechaValor)}</ns2:FECHA_VALOR>
        {
            if ($ActualizarCommitmentRequest/ns1:LineaCredito/lin:Moneda/cat:codigoExterno)
            then <ns2:MONEDA>{fn:data($ActualizarCommitmentRequest/ns1:LineaCredito/lin:Moneda/cat:codigoExterno)}</ns2:MONEDA>
            else <ns2:MONEDA>USD</ns2:MONEDA>
        }
        <ns2:MONTO_DELTA>{fn:data($ActualizarCommitmentRequest/ns1:LineaCredito/lin:MontoLinea)}</ns2:MONTO_DELTA>
        <ns2:FECHA_MADUREZ></ns2:FECHA_MADUREZ>
        <ns2:LISTACOMISIONES></ns2:LISTACOMISIONES>
        <ns2:PVUSUARIO>SYSTEM</ns2:PVUSUARIO>
    </ns2:InputParameters>
};

local:func($ActualizarCommitmentRequest)