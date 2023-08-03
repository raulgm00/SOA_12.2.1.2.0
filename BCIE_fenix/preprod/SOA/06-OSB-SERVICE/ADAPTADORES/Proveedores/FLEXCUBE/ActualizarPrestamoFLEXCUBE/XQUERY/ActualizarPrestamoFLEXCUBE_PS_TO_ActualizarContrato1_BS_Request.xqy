xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ActualizarContrato1";
(:: import schema at "../XSD/ActualizarContrato1_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace xsi="http://www.w3.org/2001/XMLSchema-instance";

declare variable $ActualizarPrestamoFLEXCUBERequest as element() (:: schema-element(ns1:ActualizarPrestamoFLEXCUBERequest) ::) external;

declare function local:func($ActualizarPrestamoFLEXCUBERequest as element() (:: schema-element(ns1:ActualizarPrestamoFLEXCUBERequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:NUMERO_LINEA_CREDITO>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:referencia)}</ns2:NUMERO_LINEA_CREDITO>
        <ns2:FECHA_VALOR>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:fechaEfectiva)}</ns2:FECHA_VALOR>
        <ns2:MONEDA></ns2:MONEDA>
        <ns2:MONTO_DELTA></ns2:MONTO_DELTA>
        <ns2:FECHA_MADUREZ></ns2:FECHA_MADUREZ>
        <ns2:LISTACOMISIONES>
            <ns2:LISTACOMISIONES_ITEM>
                <ns2:TIPO_TASA>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)}</ns2:TIPO_TASA>
                {
                   if($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno = 'F')then
                   <ns2:CODIGO_TASA>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:tasaReferencia/cat:Id)}</ns2:CODIGO_TASA>
                   else(<ns2:CODIGO_TASA xsi:nil="true"/>)
                }
                <ns2:TIPO_COMISION>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:producto/des:Componente[des:TipoComponente/cat:codigoExterno = 'INTERES']/cat:codigoExterno)}</ns2:TIPO_COMISION>
                <ns2:BASE_CALCULO></ns2:BASE_CALCULO>
                <ns2:FECHA_INICIO></ns2:FECHA_INICIO>
                <ns2:FRECUENCIA></ns2:FRECUENCIA>
                <ns2:TIPO_FRECUENCIA></ns2:TIPO_FRECUENCIA>
                {
                   if($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno = 'F')then
                   <ns2:SPREAD>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:valor)}</ns2:SPREAD>
                   else(<ns2:SPREAD xsi:nil="true"/>)
                }
                <ns2:SPREAD_MORA></ns2:SPREAD_MORA>
                {
                   if($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno = 'X')then
                   <ns2:VALOR_COMISION>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:fija/des:valor)}</ns2:VALOR_COMISION>
                   else(<ns2:VALOR_COMISION xsi:nil="true"/>)
                }
                <ns2:MONTO xsi:nil="true"/>
                <ns2:TASA_MINIMA></ns2:TASA_MINIMA>
                <ns2:TASA_MAXIMA></ns2:TASA_MAXIMA>
                <ns2:FECHA_INICIO_REVISION></ns2:FECHA_INICIO_REVISION>
                <ns2:FRECUENCIA_REVISION></ns2:FRECUENCIA_REVISION>
                <ns2:TIPO_FRECUENCIA_REVISION></ns2:TIPO_FRECUENCIA_REVISION>
            </ns2:LISTACOMISIONES_ITEM>
        </ns2:LISTACOMISIONES>
        <ns2:PVUSUARIO>SYSTEM</ns2:PVUSUARIO>
    </ns2:InputParameters>
};

local:func($ActualizarPrestamoFLEXCUBERequest)