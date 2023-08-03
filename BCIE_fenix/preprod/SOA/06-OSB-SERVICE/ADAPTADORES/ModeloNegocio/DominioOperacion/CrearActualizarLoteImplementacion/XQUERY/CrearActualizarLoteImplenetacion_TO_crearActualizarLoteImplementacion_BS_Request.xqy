xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarLoteImplementacion";
(:: import schema at "../XSD/CrearActualizarLoteImplementacion_table.xsd" ::)

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearActualizarLoteImplementacionRequest as element() (:: schema-element(ns1:CrearActualizarLoteImplementacionRequest) ::) external;

declare function local:func($CrearActualizarLoteImplementacionRequest as element() (:: schema-element(ns1:CrearActualizarLoteImplementacionRequest) ::)) as element() (:: schema-element(ns2:LoteImplementacionCollection) ::) {
    <ns2:LoteImplementacionCollection>
        <ns2:LoteImplementacion>
           <ns2:id>{fn:data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:loteImpementacion/imp:idLote)}</ns2:id>
           
            {
              if(fn:string(data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:idLote)) != '')then
                <ns2:idImplementacion>{fn:data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:idLote)}</ns2:idImplementacion>
              else()
            }
            {
            if(fn:string(fn:data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:loteImpementacion/imp:nombre)) != '')then
                  <ns2:nombreLote>{fn:data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:loteImpementacion/imp:nombre)}</ns2:nombreLote>
              else()
            }
            {
            if(fn:string(data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:loteImpementacion/imp:monto/com:importe)) != '')then
                <ns2:montoPresupuestado>{fn:data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:loteImpementacion/imp:monto/com:importe)}</ns2:montoPresupuestado>
              else()
            }
            {
            if(fn:string(data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:loteImpementacion/imp:monto/com:moneda/cat:Id)) != '')then
                 <ns2:idTcaTipoMoneda>{fn:data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:loteImpementacion/imp:monto/com:moneda/cat:Id)}</ns2:idTcaTipoMoneda>
              else()
            }
            {
              if(fn:string(data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:loteImpementacion/imp:idLote)) = '')then
                  <ns2:banEstatus>1</ns2:banEstatus>
              else()
            }
            {
            if(fn:string(data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:fechaOrdenInicio)) != '')then
                  <ns2:fechaOrdenInicio>{fn:data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:fechaOrdenInicio)}</ns2:fechaOrdenInicio>
              else()
            }
           {
           if(fn:string(fn:data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:enProceso)) != '')then
                 <ns2:enProceso>{if(fn:string( fn:data($CrearActualizarLoteImplementacionRequest/ns1:LoteImplementacion/imp:enProceso))= 'true')then 1 else(0)}</ns2:enProceso>
              else()
            }
      </ns2:LoteImplementacion>
    </ns2:LoteImplementacionCollection>
};

local:func($CrearActualizarLoteImplementacionRequest)