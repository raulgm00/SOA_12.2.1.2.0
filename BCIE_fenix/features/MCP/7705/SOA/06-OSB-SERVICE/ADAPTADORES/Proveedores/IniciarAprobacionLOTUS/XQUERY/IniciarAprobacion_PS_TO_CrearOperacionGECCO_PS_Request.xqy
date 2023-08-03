xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $idOperacion as element() external;
declare variable $responsable as element() external;
declare variable $pais as element() external;
declare variable $idCliente as element() external;
declare variable $idFacturador as element() external;
declare variable $nombre as element() external;
declare variable $descripcion as element() external;
declare variable $monto as element() external;
declare variable $codExtActividadEco as element() external;
declare variable $unidadEjecutora as element() external;


declare function local:func($idOperacion as element(),
                            $responsable as element(), 
                            $pais as element(), 
                            $idCliente as element(), 
                            $idFacturador as element(), 
                            $nombre as element(), 
                            $descripcion as element(), 
                            $monto as element(), 
                            $codExtActividadEco as element(), 
                            $unidadEjecutora as element()) 
                            as element() (:: schema-element(ns1:CrearOperacionRequest) ::) {
    <ns1:CrearOperacionRequest>
      <ns1:Operacion>
        
          <ope:idOperacion>{fn:data($idOperacion)}</ope:idOperacion>
          <ope:responsable>{fn:data($responsable)}</ope:responsable>
          <ope:oficina>{fn:substring-after(fn:data($pais), '')}</ope:oficina>
          <ope:nombre>{fn:data($nombre)}</ope:nombre>
          <ope:cliente>
              <cli:idCliente>{fn:data($idCliente)}</cli:idCliente>
              <cli:idFacturador>{fn:data($idFacturador)}</cli:idFacturador>
          </ope:cliente>
          <ope:descripcion>{fn:data($descripcion)}</ope:descripcion>
          <ope:actividadEconomica>
              <cat:codigoExterno>{fn:data($codExtActividadEco)}</cat:codigoExterno>
          </ope:actividadEconomica>
          <ope:unidadEjecutora>{fn:data($unidadEjecutora)}</ope:unidadEjecutora>
          <ope:etapa>8</ope:etapa>
          <ope:montoOperacion>
              <ope:montoOperacion>
                  <ope:monto>{fn:data($monto)}</ope:monto>                                    
              </ope:montoOperacion>
          </ope:montoOperacion>
      </ns1:Operacion>
    </ns1:CrearOperacionRequest>
};

local:func($idOperacion, $responsable, $pais, $idCliente, $idFacturador, $nombre, $descripcion, $monto, $codExtActividadEco, $unidadEjecutora)
