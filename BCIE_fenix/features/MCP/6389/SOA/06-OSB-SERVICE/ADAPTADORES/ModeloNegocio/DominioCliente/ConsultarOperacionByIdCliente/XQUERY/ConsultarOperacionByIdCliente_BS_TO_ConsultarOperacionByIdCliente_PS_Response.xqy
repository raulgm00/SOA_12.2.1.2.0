xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionByIdCliente";
(:: import schema at "../XSD/ConsultarOperacionByIdCliente.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ClienteResponse as element() (:: schema-element(ns1:ConsultarOperacionByIdClienteOutputCollection) ::) external;
declare variable $idOperacion as element() external;

declare function local:func($ClienteResponse as element() (:: schema-element(ns1:ConsultarOperacionByIdClienteOutputCollection) ::), 
                            $idOperacion as element()) 
                            as element() (:: schema-element(ns2:ConsultarClienteResponse) ::) {
    <ns2:ConsultarClienteResponse>
        <ns2:idOperacion>{
                  if(fn:data($ClienteResponse))
                    then(for $idOper in $ClienteResponse
                            return (if ($idOper//ns1:ConsultarOperacionByIdClienteOutput/ns1:ID_OPERACION = $idOperacion)
                                      then('1')
                                        else('0')))                   
                      else(empty($ClienteResponse/ns1:ConsultarOperacionByIdClienteOutput/ns1:ID_OPERACION))
             
             }
        </ns2:idOperacion>
    </ns2:ConsultarClienteResponse>
};

local:func($ClienteResponse, $idOperacion)
