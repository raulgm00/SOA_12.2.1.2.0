xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarComisionesAsignadas";
(:: import schema at "../XSD/ConsultarComisionesAsignadas.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarComisionesAsignadasOutputCollection as element() (:: schema-element(ns1:ConsultarComisionesAsignadasOutputCollection) ::) external;

declare function local:func($ConsultarComisionesAsignadasOutputCollection as element() (:: schema-element(ns1:ConsultarComisionesAsignadasOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarComisionesAsignadasResponse) ::) {
    <ns2:ConsultarComisionesAsignadasResponse>
    {
    for $idDesembolso in distinct-values($ConsultarComisionesAsignadasOutputCollection/ns1:ConsultarComisionesAsignadasOutput/ns1:ID_DESEMBOLSO)
        let $desembolso := $ConsultarComisionesAsignadasOutputCollection/ns1:ConsultarComisionesAsignadasOutput[ns1:ID_DESEMBOLSO = $idDesembolso][1]
        return 
        <ns2:Desembolso>
            <des:idDesembolso>{fn:data($idDesembolso)}</des:idDesembolso>
            <des:monto>
                <com:tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>DESEMBOLSO</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipo>
                <com:importe>{fn:data($desembolso/ns1:MONTO_DESEMBOLSAR)}</com:importe>
            </des:monto>
            <des:estado>
                <cat:Id>{fn:data($desembolso/ns1:ID_TCA_ESTADO)}</cat:Id>
            </des:estado>
            <des:programado>{fn:data($desembolso/ns1:PROGRAMADO)}</des:programado>
            <des:fechaEstimadaDesembolsar>{fn:data($desembolso/ns1:FECHA_ESTIMADA_DESEMBOLSAR)}</des:fechaEstimadaDesembolsar>
            <des:fechaEfectiva>{fn:data($desembolso/ns1:FECHA_EFECTIVA)}</des:fechaEfectiva>
            <des:fechaAsignacion></des:fechaAsignacion>
            <des:fechaRegistro>{fn:data($desembolso/ns1:FECHA_REGISTRO_DESEMBLOSO)}</des:fechaRegistro>
            <des:estatus></des:estatus>
            <des:idLinea></des:idLinea>
            <des:recursosExternos>{fn:data($desembolso/ns1:RECURSOS_EXTERNOS)}</des:recursosExternos>
            {for $comision in $ConsultarComisionesAsignadasOutputCollection/ns1:ConsultarComisionesAsignadasOutput[ns1:ID_DESEMBOLSO = $idDesembolso]
                return
                <des:Comision>
                    <com1:idComision>{fn:data($comision/ns1:ID_COMISION)}</com1:idComision>
                    <com1:idOperacion>{fn:data($comision/ns1:ID_OPERACION)}</com1:idOperacion>
                    <com1:nombre>{fn:data($comision/ns1:NOMBRE)}</com1:nombre>
                    <com1:descripcion>{fn:data($comision/ns1:DESCRIPCION_COMISION)}</com1:descripcion>
                    <com1:tipoComision>
                        <com1:idCatComision>{fn:data($comision/ns1:ID_TCA_COMISION)}</com1:idCatComision>
                        <com1:descripcion></com1:descripcion>
                    </com1:tipoComision>
                </des:Comision>
              }
        </ns2:Desembolso>
        }
    <ns2:Resultado>
        <res:result>OK</res:result>
        <res:message>{if (count($ConsultarComisionesAsignadasOutputCollection/ns1:ConsultarComisionesAsignadasOutput)>0)
              then ('Consulta Exitosa')
              else('Consulta sin resultados')
              }
        </res:message>
    </ns2:Resultado>
    </ns2:ConsultarComisionesAsignadasResponse>
};

local:func($ConsultarComisionesAsignadasOutputCollection)
