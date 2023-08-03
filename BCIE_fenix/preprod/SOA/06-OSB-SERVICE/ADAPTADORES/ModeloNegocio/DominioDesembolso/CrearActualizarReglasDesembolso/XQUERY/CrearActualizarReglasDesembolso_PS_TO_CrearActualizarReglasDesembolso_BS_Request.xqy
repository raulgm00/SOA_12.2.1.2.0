xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarReglasDesembolso_DB";
(:: import schema at "../XSD/CrearActualizarReglasDesembolso_DB_sp.xsd" ::)

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearActualizarReglasDesembolsoRequest as element() (:: schema-element(ns1:CrearActualizarReglasDesembolsoRequest) ::) external;

declare function local:func($CrearActualizarReglasDesembolsoRequest as element() (:: schema-element(ns1:CrearActualizarReglasDesembolsoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_LISTA_REGLA_DESEMBOLSO>
          {
          for $reglasDesembolso in $CrearActualizarReglasDesembolsoRequest/ns1:ReglasDesembolso
          return
            <ns2:P_LISTA_REGLA_DESEMBOLSO_ITEM>
                {
                    if ($reglasDesembolso/reg:Exceptuado/cat:Id)
                    then <ns2:ID>{fn:data($reglasDesembolso/reg:Exceptuado/cat:Id)}</ns2:ID>
                    else ()
                }
                <ns2:ID_DESEMBOLSO>{fn:data($CrearActualizarReglasDesembolsoRequest/ns1:idDesembolso)}</ns2:ID_DESEMBOLSO>
                <ns2:ID_TCA_REGLA>{fn:data($reglasDesembolso/reg:Id)}</ns2:ID_TCA_REGLA>
                {
                    if ($reglasDesembolso/reg:Exceptuado/cat:estatus)
                    then <ns2:EXCEPTUADO>{
                    if (fn:data($reglasDesembolso/reg:Exceptuado/cat:estatus)= true())then 1 else 0}</ns2:EXCEPTUADO>
                    else ()
                }
                {
                    if ($reglasDesembolso/reg:UsuarioExceptua)
                    then <ns2:USUARIO_EXCEPTUA>{fn:data($reglasDesembolso/reg:UsuarioExceptua)}</ns2:USUARIO_EXCEPTUA>
                    else ()
                }
                {
                    if ($reglasDesembolso/reg:FechaExceptua)
                    then <ns2:FECHA_EXCEPCION>{fn:data($reglasDesembolso/reg:FechaExceptua)}</ns2:FECHA_EXCEPCION>
                    else ()
                }
            </ns2:P_LISTA_REGLA_DESEMBOLSO_ITEM>
            }
        </ns2:P_LISTA_REGLA_DESEMBOLSO>
    </ns2:InputParameters>
};

local:func($CrearActualizarReglasDesembolsoRequest)
