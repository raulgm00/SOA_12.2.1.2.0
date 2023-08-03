xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarCondicionDB";
(:: import schema at "../XSD/ConsultarCondicionDB_table.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $CondicionResponse as element() (:: schema-element(ns1:CondicionCollection) ::) external;

declare function local:func($CondicionResponse as element() (:: schema-element(ns1:CondicionCollection) ::)) as element() (:: schema-element(ns2:ConsultarCondicionResponse) ::) {
    <ns2:ConsultarCondicionResponse>
        <ns2:Condicion>
            <con:idCondicion>{fn:data($CondicionResponse/ns1:Condicion/ns1:id)}</con:idCondicion>
            <con:operacion>
                {
                    if ($CondicionResponse/ns1:Condicion/ns1:idOperacion)
                    then <ope:idOperacion>{fn:data($CondicionResponse/ns1:Condicion/ns1:idOperacion)}</ope:idOperacion>
                    else ()
                }                
            </con:operacion>
            <con:nombre>{fn:data($CondicionResponse/ns1:Condicion/ns1:nombre)}</con:nombre>
            <con:descripcion>{fn:data($CondicionResponse/ns1:Condicion/ns1:descripcion)}</con:descripcion>
            <con:tipoCondicion>
                <con:idCondicion>{fn:data($CondicionResponse/ns1:Condicion/ns1:idTcaCondicion)}</con:idCondicion>                
            </con:tipoCondicion>
            <con:controlCondicion>
                {
                    if ($CondicionResponse/ns1:Condicion/ns1:idTcaControlCondicion)
                    then <cat:Id>{fn:data($CondicionResponse/ns1:Condicion/ns1:idTcaControlCondicion)}</cat:Id>
                    else ()
                }
            </con:controlCondicion>
            <con:tipoFechaInicio>
                {
                    if ($CondicionResponse/ns1:Condicion/ns1:idTcaTipoFechaInicio)
                    then <cat:Id>{fn:data($CondicionResponse/ns1:Condicion/ns1:idTcaTipoFechaInicio)}</cat:Id>
                    else ()
                }
            </con:tipoFechaInicio>
            <con:fechaInicio>{fn:data($CondicionResponse/ns1:Condicion/ns1:fechaInicio)}</con:fechaInicio>
            <con:plazo>{fn:data($CondicionResponse/ns1:Condicion/ns1:plazo)}</con:plazo>
            <con:frecuenciaPlazo>
                {
                    if ($CondicionResponse/ns1:Condicion/ns1:idTcaFrecuenciaPlazo)
                    then <cat:Id>{fn:data($CondicionResponse/ns1:Condicion/ns1:idTcaFrecuenciaPlazo)}</cat:Id>
                    else ()
                }
            </con:frecuenciaPlazo>
            <con:fechaFinal>{fn:data($CondicionResponse/ns1:Condicion/ns1:fechaFinal)}</con:fechaFinal>
            <con:estadoTCC>
                {
                    if ($CondicionResponse/ns1:Condicion/ns1:idTcaEstadoTcc)
                    then <atr:id>{fn:data($CondicionResponse/ns1:Condicion/ns1:idTcaEstadoTcc)}</atr:id>
                    else ()
                }
            </con:estadoTCC>
            <con:subEstadoTCC>
                {
                    if ($CondicionResponse/ns1:Condicion/ns1:idTcaSubEstadoTcc)
                    then <atr:id>{fn:data($CondicionResponse/ns1:Condicion/ns1:idTcaSubEstadoTcc)}</atr:id>
                    else ()
                }
            </con:subEstadoTCC>            
            <con:fechaRegistro>{fn:data($CondicionResponse/ns1:Condicion/ns1:fechaRegistro)}</con:fechaRegistro>
            <con:estado>{fn:data($CondicionResponse/ns1:Condicion/ns1:banEstatus)}</con:estado>
            <con:CondicionEnmendada>
                <con:idCondicion>{fn:data($CondicionResponse/ns1:Condicion/ns1:idCondicionEnmendada/ns1:id)}</con:idCondicion>
            </con:CondicionEnmendada>
            <con:fechaEnmienda>{fn:data($CondicionResponse/ns1:Condicion/ns1:fechaEnmienda)}</con:fechaEnmienda>
            {
                if ($CondicionResponse/ns1:Condicion/ns1:fechaVigencia)
                then <con:fechaVigencia>{fn:data($CondicionResponse/ns1:Condicion/ns1:fechaVigencia)}</con:fechaVigencia>
                else ()
            }
            
        </ns2:Condicion>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La consulta se ha realizado correctamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarCondicionResponse>
};

local:func($CondicionResponse)