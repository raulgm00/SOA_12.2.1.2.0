CREATE OR REPLACE FUNCTION BCIE.modificarfecha(pdFecha IN Date, pnPlazo IN Number, pvTipo_Plazo in Varchar2)  RETURN Date IS
vdFecha DATE;
BEGIN
   vdFecha := pdFecha;   
   if    pvTipo_Plazo ='D' then
         vdFecha := pdFecha + pnPlazo;
   elsif pvTipo_Plazo='M' then
         vdFecha := Add_Months(pdFecha, pnPlazo);
   elsif pvTipo_Plazo in ('A', 'Y') then
         vdFecha := Add_Months(pdFecha, pnPlazo * 12);
   elsif pvTipo_Plazo = 'P' then
         vdFecha := to_Date(Extract(Year From pdFecha)-pnPlazo || '1231','yyyymmdd');
   end if;
   RETURN vdFecha;
END modificarfecha;
/
