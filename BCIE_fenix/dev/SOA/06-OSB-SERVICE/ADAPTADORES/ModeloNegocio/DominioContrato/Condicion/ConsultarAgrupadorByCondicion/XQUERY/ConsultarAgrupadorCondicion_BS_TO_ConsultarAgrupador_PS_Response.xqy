xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAgrupadorByCondicion";
(:: import schema at "../XSD/ConsultarAgrupadorByCondicion.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarAgrupadorByCondicionOutputCollection as element() (:: schema-element(ns1:ConsultarAgrupadorByCondicionOutputCollection) ::) external;

declare function local:func($ConsultarAgrupadorByCondicionOutputCollection as element() (:: schema-element(ns1:ConsultarAgrupadorByCondicionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarAgrupadorByCondicionResponse) ::) {
    <ns2:ConsultarAgrupadorByCondicionResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La consulta se ha realizado correctamente</res:message>
        </ns2:Resultado>
        <ns2:Agrupadores>
        {for $agrupador in $ConsultarAgrupadorByCondicionOutputCollection/ns1:ConsultarAgrupadorByCondicionOutput
         return
          <ns2:Agrupador>
                <ns2:idAgrupador>{fn:data($agrupador/ns1:AGRUPADOR)}</ns2:idAgrupador>
            </ns2:Agrupador>
        }            
        </ns2:Agrupadores>
    </ns2:ConsultarAgrupadorByCondicionResponse>
};

local:func($ConsultarAgrupadorByCondicionOutputCollection)
