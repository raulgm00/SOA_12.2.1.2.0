xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $valor as xs:string external;
declare variable $clave as xs:string external;

declare function tns:func($valor as xs:string, 
                          $clave as xs:string) 
                          as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::) {
    <ns1:DecodificarParametrosResponse>
        <ns1:Parametros>
            <ns1:Parametro>
                <ns1:Nombre>{fn:data($valor)}</ns1:Nombre>
                <ns1:Valor>{fn:data($clave)}</ns1:Valor>
            </ns1:Parametro>
        </ns1:Parametros>
    </ns1:DecodificarParametrosResponse>
};

tns:func($valor, $clave)
