xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroBO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroBO.xsd" ::)
declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ReciboPago as element() (:: schema-element(ns1:ReciboPago) ::) external;

declare function local:func($ReciboPago as element() (:: schema-element(ns1:ReciboPago) ::)) as element() (:: schema-element(ns2:GenerarReciboPagoProcesoRequest) ::) {
    <ns2:GenerarReciboPagoProcesoRequest>
        <ns2:ReciboPago>
            <ns1:recibo>
                <ns1:Recibo>{fn:data($ReciboPago/ns1:recibo/ns1:Recibo)}</ns1:Recibo>
                <ns1:FechaEjecucion>{fn:data($ReciboPago/ns1:recibo/ns1:FechaEjecucion)}</ns1:FechaEjecucion>
                <ns1:FechaEfectiva>{fn:data($ReciboPago/ns1:recibo/ns1:FechaEfectiva)}</ns1:FechaEfectiva>
                <ns1:Cliente>
                    <cli:idCliente>{fn:data($ReciboPago/ns1:recibo/ns1:Cliente/cli:idCliente)}</cli:idCliente>
                    <cli:idFacturador>{fn:data($ReciboPago/ns1:recibo/ns1:Cliente/cli:idFacturador)}</cli:idFacturador>
                    <cli:razonSocial>{fn:data($ReciboPago/ns1:recibo/ns1:Cliente/cli:razonSocial)}</cli:razonSocial>
                    <cli:responsableCliente>{fn:data($ReciboPago/ns1:recibo/ns1:Cliente/cli:responsableCliente)}</cli:responsableCliente>
                </ns1:Cliente>
                {
                for $desembolso in $ReciboPago/ns1:recibo/ns1:Desembolsos
                return
                <ns1:Desembolsos>
                    <des:idFacturador>{fn:data($desembolso/des:idFacturador)}</des:idFacturador>
                    <des:referencia>{fn:data($desembolso/des:referencia)}</des:referencia>
                    {
                    for $monto in $desembolso/des:monto
                    return
                    <des:monto>
                        <com:importe>{fn:data($monto/com:importe)}</com:importe>
                        <com:moneda>
                            <cat:Descripcion>{fn:data($monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            <cat:codigoExterno>{fn:data($monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                        </com:moneda>
                    </des:monto>
                    }
                </ns1:Desembolsos>
              }
            </ns1:recibo>
        </ns2:ReciboPago>
    </ns2:GenerarReciboPagoProcesoRequest>
};

local:func($ReciboPago)
