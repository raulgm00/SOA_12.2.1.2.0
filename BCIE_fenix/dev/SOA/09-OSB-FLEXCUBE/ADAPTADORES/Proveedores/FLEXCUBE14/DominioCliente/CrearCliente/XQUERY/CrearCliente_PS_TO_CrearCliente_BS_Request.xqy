xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/FLEXCUBE14/ClienteMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Flexcube14/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/crearCliente_DB";
(:: import schema at "../XSD/crearCliente_DB_sp.xsd" ::)

declare variable $ClienteRequest as element() (:: schema-element(ns1:CreaClienteRequest) ::) external;

declare function local:func($ClienteRequest as element() (:: schema-element(ns1:CreaClienteRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PRECCLIENTE>
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Ejecutivo)
                then <ns2:EJECUTIVO>{fn:data($ClienteRequest/ns1:Cliente/ns1:Ejecutivo)}</ns2:EJECUTIVO>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Razon_Social)
                then <ns2:RAZON_SOCIAL>{fn:data($ClienteRequest/ns1:Cliente/ns1:Razon_Social)}</ns2:RAZON_SOCIAL>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Abreviatura)
                then <ns2:ABREVIATURA>{fn:data($ClienteRequest/ns1:Cliente/ns1:Abreviatura)}</ns2:ABREVIATURA>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Tipo_Identificacion)
                then <ns2:TIPO_IDENTIFICACION>{fn:data($ClienteRequest/ns1:Cliente/ns1:Tipo_Identificacion)}</ns2:TIPO_IDENTIFICACION>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Identificacion)
                then <ns2:IDENTIFICACION>{fn:data($ClienteRequest/ns1:Cliente/ns1:Identificacion)}</ns2:IDENTIFICACION>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Grupo_Empresarial)
                then <ns2:GRUPO_EMPRESARIAL>{fn:data($ClienteRequest/ns1:Cliente/ns1:Grupo_Empresarial)}</ns2:GRUPO_EMPRESARIAL>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Tipo_Persona)
                then <ns2:TIPO_PERSONA>{fn:data($ClienteRequest/ns1:Cliente/ns1:Tipo_Persona)}</ns2:TIPO_PERSONA>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Tipo_Institucion)
                then <ns2:TIPO_INSTITUCION>{fn:data($ClienteRequest/ns1:Cliente/ns1:Tipo_Institucion)}</ns2:TIPO_INSTITUCION>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Sector_Institucional)
                then <ns2:SECTOR_INSTITUCIONAL>{fn:data($ClienteRequest/ns1:Cliente/ns1:Sector_Institucional)}</ns2:SECTOR_INSTITUCIONAL>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Actividad_Economica)
                then <ns2:ACTIVIDAD_ECONOMICA>{fn:data($ClienteRequest/ns1:Cliente/ns1:Actividad_Economica)}</ns2:ACTIVIDAD_ECONOMICA>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Eje_Estrategico)
                then <ns2:EJE_ESTRATEGICO>{fn:data($ClienteRequest/ns1:Cliente/ns1:Eje_Estrategico)}</ns2:EJE_ESTRATEGICO>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Direccion)
                then <ns2:DIRECCION>{fn:data($ClienteRequest/ns1:Cliente/ns1:Direccion)}</ns2:DIRECCION>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Pais)
                then <ns2:PAIS>{fn:data($ClienteRequest/ns1:Cliente/ns1:Pais)}</ns2:PAIS>
                else ()
            }
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Ciudad)
                then <ns2:CIUDAD>{fn:data($ClienteRequest/ns1:Cliente/ns1:Ciudad)}</ns2:CIUDAD>
                else ()
            }           
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Telefono)
                then <ns2:TELEFONO>{fn:data($ClienteRequest/ns1:Cliente/ns1:Telefono)}</ns2:TELEFONO>
                else ()
            }          
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Fax)
                then <ns2:FAX>{fn:data($ClienteRequest/ns1:Cliente/ns1:Fax)}</ns2:FAX>
                else ()
            }         
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Email)
                then <ns2:EMAIL>{fn:data($ClienteRequest/ns1:Cliente/ns1:Email)}</ns2:EMAIL>
                else ()
            }          
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Dia_De_Pago)
                then <ns2:DIA_DE_PAGO>{fn:data($ClienteRequest/ns1:Cliente/ns1:Dia_De_Pago)}</ns2:DIA_DE_PAGO>
                else ()
            }          
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Cliente_Padre)
                then <ns2:CLIENTE_PADRE>{fn:data($ClienteRequest/ns1:Cliente/ns1:Cliente_Padre)}</ns2:CLIENTE_PADRE>
                else ()
            }          
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Puesto)
                then <ns2:PUESTO>{fn:data($ClienteRequest/ns1:Cliente/ns1:Puesto)}</ns2:PUESTO>
                else ()
            }           
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Tipo_Relacion)
                then <ns2:TIPO_RELACION>{fn:data($ClienteRequest/ns1:Cliente/ns1:Tipo_Relacion)}</ns2:TIPO_RELACION>
                else ()
            }          
            {
                if ($ClienteRequest/ns1:Cliente/ns1:BIC_Code)
                then <ns2:BIC_CODE>{fn:data($ClienteRequest/ns1:Cliente/ns1:BIC_Code)}</ns2:BIC_CODE>
                else ()
            }
                     
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Codigo_Cliente)
                then <ns2:CODIGO_CLIENTE>{fn:data($ClienteRequest/ns1:Cliente/ns1:Codigo_Cliente)}</ns2:CODIGO_CLIENTE>
                else ()
            }          
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Overall_Limit)
                then <ns2:OVERALL_LIMIT>{fn:data($ClienteRequest/ns1:Cliente/ns1:Overall_Limit)}</ns2:OVERALL_LIMIT>
                else ()
            }          
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Rating)
                then <ns2:RATING>{fn:data($ClienteRequest/ns1:Cliente/ns1:Rating)}</ns2:RATING>
                else ()
            }           
            {
                if ($ClienteRequest/ns1:Cliente/ns1:Autorizado)
                then <ns2:AUTORIZADO>{fn:data($ClienteRequest/ns1:Cliente/ns1:Autorizado)}</ns2:AUTORIZADO>
                else ()
            }
        </ns2:PRECCLIENTE>
        <ns2:PVUSUARIO>{fn:data($ClienteRequest/ns1:Usuario)}</ns2:PVUSUARIO>
    </ns2:InputParameters>
};

local:func($ClienteRequest)