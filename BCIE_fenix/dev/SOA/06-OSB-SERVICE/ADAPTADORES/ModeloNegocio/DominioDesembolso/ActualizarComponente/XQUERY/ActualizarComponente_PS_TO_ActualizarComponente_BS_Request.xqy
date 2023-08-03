xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarComponente";
(:: import schema at "../XSD/ActualizarComponente_table.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBOV2";

declare variable $componente as element() (:: schema-element(ns2:ActualizarComponenteRequest) ::) external;

declare function local:func($componente as element() (:: schema-element(ns2:ActualizarComponenteRequest) ::)) as element() (:: schema-element(ns1:ComponenteCollection) ::) {
    <ns1:ComponenteCollection>
        <ns1:Componente>
            <ns1:id>{fn:data($componente/ns2:Componente/cat:Id)}</ns1:id>
            {
                if ($componente/ns2:Componente/des:TipoComponente/cat:Id)
                then <ns1:idTcaTipoComponente>{fn:data($componente/ns2:Componente/des:TipoComponente/cat:Id)}</ns1:idTcaTipoComponente>
                else ()
            }
            {
                if ($componente/ns2:Componente/des:TipoTasa/cat:Id)
                then <ns1:idTcaTipoTasaDesembolso>{fn:data($componente/ns2:Componente/des:TipoTasa/cat:Id)}</ns1:idTcaTipoTasaDesembolso>
                else ()
            }
            {
                if ($componente/ns2:Componente/des:BaseCalculo/cat:Id)
                then <ns1:idTcaBaseCalculo>{fn:data($componente/ns2:Componente/des:BaseCalculo/cat:Id)}</ns1:idTcaBaseCalculo>
                else ()
            }
            <ns1:esDependiente>{fn:data($componente/ns2:Componente/des:esDependiente)}</ns1:esDependiente>
            <ns1:esFactor>{fn:data($componente/ns2:Componente/des:esFactor)}</ns1:esFactor>
            <ns1:codigoTasaReferencia>{fn:data($componente/ns2:Componente/des:CodigoTasaReferencia)}</ns1:codigoTasaReferencia>
            <ns1:fechaEfectivaTasaReferencia>{fn:data($componente/ns2:Componente/des:FechaEfectivaTasaReferencia)}</ns1:fechaEfectivaTasaReferencia>
            <ns1:valorTasaReferencia>{fn:data($componente/ns2:Componente/des:ValorTasaReferencia)}</ns1:valorTasaReferencia>
            <ns1:tasa>{fn:data($componente/ns2:Componente/des:Tasa)}</ns1:tasa>
            <ns1:spreadTasa>{fn:data($componente/ns2:Componente/des:SpreadTasa)}</ns1:spreadTasa>
            <ns1:montoDescuento>{fn:data($componente/ns2:Componente/des:MontoDescuento)}</ns1:montoDescuento>
            <ns1:diasSpotTasa>{fn:data($componente/ns2:Componente/des:DiasSpotTasa)}</ns1:diasSpotTasa>
            {
                if ($componente/ns2:Componente/des:TipoRedondeo/cat:Id)
                then <ns1:idTcaTipoRedondeo>{fn:data($componente/ns2:Componente/des:TipoRedondeo/cat:Id)}</ns1:idTcaTipoRedondeo>
                else ()
            }
            <ns1:cantidadDecimales>{fn:data($componente/ns2:Componente/des:CantidadDecimales)}</ns1:cantidadDecimales>
            <ns1:limiteTasaMinima>{fn:data($componente/ns2:Componente/des:LimiteTasaMinima)}</ns1:limiteTasaMinima>
            <ns1:limiteTasaMaxima>{fn:data($componente/ns2:Componente/des:LimiteTasaMaxima)}</ns1:limiteTasaMaxima>
        </ns1:Componente>
    </ns1:ComponenteCollection>
};

local:func($componente)
