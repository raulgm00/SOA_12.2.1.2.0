xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarTransaccionCondicionResponse as element() (:: schema-element(ns1:ConsultarTransaccionCondicionResponse) ::) external;
declare variable $ActualizarEstadoCondicionRequest as element() (:: schema-element(ns1:ActualizarEstadoCondicionRequest) ::) external;

declare function local:func($ConsultarTransaccionCondicionResponse as element() (:: schema-element(ns1:ConsultarTransaccionCondicionResponse) ::), 
                            $ActualizarEstadoCondicionRequest as element() (:: schema-element(ns1:ActualizarEstadoCondicionRequest) ::)) 
                            as element() (:: schema-element(ns1:UpsertTransaccionCondicionRequest) ::) {
    let $condiciones := $ConsultarTransaccionCondicionResponse/ns1:TransaccionCondicion/con:Condicion[con:idCondicion = $ActualizarEstadoCondicionRequest/ns1:idCondicion]
    return
    <ns1:UpsertTransaccionCondicionRequest>
        <ns1:transaccionCondicion>
            {for $condicion in $condiciones
            return
            <con:Condicion>
                <con:idCondicion>{fn:data($condicion/con:idCondicion)}</con:idCondicion>
            </con:Condicion>
            }
            <con:EstadoTCC>
                {
                    if ($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:Id)
                    then <cat:Id>{fn:data($ActualizarEstadoCondicionRequest/ns1:EstadoTCC/cat:Id)}</cat:Id>
                    else ()
                }
            </con:EstadoTCC>
            <con:agrupador>{fn:data($ActualizarEstadoCondicionRequest/ns1:Agrupador)}</con:agrupador>
            <con:subEstadoTCC>
                {
                    if ($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:Id)
                    then <cat:Id>{fn:data($ActualizarEstadoCondicionRequest/ns1:SubEstadoTCC/cat:Id)}</cat:Id>
                    else ()
                }
            </con:subEstadoTCC>
        </ns1:transaccionCondicion>
    </ns1:UpsertTransaccionCondicionRequest>
};

local:func($ConsultarTransaccionCondicionResponse, $ActualizarEstadoCondicionRequest)
