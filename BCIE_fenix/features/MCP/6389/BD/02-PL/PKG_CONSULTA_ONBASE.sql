DROP PACKAGE BODY PKG_CONSULTA_ONBASE;
DROP TYPE T_ONBASE;
DROP  TYPE T_CON_ONBASE1 FORCE;


CREATE OR REPLACE TYPE T_CON_ONBASE1 as object (
    ETAPA VARCHAR2 (100),
NOMBRE_TIPO_DOCUMENTO VARCHAR2(255),
      ID_DOCUMENTO NUMBER ,
       ID_TIPO_DOCUMENTO NUMBER,
       CODIGO_DOCUMENTO VARCHAR2(55),
       ID_ONBASE VARCHAR2 (250),                                             --AS ID_EXTERNO,
       ID_OPERACION NUMBER,
       ID_PRODUCTO NUMBER,
       ID_PAIS NUMBER,
       FILENAME VARCHAR2(120),
       MIME_TYPE VARCHAR2(80),
       TAMANIO NUMBER,
       ID_ADJUNTO NUMBER ,
       ES_JUSTIFICACION NUMBER,
       COMENTARIO VARCHAR2 (1024),
       FECHA_DOCUMENTO DATE,
       FECHA_REGISTRO DATE,
       TAREA VARCHAR2(256),
       ID_TAREA NUMBER ,
       PUEDE_MODIFICAR VARCHAR2(10),
                       PUEDE_BORRAR VARCHAR2(10),
      ItemTypeGroupName     CHAR (250),
      ItemTypeGroupNum      INTEGER,
      ItemTypeName          CHAR (250),
      ItemNum               INTEGER,
      ItemTypeNum           INTEGER,
      ItemName              CHAR (300),
      Status                INTEGER,
      ItemDate              DATE,
      DateStored            DATE,
      epdocid               INTEGER,
      SecurityKW            CHAR (250),
      Pais                  CHAR (250),
      Linea_Credito         CHAR (250),
      Nombre                CHAR (300),
      Numero_Documento      CHAR (250),
      Periodo               DATE,
      Resumen               CHAR (250),
      Tipo                  CHAR (250),
      Codigo_Intervencion   CHAR (250),
       NOMBRE_USUARIO_CREA VARCHAR2(128),
               NOMBRE_USUARIO_MODIFICA varchar2(128),
                             IDFLUJONEGOCIO NUMBER
       );
/
CREATE OR REPLACE TYPE T_ONBASE AS TABLE  OF T_CON_ONBASE1;



/

CREATE OR REPLACE PACKAGE PKG_CONSULTA_ONBASE
IS
   


FUNCTION F_Consulta_on_Base (  P_ID_Operacion   IN     CHAR, P_IdCliente IN NUMBER, P_TipoDocumento IN NUMBER,  P_IdTarea IN   NUMBER, P_InstanciaProceso   IN     CHAR)
RETURN T_ONBASE;

   FUNCTION F_ESTADO_ONBASE (p_item INTEGER, p_type VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION F_ETAPA_ONBASE (p_id NUMBER)
      RETURN VARCHAR2;
END PKG_CONSULTA_ONBASE;
/
