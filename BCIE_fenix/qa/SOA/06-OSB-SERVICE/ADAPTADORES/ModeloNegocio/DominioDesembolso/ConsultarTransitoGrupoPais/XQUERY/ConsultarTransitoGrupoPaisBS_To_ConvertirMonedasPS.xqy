xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarTransitoGrupoPais";
(:: import schema at "../XSD/ConsultarTransitoGrupoPais_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/ConversorMonedasBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $SP_ConultarTransitoGrupoPaisOutput as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($SP_ConultarTransitoGrupoPaisOutput as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConvertirMonedasImportesRequest) ::) {
    <ns2:ConvertirMonedasImportesRequest>
        {
            for $Row in $SP_ConultarTransitoGrupoPaisOutput/ns1:RECORDSET_GE/ns1:Row[ns1:Column[@name='COD_EXTERNO']!='USD']
            return 
            <ns2:ConvierteMonedas>
                <con:monedaDe>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>RECORDSET_GE</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($Row/ns1:Column[@name='COD_EXTERNO'])}</cat:codigoExterno>
                </con:monedaDe>
                <con:monedaA>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>RECORDSET_GE</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>USD</cat:codigoExterno>
                </con:monedaA>
                <con:monto>{fn:data($Row/ns1:Column[@name='MONTO_DESEMBOLSAR'])}</con:monto>
            </ns2:ConvierteMonedas>
        }
        {
            for $RowPais in $SP_ConultarTransitoGrupoPaisOutput/ns1:RECORDSET_PAIS/ns1:Row[ns1:Column[@name='COD_EXTERNO']!='USD']
            return 
            <ns2:ConvierteMonedas>
                <con:monedaDe>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>RECORDSET_PAIS</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($RowPais/ns1:Column[@name='COD_EXTERNO'])}</cat:codigoExterno>
                </con:monedaDe>
                <con:monedaA>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>RECORDSET_PAIS</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>USD</cat:codigoExterno>
                </con:monedaA>
                 <con:monto>{fn:data($RowPais/ns1:Column[@name='MONTO_DESEMBOLSAR'])}</con:monto>
            </ns2:ConvierteMonedas>
        }
    </ns2:ConvertirMonedasImportesRequest>
};

local:func($SP_ConultarTransitoGrupoPaisOutput)
