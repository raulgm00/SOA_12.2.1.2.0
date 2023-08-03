CREATE OR REPLACE PACKAGE BODY PKG_CONSULTA_ONBASE
IS
   FUNCTION F_ETAPA_ONBASE (p_id NUMBER)
      RETURN VARCHAR2
   IS
      v_resultado   VARCHAR2 (1000);
   BEGIN
      SELECT TCB.DESCRIPCION AS "ETAPA"
        INTO v_resultado
        FROM TCO_CONFIGURAR_TIPO_ONBASE TCTO
             INNER JOIN TCA_DOCUMENTO TD
                ON TCTO.ID_TCA_DOCUMENTO = TD.ID
             INNER JOIN TCO_DOCUMENTO_TAREA TDT
                ON TD.ID = TDT.ID
             INNER JOIN TCA_TAREA_BPM TTB
                ON TTB.ID = TDT.ID_TAREA_BPM
             INNER JOIN TCA_PROCESO_BPM TCB
                ON TCB.ID = TTB.ID
       WHERE TCTO.ID = p_id;


      RETURN v_resultado;
   END F_ETAPA_ONBASE;

   FUNCTION F_Consulta_on_Base (P_ID_Operacion    IN CHAR,
                                P_IdCliente       IN NUMBER,
                                P_TipoDocumento   IN NUMBER,
                                P_IdTarea         IN NUMBER,
                                P_InstanciaProceso   IN     CHAR)
      RETURN T_ONBASE
   IS
      L_T_ONBASE    T_ONBASE := T_ONBASE ();
      id_flexcube   CHAR (250);
   BEGIN
      --



      IF P_ID_Operacion IS NOT NULL
      THEN
         SELECT CAST (
                   COLLECT (T_CON_ONBASE1 (ETAPA,
                                           NOMBRE_TIPO_DOCUMENTO,
                                           ID_DOCUMENTO,
                                           ID_TIPO_DOCUMENTO,
                                           CODIGO_DOCUMENTO,
                                           ID_ONBASE,
                                           ID_OPERACION,
                                           ID_PRODUCTO,
                                           ID_PAIS,
                                           FILENAME,
                                           MIME_TYPE,
                                           TAMANIO,
                                           ID_ADJUNTO,
                                           ES_JUSTIFICACION,
                                           COMENTARIO,
                                           FECHA_DOCUMENTO,
                                           FECHA_REGISTRO,
                                           TAREA,
                                           ID_TAREA,
                                           PUEDE_MODIFICAR,
                                           PUEDE_BORRAR,
                                           ItemTypeGroupName,
                                           ItemTypeGroupNum,
                                           ItemTypeName,
                                           ItemNum,
                                           ItemTypeNum,
                                           ItemName,
                                           Status,
                                           ItemDate,
                                           DateStored,
                                           epdocid,
                                           SecurityKW,
                                           Pais,
                                           Linea_Credito,
                                           Nombre,
                                           Numero_Documento,
                                           Periodo,
                                           Resumen,
                                           Tipo,
                                           Codigo_Intervencion,
                                           NOMBRE_USUARIO_CREA,
                                           NOMBRE_USUARIO_MODIFICA,
                                           NUM_SERIE_DOCUMENTO)) AS T_ONBASE)
           INTO L_T_ONBASE
           FROM ( (SELECT DISTINCT
                          CASE
                             WHEN ETAPA IS NULL THEN 'En Transito'
                             ELSE ETAPA
                          END
                             AS ETAPA,
                          NVL (ID_ETAPA, 0) ID_ETAPA,
                          CASE
                             WHEN NOMBRE_TIPO_DOCUMENTO IS NULL
                             THEN
                                'General'
                             ELSE
                                NOMBRE_TIPO_DOCUMENTO
                          END
                             AS NOMBRE_TIPO_DOCUMENTO,
                          ID_DOCUMENTO,
                          ID_TIPO_DOCUMENTO,
                          CODIGO_DOCUMENTO,
                          ID_ONBASE,                          --AS ID_EXTERNO,
                          ID_OPERACION,
                          ID_PRODUCTO,
                          ID_PAIS,
                          FILENAME,
                          MIME_TYPE,
                          TAMANIO,
                          ID_ADJUNTO,
                          ES_JUSTIFICACION,
                          COMENTARIO,
                          FECHA_DOCUMENTO,
                          FECHA_REGISTRO,
                          TAREA,
                          ID_TAREA,
                          CASE
                             WHEN PUEDE_MODIFICAR = 1 THEN 'true'
                             ELSE 'false'
                          END
                             AS PUEDE_MODIFICAR,
                          CASE
                             WHEN PUEDE_BORRAR = 1 THEN 'true'
                             ELSE 'false'
                          END
                             AS PUEDE_BORRAR,
                          PUEDE_CONSULTAR,
                          NULL ItemTypeGroupName,
                          NULL ItemTypeGroupNum,
                          NULL ItemTypeName,
                          NULL ItemNum,
                          NULL ItemTypeNum,
                          NULL ItemName,
                          NULL Status,
                          NULL ItemDate,
                          NULL DateStored,
                          NULL epdocid,
                          NULL SecurityKW,
                          NULL Pais,
                          NULL Linea_Credito,
                          'Justificación' Nombre, --CASE WHEN  Nombre IS NULL THEN  RTRIM (FILENAME,  '.pdf') ELSE Nombre END Nombre ,
                          NULL Numero_Documento,
                          NULL Periodo,
                          NULL Resumen,
                          NULL Tipo,
                          NULL Codigo_Intervencion,
                          NOMBRE_USUARIO_CREA,
                          NOMBRE_USUARIO_MODIFICA,
                          NUM_SERIE_DOCUMENTO
                     FROM (SELECT TCB.ID AS ID_ETAPA,
                                  TCB.DESCRIPCION AS ETAPA,
                                  TD.DESCRIPCION AS NOMBRE_TIPO_DOCUMENTO,
                                  DOC.ID_DOCUMENTO,
                                  DOC.ID_TIPO_DOCUMENTO,
                                  DOC.CODIGO_DOCUMENTO,
                                  ADJ.ID_ONBASE,              --AS ID_EXTERNO,
                                  OP.ID_OPERACION,
                                  CP.ID AS ID_PRODUCTO,
                                  TCTO.ID_CAT_PAISES AS ID_PAIS,
                                  ADJ.FILENAME,
                                  ADJ.MIME_TYPE,
                                  DBMS_LOB.getlength (CONTENT) "TAMANIO",
                                  ADJ.ID_ADJUNTO,
                                  DOC.ES_JUSTIFICACION,
                                  DOC.COMENTARIO,
                                  DOC.FECHA_DOCUMENTO,
                                  DOC.FECHA_REGISTRO,
                                  TTB.DESCRIPCION AS TAREA,
                                  TTB.ID AS ID_TAREA,
                                  CL.ID_CLIENTE,
                                  TDT.PUEDE_MODIFICAR,
                                  TDT.PUEDE_BORRAR,
                                  TDT.PUEDE_CONSULTAR,
                                  NVL (DOC.NOMBRE_USUARIO_CREA,
                                       DOC.LOGIN_USUARIO_CREA)
                                     NOMBRE_USUARIO_CREA,
                                  NVL (DOC.NOMBRE_USUARIO_MODIFICA,
                                       DOC.LOGIN_USUARIO_MODIFICA)
                                     NOMBRE_USUARIO_MODIFICA,
                                  NUM_SERIE_DOCUMENTO
                             FROM DOCUMENTO DOC                          --460
                                  LEFT OUTER JOIN ADJUNTO ADJ
                                     ON ADJ.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                                  INNER JOIN TCA_DOCUMENTO TD
                                     ON DOC.ID_TIPO_DOCUMENTO = TD.ID
                                  INNER JOIN TCO_CONFIGURAR_TIPO_ONBASE TCTO
                                     ON TD.id = TCTO.ID_TCA_DOCUMENTO
                                  INNER JOIN DOCUMENTOS DCTS
                                     ON DCTS.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                                  INNER JOIN OPERACION OP
                                     ON OP.ID_OPERACION = DCTS.ID_OPERACION
                                  INNER JOIN CLIENTES CL
                                     ON OP.ID_CLIENTE = CL.ID_CLIENTE
                                  INNER JOIN CAT_PRODUCTO CP
                                     ON CP.ID = OP.ID_PRODUCTO
                                  INNER JOIN CAT_PAISES CPS
                                     ON CPS.ID = TCTO.ID_CAT_PAISES
                                        AND CPS.ID = CL.PAIS
                                  INNER JOIN TCA_TIPO_OPERACION TTO
                                     ON CP.ID_TIPO_OPERACION = TTO.ID
                                        AND TCTO.ID_TCA_TIPO_OPERACION =
                                              TTO.ID
                                  INNER JOIN TCA_TAREA_BPM TTB
                                     ON TTB.ID = DOC.ID_TAREA_BPM
                                  INNER JOIN TCA_PROCESO_BPM TCB
                                     ON TCB.ID = TTB.ID_PROCESO_BPM
                                  INNER JOIN TCO_DOCUMENTO_TAREA TDT
                                     ON TD.ID = TDT.ID_TCA_DOCUMENTO
                                        AND TDT.ID_TAREA_BPM =
                                              NVL (P_IdTarea,
                                                   TDT.ID_TAREA_BPM)  --TTB.ID
                            WHERE     DOC.BAN_ESTATUS <> 0 -- Se agrega el estado  activo o incativo, mostrar solo activos
                                  AND OP.ID_OPERACION = P_ID_Operacion
                                  AND TDT.PUEDE_CONSULTAR = 1
                                  AND ES_JUSTIFICACION = 1
                                  AND NVL (INSTANCIA_PROCESO, 0) =
                                  COALESCE (P_InstanciaProceso,
                                          NVL (INSTANCIA_PROCESO, 0))) DBFENIX
                    WHERE NVL (ID_CLIENTE, 0) =
                             COALESCE (P_IdCliente, NVL (ID_CLIENTE, 0))
                          AND NVL (ID_TIPO_DOCUMENTO, 0) =
                                COALESCE (P_TipoDocumento,
                                          NVL (ID_TIPO_DOCUMENTO, 0)))
                 UNION
                 ---DOCUMENTOS QUE ESTAN EN FENIX PERO NO EN ONBSE

                 (SELECT DISTINCT
                         CASE
                            WHEN ETAPA IS NULL THEN 'En Transito'
                            ELSE ETAPA
                         END
                            AS ETAPA,
                         NVL (ID_ETAPA, 0) ID_ETAPA,
                         CASE
                            WHEN NOMBRE_TIPO_DOCUMENTO IS NULL THEN 'General'
                            ELSE NOMBRE_TIPO_DOCUMENTO
                         END
                            AS NOMBRE_TIPO_DOCUMENTO,
                         ID_DOCUMENTO,
                         ID_TIPO_DOCUMENTO,
                         CODIGO_DOCUMENTO,
                         ID_ONBASE,                           --AS ID_EXTERNO,
                         ID_OPERACION,
                         ID_PRODUCTO,
                         ID_PAIS,
                         FILENAME,
                         MIME_TYPE,
                         TAMANIO,
                         ID_ADJUNTO,
                         ES_JUSTIFICACION,
                         COMENTARIO,
                         FECHA_DOCUMENTO,
                         FECHA_REGISTRO,
                         TAREA,
                         ID_TAREA,
                         CASE
                            WHEN PUEDE_MODIFICAR = 1 THEN 'true'
                            ELSE 'false'
                         END
                            AS PUEDE_MODIFICAR,
                         CASE
                            WHEN PUEDE_BORRAR = 1 THEN 'true'
                            ELSE 'false'
                         END
                            AS PUEDE_BORRAR,
                         PUEDE_CONSULTAR,
                         NULL ItemTypeGroupName,
                         NULL ItemTypeGroupNum,
                         NULL ItemTypeName,
                         NULL ItemNum,
                         NULL ItemTypeNum,
                         NULL ItemName,
                         NULL Status,
                         NULL ItemDate,
                         NULL DateStored,
                         NULL epdocid,
                         NULL SecurityKW,
                         NULL Pais,
                         NULL Linea_Credito,
                         NVL (
                            SUBSTR (FILENAME, 1, (INSTR (FILENAME, '.') - 1)),
                            FILENAME)
                            Nombre,
                         NULL Numero_Documento,
                         NULL Periodo,
                         NULL Resumen,
                         NULL Tipo,
                         NULL Codigo_Intervencion,
                         NOMBRE_USUARIO_CREA,
                         NOMBRE_USUARIO_MODIFICA,
                         NUM_SERIE_DOCUMENTO
                    FROM (SELECT TCB.ID AS ID_ETAPA,
                                 TCB.DESCRIPCION AS ETAPA,
                                 TD.DESCRIPCION AS NOMBRE_TIPO_DOCUMENTO,
                                 DOC.ID_DOCUMENTO,
                                 DOC.ID_TIPO_DOCUMENTO,
                                 DOC.CODIGO_DOCUMENTO,
                                 ADJ.ID_ONBASE,               --AS ID_EXTERNO,
                                 OP.ID_OPERACION,
                                 CP.ID AS ID_PRODUCTO,
                                 TCTO.ID_CAT_PAISES AS ID_PAIS,
                                 ADJ.FILENAME,
                                 ADJ.MIME_TYPE,
                                 DBMS_LOB.getlength (CONTENT) "TAMANIO",
                                 ADJ.ID_ADJUNTO,
                                 DOC.ES_JUSTIFICACION,
                                 DOC.COMENTARIO,
                                 DOC.FECHA_DOCUMENTO,
                                 DOC.FECHA_REGISTRO,
                                 TTB.DESCRIPCION AS TAREA,
                                 TTB.ID AS ID_TAREA,
                                 CL.ID_CLIENTE,
                                 TDT.PUEDE_MODIFICAR,
                                 TDT.PUEDE_BORRAR,
                                 TDT.PUEDE_CONSULTAR,
                                 NVL (DOC.NOMBRE_USUARIO_CREA,
                                      DOC.LOGIN_USUARIO_CREA)
                                    NOMBRE_USUARIO_CREA,
                                 NVL (DOC.NOMBRE_USUARIO_MODIFICA,
                                      DOC.LOGIN_USUARIO_MODIFICA)
                                    NOMBRE_USUARIO_MODIFICA,
                                 NUM_SERIE_DOCUMENTO
                         FROM DOCUMENTO DOC                           --460
                                 LEFT OUTER JOIN ADJUNTO ADJ
                                    ON ADJ.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                                 INNER JOIN TCA_DOCUMENTO TD
                                    ON DOC.ID_TIPO_DOCUMENTO = TD.ID
                                 INNER JOIN DOCUMENTOS DCTS
                                    ON DCTS.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                                 INNER JOIN OPERACION OP
                                    ON OP.ID_OPERACION = DCTS.ID_OPERACION
                                 INNER JOIN CLIENTES CL
                                    ON OP.ID_CLIENTE = CL.ID_CLIENTE
                                 INNER JOIN CAT_PRODUCTO CP
                                    ON CP.ID = OP.ID_PRODUCTO
                                  INNER JOIN CAT_PAISES CPS
                                    ON  CPS.ID = CL.PAIS
                             INNER     JOIN TCA_TIPO_OPERACION TTO
                                    ON CP.ID_TIPO_OPERACION = TTO.ID
                                 LEFT JOIN TCA_TAREA_BPM TTB
                                    ON TTB.ID = DOC.ID_TAREA_BPM
                                 LEFT JOIN TCA_PROCESO_BPM TCB
                                    ON TCB.ID = TTB.ID_PROCESO_BPM
                                  LEFT      JOIN TCO_CONFIGURAR_TIPO_ONBASE TCTO
                                    ON TD.id = TCTO.ID_TCA_DOCUMENTO and CPS.ID = TCTO.ID_CAT_PAISES and TCTO.ID_TCA_TIPO_OPERACION =  TTO.ID
                                 INNER JOIN TCO_DOCUMENTO_TAREA TDT
                                    ON TD.ID = TDT.ID_TCA_DOCUMENTO
                                       AND TDT.ID_TAREA_BPM =P_IdTarea --TTB.ID
                           WHERE     DOC.BAN_ESTATUS <> 0 -- Se agrega el estado  activo o incativo, mostrar solo activos
                                 AND OP.ID_OPERACION = P_ID_Operacion
                                 AND TDT.PUEDE_CONSULTAR = 1
                                 AND ES_JUSTIFICACION = 0
                                 AND TTB.ID IS NULL                 --ID TAREA
                                 AND ADJ.ID_ONBASE IS NULL
                                 AND NVL (INSTANCIA_PROCESO, 0) =
                                COALESCE (P_InstanciaProceso,
                                          NVL (INSTANCIA_PROCESO, 0))) DBFENIX
                   WHERE NVL (ID_CLIENTE, 0) =
                            COALESCE (P_IdCliente, NVL (ID_CLIENTE, 0))
                         AND NVL (ID_TIPO_DOCUMENTO, 0) =
                               COALESCE (P_TipoDocumento,
                                         NVL (ID_TIPO_DOCUMENTO, 0)))      ---
                 UNION
                 SELECT DISTINCT
                        CASE
                           WHEN ETAPA IS NULL THEN 'En Transito'
                           ELSE ETAPA
                        END
                           AS ETAPA,
                        NVL (ID_ETAPA, 0) ID_ETAPA,
                        CASE
                           WHEN NOMBRE_TIPO_DOCUMENTO IS NULL THEN 'General'
                           ELSE NOMBRE_TIPO_DOCUMENTO
                        END
                           AS NOMBRE_TIPO_DOCUMENTO,
                        ID_DOCUMENTO,
                        ID_TIPO_DOCUMENTO,
                        CODIGO_DOCUMENTO,
                        ID_ONBASE,                            --AS ID_EXTERNO,
                        ID_OPERACION,
                        ID_PRODUCTO,
                        ID_PAIS,
                        FILENAME,
                        MIME_TYPE,
                        TAMANIO,
                        ID_ADJUNTO,
                        ES_JUSTIFICACION,
                        COMENTARIO,
                        FECHA_DOCUMENTO,
                        FECHA_REGISTRO,
                        TAREA,
                        ID_TAREA,
                        CASE
                           WHEN PUEDE_MODIFICAR = 1 OR ETAPA IS NULL
                           THEN
                              'true'
                           ELSE
                              'false'
                        END
                           AS PUEDE_MODIFICAR,
                        CASE
                           WHEN PUEDE_BORRAR = 1 THEN 'true'
                           ELSE 'false'
                        END
                           AS PUEDE_BORRAR,
                        PUEDE_CONSULTAR,
                        ItemTypeGroupName,
                        ItemTypeGroupNum,
                        ItemTypeName,
                        ItemNum,
                        ItemTypeNum,
                        ItemName,
                        Status,
                        ItemDate ItemDate,
                        DateStored,
                        epdocid,
                        SecurityKW,
                        Pais,
                        Linea_Credito,
                        CASE
                           WHEN etapa IS NOT NULL
                           THEN
                              NVL (
                                 SUBSTR (FILENAME,
                                         1,
                                         (INSTR (FILENAME, '.') - 1)),
                                 FILENAME)
                           ELSE
                              RTRIM (ItemName)
                        END
                           Nombre,
                        Numero_Documento,
                        Periodo,
                        Resumen,
                        Tipo,
                        Codigo_Intervencion,
                        NOMBRE_USUARIO_CREA,
                        NOMBRE_USUARIO_MODIFICA,
                        NUM_SERIE_DOCUMENTO
                   FROM (SELECT itG.ItemTypeGroupName,         -- nombre grupo
                                dcT.ItemTypeGroupNum,
                                dcT.ItemTypeName,               -- nombre tipo
                                itD.ItemNum,               --"ID_DOC"-- doc id
                                itD.ItemTypeNum, --"Tipo_documento_Fénix"-- codigo tipo documento -- llaves
                                itD.ItemName,              -- Nombre documento
                                itD.Status,
                                itD.ItemDate ItemDate,               --"Fecha"
                                itD.DateStored,
                                PKG_CONSULTA_ONBASE.f_estado_onbase (
                                   itD.ItemNum,
                                   'epdocid')
                                   AS epdocid,
                                PKG_CONSULTA_ONBASE.f_estado_onbase (
                                   itD.ItemNum,
                                   'SecurityKW')
                                   AS SecurityKW,
                                PKG_CONSULTA_ONBASE.f_estado_onbase (
                                   itD.ItemNum,
                                   'Pais')
                                   AS Pais,
                                PKG_CONSULTA_ONBASE.f_estado_onbase (
                                   itD.ItemNum,
                                   'Linea Credito')
                                   AS Linea_Credito,
                                PKG_CONSULTA_ONBASE.f_estado_onbase (
                                   itD.ItemNum,
                                   'Nombre')
                                   AS Nombre,
                                PKG_CONSULTA_ONBASE.f_estado_onbase (
                                   itD.ItemNum,
                                   'Numero Documento')
                                   AS Numero_Documento,
                                PKG_CONSULTA_ONBASE.f_estado_onbase (
                                   itD.ItemNum,
                                   'Periodo')
                                   AS Periodo,
                                PKG_CONSULTA_ONBASE.f_estado_onbase (
                                   itD.ItemNum,
                                   'Resumen')
                                   AS Resumen,
                                PKG_CONSULTA_ONBASE.f_estado_onbase (
                                   itD.ItemNum,
                                   'Tipo')
                                   AS Tipo,
                                PKG_CONSULTA_ONBASE.f_estado_onbase (
                                   itD.ItemNum,
                                   'Codigo Intervencion')
                                   AS Codigo_Intervencion
                           FROM hsi.DocType@OnBase dcT,
                                hsi.ItemTypeGroup@OnBase itG,
                                hsi.ItemData@OnBase itD
                          WHERE dcT.ItemTypeGroupNum = itG.ItemTypeGroupNum
                                AND itD.ItemTypeNum = dcT.ItemTypeNum
                                AND itD.STATUS = 0
                                AND itD.ItemNum IN
                                         (SELECT kiINT.ItemNum
                                            FROM hsi.KeyItem109@OnBase kiINT
                                           WHERE kiINT.KeyValueChar =
                                                    P_ID_Operacion)) DBLINK,
                        (SELECT TCB.ID AS ID_ETAPA,
                                TCB.DESCRIPCION AS ETAPA,
                                TD.DESCRIPCION AS NOMBRE_TIPO_DOCUMENTO,
                                DOC.ID_DOCUMENTO,
                                DOC.ID_TIPO_DOCUMENTO,
                                DOC.CODIGO_DOCUMENTO,
                                ADJ.ID_ONBASE,                --AS ID_EXTERNO,
                                OP.ID_OPERACION,
                                CP.ID AS ID_PRODUCTO,
                                TCTO.ID_CAT_PAISES AS ID_PAIS,
                                ADJ.FILENAME,
                                ADJ.MIME_TYPE,
                                DBMS_LOB.getlength (CONTENT) "TAMANIO",
                                ADJ.ID_ADJUNTO,
                                DOC.ES_JUSTIFICACION,
                                DOC.COMENTARIO,
                                DOC.FECHA_DOCUMENTO,
                                DOC.FECHA_REGISTRO,
                                TTB.DESCRIPCION AS TAREA,
                                TTB.ID AS ID_TAREA,
                                CL.ID_CLIENTE,
                                TDT.PUEDE_MODIFICAR,
                                TDT.PUEDE_BORRAR,
                                TDT.PUEDE_CONSULTAR,
                                NVL (DOC.NOMBRE_USUARIO_CREA,
                                     DOC.LOGIN_USUARIO_CREA)
                                   NOMBRE_USUARIO_CREA,
                                NVL (DOC.NOMBRE_USUARIO_MODIFICA,
                                     DOC.LOGIN_USUARIO_MODIFICA)
                                   NOMBRE_USUARIO_MODIFICA,
                                DOC.NUM_SERIE_DOCUMENTO
                           FROM DOCUMENTO DOC                            --460
                                LEFT OUTER JOIN ADJUNTO ADJ
                                   ON ADJ.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                                INNER JOIN TCA_DOCUMENTO TD
                                   ON DOC.ID_TIPO_DOCUMENTO = TD.ID
                                INNER JOIN TCO_CONFIGURAR_TIPO_ONBASE TCTO
                                   ON TD.id = TCTO.ID_TCA_DOCUMENTO
                                INNER JOIN DOCUMENTOS DCTS
                                   ON DCTS.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                                INNER JOIN OPERACION OP
                                   ON OP.ID_OPERACION = DCTS.ID_OPERACION
                                INNER JOIN CLIENTES CL
                                   ON OP.ID_CLIENTE = CL.ID_CLIENTE
                                INNER JOIN CAT_PRODUCTO CP
                                   ON CP.ID = OP.ID_PRODUCTO
                                INNER JOIN CAT_PAISES CPS
                                   ON CPS.ID = TCTO.ID_CAT_PAISES
                                      AND CPS.ID = CL.PAIS
                                INNER JOIN TCA_TIPO_OPERACION TTO
                                   ON CP.ID_TIPO_OPERACION = TTO.ID
                                      AND TCTO.ID_TCA_TIPO_OPERACION = TTO.ID
                                INNER JOIN TCA_TAREA_BPM TTB
                                   ON TTB.ID = DOC.ID_TAREA_BPM
                                INNER JOIN TCA_PROCESO_BPM TCB
                                   ON TCB.ID = TTB.ID_PROCESO_BPM
                                INNER JOIN TCO_DOCUMENTO_TAREA TDT
                                   ON TD.ID = TDT.ID_TCA_DOCUMENTO
                                      AND TDT.ID_TAREA_BPM =
                                            NVL (P_IdTarea, TTB.ID)   --TTB.ID
                          WHERE     DOC.BAN_ESTATUS <> 0 -- Se agrega el estado  activo o incativo, mostrar solo activos
                                AND OP.ID_OPERACION = P_ID_Operacion
                                AND TDT.PUEDE_CONSULTAR = 1
                                AND ES_JUSTIFICACION = 0
                                AND NVL (INSTANCIA_PROCESO, 0) =
                                COALESCE (P_InstanciaProceso,
                                          NVL (INSTANCIA_PROCESO, 0))) DBFENIX
                  WHERE DBLINK.ItemNum = DBFENIX.ID_ONBASE(+)
                        AND NVL (ID_CLIENTE, 0) =
                              COALESCE (P_IdCliente, NVL (ID_CLIENTE, 0))
                        AND NVL (ID_TIPO_DOCUMENTO, 0) =
                              COALESCE (P_TipoDocumento,
                                        NVL (ID_TIPO_DOCUMENTO, 0))
                 ORDER BY ID_ETAPA, NOMBRE_TIPO_DOCUMENTO ASC);
      ELSE
         IF (P_IdCliente IS NOT NULL)
         THEN
            /*
            ###############################################################
            #                                  Consultar Documentos por Cliente           F3                                        #
            #                                                                                                                                         #
            ############################################################### */

            SELECT ID_FLEXCUBE
              INTO id_flexcube
              FROM CLIENTES
             WHERE ID_CLIENTE = P_IdCliente;


            SELECT CAST (
                      COLLECT (T_CON_ONBASE1 (ETAPA,
                                              NOMBRE_TIPO_DOCUMENTO,
                                              ID_DOCUMENTO,
                                              ID_TIPO_DOCUMENTO,
                                              CODIGO_DOCUMENTO,
                                              ID_ONBASE,
                                              ID_OPERACION,
                                              ID_PRODUCTO,
                                              ID_PAIS,
                                              FILENAME,
                                              MIME_TYPE,
                                              TAMANIO,
                                              ID_ADJUNTO,
                                              ES_JUSTIFICACION,
                                              COMENTARIO,
                                              FECHA_DOCUMENTO,
                                              FECHA_REGISTRO,
                                              TAREA,
                                              ID_TAREA,
                                              PUEDE_MODIFICAR,
                                              PUEDE_BORRAR,
                                              ItemTypeGroupName,
                                              ItemTypeGroupNum,
                                              ItemTypeName,
                                              ItemNum,
                                              ItemTypeNum,
                                              ItemName,
                                              Status,
                                              ItemDate,
                                              DateStored,
                                              epdocid,
                                              SecurityKW,
                                              Pais,
                                              Linea_Credito,
                                              Nombre,
                                              Numero_Documento,
                                              Periodo,
                                              Resumen,
                                              Tipo,
                                              Codigo_Intervencion,
                                              NOMBRE_USUARIO_CREA,
                                              NOMBRE_USUARIO_MODIFICA,
                                              NUM_SERIE_DOCUMENTO)) AS T_ONBASE)
              INTO L_T_ONBASE
              FROM (  SELECT DISTINCT
                             CASE
                                WHEN ETAPA IS NULL THEN 'En Transito'
                                ELSE ETAPA
                             END
                                AS ETAPA,
                             NVL (ID_ETAPA, 0) ID_ETAPA,
                             CASE
                                WHEN NOMBRE_TIPO_DOCUMENTO IS NULL
                                THEN
                                   'General'
                                ELSE
                                   NOMBRE_TIPO_DOCUMENTO
                             END
                                AS NOMBRE_TIPO_DOCUMENTO,
                             ID_DOCUMENTO,
                             ID_TIPO_DOCUMENTO,
                             CODIGO_DOCUMENTO,
                             ID_ONBASE,                       --AS ID_EXTERNO,
                             ID_OPERACION,
                             ID_PRODUCTO,
                             ID_PAIS,
                             FILENAME,
                             MIME_TYPE,
                             TAMANIO,
                             ID_ADJUNTO,
                             ES_JUSTIFICACION,
                             COMENTARIO,
                             FECHA_DOCUMENTO,
                             FECHA_REGISTRO,
                             TAREA,
                             ID_TAREA,
                             CASE
                                WHEN PUEDE_MODIFICAR = 1 OR ETAPA IS NULL
                                THEN
                                   'true'
                                ELSE
                                   'false'
                             END
                                AS PUEDE_MODIFICAR,
                             CASE
                                WHEN PUEDE_BORRAR = 1 THEN 'true'
                                ELSE 'false'
                             END
                                AS PUEDE_BORRAR,
                             PUEDE_CONSULTAR,
                             ItemTypeGroupName,
                             ItemTypeGroupNum,
                             ItemTypeName,
                             ItemNum,
                             ItemTypeNum,
                             ItemName,
                             Status,
                             ItemDate ItemDate,
                             DateStored,
                             NULL epdocid,
                             NULL SecurityKW,
                             NULL Pais,
                             NULL Linea_Credito,
                             CASE
                                WHEN etapa IS NOT NULL
                                THEN
                                   NVL (
                                      SUBSTR (FILENAME,
                                              1,
                                              (INSTR (FILENAME, '.') - 1)),
                                      FILENAME)
                                ELSE
                                   RTRIM (ItemName)
                             END
                                Nombre,
                             NULL Numero_Documento,
                             NULL Periodo,
                             NULL Resumen,
                             NULL Tipo,
                             NULL Codigo_Intervencion,
                             NOMBRE_USUARIO_CREA,
                             NOMBRE_USUARIO_MODIFICA,
                             NUM_SERIE_DOCUMENTO
                        FROM (SELECT itG.ItemTypeGroupName,    -- nombre grupo
                                     dcT.ItemTypeGroupNum,
                                     dcT.ItemTypeName,          -- nombre tipo
                                     itD.ItemNum,          --"ID_DOC"-- doc id
                                     itD.ItemTypeNum, --"Tipo_documento_Fénix"-- codigo tipo documento -- llaves
                                     itD.ItemName,         -- Nombre documento
                                     itD.Status,
                                     itD.ItemDate ItemDate,          --"Fecha"
                                     itD.DateStored
                                FROM hsi.DocType@OnBase dcT,
                                     hsi.ItemTypeGroup@OnBase itG,
                                     hsi.ItemData@OnBase itD
                               WHERE dcT.ItemTypeGroupNum =
                                        itG.ItemTypeGroupNum
                                     AND itD.ItemTypeNum = dcT.ItemTypeNum
                                     AND itD.STATUS = 0
                                     AND itD.ItemNum IN
                                              (SELECT kiCHAR.ItemNum
                                                 FROM hsi.KeyItem118@OnBase kiCHAR
                                                WHERE kiCHAR.KeyValueChar =
                                                         id_flexcube)) DBLINK,
                             (SELECT TCB.ID AS ID_ETAPA,
                                     TCB.DESCRIPCION AS ETAPA,
                                     TD.DESCRIPCION AS NOMBRE_TIPO_DOCUMENTO,
                                     DOC.ID_DOCUMENTO,
                                     DOC.ID_TIPO_DOCUMENTO,
                                     DOC.CODIGO_DOCUMENTO,
                                     ADJ.ID_ONBASE,
                                     -NULL ID_OPERACION,
                                     NULL ID_PRODUCTO,
                                     TCTO.ID_CAT_PAISES AS ID_PAIS,
                                     ADJ.FILENAME,
                                     ADJ.MIME_TYPE,
                                     DBMS_LOB.getlength (CONTENT) "TAMANIO",
                                     ADJ.ID_ADJUNTO,
                                     DOC.ES_JUSTIFICACION,
                                     DOC.COMENTARIO,
                                     DOC.FECHA_DOCUMENTO,
                                     DOC.FECHA_REGISTRO,
                                     TTB.DESCRIPCION AS TAREA,
                                     TTB.ID AS ID_TAREA,
                                     CL.ID_CLIENTE,
                                     TDT.PUEDE_MODIFICAR,
                                     TDT.PUEDE_BORRAR,
                                     TDT.PUEDE_CONSULTAR,
                                     NVL (DOC.NOMBRE_USUARIO_CREA,
                                          DOC.LOGIN_USUARIO_CREA)
                                        NOMBRE_USUARIO_CREA,
                                     NVL (DOC.NOMBRE_USUARIO_MODIFICA,
                                          DOC.LOGIN_USUARIO_MODIFICA)
                                        NOMBRE_USUARIO_MODIFICA,
                                     DOC.NUM_SERIE_DOCUMENTO
                                FROM DOCUMENTO DOC                       --460
                                     LEFT OUTER JOIN ADJUNTO ADJ
                                        ON ADJ.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                                     INNER JOIN TCA_DOCUMENTO TD
                                        ON DOC.ID_TIPO_DOCUMENTO = TD.ID
                                     INNER JOIN TCO_CONFIGURAR_TIPO_ONBASE TCTO
                                        ON TD.id = TCTO.ID_TCA_DOCUMENTO
                                     JOIN DOCUMENTOS_CLIENTE DCTS
                                        ON DCTS.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                                     INNER JOIN CLIENTES CL
                                        ON DCTS.ID_CLIENTE = CL.ID_CLIENTE
                                     INNER JOIN CAT_PAISES CPS
                                        ON CPS.ID = TCTO.ID_CAT_PAISES
                                           AND CPS.ID = CL.PAIS
                                     INNER JOIN TCA_TAREA_BPM TTB
                                        ON TTB.ID = DOC.ID_TAREA_BPM
                                     INNER JOIN TCA_PROCESO_BPM TCB
                                        ON TCB.ID = TTB.ID_PROCESO_BPM
                                     INNER JOIN TCO_DOCUMENTO_TAREA TDT
                                        ON TD.ID = TDT.ID_TCA_DOCUMENTO
                                           AND TDT.ID_TAREA_BPM =
                                                 NVL (P_IdTarea, TTB.ID) --TTB.ID
                               WHERE     DOC.BAN_ESTATUS <> 0 -- Se agrega el estado  activo o incativo, mostrar solo activos
                                     AND CL.ID_CLIENTE = P_IdCliente
                                     AND TDT.PUEDE_CONSULTAR = 1
                                     AND ES_JUSTIFICACION = 0
                                     AND NVL (INSTANCIA_PROCESO, 0) =
                                     COALESCE (P_InstanciaProceso,
                                          NVL (INSTANCIA_PROCESO, 0))) DBFENIX
                       WHERE DBLINK.ItemNum = DBFENIX.ID_ONBASE(+)
                    ORDER BY ID_ETAPA, NOMBRE_TIPO_DOCUMENTO ASC);
         END IF;
      END IF;

      RETURN L_T_ONBASE;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         DBMS_OUTPUT.PUT_LINE (
            'no existe el cliente' || id_flexcube || P_IdCliente);
   END F_Consulta_on_Base;

   FUNCTION F_ESTADO_ONBASE (p_item INTEGER, p_type VARCHAR2)
      RETURN VARCHAR2
   IS
      v_query_str   VARCHAR2 (1000);
      v_resultado   VARCHAR2 (1000);
      v_tabla       VARCHAR2 (50);
      v_columna     VARCHAR2 (50);
   BEGIN
      SELECT TABLA, COLUMNA
        INTO v_tabla, v_columna
        FROM TCA_KEY_ONBASE
       WHERE TRIM (KEYTYPE) = p_type;

      v_query_str :=
            'SELECT'
         || ' '
         || v_columna
         || ' '
         || 'FROM '
         || v_tabla
         || ' WHERE ItemNum = '
         || p_item;

      EXECUTE IMMEDIATE v_query_str INTO v_resultado;

      RETURN v_resultado;
   END F_ESTADO_ONBASE;
END PKG_CONSULTA_ONBASE;
/
