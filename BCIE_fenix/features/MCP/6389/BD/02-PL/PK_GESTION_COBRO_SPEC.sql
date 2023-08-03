CREATE OR REPLACE PACKAGE PK_GESTION_COBRO
IS
   PROCEDURE PR_GENERAR_AVISO_COBRO (
      P_FECHAINICIO           IN     DATE,
      P_FECHAFIN              IN     DATE,
      P_ESPUBLICO             IN     VARCHAR2,
      P_TIPOAVISO             IN     VARCHAR2,
      P_LINEA                 IN     VARCHAR2,
      P_CLIENTE               IN     VARCHAR2,
      P_MONEDA                IN     VARCHAR2,
      P_PAIS                  IN     VARCHAR2,
      P_SECTORINSTITUCIONAL   IN     VARCHAR2,
      P_PERIODICIDAD          IN     VARCHAR2,
      P_TIPOSALDO             IN     VARCHAR2,
      P_FONDOS                IN     VARCHAR2,
      P_CODIGOOPERACION       IN     VARCHAR2:= NULL,
      P_USUARIOCREADOR        IN     VARCHAR2,
      P_IDAVISO                  OUT NUMBER,
      P_MENSAJEERROR             OUT VARCHAR2);

   PROCEDURE PR_OBTENER_AVISO_COBRO (P_IDAVISO   IN     NUMBER,
                                     XML_AVISO      OUT XMLTYPE);
END PK_GESTION_COBRO;
/
