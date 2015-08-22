package co.edu.uniandes.ecos.statusquo.operador.web.utils;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Clase HelperMethods.java
 *
 * @author DEV
 */
public class HelperMethods {

    /**
     * Encuentra un ManagedBean por su nombre
     *
     * @param <T> Tipo de retorno
     * @param beanName nombre del ManagedBean a buscar
     * @return la instancia del ManagedBean buscado o null si esta no existe
     */
    public static <T> T findManagedBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }

    /**
     * Encuentra un UIComponent por su id dentro de un UIComponent raiz o si el
     * id corresponde a la raiz, retorna el mismo componente.
     *
     * @param c Componente UIComponent
     * @param id id del componente buscado
     * @return
     */
    public static UIComponent findComponent(UIComponent c, String id) {
        if (id.equals(c.getId())) {
            return c;
        }
        Iterator<UIComponent> kids = c.getFacetsAndChildren();

        while (kids.hasNext()) {
            UIComponent found = findComponent(kids.next(), id);
            return found;
        }
        return null;
    }

    /**
     * Muestra un growl de severidad INFO con el título y mensaje suministrados.
     *
     * @param titulo Título del mensaje
     * @param msj Contenido del mensaje
     */
    public static void showMessageGrowl(String titulo, String msj) {
        showMessageGrowl(titulo, msj, FacesMessage.SEVERITY_INFO);
    }

    /**
     * Muestra un growl con el título, mensaje y severidad suministrados.
     *
     * @param titulo Título del mensaje
     * @param msj Contenido del mensaje
     * @param severity Severidad.
     */
    public static void showMessageGrowl(String titulo, String msj, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, titulo, msj));
    }

//assert: startDate must be before endDate
    /**
     * Calcula la cantidad de días entre 2 instancias de Calendar
     *
     * @param startDate Fecha inicial
     * @param endDate Fecha final
     * @return número de días
     */
    public static long daysBetween(final Calendar startDate, final Calendar endDate) {
        Calendar sDate = (Calendar) startDate.clone();
        long daysBetween = 0;

        int y1 = sDate.get(Calendar.YEAR);
        int y2 = endDate.get(Calendar.YEAR);
        int m1 = sDate.get(Calendar.MONTH);
        int m2 = endDate.get(Calendar.MONTH);
        int d1 = sDate.get(Calendar.DAY_OF_MONTH);
        int d2 = endDate.get(Calendar.DAY_OF_MONTH);

        if (y1 == y2 && m1 == m2 && d1 == d2) {
            return 0;
        }

        //**year optimization**
        while (((y2 - y1) * 12 + (m2 - m1)) > 12) {

            //move to Jan 01
            if (sDate.get(Calendar.MONTH) == Calendar.JANUARY
                    && sDate.get(Calendar.DAY_OF_MONTH) == sDate.getActualMinimum(Calendar.DAY_OF_MONTH)) {

                daysBetween += sDate.getActualMaximum(Calendar.DAY_OF_YEAR);
                sDate.add(Calendar.YEAR, 1);
            } else {
                int diff = 1 + sDate.getActualMaximum(Calendar.DAY_OF_YEAR) - sDate.get(Calendar.DAY_OF_YEAR);
                sDate.add(Calendar.DAY_OF_YEAR, diff);
                daysBetween += diff;
            }
            y1 = sDate.get(Calendar.YEAR);
        }

        //** optimize for month **
        //while the difference is more than a month, add a month to start month
        while ((m2 - m1) % 12 > 1) {
            daysBetween += sDate.getActualMaximum(Calendar.DAY_OF_MONTH);
            sDate.add(Calendar.MONTH, 1);
            m1 = sDate.get(Calendar.MONTH);
        }

        // process remainder date
        while (sDate.before(endDate)) {
            sDate.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }

        return daysBetween;
    }

    /**
     * Obtiene el caracter separador decimal del locale actual del entorno JAVA
     * @return el caracter separador decimal del Locale actual.
     */
    public static char getDecimalSeparator() {
        java.text.DecimalFormatSymbols dfs = new java.text.DecimalFormatSymbols();
        return dfs.getDecimalSeparator();
    }
}
