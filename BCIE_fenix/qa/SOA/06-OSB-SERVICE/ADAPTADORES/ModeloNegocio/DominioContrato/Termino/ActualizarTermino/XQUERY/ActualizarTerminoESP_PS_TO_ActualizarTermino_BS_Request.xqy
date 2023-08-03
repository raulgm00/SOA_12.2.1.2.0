xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarTerminosEsp";
(:: import schema at "../XSD/ActualizarTerminosEsp.xsd" ::)

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $TerminoRequest as element() (:: schema-element(ns1:ActualizarTerminoRequest) ::) external;

declare function local:func($TerminoRequest as element() (:: schema-element(ns1:ActualizarTerminoRequest) ::)) as element() (:: schema-element(ns2:ActualizarTerminosEspInput) ::) {
    <ns2:ActualizarTerminosEspInput>    

        <ns2:MONTO_MINIMO_DESEMBOLSO>{ if (string ($TerminoRequest/ns1:Termino/ter:montoMinimoDesembolso)='NaN') then  () else
            fn:data($TerminoRequest/ns1:Termino/ter:montoMinimoDesembolso)}</ns2:MONTO_MINIMO_DESEMBOLSO>
        <ns2:P_MONTO_MAXIMO_DESEMBOLSO>{ if (string($TerminoRequest/ns1:Termino/ter:montoMaximoDesembolso)='NaN') then () else
            fn:data($TerminoRequest/ns1:Termino/ter:montoMaximoDesembolso)}</ns2:P_MONTO_MAXIMO_DESEMBOLSO>
        <ns2:P_TASA_MINIMA_DESEMBOLSO>{ if (string ($TerminoRequest/ns1:Termino/ter:tasaMinimaDesembolso)='NaN') then () else
            fn:data($TerminoRequest/ns1:Termino/ter:tasaMinimaDesembolso)}</ns2:P_TASA_MINIMA_DESEMBOLSO>
        <ns2:P_TASA_MAXIMA_DESEMBOLSO>{ if (string ( $TerminoRequest/ns1:Termino/ter:tasaMaximaDesembolso)='NaN') then () else
            fn:data($TerminoRequest/ns1:Termino/ter:tasaMaximaDesembolso)}</ns2:P_TASA_MAXIMA_DESEMBOLSO>
        <ns2:p_id>{fn:data($TerminoRequest/ns1:Termino/ter:idTermino)}</ns2:p_id>
    </ns2:ActualizarTerminosEspInput>
};

local:func($TerminoRequest)
