xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $codigoError as xs:string external;
declare variable $mensajeError as xs:string external;
declare variable $descripcionError as xs:string external;
declare variable $idOperacion as xs:long external;

declare function local:func($idOperacion as xs:long,
                            $codigoError as xs:string, 
                            $mensajeError as xs:string,
                            $descripcionError as xs:string) as element() (:: schema-element(ns1:EliminarOperacionResponse) ::) {
    <ns1:EliminarOperacionResponse>
        <ns1:Operacion>
            <ope:idOperacion>{fn:data($idOperacion)}</ope:idOperacion>
        </ns1:Operacion>
        <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($mensajeError)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($codigoError)}</err:errorCode>
                <err:errorDescription>{fn:data($descripcionError)}</err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:EliminarOperacionResponse>
};

local:func($idOperacion, $codigoError, $mensajeError, $descripcionError)
