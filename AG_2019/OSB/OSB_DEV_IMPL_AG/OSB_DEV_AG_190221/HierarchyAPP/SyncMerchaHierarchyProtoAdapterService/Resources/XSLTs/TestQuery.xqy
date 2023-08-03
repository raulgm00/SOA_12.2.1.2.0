xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.agarcia.mx/EnterpriseObjects/MerchandiseHierarchy";
(:: import schema at "../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/MerchandiseHierarchy/MerchandiseHierarchyEBM.xsd", 
                     "../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/MerchandiseHierarchy/XSDtoJson.xsd" ::)

declare namespace com = "http://www.agarcia.mx/EnterpriseObjects/Common";

declare variable $HierarchyParam as element() (:: schema-element(ns1:MerchandiseHierarchyEBM) ::) external;


declare function local:func($HierarchyParam as element() (:: schema-element(ns1:MerchandiseHierarchyEBM) ::)) as element() (:: schema-element(ns1:Root-Element) ::) {
    
    <ns1:Root-Element>
        <ns1:jerarquias_array>
                <ns1:marca_id>{fn:data($HierarchyParam/ns1:DataArea/ns1:MerchandiseHierarchy/ns1:Brand/com:ID)}</ns1:marca_id>
                <ns1:marca>{fn:data($HierarchyParam/ns1:DataArea/ns1:MerchandiseHierarchy/ns1:Brand/com:ApplicationObjectReference/com:ApplicationName)}</ns1:marca>
                <ns1:division_id>{fn:data($HierarchyParam/ns1:DataArea/ns1:MerchandiseHierarchy/ns1:Division/com:ID)}</ns1:division_id>
                <ns1:division>{fn:data($HierarchyParam/ns1:DataArea/ns1:MerchandiseHierarchy/ns1:Division/com:ApplicationObjectReference/com:ApplicationName)}</ns1:division>
                <ns1:departamento_id>{fn:data($HierarchyParam/ns1:DataArea/ns1:MerchandiseHierarchy/ns1:Department/com:ID)}</ns1:departamento_id>
                <ns1:departamento>{fn:data($HierarchyParam/ns1:DataArea/ns1:MerchandiseHierarchy/ns1:Department/com:ApplicationObjectReference/com:ApplicationName)}</ns1:departamento>
                <ns1:clase_id>{fn:data($HierarchyParam/ns1:DataArea/ns1:MerchandiseHierarchy/ns1:Class/com:ID)}</ns1:clase_id>
                <ns1:clase>{fn:data($HierarchyParam/ns1:DataArea/ns1:MerchandiseHierarchy/ns1:Class/com:ApplicationObjectReference/com:ApplicationName)}</ns1:clase>
                <ns1:tipo_id>{fn:data($HierarchyParam/ns1:DataArea/ns1:MerchandiseHierarchy/ns1:Type/com:ID)}</ns1:tipo_id>
                <ns1:tipo>{fn:data($HierarchyParam/ns1:DataArea/ns1:MerchandiseHierarchy/ns1:Type/com:ApplicationObjectReference/com:ApplicationName)}</ns1:tipo>
            </ns1:jerarquias_array>
    </ns1:Root-Element>
};

local:func($HierarchyParam)