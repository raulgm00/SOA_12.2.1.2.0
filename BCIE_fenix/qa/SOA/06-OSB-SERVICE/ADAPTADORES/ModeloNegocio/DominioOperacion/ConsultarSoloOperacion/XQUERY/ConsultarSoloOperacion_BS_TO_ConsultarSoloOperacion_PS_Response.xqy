xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarOperacion";
(:: import schema at "../XSD/ConsultarOperacion_table.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OperacionCollection as element() (:: schema-element(ns1:OperacionCollection) ::) external;

declare function local:func($OperacionCollection as element() (:: schema-element(ns1:OperacionCollection) ::)) as element() (:: schema-element(ns2:ConsultarSoloOperacionResponse) ::) {
    <ns2:ConsultarSoloOperacionResponse>
        <ns2:Operacion>
            <ope:idOperacion>{fn:data($OperacionCollection/ns1:Operacion/ns1:idOperacion)}</ope:idOperacion>
        <ope:responsable>{fn:data($OperacionCollection/ns1:Operacion/ns1:usuario)}</ope:responsable>
               <ope:oficina>{fn:data($OperacionCollection/ns1:Operacion/ns1:oficina)}</ope:oficina>
                <ope:nombre>{fn:data($OperacionCollection/ns1:Operacion/ns1:nombre)}</ope:nombre>
            <ope:cliente>
                <cli:idCliente>{fn:data($OperacionCollection/ns1:Operacion/ns1:idCliente)}</cli:idCliente>
            </ope:cliente>
            <ope:producto>
                <pro:idProducto>{fn:data($OperacionCollection/ns1:Operacion/ns1:idProducto)}</pro:idProducto>
            </ope:producto>
           <ope:descripcion>{fn:data($OperacionCollection/ns1:Operacion/ns1:descripcion)}</ope:descripcion>
            <ope:actividadEconomica>
                <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:actividadEconomica)}</cat:Id>
            </ope:actividadEconomica>
            <ope:actividadEconomicaAsociada>
              <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:idActividadEconomicaAsoc)}</cat:Id>
            </ope:actividadEconomicaAsociada>
           <ope:objetivosEstrategicos>{fn:data($OperacionCollection/ns1:Operacion/ns1:objetivosEstrategicos)}</ope:objetivosEstrategicos>
            <ope:areaFocalizacion>
               <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:areaFocalizacion)}</cat:Id>
            </ope:areaFocalizacion>
            <ope:ejeEstrategico>
                <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:ejeEstrategico)}</cat:Id>
            </ope:ejeEstrategico>
            <ope:iniciativaEstrategica>
                <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:iniciativaEstrategica)}</cat:Id>
            </ope:iniciativaEstrategica>
            <ope:cantidadPaises>
               <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:idRangoPais)}</cat:Id>
            </ope:cantidadPaises>
            <ope:sectorMercado>
                 <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:sectorMercado)}</cat:Id>
            </ope:sectorMercado>
            <ope:fechaInicio>{fn:data($OperacionCollection/ns1:Operacion/ns1:fechaInicio)}</ope:fechaInicio>
            <ope:programadoPOA>{fn:data($OperacionCollection/ns1:Operacion/ns1:programadoPoa)}</ope:programadoPOA>
            <ope:ejercicioPOA>
                 <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:ejercicioPoa)}</cat:Id>
            </ope:ejercicioPOA>
            <ope:tipoGarantia>
              <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:tipoGarantia)}</cat:Id>
            </ope:tipoGarantia>
            <ope:componenteConcesionalidad>{if(fn:data($OperacionCollection/ns1:Operacion/ns1:componenteConcecionalidad)=1) then true() else false()}</ope:componenteConcesionalidad>
            <ope:estructurador>{fn:data($OperacionCollection/ns1:Operacion/ns1:estructurador)}</ope:estructurador>
            <ope:beneficiario>{fn:data($OperacionCollection/ns1:Operacion/ns1:beneficiario)}</ope:beneficiario>
            <ope:unidadEjecutora>{fn:data($OperacionCollection/ns1:Operacion/ns1:unidadEjecutora)}</ope:unidadEjecutora>
            <ope:programa>{fn:data($OperacionCollection/ns1:Operacion/ns1:programa)}</ope:programa>
            <ope:etapa>{fn:data($OperacionCollection/ns1:Operacion/ns1:etapa)}</ope:etapa>
            <ope:status>{fn:data($OperacionCollection/ns1:Operacion/ns1:banEstatus)}</ope:status>
            <ope:fechaBaja>{fn:data($OperacionCollection/ns1:Operacion/ns1:fechaBaja)}</ope:fechaBaja>
            <ope:perspectiva>
                <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:idPerspectiva)}</cat:Id>
            </ope:perspectiva>
            <ope:estado>
                <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:estado)}</cat:Id>
            </ope:estado>
            <ope:lineaCredito></ope:lineaCredito>
            <ope:SRC>
                <cat:Id>{fn:data($OperacionCollection/ns1:Operacion/ns1:scr)}</cat:Id>
                 <cat:Descripcion>{fn:data($OperacionCollection/ns1:Operacion/ns1:scrEstatus)}</cat:Descripcion>
            </ope:SRC>
            <ope:RAROC>{fn:data($OperacionCollection/ns1:Operacion/ns1:raroc)}</ope:RAROC>
            <ope:TIR>{fn:data($OperacionCollection/ns1:Operacion/ns1:tir)}</ope:TIR>
            <ope:TIREstatus>{fn:data($OperacionCollection/ns1:Operacion/ns1:tirEstatus)}</ope:TIREstatus>
            <ope:requiereRecursosExternos>{fn:data($OperacionCollection/ns1:Operacion/ns1:requiereRecursosExt)}</ope:requiereRecursosExternos>
            <ope:aplicaLaft>{ if(fn:data($OperacionCollection/ns1:Operacion/ns1:aplicaLaft)=1) then true() else false()}</ope:aplicaLaft>
            <ope:justificacionCancela>{fn:data($OperacionCollection/ns1:Operacion/ns1:justificacionCancela)}</ope:justificacionCancela>
            <ope:observacionCancela>{fn:data($OperacionCollection/ns1:Operacion/ns1:observacionCancela)}</ope:observacionCancela>
        </ns2:Operacion>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarSoloOperacionResponse>
};

local:func($OperacionCollection)
