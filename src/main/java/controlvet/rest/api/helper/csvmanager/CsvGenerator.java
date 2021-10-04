package controlvet.rest.api.helper.csvmanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import controlvet.rest.api.helper.StringManager;

import javax.xml.bind.ValidationException;

public class CsvGenerator {

    private static final String BARRA = "/";

    private String dataAtual() {
        return new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
    }

    public String generateCSV(List<?> list, String customText) throws IOException, ValidationException {
        final String reportDir = "csvs";

        String tempDir = System.getProperty("java.io.tmpdir");

        File theDir = new File(tempDir + BARRA + reportDir);
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        final String nomeCsv = BARRA + dataAtual() + ".csv";

        String caminhoArquivo = tempDir + BARRA + reportDir;

        final FileWriter writer = new FileWriter(caminhoArquivo + nomeCsv);
        try {
            if (!StringManager.getInstance().isNullOrEmpty(customText)) {
                writer.append(customText);
                writer.append("\n");
            }
            writer.append(generateHeader(list));

            for (final Object l : list) {

                final Class<? extends Object> cls = l.getClass();

                for (final Field f : cls.getDeclaredFields()) {
                    escreverLinha(writer, l, f);

                }
                writer.append("\n");
            }

            writer.flush();

            return caminhoArquivo + nomeCsv;

        } catch (final Exception e) {
            throw new ValidationException(e);
        } finally {
            writer.close();
        }
    }

    private void escreverLinha(final FileWriter writer, final Object l, final Field f)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        if (f.isAnnotationPresent(CsvInterface.class)) {
            f.setAccessible(true);

            final String propriedade = f.getAnnotation(CsvInterface.class).property();

            final Method method = l.getClass().getMethod("get" + Character.toUpperCase(propriedade.charAt(0)) + propriedade.substring(1));
            Object value = " ";

            if (method != null) {
                value = "" + method.invoke(l);
            }

            if (f.getAnnotation(CsvInterface.class).colClass() == Classes.BIGDECIMAL) {
                value = value.toString().replace(",", "").replace(".", ",");
            } else if (f.getAnnotation(CsvInterface.class).colClass() == Classes.STRING && value != null && !value.equals("null")) {
                value = "\"" + value + "\"";
            } else {
                value = "\"\"";
            }

            writer.append(value.toString());
            writer.append(';');

        }
    }

    private String generateHeader(List<?> list) {

        StringBuilder lsb = new StringBuilder();

        Object l = list.get(0);

        final Class<? extends Object> cls = l.getClass();

        for (final Field f : cls.getDeclaredFields()) {
            if (f.isAnnotationPresent(CsvInterface.class)) {
                f.setAccessible(true);

                if (lsb.toString().length() > 0) {
                    lsb.append(";");
                }

                String header = f.getAnnotation(CsvInterface.class).header();

                lsb.append(header);
            }
        }

        if (lsb.toString().length() > 0) {
            lsb.append(";");
            lsb.append("\n");
        }
        return lsb.toString();
    }

}