xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearAvisoCobroV2_db";
(:: import schema at "../XSD/CrearAvisoCobroV2_db_sp.xsd" ::)

declare variable $aviso as element() (:: schema-element(ns1:CrearAvisoCobroV2Request) ::) external;

declare function local:func($aviso as element() (:: schema-element(ns1:CrearAvisoCobroV2Request) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_FECHA_INICIO>
        {
        fn:concat(fn:day-from-date($aviso/ns1:fechaInicio),'-'
                ,fn:month-from-date($aviso/ns1:fechaInicio),'-'
                ,fn:year-from-date($aviso/ns1:fechaInicio))
        }
        </ns2:P_FECHA_INICIO>
        <ns2:P_FECHA_FIN>
        {
        fn:concat(fn:day-from-date($aviso/ns1:fechaFin),'-'
                ,fn:month-from-date($aviso/ns1:fechaFin),'-'
                ,fn:year-from-date($aviso/ns1:fechaFin))
        }
        </ns2:P_FECHA_FIN>
        <ns2:P_SECTOR>{fn:data($aviso/ns1:sectorInstitucional)}</ns2:P_SECTOR>
        {
            if($aviso/ns1:codigoFondo)
            then
                <ns2:P_CODIGO_FONDO>{fn:data($aviso/ns1:codigoFondo)}</ns2:P_CODIGO_FONDO>
            else ()
        }
        {
            if($aviso/ns1:codigoCliente)
            then
                <ns2:P_CODIGO_CLIENTE>{fn:data($aviso/ns1:codigoCliente)}</ns2:P_CODIGO_CLIENTE>
            else ()
        }
        {
            if($aviso/ns1:operacion)
            then
                <ns2:P_OPERACION>{fn:data($aviso/ns1:operacion)}</ns2:P_OPERACION>
            else ()
        }
        {
            if($aviso/ns1:lineaCredito)
            then
                <ns2:P_LINEA_CREDITO>{fn:data($aviso/ns1:lineaCredito)}</ns2:P_LINEA_CREDITO>
            else ()
        }
        {
            if($aviso/ns1:vencido)
            then
                <ns2:P_VENCIDO>{fn:data($aviso/ns1:vencido)}</ns2:P_VENCIDO>
            else ()
        }
    </ns2:InputParameters>
};

local:func($aviso)
