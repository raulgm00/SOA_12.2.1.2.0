xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarProductoComisionByIdLineaCredito_DB";
(:: import schema at "../XSD/ConsultarProductoComisionByIdLineaCredito_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarProductoComisionByIdLineaCredito_DBOutputCollection as element() (:: schema-element(ns1:ConsultarProductoComisionByIdLineaCredito_DBOutputCollection) ::) external;

declare function local:func($ConsultarProductoComisionByIdLineaCredito_DBOutputCollection as element() (:: schema-element(ns1:ConsultarProductoComisionByIdLineaCredito_DBOutputCollection) ::)) as element() (:: element(*, ns2:ConsultarComisionByIdLineaCreditoResponseType) ::) {
    <ns2:ConsultarComisionByIdLineaCreditoResponseType>
    {
    for $comisionProducto in $ConsultarProductoComisionByIdLineaCredito_DBOutputCollection/ns1:ConsultarProductoComisionByIdLineaCredito_DBOutput
    return
        <ns2:Comision>
            <com:tipoComision>
                <com:idTipoComision>
                    <cat:Descripcion>{fn:data($comisionProducto/ns1:CODIGO)}</cat:Descripcion>
                </com:idTipoComision>
            </com:tipoComision>
            <com:montoComision></com:montoComision>
            <com:montoBase>
                <com:idMontoBase></com:idMontoBase>
                <com:valorMontoBase></com:valorMontoBase>
                <com:porcentajeMontoBase>0</com:porcentajeMontoBase>
                <com:descripcionMontoBase></com:descripcionMontoBase>
            </com:montoBase>
            <com:fechaInicioCapital></com:fechaInicioCapital>
            <com:tipoFrecuencia>
                <cat:codigoExterno>M</cat:codigoExterno>
            </com:tipoFrecuencia>
            <com:frecuenciaPago>6</com:frecuenciaPago>
            <com:tipoTasa>
                <cat:codigoExterno>X</cat:codigoExterno>
            </com:tipoTasa>
            <com:baseCalculo>
                <cat:Id>7</cat:Id>
                <cat:codigoExterno>3</cat:codigoExterno>
            </com:baseCalculo>
        </ns2:Comision>
        }
    <ns2:Resultado>
        <res:result>OK</res:result>
        <res:message>{
        if (count($ConsultarProductoComisionByIdLineaCredito_DBOutputCollection/ns1:ConsultarProductoComisionByIdLineaCredito_DBOutput)>0)
        then ('Consulta Exitosa')
        else('No existen registros')
        }</res:message>
    </ns2:Resultado>
    </ns2:ConsultarComisionByIdLineaCreditoResponseType>
};

local:func($ConsultarProductoComisionByIdLineaCredito_DBOutputCollection)
