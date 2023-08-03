xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/EliminarLineaCredito";
(:: import schema at "../XSD/EliminarLineaCredito_table.xsd" ::)

declare variable $EliminarLineaCreditoRequest as element() (:: schema-element(ns1:EliminarLineaCreditoRequest) ::) external;

declare function local:func($EliminarLineaCreditoRequest as element() (:: schema-element(ns1:EliminarLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:LineaCreditoCollection) ::) {
    <ns2:LineaCreditoCollection>
        <ns2:LineaCredito>
            <ns2:id>{fn:data($EliminarLineaCreditoRequest/ns1:idLineaCreadito)}</ns2:id>
            <ns2:idContrato></ns2:idContrato>
            <ns2:numeroLineaCredito></ns2:numeroLineaCredito>
            <ns2:descripcionLinea></ns2:descripcionLinea>
            <ns2:idFlexcube></ns2:idFlexcube>
            <ns2:fondo></ns2:fondo>
            <ns2:montoLinea></ns2:montoLinea>
            <ns2:esRevolvente></ns2:esRevolvente>
            <ns2:tratamientoDiasFeriados></ns2:tratamientoDiasFeriados>
            <ns2:idProductoFlexcube></ns2:idProductoFlexcube>
            <ns2:fechaValor></ns2:fechaValor>
            <ns2:fechaVencimiento></ns2:fechaVencimiento>
            <ns2:codigoPlantillaLimite></ns2:codigoPlantillaLimite>
            <ns2:codigoSerialLimite></ns2:codigoSerialLimite>
            <ns2:codigoAsignacion></ns2:codigoAsignacion>
            <ns2:descripcionAsignacion></ns2:descripcionAsignacion>
            <ns2:idFlexcubePasivo></ns2:idFlexcubePasivo>
            <ns2:condicionEspecial></ns2:condicionEspecial>
            <ns2:descripcionCondEspecial></ns2:descripcionCondEspecial>
            <ns2:fechaRegistro></ns2:fechaRegistro>
            <ns2:banEstatus></ns2:banEstatus>
        </ns2:LineaCredito>
    </ns2:LineaCreditoCollection>
};

local:func($EliminarLineaCreditoRequest)
