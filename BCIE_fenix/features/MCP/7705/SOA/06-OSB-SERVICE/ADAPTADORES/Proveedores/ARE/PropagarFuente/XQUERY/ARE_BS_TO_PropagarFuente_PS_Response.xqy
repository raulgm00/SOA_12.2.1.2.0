xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://org/bcie/ws/middle/ARE/ARE.wsdl/types/";
(:: import schema at "../../WSDL/ARE.wsdl" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $asignacion as element() (:: element(*, ns1:AsignacionTypeUser) ::) external;
declare variable $PropagarFuenteRequest as element() (:: schema-element(ns2:PropagarFuenteRequest) ::) external;
declare variable $codigoresultado external;
declare variable $mensajeerror external;

declare function local:func($asignacion as element() (:: element(*, ns1:AsignacionTypeUser) ::),
                            $PropagarFuenteRequest as element() (:: schema-element(ns2:PropagarFuenteRequest) ::),
                            $codigoresultado, 
                            $mensajeerror) 
                            as element() (:: schema-element(ns2:PropagarFuenteResponse) ::) {
    <ns2:PropagarFuenteResponse>
        <ns2:LineaCredito>
            <lin:CodigoAsignacion>{fn:data($asignacion/ns1:codigoAsignacion)}</lin:CodigoAsignacion>
        {for $array in $asignacion/ns1:listaDetalles/ns1:array
         let $idfuente:= if($array/ns1:codigoLineaPasiva != '') then $PropagarFuenteRequest/ns2:LineaCredito/lin:Fuente[string(lin:idLineaPasiva) = $array/ns1:codigoLineaPasiva]/lin:idFuente/text()
                          else $PropagarFuenteRequest/ns2:LineaCredito/lin:Fuente[empty(lin:FechaObtenido) or string(lin:FechaObtenido)='']/lin:idFuente/text()
            return
            <lin:Fuente>
                <lin:idFuente>{$idfuente}</lin:idFuente>
                <lin:codigoLineaPasiva>{fn:data($array/ns1:codigoLineaPasiva)}</lin:codigoLineaPasiva>
                <lin:NumeroAsignacion>{fn:data($array/ns1:numeroAsignacion)}</lin:NumeroAsignacion>
            </lin:Fuente>
        }
        </ns2:LineaCredito>
        <ns2:Resultado>
             {
             if ( fn:data($codigoresultado) = '0' ) then
                <res:result>OK</res:result>
             else
                <res:result>ERROR</res:result>
             }
            <res:message>{fn:data($mensajeerror)}</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:PropagarFuenteResponse>
};

local:func($asignacion,$PropagarFuenteRequest, $codigoresultado, $mensajeerror)
