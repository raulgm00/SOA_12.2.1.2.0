xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CalcularPlazoCondiciones";
(:: import schema at "../XSD/CalcularPlazoCondiciones_sp.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CalcularPlazoCondicionesRequest as element() (:: schema-element(ns2:CalcularPlazoCondicionesRequest) ::) external;

declare function ns1:func($CalcularPlazoCondicionesRequest as element() (:: schema-element(ns2:CalcularPlazoCondicionesRequest) ::)) as element() (:: schema-element(ns3:InputParameters) ::) {
   
    <ns3:InputParameters>
    <ns3:P_CONDICIONES>
     {for $condicion in $CalcularPlazoCondicionesRequest/ns2:ListaCondiciones/con:Condicion
     
        return
        <ns3:P_CONDICIONES_ITEM>
            <ns3:ID>{fn:data($condicion/con:idCondicion)}</ns3:ID>
            {
                if ($condicion/con:tipoFechaInicio/cat:Id)
                then <ns3:ID_TCA_TIPO_FECHA_INICIO>{fn:data($condicion/con:tipoFechaInicio/cat:Id)}</ns3:ID_TCA_TIPO_FECHA_INICIO>
                else ()
            }
            {
                if ($condicion/con:fechaVigencia)
                then <ns3:FECHA_VIGENCIA>{fn:data($condicion/con:fechaVigencia)}</ns3:FECHA_VIGENCIA>
                else ()
            }
        </ns3:P_CONDICIONES_ITEM>
    }
    </ns3:P_CONDICIONES>
    {if(fn:count($CalcularPlazoCondicionesRequest/ns2:ListaCondiciones/con:Condicion)>0)then
        <ns3:P_ID_OPERACION>{fn:data($CalcularPlazoCondicionesRequest/ns2:ListaCondiciones/con:Condicion[1]/con:operacion)}</ns3:P_ID_OPERACION>
   else()
   }
    </ns3:InputParameters>
    
    
};

ns1:func($CalcularPlazoCondicionesRequest)
