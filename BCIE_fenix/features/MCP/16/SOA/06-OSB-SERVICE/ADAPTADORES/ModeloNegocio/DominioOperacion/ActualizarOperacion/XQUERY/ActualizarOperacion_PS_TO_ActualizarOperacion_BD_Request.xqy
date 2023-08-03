xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarOperacion";
(:: import schema at "../XSD/ActualizarOperacion_table.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare variable $ActualizarOperacion as element() (:: schema-element(ns1:ActualizarOperacionRequest) ::) external;

declare function local:func($ActualizarOperacion as element() (:: schema-element(ns1:ActualizarOperacionRequest) ::)) as element() (:: schema-element(ns2:OperacionCollection) ::) {
    <ns2:OperacionCollection>
        <ns2:Operacion>
            <ns2:idOperacion>{fn:data($ActualizarOperacion/ns1:Operacion/ope:idOperacion)}</ns2:idOperacion>
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:responsable)
                then <ns2:usuario>{fn:data($ActualizarOperacion/ns1:Operacion/ope:responsable)}</ns2:usuario>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:oficina)
                then <ns2:oficina>{fn:data($ActualizarOperacion/ns1:Operacion/ope:oficina)}</ns2:oficina>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:nombre)
                then <ns2:nombre>{fn:data($ActualizarOperacion/ns1:Operacion/ope:nombre)}</ns2:nombre>
                else ()
            }
            <ns2:idCliente>{fn:data($ActualizarOperacion/ns1:Operacion/ope:cliente/cli:idCliente)}</ns2:idCliente>
            <ns2:idProducto>{fn:data($ActualizarOperacion/ns1:Operacion/ope:producto/pro:idProducto)}</ns2:idProducto>
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:descripcion)
                then <ns2:descripcion>{fn:data($ActualizarOperacion/ns1:Operacion/ope:descripcion)}</ns2:descripcion>
                else ()
            }
   
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:actividadEconomica/cat:Id)
                then <ns2:actividadEconomica>{fn:data($ActualizarOperacion/ns1:Operacion/ope:actividadEconomica/cat:Id)}</ns2:actividadEconomica>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:objetivosEstrategicos)
                then <ns2:objetivosEstrategicos>{fn:data($ActualizarOperacion/ns1:Operacion/ope:objetivosEstrategicos)}</ns2:objetivosEstrategicos>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:areaFocalizacion/cat:Id)
                then <ns2:areaFocalizacion>{fn:data($ActualizarOperacion/ns1:Operacion/ope:areaFocalizacion/cat:Id)}</ns2:areaFocalizacion>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:ejeEstrategico/cat:Id)
                then <ns2:ejeEstrategico>{fn:data($ActualizarOperacion/ns1:Operacion/ope:ejeEstrategico/cat:Id)}</ns2:ejeEstrategico>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:iniciativaEstrategica/cat:Id)
                then <ns2:iniciativaEstrategica>{fn:data($ActualizarOperacion/ns1:Operacion/ope:iniciativaEstrategica/cat:Id)}</ns2:iniciativaEstrategica>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:sectorMercado/cat:Id)
                then <ns2:sectorMercado>{fn:data($ActualizarOperacion/ns1:Operacion/ope:sectorMercado/cat:Id)}</ns2:sectorMercado>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:fechaInicio)
                then <ns2:fechaInicio>{fn:data($ActualizarOperacion/ns1:Operacion/ope:fechaInicio)}</ns2:fechaInicio>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:programadoPOA) then
                  <ns2:programadoPoa>
                  {
                    if(xs:string($ActualizarOperacion/ns1:Operacion/ope:programadoPOA)='true') then 1 else 0
                  }
                  </ns2:programadoPoa>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:ejercicioPOA/cat:Id)
                then <ns2:ejercicioPoa>{fn:data($ActualizarOperacion/ns1:Operacion/ope:ejercicioPOA/cat:Id)}</ns2:ejercicioPoa>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:tipoGarantia/cat:Id)
                then <ns2:tipoGarantia>{fn:data($ActualizarOperacion/ns1:Operacion/ope:tipoGarantia/cat:Id)}</ns2:tipoGarantia>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:componenteConcesionalidad)
                then <ns2:componenteConcecionalidad>{if(xs:string($ActualizarOperacion/ns1:Operacion/ope:componenteConcesionalidad)='true') then 1 else 0}</ns2:componenteConcecionalidad>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:estructurador)
                then <ns2:estructurador>{fn:data($ActualizarOperacion/ns1:Operacion/ope:estructurador)}</ns2:estructurador>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:beneficiario)
                then <ns2:beneficiario>{fn:data($ActualizarOperacion/ns1:Operacion/ope:beneficiario)}</ns2:beneficiario>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:unidadEjecutora)
                then <ns2:unidadEjecutora>{fn:data($ActualizarOperacion/ns1:Operacion/ope:unidadEjecutora)}</ns2:unidadEjecutora>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:programa)
                then <ns2:programa>{fn:data($ActualizarOperacion/ns1:Operacion/ope:programa)}</ns2:programa>
                else ()
            }
           
          
          
           
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:etapa)
                then <ns2:etapa>{fn:data($ActualizarOperacion/ns1:Operacion/ope:etapa)}</ns2:etapa>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:status)
                then <ns2:banEstatus>{fn:data($ActualizarOperacion/ns1:Operacion/ope:status)}</ns2:banEstatus>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:fechaBaja)
                then <ns2:fechaBaja>{fn:data($ActualizarOperacion/ns1:Operacion/ope:fechaBaja)}</ns2:fechaBaja>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:cantidadPaises/cat:Id)
                then <ns2:idRangoPais>{fn:data($ActualizarOperacion/ns1:Operacion/ope:cantidadPaises/cat:Id)}</ns2:idRangoPais>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Id)
                then <ns2:idActividadEconomicaAsoc>{fn:data($ActualizarOperacion/ns1:Operacion/ope:actividadEconomicaAsociada/cat:Id)}</ns2:idActividadEconomicaAsoc>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:SRC/cat:Id)
                then <ns2:scr>{fn:data($ActualizarOperacion/ns1:Operacion/ope:SRC/cat:Id)}</ns2:scr>
                else ()
            }
            
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:RAROC)
                then <ns2:raroc>{fn:data($ActualizarOperacion/ns1:Operacion/ope:RAROC)}</ns2:raroc>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:TIR)
                then <ns2:tir>{fn:data($ActualizarOperacion/ns1:Operacion/ope:TIR)}</ns2:tir>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:requiereRecursosExternos)
                then <ns2:requiereRecusrosExt>{fn:data($ActualizarOperacion/ns1:Operacion/ope:requiereRecursosExternos)}</ns2:requiereRecusrosExt>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:aplicaLaft)
                then <ns2:aplicaLaft>
                {
                  if(xs:string($ActualizarOperacion/ns1:Operacion/ope:aplicaLaft)='true') then 1 else 0
                }</ns2:aplicaLaft>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:estado/cat:Id)
                then <ns2:estado>{fn:data($ActualizarOperacion/ns1:Operacion/ope:estado/cat:Id)}</ns2:estado>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:SRC/cat:Descripcion)
                then <ns2:scrEstatus>{fn:data($ActualizarOperacion/ns1:Operacion/ope:SRC/cat:Descripcion)}</ns2:scrEstatus>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:TIREstatus)
                then <ns2:tirEstatus>{fn:data($ActualizarOperacion/ns1:Operacion/ope:TIREstatus)}</ns2:tirEstatus>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:perspectiva/cat:Id)
                then <ns2:idPerspectiva>{fn:data($ActualizarOperacion/ns1:Operacion/ope:perspectiva/cat:Id)}</ns2:idPerspectiva>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:idTcaTipoMoneda)
                then <ns2:idTcaTipoMoneda>{fn:data($ActualizarOperacion/ns1:Operacion/ope:idTcaTipoMoneda)}</ns2:idTcaTipoMoneda>
                else ()
            }
            {
                if ($ActualizarOperacion/ns1:Operacion/ope:idCatPais)
                then <ns2:idCatPais>{(xs:int(fn:data($ActualizarOperacion/ns1:Operacion/ope:idCatPais)))}</ns2:idCatPais>
                else ()
            }
            
            
        </ns2:Operacion>
    </ns2:OperacionCollection>
};

local:func($ActualizarOperacion)