<?xml version="1.0" encoding="UTF-8"?>
<ns0:businessIndicators xmlns:ns0="http://xmlns.oracle.com/bpm/analytics/businessIndicator" fileVersion="20130625">
   <ns0:businessIndicator name="totalReprocesos">
      <ns0:id>5bfe9135-1f30-4b4a-97ca-d47a705aa2b0</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter2')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalDesperdicioPA10">
      <ns0:id>2e8b1254-e8d6-4264-9364-918e54a21cc5</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter3')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalDesperdicio">
      <ns0:id>e4a54f44-36bc-4942-b32c-bfe4afab508c</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter1')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalReprocesosPA10">
      <ns0:id>70c1424c-cec0-47a1-9d8f-2f2264ceeef6</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter4')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idOperacion">
      <ns0:id>a8daebc5-a2c7-47ee-8e9c-206a6c74b2c1</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('idOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="accionTarea">
      <ns0:id>2088332b-242c-4f01-91a5-ca0d9c53882d</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('accionTarea')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idOperacionPA10">
      <ns0:id>ae732645-7f23-4b50-9995-278f352b6336</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('idOperacionPA10')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idEstadoOperacionPA10">
      <ns0:id>78ab2567-02a7-4021-bbb6-3e64e738d90d</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('idEstadoOperacionPA10')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="nombreOperacion">
      <ns0:id>9737f182-98dc-4813-b4d6-b0c7e6a50291</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('nombreOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="nombreOperacionPA10">
      <ns0:id>b2083626-dc4d-481b-8e50-ef7fef4ff408</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('nombreOperacionPA10')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="accionTareaPA10">
      <ns0:id>c68d14c4-ab54-454c-9bc6-55e5d99612b2</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('accionTareaPA10')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idEstadoOperacion">
      <ns0:id>0c53f9e4-bb4f-465c-aae2-a2cc850e2ce0</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('idEstadoOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
</ns0:businessIndicators>
