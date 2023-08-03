xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearOperacionBD";
(:: import schema at "../XSD/CrearOperacionBD_table.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare variable $CrearOperacionRequest as element() (:: schema-element(ns1:CrearOperacionRequest) ::) external;

declare function local:func($CrearOperacionRequest as element() (:: schema-element(ns1:CrearOperacionRequest) ::)) as element() (:: schema-element(ns2:OperacionCollection) ::) {
    <ns2:OperacionCollection>
        <ns2:Operacion>
            <ns2:usuario>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:responsable)}</ns2:usuario>
               <ns2:oficina>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:oficina)}</ns2:oficina>          
         <ns2:nombre>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:nombre)}</ns2:nombre>
              
          <ns2:idCliente>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:cliente/cli:idCliente)}</ns2:idCliente>
            <ns2:idProducto>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:producto/pro:idProducto)}</ns2:idProducto>
               
          <ns2:descripcion>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:descripcion)}</ns2:descripcion>
             
            {
                if ($CrearOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:Id)
                then <ns2:actividadEconomica>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:actividadEconomica/cat:Id)}</ns2:actividadEconomica>
                else ()
            }
                <ns2:objetivosEstrategicos>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:objetivosEstrategicos)}</ns2:objetivosEstrategicos>
                <ns2:areaFocalizacion>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:areaFocalizacion/cat:Id)}</ns2:areaFocalizacion>
                <ns2:ejeEstrategico>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:ejeEstrategico/cat:Id)}</ns2:ejeEstrategico>
                <ns2:iniciativaEstrategica>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:iniciativaEstrategica/cat:Id)}</ns2:iniciativaEstrategica>
                <ns2:sectorMercado>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:sectorMercado/cat:Id)}</ns2:sectorMercado>
                <ns2:fechaInicio>{current-date()}</ns2:fechaInicio>
                <ns2:programadoPoa>{if(xs:string($CrearOperacionRequest/ns1:Operacion/ope:programadoPOA)='true') then 1 else 0}</ns2:programadoPoa>
                <ns2:ejercicioPoa>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:ejercicioPOA/cat:Id)}</ns2:ejercicioPoa>
                <ns2:tipoGarantia>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:tipoGarantia/cat:Id)}</ns2:tipoGarantia>
                <ns2:componenteConcecionalidad>{if(fn:data($CrearOperacionRequest/ns1:Operacion/ope:componenteConcesionalidad)=true()) then 1 else 0}</ns2:componenteConcecionalidad>
                <ns2:estructurador>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:estructurador)}</ns2:estructurador>
                <ns2:beneficiario>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:beneficiario)}</ns2:beneficiario>
                <ns2:unidadEjecutora>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:unidadEjecutora)}</ns2:unidadEjecutora>
                <ns2:programa>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:programa)}</ns2:programa>
                                             <ns2:etapa>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:etapa)}</ns2:etapa>
                <ns2:banEstatus>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:status)}</ns2:banEstatus>
                <ns2:fechaBaja>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:fechaBaja)}</ns2:fechaBaja>
                <ns2:idRangoPais>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:cantidadPaises/cat:Id)}</ns2:idRangoPais>
            {
                if ($CrearOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Id)
                then <ns2:idActividadEconomicaAsoc>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Id)}</ns2:idActividadEconomicaAsoc>
                else ()
            }
             <ns2:estado>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:estado/cat:Id)}</ns2:estado>
            <ns2:scr>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:SRC)}</ns2:scr>
            <ns2:raroc>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:RAROC)}</ns2:raroc>
            <ns2:tir>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:TIR)}</ns2:tir>
            <ns2:requiereRecursosExt>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:requiereRecursosExternos)}</ns2:requiereRecursosExt>
            <ns2:aplicaLaft>{
              if(fn:data($CrearOperacionRequest/ns1:Operacion/ope:aplicaLaft)=true()) then
                1
              else
                0
              }</ns2:aplicaLaft>
            {
                if ($CrearOperacionRequest/ns1:Operacion/ope:perspectiva/cat:Id)
                then <ns2:idPerspectiva>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:perspectiva/cat:Id)}</ns2:idPerspectiva>
                else ()
            }
            {
                if ($CrearOperacionRequest/ns1:Operacion/ope:esMultisectorial)
                then <ns2:esMultisectorial>{
              if(fn:data($CrearOperacionRequest/ns1:Operacion/ope:esMultisectorial)=true()) then
                1
              else
                0
              }</ns2:esMultisectorial>
                else ()
            }
            {
                if ($CrearOperacionRequest/ns1:Operacion/ope:idTcaTipoMoneda)
                then <ns2:idTcaTipoMoneda>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:idTcaTipoMoneda)}</ns2:idTcaTipoMoneda>
                else ()
            }
            {
                if ($CrearOperacionRequest/ns1:Operacion/ope:idSectorInstitucional)
                then <ns2:idSectorInstitucional>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:idSectorInstitucional)}</ns2:idSectorInstitucional>
                else ()
            }
            {
                if ($CrearOperacionRequest/ns1:Operacion/ope:idEncargado)
                then <ns2:idEncargado>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:idEncargado)}</ns2:idEncargado>
                else ()
            }
            {
                if ($CrearOperacionRequest/ns1:Operacion/ope:ubicacionProyecto)
                then <ns2:ubicacion>{fn:data($CrearOperacionRequest/ns1:Operacion/ope:ubicacionProyecto)}</ns2:ubicacion>
                else ()
            }
            {
                if ($CrearOperacionRequest/ns1:Operacion/ope:idCatPais)
                then <ns2:idCatPais>{(xs:int(fn:data($CrearOperacionRequest/ns1:Operacion/ope:idCatPais)))}</ns2:idCatPais>
                else ()
            }
       
        </ns2:Operacion>
      </ns2:OperacionCollection>
};

local:func($CrearOperacionRequest)
