package it.units.inginf.male.outputs.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/**
 * DoubleTypeAdapter is a GSON adapter; this maps the NaN double value to a string and viceversa.
 * @author nvhuy9527
 */
public class DoubleTypeAdapter extends TypeAdapter<Double> {

    @Override
    public void write(JsonWriter out, Double value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        if (Double.isNaN(value)) {
            out.value("NaN");
            return;
        }
        out.value(value);
    }

    @Override
    public Double read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        Double nextDouble;
        try {
            //nextDouble parses as a double or fallback to parsing a string using Java parseDouble which MANAGES NaN
            nextDouble = in.nextDouble();
        } catch (NumberFormatException ex) {
            nextDouble = Double.NaN;
        }
        return nextDouble;
    }

}
