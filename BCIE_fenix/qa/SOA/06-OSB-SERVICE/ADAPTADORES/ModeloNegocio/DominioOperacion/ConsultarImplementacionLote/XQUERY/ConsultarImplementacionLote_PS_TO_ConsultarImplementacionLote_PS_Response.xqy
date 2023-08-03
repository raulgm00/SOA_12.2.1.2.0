xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarImplementacionLoteSAD";
(:: import schema at "../XSD/ConsultarImplementacionLoteSAD.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare variable $responseConsultarLote as element() (:: schema-element(ns1:ConsultarImplementacionLoteSADOutputCollection) ::) external;

declare function local:func($responseConsultarLote as element() (:: schema-element(ns1:ConsultarImplementacionLoteSADOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarImplementacionLoteResponse) ::) {
   <ns2:ConsultarImplementacionLoteResponse>
    {
      for $Implementacion in distinct-values($responseConsultarLote/ns1:ConsultarImplementacionLoteSADOutput/ns1:ID_IMPLEMENTACION)
      let $implementacionDistinc := $responseConsultarLote/ns1:ConsultarImplementacionLoteSADOutput[ns1:ID_IMPLEMENTACION = $Implementacion]
      return
      <ns2:ImplementacionLote>
            <imp:idLote> {fn:data($implementacionDistinc[1]/ns1:ID_IMPLEMENTACION)}</imp:idLote>
            <imp:nombre> {fn:data($implementacionDistinc[1]/ns1:NOMBRE_ADQUISICION)} </imp:nombre>
            <imp:tipoProceso>{fn:data($implementacionDistinc[1]/ns1:TIPO_PROCESO) }</imp:tipoProceso>
            {
              for $loteImplementacion in $responseConsultarLote/ns1:ConsultarImplementacionLoteSADOutput
              where $loteImplementacion/ns1:ID_IMPLEMENTACION =$implementacionDistinc[1]/ns1:ID_IMPLEMENTACION
              return
                 <imp:loteImplementacion>
                  <imp:idLote>{fn:data($loteImplementacion/ns1:ID_LOTE) }</imp:idLote>
                  <imp:nombre>{fn:data($loteImplementacion/ns1:NOMBRE_LOTE)} </imp:nombre>
                  <imp:monto>
                    <com:importe>{fn:data($loteImplementacion/ns1:MONTO_PRESUPUESTADO)}</com:importe>
                    <com:moneda>{fn:data($loteImplementacion/ns1:ID_TCA_TIPO_MONEDA) }</com:moneda>
                  </imp:monto>
                     <imp:enProceso>{if(fn:string( fn:data($loteImplementacion/ns1:EN_PROCESO)) = '1') then fn:true() else(fn:false())}
                      </imp:enProceso>
               </imp:loteImplementacion>
            }
        </ns2:ImplementacionLote>
    }
    </ns2:ConsultarImplementacionLoteResponse>
};

local:func($responseConsultarLote)